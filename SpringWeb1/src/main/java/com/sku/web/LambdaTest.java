package com.sku.web;


interface MyInterface
{
   void printAdd(int a, int b);
}

class MyInterImpl implements MyInterface  // 인터페이스를 구현한 완전한(이름을 가진) 클래스
{
   @Override
   public void printAdd(int a, int b) {
      int ans = a + b;
      System.out.println("합계:" + ans);
   }
}

public class LambdaTest 
{
   public static void main(String[] args) 
   {/*
      MyInterface mi = (a,b)->{
         int ans = a + b;
         System.out.println("합계:" + ans);
      };*/
      
      lambdaParam((a,b)->{
         int ans = a + b;
         System.out.println("합계:" + ans);
      });
   }
   
   static void lambdaParam(MyInterface mi)
   {
      mi.printAdd(5, 8);
   }
   
   static void anonymousCls() {
      // 익명클래스 선언/오버라이드/인스턴스 생성
      MyInterImpl mi = new MyInterImpl() 
      {
         @Override
         public void printAdd(int a, int b) {
            int ans = a + b;
            System.out.println("합계:" + ans);
         }
      };
      mi.printAdd(3, 5);
   }
   
   static void concreteClass() {
      MyInterImpl mi = new MyInterImpl();
      mi.printAdd(3, 5);
   }
   
   
   
   static void anonymousClass() {
      // 이름 없는 클래스 선언/오버라이드/인스턴스생성
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("익명 클래스를 사용한 스레드가 실행 중입니다.");
                for (int i = 0; i < 5; i++) {
                    System.out.println("i = " + i);
                    try {
                        Thread.sleep(400); // 400밀리초 동안 스레드 일시 중지
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();
   }

   static void lambdaExp() {
        // 익명 클래스를 더 간략화한 람다(람다 표현식, Lambda Expression)
      // 클래스 선언 / 메소드 오버라이드 / 인스턴스 생성
      // 람다표현식을 적용할 수 있는 경우는 클래스 안에 메소드가 한개만 있는 경우
        Runnable runnable = () -> {
            System.out.println("람다 표현식을 사용한 스레드가 실행 중입니다.");
            for (int i = 0; i < 5; i++) {
                System.out.println("i = " + i);
                try {
                    Thread.sleep(400); // 400밀리초 동안 스레드 일시 중지
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
   }
}
/* Functional Interface(함수형 인터페이스)
 * 람다 표현식으로 인스턴스를 생성할 수 있다
 * 인터페이스이고 그 안에 메소드가 1개인 경우
 */



// 이름을 가진 클래스 선언
class RunnableImpl implements Runnable
{
   @Override
    public void run() {
        System.out.println("익명 클래스를 사용한 스레드가 실행 중입니다.");
        for (int i = 0; i < 5; i++) {
            System.out.println("i = " + i);
            try {
                Thread.sleep(400); // 400밀리초 동안 스레드 일시 중지
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
