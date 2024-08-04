package thread;

public class Producer extends  Thread{
    private  Counter counter;

    public Producer(Counter counter){

        //Runnable Pool이란      t1.start() ~t5.start() 쓰레드들이  대기하는 공간(관리 됨)   쓰레드 스케줄러가 이중 하나를 Run 상태로 돌림 쓰레드는 동시에 시작되는것 같지만  사실은 switch 되는 것.


        super("생산자");
        this.counter=counter;
        this.setPriority(MAX_PRIORITY);  // 우선권  쓰레드가 여러개 있을때  유용
        //객체 한개의 주소를 2개의 쓰래드에서 공유하는 상황
    }
    @Override
    public void run() {

        while (true){

            counter.increase();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
