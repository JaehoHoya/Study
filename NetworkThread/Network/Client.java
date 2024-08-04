package Network;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    static Scanner kbd= new Scanner(System.in);

    public static void main(String[] args){
        // 클라이언트 소캣을 이용하여 서버에 접속한다

        try {
            Socket s = new Socket("localhost",1234); // 접속요청

            // 회원 가입 메세지 가져요기
            InputStream in = s.getInputStream();
            /*
            InputStreamReader isr= new InputStreamReader(in);
            BufferedReader br= new BufferedReader(isr);
            String line = br.readLine();
             */
            ObjectInputStream oin= new ObjectInputStream(in);


            ChatMsg data = (ChatMsg) oin.readObject();

            System.out.println(data.msg); // 아이디 암호입력 하세요!


            // 아이디 입력 받기
            String uid= kbd.next();
            String pwd= kbd.nextLine().trim();

            // 출력 스트림
            /*
            OutputStream out= s.getOutputStream();
            OutputStreamWriter osw= new OutputStreamWriter(out); //변환 스트림
            PrintWriter pout=new PrintWriter(osw);
            pout.print(uid+":"+pwd);
            pout.flush();
            */
                //로그인 쓰레드에 아이디 비번 보내기
                ChatMsg data2 = new ChatMsg(true, uid, pwd);

                OutputStream out = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(out);
                oos.writeObject(data2);
                oos.flush();

                // 로그인 쓰레드에서 받을때


                ChatMsg data3 = (ChatMsg) oin.readObject();

                System.out.println(data3.msg); // 로그인 성공 여부


                // 채팅하기
            if(data3.msg.equals("로그인 성공")) {

                System.out.println("------다중 채팅 서버--------");

                new ClientNetInputThread(oin).start(); // 여러 사람 메세지 받을때

                System.out.print("귓속말(s) ,공개(p) 종료(x) \n\n");
                while (true) {

                    // 귓속말(s) ,공개(p):
                    // 수신자 아이디 받고 보낼 메세지  msg.to = ㅁㅁㅁ msh.msg == rrr
                    // msg.isSecreate =true;

                    String menu = kbd.nextLine().trim();  // 다른데엇 사용 못함
                    if(menu.equalsIgnoreCase("x")){ // 대소문자 상관없이

                        System.out.println("채팅을 종료합니다.");
                        break;
                    }

                    else if(menu.equalsIgnoreCase("s")){
                        System.out.print("수신자:");
                        String toId= kbd.nextLine();
                        System.out.print("메세지:");
                        String toMsg = kbd.nextLine();
                        System.out.print("파일 첨부(파일명):");
                        String fname =kbd.nextLine();

                        ChatMsg msg = new ChatMsg();
                        if(fname!=null&& !fname.equals("")){
                            byte[] fdata= new FileIO().load(fname);
                            if(fdata!=null){
                                msg.fname=fname;
                                msg.fdata=fdata;
                            }else {
                                System.out.println("파일을 첨부하지 않아 메세지만 보냅니다");
                            }
                        }

                        msg.uid= uid;
                        msg.isSecret=true;
                        msg.to=toId;
                        msg.msg=toMsg;

                        oos.writeObject(msg);
                        oos.flush();

                    }
                    else if (menu.equalsIgnoreCase("p")) {

                            System.out.print("메세지:");
                            String me = kbd.nextLine().trim();
                            ChatMsg data4 = new ChatMsg(uid, me);
                            /* 순차적 실행 : 메세지를 보내야 받는다? 안보내도 받고 싶은면 어떻게 해야하는가  --> 병행처리 --> 쓰레드  */
                            oos.writeObject(data4);
                            oos.flush();



                    } else if(menu.equals("y")){
                        String fname= ClientNetInputThread.chatMsg.fname;
                        byte[] fdata= ClientNetInputThread.chatMsg.fdata;
                        boolean saved= new FileIO().dowmload(fname,fdata);

                        if(saved) System.out.println("다운로드 성공");
                        else System.err.println("다운로드 실패");



                    }

                    /*
                    ChatMsg other = (ChatMsg) oin.readObject();
                    System.out.println(other.msg);
                    이거를  쓰레드로
                    */
                    // 입력과 출력이 간섭 하지 않아야한다

                }

            }






        }catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("클라이언트 종료");
    }



}
