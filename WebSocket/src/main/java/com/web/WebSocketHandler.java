package com.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebSocketHandler extends TextWebSocketHandler
{   //리스트 보단 맵
    //private final List<WebSocketSession> webSocketSessions = new ArrayList<>();
    private static Map<String, WebSocketSession> userMap = new HashMap<>();

    @Override  /* 클라이언트 접속시에 호출됨 */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception //session이 한 이용자 역할을 함
    {   //클라이언트가 접속하자마자 돌아감
        /* 인터셉터에서 전달된 userid 를 추출하여 사용하는 예 */
        String userid = (String)session.getAttributes().get("userid"); // Map<String ,object >   dbject 형이 나오기에 (String)으로
        log.info("웹소켓핸들러, userid={}", userid);

        userMap.put(userid, session); //이용자를 모아야만 메세지가 이용자마다 전달됨
        log.info("Client Connected");
    }

    @Override  /* 서버에 메시지 도착시 호출됨 */
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("서버에서 받은 메시지:{}", message.getPayload()); // getPayload: 문자열로 ,WebSocketSession session 메세지를 보낸사람

        // JSON 문자열을 파싱하여 JSONObject로 변환



        //채팅서비스에 접속된 모든 클라이언트에게 브로드캐스팅
        Collection<WebSocketSession> coll = userMap.values(); // 세션의 집합
        for(WebSocketSession ss : coll) {
            ss.sendMessage(message); //브로드캐스팅
        }

		/* JSON 포맷으로 통신할 때는 아래처럼... 복잡한 메세지일 경우
		JSONParser parser = new JSONParser();
		JSONObject jsObj = (JSONObject) parser.parse( message.getPayload()); json 문자열이 jsonObject로
		String receiver = (String)jsObj.get("receiver");

		WebSocketSession wss = userMap.get(receiver);
		wss.sendMessage(message);  // 특정 접속자에게만 메시지를 전달함
		*/
    }







    @Override   /* 접속 해제시 호출됨 */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Connection Closed");
        for(Entry<String, WebSocketSession> entry : userMap.entrySet())
        {
            if(entry.getValue()==session)
            {
                String userid = entry.getKey();
                userMap.remove(userid);
                log.info("퇴장:{}", userid);
                break;
            }
        }
    }

    @Override   /* 오류 발생시 호출됨 */
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Error:" + exception);
        super.handleTransportError(session, exception);
    }
}
