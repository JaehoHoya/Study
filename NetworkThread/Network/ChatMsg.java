package Network;

import java.io.Serializable;
// 직렬화 가능

public class ChatMsg implements Serializable
{
    String uid;
    String pwd;
    String from;
    String to;    // 받는 사람의 아이디
    String msg;
    boolean login;

    byte[] fdata;
    String fname;

    boolean isSecret;   // 비밀 채팅

    public  ChatMsg(String from ,String to,String msg){

        this.from = from;
        this.to =to;
        this.msg=msg;

    }

    public  ChatMsg(String uid,String msg){
        this.msg=msg;
        this.uid=uid;

    }

    public  ChatMsg(String uid,String toId,String toMsg ,boolean isSecret){

        this.uid=uid;
        this.to=toId;
        this.msg=toMsg;
        this.isSecret=isSecret;

    }


    public  ChatMsg(){


    }

    public  ChatMsg(String msg){
        this.msg=msg;
    }


    public  ChatMsg(boolean login, String uid,String pwd){
        this.login=login;
        this.uid=uid;
        this.pwd=pwd;
    }
}
