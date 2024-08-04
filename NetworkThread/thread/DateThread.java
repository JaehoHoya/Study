package thread;

import java.util.Date;

public class DateThread extends  Thread{

    public DateThread(String name){
        super(name);
    }
    @Override
    public void run() {
        String n =Thread.currentThread().getName();
        Date date =new Date();
        for(int i=0;i<10;i++) {
            System.out.println(n+":"+date);
        }
        try {
            DateThread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
