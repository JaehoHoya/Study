package com.test.hello;


import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class ControlStatement {

	/*
		반복문,조건문
		반복문(while, do~while,for)
		제어문(if, if~else, switch)
	*/
	public static void main(String[] args)
    {
		/*
		// while(조건식) 조건이 true 일때만 반복  
		int i=0;
		boolean go =true;
		while(go)
		{	
			i++;
			go= i<10?true:false;
			System.out.println("hoho");
		}
		*/
		
		//showGugu();
	    //getSumRange();
		//getSumRange2();
		//test();  while 문장만 사용 
		//getEvent5();
		//dateTest();
		//variableInit();
		//login();
		forLoopTest(); 
    }
	private static void showGugu() // 구구단 출력 숫자 (2~9) 랜덤추출 (while) 
	
	{
		Random rd= new Random();
		int number=rd.nextInt(9)+1;
		int i=0;
		boolean go =true;
		while(go) {
			System.out.printf("%d X %d = %d %n",number,++i,number*i);
			go= i==9?false:true;
		}
	}
	
	private static void getSumRange() // 무작위 숫자 추출,  그 수 부터 10까지 합산. ex) 3추출 3부터 10합
	{
		Random rd= new Random();
		int number=rd.nextInt(10); 
		
		int start=number;
		int sum=0;
		while(number<=10) 
		{
			sum +=number++;
		}
		System.out.printf("무작위 수: %d 부터 10까지 합 : %d",start,sum);
		
	}
	
	
	private static void getSumRange2() // 무작위 숫자 추출,  그 수 부터 10까지 합산(2번째 대안)

	{
		Random rd= new Random();
		int number=rd.nextInt(10); 
		
		int start=number;
		int sum=0;
		
		while(number++<=10) 
		{
			sum =number-1;
			
		}
		System.out.printf("무작위 수: %d 부터 10까지 합 : %d",start,sum);
		
	}
	
	private static void test() //1~5~1 출려 문제 while문장
	{
		int i=1;
		int delta =1;
		int cnt =0;
		while(i>0) 
		{
			System.out.println(i);
			cnt++;
			delta= cnt>=5?-1:1;
			i +=delta;
		}
		
		
		
		
	}
	
	private static void getEvent5() // 무작위(1~20) 수중에서 짝수가 5개 출력  
	{
		int count=0;
		Random rd=new Random();
		/*
		while(count<=5)
		{
		
		int number =rd.nextInt(20)+1;
		
		int result= number%2==0?number:null;
		System.out.printf("랜덤으로 추출된 짝수:%d %n",result);
		count++;
		
	    }
	    */
		
		while(count<5) 
		{
			int number =rd.nextInt(20)+1;
			String s=number%2==0?number+"":""; //짝수가 아니면 출려하지않은것같은 느낌.
			System.out.println(s);
			count +=!s.equals("")?1:0;
			
		}
   }

	//if 문 예시
	private static void ifTest() 
	
	{
		int i=0;
		if(i==1)
		{ 
			System.out.println("참인 경우");
		}
		else if(i==2)
		{
			System.out.println("참인 경우2");
		}
		else
		{
			System.out.println("거짓인 경우");
		}
		
		System.out.println("메소드 종료");
		
	}

	private static void dateTest() // 날짜 
	{
		
		Calendar cal = Calendar.getInstance();     //나라마다 달라서 new (x)
		int y= cal.get(Calendar.YEAR);
		int M= cal.get(cal.MONTH)+1;
		int d= cal.get(cal.DAY_OF_MONTH);
		int wd= cal.get(cal.DAY_OF_WEEK);
		int h= cal.get(cal.HOUR);
		int m= cal.get(cal.MINUTE);
		int s= cal.get(cal.SECOND);
		String  sWeekDay=null; //참조 변수는 null로 초기화 가능
		/*
		if     (wd==1) sWeekDay ="일";
		else if(wd==2) sWeekDay ="월";
		else if(wd==3) sWeekDay ="화";
		else if(wd==4) sWeekDay ="수";
		else if(wd==5) sWeekDay ="목";
		else if(wd==6) sWeekDay ="금";
		else if(wd==7) sWeekDay ="토";
		*/
		switch(wd) 
		{
		case 1: sWeekDay ="일";  break;
		case 2: sWeekDay ="월";  break;
		case 3: sWeekDay ="화";  break;
		case 4: sWeekDay ="수";  break;
		case 5: sWeekDay ="목";  break;
		case 6: sWeekDay ="금";  break;
		case 7: sWeekDay ="토";  break;
		}
		
									
		//엔트리 포인트 시작점만 지시할 
		
		System.out.printf("%d년-%d월-%d일 %s요일 %d:%d:%d%n",y,M,d,sWeekDay,h,m,s);

	
    }	
	
	private static void variableInit() //변수 초기화 
	
	{
		int a=0; 
		String str= null; //참조변수 초기화 방법 1
		str=""; // 2
		
		String name=""; //1.null로 초기활 경우 java.lang.NullPointerExceptio 오류 
		//1. null 은 아무것도 없음 객체도 아님 진짜 없는 .
		//2.초기화를 "" 로하면  오류 안뜸  객체지만 내부엔 아무것도 없는 (아무것도 없어보이지만)
		boolean result =name.equals(""); 
		//1.->The local variable name may not have been initialized
		//1. 초기화 안됬다는 오류 
		
		
		
		
	}
	
	/*
	 	if while 문을 활용한 login break: 반복문 종료 활용 
	 	
	 	로그인 시도 3회 기회 3회 넘어가면 10분후 다시 시도하라고 메세지 출력후  프로그램 종료 
	 
	*/
	
	private static void login()
	{
		int cnt=0;
		
		Scanner scan =new Scanner(System.in);
		
		System.out.println("로그인하세요");
		
		while(true) 
		{
			
			System.out.print("아이디:");
			String id=scan.next();
			
			System.out.print("비밀번호:");
			String pwd=scan.next();
			
			
			if(id.equals("jaeho")&&pwd.equals("1234")) 
			{
				System.out.println("로그인 성공");
				break;
			}
			else
			{
				cnt++;  //++cnt 
				System.out.printf("로그인하세요 %n %d회/3회 실패 %n",cnt);
				if(cnt==3) 
				{
					System.out.println("10분후 다시 시도해주세요");
					break;
				}
			}
				
		}
		
		System.out.println("프로그램 종료");
			
	 }

	// 교수님 코드 
	// if(++cnt==3)
	
	
	private static void switchTest() // switch 문 예제 연습 
	{
		int n=1 ;
		switch(n)
		{
		case 1:
			break;     // n=1 이고 break 있으면 끝. 
		case 2:
			break;
		case 3:
			break;    //break 는 옵션 . 쓰는 경우가 많음 .
			
		default:
			break;    // 어디에도 해당되지 않을
		}
		
		
		
		
	}
	
		private static void forLoopTest() 
		
		{
			
			/*
			for(int i=0;i<=10;i++)   //for (a,b,c)  a,b 가 참이면 블록 실행후 c 실행 a는 한번만 실행 
			{
				
				System.out.println(i+" ");
			
			
			}
			 
			for(int i=0, j=10; i<=10; i++, j--) // 이 런 식도 가능 
			{
				
				
				
			}
			
									 // 5일차 
			
			for(int i=1; i<=10; i++) // 한개의 for 루프를 이용하여 10 부터 1까지 다시 10까지 출력하는 기능 
			{
				
				System.out.println(i);
				if(i==10) {
					
					for(int j=9;j>0;j--)
					System.out.println(j);
				}
				
			}
			
			
			for(int i=10,delta=-1; i<=10; i+=delta) // 교수님 코드 !!!!!!!!!!!!!!!
			
			{
				System.out.println(i+"");
				if(i==1) delta=1;
			}
			*/
			//continue :: while, for 문장에서  흐름을 다시 루프의 시작으로 돌린다.
			//break: while, for, switch 문장에서 반복을 종료한다. 
			
			
			
			// 무작위 정수 10개를 출력하는데 모두 홀수여야한다  break 없이 ,true false 없이 
			
			Random rd = new Random();
			int n=0;
			
			
			
			for(int cnt= 0; cnt<10;) 
			
			{
				n=rd.nextInt(20)+1;
				if(n%2==1)
				{
				System.out.printf("%d %d %n",++cnt,n);
				
				}
			}
				
		
			//배열 Array
			
			/*
			 * 자료구조 중에서 데이터를 순서대로 순화하면서 보여줄 때 가장 빠른 성능을 보여준다 
			 * 배열을 사용할때 는 배열변수 선언, 메모리할당, 초기화,배열사용 순으로 해야한
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
			
			
	        
			
			
			
			
			
			
	  }
	
		private static void arrayTest()
        {

             int[] nums; // 배열 변수 선언
             nums =new int[5]; //정수 5개 공간 확보(할당) 
             nums[0]=1;
             nums[1]=2;
             nums[2]=3;
             nums[3]=4;
             nums[4]=5;
        }
		
	
  }


