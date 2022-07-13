package com.socketio_server_example.server.config;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.socketio_server_example.server.objects.ChatObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebSocketNameSpace {

    @Autowired
    public WebSocketNameSpace(SocketIOServer server) {
        //請注意假如有生成nameSpace 不同nameSpace將會把頻道間隔開, 也注意配置Listener 不要使用原本的server 去加會導致發送不到
        //ex: SocketIOServer server;
        //server.addEventListener("chatevent", ChatObject.class, new DataListener<ChatObject>()
        //nameSpace 與原本的SocketIOServer 是切開的。


        //新增一個nameSpace 為/chat1
        SocketIONamespace socketIONamespace = server.addNamespace("/chat1");
        socketIONamespace.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println(socketIOClient + "連線成功");
            }
        });
        socketIONamespace.addEventListener("chatevent", ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) {
                System.out.println("傳訊息");

                //這條將會送回一個帶有chatevent 的訊息給前端
                server.getBroadcastOperations().sendEvent("chatevent", data);
            }
        });
        server.start();
    }
}
