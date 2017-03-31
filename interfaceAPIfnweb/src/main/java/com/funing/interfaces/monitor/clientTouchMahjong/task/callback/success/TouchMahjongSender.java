package com.funing.interfaces.monitor.clientTouchMahjong.task.callback.success;

import com.funing.commonfn.model.User;
import com.funing.commonfn.model.mahjong.ClientTouchMahjong;
import com.funing.commonfn.util.CommonError;
import com.funing.commonfn.util.JsonResultY;
import com.funing.commonfn.util.PidValue;
import com.funing.interfaces.websocket.MessageManager;

import java.util.List;

/**
 * 发送消息发摸到牌的客户端
 */
public class TouchMahjongSender implements Runnable {

    private List<User> users;
    private List<ClientTouchMahjong> clientTouchMahjongs;
    private MessageManager messageManager;

    @Override
    public void run() {
        for (int i = 0; i < users.size(); i++) {
            JsonResultY jsonResultY = new JsonResultY.Builder()
                    .setPid(PidValue.CLIENT_TOUCH_MAHJONG.getPid())
                    .setError(CommonError.SYS_SUSSES)
                    .setData(clientTouchMahjongs.get(i))
                    .build();
            messageManager.sendMessageByUserId(users.get(i).getId(), jsonResultY);
        }
    }

    public static class Builder {

        private TouchMahjongSender sender;

        public Builder() {
            sender = new TouchMahjongSender();
        }

        public Builder setUsers(List<User> users) {
            sender.users = users;
            return this;
        }

        public Builder setClientTouchMahjongs(List<ClientTouchMahjong> clientTouchMahjongs) {
            sender.clientTouchMahjongs = clientTouchMahjongs;
            return this;
        }

        public Builder setMessageManager(MessageManager messageManager) {
            sender.messageManager = messageManager;
            return this;
        }

        public TouchMahjongSender build() {
            return sender;
        }
    }
}
