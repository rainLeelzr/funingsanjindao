package com.funing.interfaces.websocket;

import com.funing.commonfn.model.User;
import com.funing.commonfn.util.CommonError;
import com.funing.commonfn.util.JsonResultY;
import com.funing.interfaces.websocket.Mapping.ActionRouter;
import com.funing.interfaces.websocket.Mapping.LoginResource;
import com.funing.interfaces.websocket.Mapping.RouterHelper;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.lang.reflect.Method;


@Component
public class SystemWebSocketHandler implements WebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(SystemWebSocketHandler.class);

    @Autowired
    private ActionRouter actionRouter;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private MessageManager messageManager;

    public SystemWebSocketHandler() {
        super();
    }

    // 连接关闭
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus closeStatus) throws Exception {
        log.debug("websocket[sessionId={}] cause[{}] connection closed......",
                session.getId(),
                closeStatus.toString());
        sessionManager.connectionClosed(session);
    }


    // 连接上
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        log.debug("[sessionId={}] connect to the websocket success......", session.getId());
        sessionManager.addSession(session);
        // 处理离线消息

    }

    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("received client  message:{}", message.getPayload());
        String clientMessage = (String) message.getPayload();

        JsonResultY jsonResultY = null;
        int pid = -1;
        try {
            System.out.print("客户端请求参数 ： "+clientMessage);
            // 请求参数解析
            JSONObject jsonObject = JSONObject.fromObject(clientMessage);
            System.out.print(jsonObject.get("data"));
            JSONObject data = JSONObject.fromObject(jsonObject.get("data"));
            pid = jsonObject.getInt("pid");

            //解析请求，根据pid寻找对应的执行方法
            Method m = RouterHelper.from(pid);

            // 假设这里执行的业务方法是登录
            // pid = 1000
            // 执行的方法  ----> ActionRouter.login()


            //用户登录检查，如果是需要登录才能访问的资源，但是客户端未登录，则不允许继续访问业务逻辑
            checkLogin(m, session);

            //通过了登录检查，继续执行逻辑
            //可变参数
            Object result = m.invoke(actionRouter, session, data);


            if (result != null) {
                //方法的返回结果是否为JsonResulty实例
                if (result instanceof JsonResultY) {
                    jsonResultY = (JsonResultY) result;
                } else {
                    throw new RuntimeException(String.format("%s方法返回值不支持！请修改！", m.getName()));
                }
            }
        } catch (Exception e) {
            //判断此次异常是否为自定义异常
            CommonError error = parse(e);
            if (error != null) {
                //若是不为自定义异常，则将错误信息进行封装
                jsonResultY = new JsonResultY.Builder()
                        .setPid(pid)
                        .setError(error)
                        .build();
            } else {
                //若是为系统抛出的运行异常则将打印日志，并封装提示为系统异常
                log.error(e.getMessage(), e);
                jsonResultY = new JsonResultY.Builder()
                        .setPid(pid)
                        .setCode(CommonError.SYS_ERR.getCode())
                        .setData(String.format("系统异常--%s)",
                                e.getCause() == null ? e.getMessage() : e.getCause().getMessage()))
                        .build();
            }
        }

        // 响应客户端
        if (jsonResultY != null) {
            messageManager.send(session, jsonResultY);
        }
    }

    /**
     * 用户登录检查
     * 思路：判断method有没有LoginResource注解
     * 如果没有LoginResource注解，则放行，执行具体的方法
     * 如果有LoginResource注解，则判断webSocketSession的id有没有对应的user
     * 如果有对应的user，则证明已登录。如果没有user，则返回未登录的信息
     */
    private void checkLogin(Method method, WebSocketSession session) {
        if (method.isAnnotationPresent(LoginResource.class)) {
            User user = sessionManager.getUser(session.getId());
            if (user == null) {
                throw CommonError.USER_UnLogin.newException();
            }
        }
    }

    /**
     * 解析异常是否为自定义的异常
     */
    private CommonError parse(Throwable e) {
        //在这个自定义的枚举类中，exceptions为所有异常的集合
        //这个类在初始化的时候将所有自定义异常类的简写名称存放在了枚举常量中，并将这些常量放到了一个map集合中
        //map的key值就是对应的异常类的简写名称，我们在捕获到异常的时候获取到该异常的字节码对象并获取到简写名称去获取map集合
        CommonError error = CommonError.exceptions.get(
                e.getClass().getSimpleName());
        //若是不为自定义异常并且存在错误的起因信息
        if (error == null && e.getCause() != null) {
            error = CommonError.exceptions.get(
                    e.getCause().getClass().getSimpleName());
        }
        return error;
    }

    public void handleTransportError(WebSocketSession session,
                                     Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        log.debug("websocket [sessionId={}] connection closed......", session.getId());
        sessionManager.connectionClosed(session);
    }

    public boolean supportsPartialMessages() {
        return false;
    }

}
