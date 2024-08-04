package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.Scanner;

public class ClientNetInputThread extends  Thread{

    private  ObjectInputStream oin;

    static  ChatMsg chatMsg;

    public ClientNetInputThread(ObjectInputStream oin) {

        this.oin=oin;
    }

    @Override
    public void run() {

        try {
            while (true) {

                ChatMsg other = (ChatMsg)oin.readObject();




                if(other.fdata!=null && other.fdata.length>0){
                    chatMsg =other;
                    System.out.println("["+other.uid+"]"+other.msg+"(비밀채팅)\n첨부파일("+other.fname+")다운로드Y/N:");

                    //이미 키보드를 받는 쓰레드가 존재하기에 쓸수 없음
                }else {
                    System.out.println("["+other.uid+"]"+other.msg);
                }
            }

        }catch (StreamCorruptedException e){
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
