package com.funing.interfaces.websocket.Mapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个类在初始化的时候会将所有业务方法初始化 并存储到一个map集合
 */
public class RouterHelper {

    private static Map<String, Method> methodMap;

    static {
        init();
    }

    /**
     * 在所有业务方法中 根据pid值找到对应的方法
     * @param pid
     * @return  返回对应的可执行业务方法
     */
    public static Method from(int pid) {
        Method m = methodMap.get(pid + "");
        if (m == null) {
            throw new RuntimeException(String.format("未实现pid为[%s]的方法!", pid));
        }
        return m;
    }

    private static void init() {
        methodMap = new HashMap<String, Method>(20);
        //根据反射获取ActionRouter 请求控制器中的所有方法
        Method[] methods = ActionRouter.class.getDeclaredMethods();

        for (Method method : methods) {
            //判断这些方法上是否存在pid标签
            if (method.isAnnotationPresent(Pid.class)) {
                //获取这些方法上的枚举
                Pid pid = method.getAnnotation(Pid.class);
                //将方法标签上的pid值 值作为方法存储在map集合中的key值，一个websocket 请求进来 根据参数中的pid去取到
                //所有可执行的业务方法
                methodMap.put(pid.value().getPid() + "", method);

            }
        }
    }


}
