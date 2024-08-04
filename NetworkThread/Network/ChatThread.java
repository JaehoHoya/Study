package Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ChatThread extends  Thread{

    private String uid;
    private ObjectInputStream oin;
    private ObjectOutputStream oos;
    private Socket s;   // 다중 채팅을 위해 소캣 까지 전송 됨

    static Map<String,ObjectOutputStream> user =new HashMap<>();

    public ChatThread(String uid,Socket s,ObjectInputStream oin, ObjectOutputStream oos) {
            this.uid=uid;
            this.oin=oin;
            this.oos=oos;
            this.s=s;
    }

    @Override
    public void run() {


        try{

            while (true) {

                ChatMsg data = (ChatMsg) this.oin.readObject();  // 클라이언트 종료시  여기서 에러   나는 안생기던데 ..



                if(data.isSecret){
                    String receiver= data.to;
                    user.get(receiver).writeObject(data);
                    user.get(receiver).flush();
                    continue;
                }
                //접속한 모든 이용자에게 메세지 전달
                ChatMsg cm = new ChatMsg(data.uid,data.msg);

                Set<String> idSet=ChatThread.user.keySet();
                Iterator <String> idIter = idSet.iterator();

                ObjectOutputStream userOut =null;


                while (idIter.hasNext()){
                //다음 요소가 있는지 확인합니다. 만약 다음 요소가 있으면 true를 반환하고, 없으면 false를 반환합니다.
                    String userid =idIter.next();
                    userOut= user.get(userid);

                    userOut.writeObject((cm));
                    userOut.flush();

                }

                // Key를 다가져옴 Keyset
               // oos.writeObject(cmg);
               // oos.flush();

                /*
                *
                *  if(data.msg.equals("x")){
                    a= false;
                }
                * 나는 여기 쓰레드에서 x를 받으면 여기를 종료 함
                *
                * 반면에 강사님은  클라이언트 가 채팅 종료시 챗 쓰레드는 받을 준비중 but  아무것도 안옴 오류처리 이것을 이용하여 한 사용자가 나갔다는 것으로 이용 (오류 이용)
                *
                * */
            }

        }catch (Exception e){
           InetAddress ia= s.getInetAddress();// 접속한 이용자의 ip 주소를 가져올수 있음
            System.out.println(ia+"/"+uid+"이용자 퇴장");
            // user 맵에서 나간 퇴장한 이용자의 정보를 삭제해야한다.
            user.remove(uid);


        }
        System.out.println("채팅 쓰레드 종료");

    }
}

