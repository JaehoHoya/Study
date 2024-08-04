package thread;

public class Counter
{

    int cnt =5;

    /*
    *  cnt의 값이 5~10 을 유지하도록 쓰레드를 제어
    *  cnt=5 소비를 멈추고 생산이 되어야한다  / 소비 쓰레드는 기다려야한다(wait())
    *  cnt=10 생산을 멈추고 소비가 되어야한다 / 생산 쓰레드는 기다려야한다(wait())
    *
    *  cnt가 5가되면 생산자에게 통지를 전해야 한다(notify(),notifyAll())
    */


    public synchronized void increase()         //synchronized : 락(LOCK) ,동기화  그럼 열쇠는?  Counter class 에 LOCK MONITOR 에 key를 가지고 실행됨  LOCK FLAG
                                                //synchronized 블럭은 하나만 실행됨 ? 순차적 ?

    {
        System.out.println("올리기전:"+cnt);
        cnt++;
        System.out.println("올린 후:"+cnt);
        if(10<=cnt){
            try {
                this.notify();
                this.wait();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }


    }
    public synchronized void decrese()
    {
        System.out.println("내리기전:"+cnt);
        cnt--;
        System.out.println("내린 후:"+cnt);

        if(cnt<=5){
            try {
                 this.notify();
                 this.wait();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
