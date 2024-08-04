package thread;

public class  NumThread extends Thread{
    public NumThread(String name){
        super(name);
    }
    @Override
    public void run() {
        String n= Thread.currentThread().getName();
        for(int i =0;i<10;i++){
            System.out.println(n+":"+i);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
