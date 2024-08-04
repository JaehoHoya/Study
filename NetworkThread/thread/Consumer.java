package thread;
//소비자
public class Consumer extends Thread{

    private  Counter counter;

    public Consumer(Counter counter){
        super("소비자");
        this.counter=counter;
    }

    @Override
    public void run() {

        while (true){

            counter.decrese();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
