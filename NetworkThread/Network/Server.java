package Network;

import java.net.*;
import java.io.*;
import java.util.*;
public class Server {

    public  static void main(String[] args){

        /*
        *  Echo Server
        *  혼자서 무한히 메세지 입력 / 메세지 수신
        *  이용자가 "x"를 입력하여 클라이언트를 종료할 때  채팅 종료
        *  클라이언트 종료
        *  서버측 반응 확인
        *  서버측 대응
        *
        *  파일 보내고 받기
        *  파일명, 파일데이터를 저장할 수 있는 변수
        *  이용자에게 메세지를 보낼때 첨부 a 메뉴를 제시한다
        *  이용자가 파일명을 입력하면 그파일을 로드하여 byte[] 형식으로 ChatMsg 변수에 저장
        *  수신자가 메세지를 확인하면 "첨부파일 다운로드(y/n)" 메뉴를 제시하고 y를 누르면 다운로드
        *  다운로드하면 첨부파일 다운로드 성공 메시지를 표시한다
        */



        // 다중 이용자 채팅 서버
        // 클라이언트가 접속해올 수 있도록 포트번호를 가지고 있어야함

        // 로그인할때 블로킹 ..
        // 로그인을 하든 안하는 서버는 계속 ㄱㄱ -> 로그인 부분을 쓰레드화

        try {
            ServerSocket ss = new ServerSocket(1234);
            while (true) {
                System.out.println("서버 대기중...");
                //24시간 대기용
                Socket s = ss.accept(); // 클라이언트가 접속하면 통신용 소캣을 리턴한다
                // 소켓에는 클라이언트 정보가 담김
                //접속한 이용자와 통신할 때 사용하는 소캣
                System.out.println("클라이언트 접속 ");

                new LoginThread(s).start();


                /* 쓰레드화


               // 서버측에서 클라이언트에게  메세지 전달
                OutputStream out = s.getOutputStream();

               // OutputStreamWriter osw = new OutputStreamWriter(out); //변환 스트림
               // PrintWriter pout = new PrintWriter(osw);
               // pout.println("Id Password 입력하세요:");
               // pout.flush();

               // 직렬화
                ObjectOutputStream oos= new ObjectOutputStream(out);
                ChatMsg cm =new ChatMsg("서버","클라이언트","아이디 암호");
                oos.writeObject(cm);
                oos.flush();



                // 클라이언트로 부터 아이디 패스워드를 받는다 .
                // InputStream in = s.getInputStream();
                // InputStreamReader isr= new InputStreamReader(in);
                // BufferedReader br= new BufferedReader(isr);
                // String line = br.readLine();
                // System.out.println(line);

                // 직렬화
                InputStream in = s.getInputStream(); //이용자로부터 오는 스트림
                ObjectInputStream ois= new ObjectInputStream(in);

                try {
                    ChatMsg data = (ChatMsg) ois.readObject();
                    System.out.println(data.uid+":"+data.pwd);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("서버 종료");


    }
}
