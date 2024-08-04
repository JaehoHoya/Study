package thread;
//7/16 스레드
public class threadMain {
    public  static void main(String[] args){
        /*
        *  Thread : 가상의 CPU(VCPU) ,코드를 실행하는 것
        *  MultiTasking:
        *  chet : 이용자가  메세지를 네트워크로 출력 , 다른 컴에서 메세지를 입력하는 것 (동시에)
        *  쓰레드를 쓰지 않으면 동시에 메소드를 돌릴수 없음
        *  쓰레드는 VCPU이므로 지정된 코드를 할당하여 실핼할 수 있다
        *  자신만의 Stack을 가지고 지역변수를 생성한다
        *  쓰레드가 실행할 수 있는 코드는 Runnable 인터페이스 구현체이어야한다 (class)
        *  쓰레드에게 전달될 코드는 Runnable이어야 한다
        *  인터페이스 : 메소드 바디가 없고 이름만 있는거
        */


        //test01();
        //test02();
        // 한 객체가 쓰레드 2개에 공유되는 상황  결과: 일관성이 없음
        Counter cnt = new Counter();
        Producer pt=new Producer(cnt);
        pt.start();
        Consumer ct=new Consumer(cnt);
        ct.start();

        Thread mT =Thread.currentThread();
        String tname =mT.getName();
        System.out.println(tname+"프로그램 종료");  // 메인 쓰레드
        // 메인 메소드도 메인 쓰레드에 의해 돌아간다
    }
    private  static  void test01()
    {
        Thread t1= new Thread(new runnableImpl01(), "수 올리기 쓰레드");
        t1.start();
        // new Thread : cpu 생성
        // new runnableImpl01 코드 생성 ,테스크
        Thread t2= new Thread(new runnableImpl02(),"날짜 쓰레드");
        t2.start();

        // cpu 시작하는데는 시간이 별로 안걸림 그니깐 동시에
    }
    private  static  void test02(){

        // 쓰레드는 implements  Run 이미 가지고 있음
        NumThread nt = new NumThread("숫자 쓰레드");
        nt.start();
        DateThread dt=new DateThread("날짜 쓰레드");
        dt.start();
    }

    private  static  void test03(){


    }
}

// 0719 쓰레드  더 수업