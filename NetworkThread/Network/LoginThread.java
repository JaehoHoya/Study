package Network;

import java.io.*;
import java.net.Socket;

public class LoginThread extends Thread {

    private Socket s ;
    private ObjectInputStream oin;
    private ObjectOutputStream oos;

    public LoginThread(Socket s) {
        this.s=s;
    }

    @Override
    public void run() {

        try {
            OutputStream out = s.getOutputStream();
            this.oos= new ObjectOutputStream(out);
            ChatMsg cm =new ChatMsg("서버","클라이언트","아이디 암호");
            oos.writeObject(cm);
            oos.flush();



            InputStream in = s.getInputStream(); //이용자로부터 오는 스트림
            this.oin= new ObjectInputStream(in);

            try {
                ChatMsg data = (ChatMsg)this.oin.readObject();
                if(data.login){
                    if(data.uid.length()>3&&data.pwd.length()>3) {
                        //???????
                        ChatThread.user.put(data.uid,oos);


                        ChatMsg cm2 =new ChatMsg(data.uid,"로그인 성공");  // 다중 채팅시 로그인 할때마다 컬랙션에 모아야함  id ,out  저장 아이디만 알면 그사람에게 전달
                        oos.writeObject(cm2);
                        oos.flush();

                        new ChatThread(data.uid,this.s,this.oin ,this.oos).start();



                    }else{


                        oos= new ObjectOutputStream(out);
                        ChatMsg cm2 =new ChatMsg(data.uid,"로그인 실패");
                        oos.writeObject(cm2);
                        oos.flush();


                    }

                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("로그인 쓰레드 종료");

    }
}
