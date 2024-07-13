package com.test.hello;

import java.util.Random;
import java.util.Scanner;

public class HelloWorld {

	public static void main(String[] args) //매인 메서드 
	{
		// TODO Auto-generated method stub
			System.out.println("hello world");
			
			
		//	incDecTest();  
		//	printGugu5(); 
		//	printGugu7();
	    //  inputDanPrint();
		//  notEquals();
		//	logicalAnd();
			login();
	}
	
	private static void incDecTest() //증가 감소 메소드 
	{
		            // increment , Decrement Operators
		
		int v = 0;
		v++;        // 증가연산자 
		System.out.println(v);   //1
		++v; 
		System.out.println(v);   //2
		
		System.out.println(v++); //2 후증가 v가 사용된 다음에 올라감(=화면 출력후) Post-Increment 
		System.out.println(v);   //3
		
		System.out.println(++v); //4 전증가 Pre-Increment  
		System.out.println(v);   //4
	}
	
	private static void printGugu5() //구구단 5단 표시하기 ,증가연산자 활용
	{
		
		int number=5;
		int i=0;
		
		System.out.println("구구단 5단 출력입니다.(전증가)");
		System.out.printf("%d X%d=%d %n",number,++i,number*i);
		System.out.printf("%d X%d=%d %n",number,++i,number*i);
		System.out.printf("%d X%d=%d %n",number,++i,number*i);
		System.out.printf("%d X%d=%d %n",number,++i,number*i);
		System.out.printf("%d X%d=%d %n",number,++i,number*i);
		System.out.printf("%d X%d=%d %n",number,++i,number*i);
		System.out.printf("%d X%d=%d %n",number,++i,number*i);
		System.out.printf("%d X%d=%d %n",number,++i,number*i);
		System.out.printf("%d X%d=%d %n",number,++i,number*i);
		
		
		
		
		
		
	}
	
	private static void printGugu7() //구구단 7단 표시하기 ,증가연산자 활용 
	{
		int number=7;
		int i=1;
		
		System.out.println("구구단 7단 출력입니다.(후증가)");
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		
	}
	
	private static void inputDanPrint() //키보드에서 입력된 수의 구구단이 표시되도록 해보세요
	{
		int i=1;
		Scanner kbd =new Scanner(System.in);
		System.out.print("출력하고자하는 구구단의 수를 입력해주세요:");
		int number=kbd.nextInt();
		// String GuguForm="%d *%d =%d%n";       ~~printf("GuguForm",,,);
		System.out.printf("%n구구단 %d단 출력입니다.%n",number);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		System.out.printf("%dX%d=%d %n",number,i,number*i++);
		
		
		
	}

	private static void notEquals() //비교 논리연산 
	{
		/*
		int a,b =0;
		a=3; 
		b=5;
		
		System.out.println(a !=b);
		System.out.println(a==b);
		*/
		Scanner scan = new Scanner(System.in);
		
		
		System.out.println("홀수인가, 짝수인가");
		System.out.println("숫자를 입력해주세요");
		int number = scan.nextInt();
		
		String result=(number%2==1)?"홀수입니다":"짝수입니다";
		System.out.printf("%d 숫자는 %s %n",number,result);
		// n%2== 0  작수 
		// msg= String.Format("여기에 3항연산자") 
	}	
	
	private static void logicalAnd() // and 연산

	
	{   /*
		boolean result = true && true; //true 단축연산
		result = true || false ;       //true 
		result = false & true; // 비단축 AND 연산자 
		*/
		
		System.out.println("무작위 정수 2개 추출하여 둘다 홀수면 유효한 수");
		Random rd =new Random();
		int number1=rd.nextInt(20);
		int number2=rd.nextInt(20);
		
		String result=number1%2==0&&number2%2==0?"유효한 수 ":"무효한 수"; 
		String msg =String.format("숫자1:%d ,숫자2:%d 값:%s",number1,number2,result);
		System.out.printf(msg);
	}

	public static void login() //아이디 비번이 맞으면 로그인 성공/실패 부정연산자 사용할것 


	{
		String userId="jaeho";
		String userPwd="1234";
		
		Scanner scan= new Scanner(System.in);
		System.out.print("아이디를 입력하시오:");
		String id= scan.next();
		System.out.print("비밀번호를 입력하시오:");
		String pwd= scan.next();
		
		boolean idE=userId.equals(id);
		boolean pwE=userPwd.equals(pwd);
		
		String result=!(idE&&pwE)?"로그인 실패":"로그인 성공";
		System.out.printf("%s",result);
		
		
	}

	private static void xor() //베타적 or 

	
	{
		boolean result =true || true;
		result =true ^ true; // false 
	}

	private static void bitShiptOp() //비트 시프트 연산자 

	{
		int a=1;
		int b= a <<1; //왼쪽으로 시프트 
		System.out.println(b); //2 
		
		b= a<<2;
		System.out.println(b); //4
		
	}
	
	
}
/* 1항 연산자 : -,++,.....
   2항 연산자 : 1+2
   할당 연산자 : +=,*= ... a<<=1 이동 해서 할당. 
 
*/
