package thread;

import java.util.Date;

// 가상의 cpu가 실행할 수 있다
public class runnableImpl02 implements Runnable{
    @Override

    public void run() {  // 쓰레드는 run만 실행 가능  이게 끝나면 사라짐

        Date date= new Date();

        Thread rT=Thread.currentThread();
        String name = rT.getName();

        for(int i=0;i<10;i++){
            System.out.println(name+":"+date);


            try {
                Thread.sleep(1000); //1초동안 쉬는 명령
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 왜 thread에 sleep이 있는가.. 자바는 가상의 cpu가
        }
        System.out.println(name+":"+"Thread Dead");
    }

}


