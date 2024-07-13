import java.util.*;

public class DataTypes
{
	public static void main(String[] args)
        {
		

		/*      숫자(정수,실수,문자),논리

			정수 byte(8비트), short(16), int(32) ,long(64)
			실수 float, double
			문자 char
			논리 boolean

               */
	       
		int age ; //메모리에 정수 4byte(32bit 2^32)공간 확보 
		age =25;  
		System.out.println("age 변수에 기억된 정보:"+age);


		System.out.println("int min:"+Integer.MIN_VALUE);
  		System.out.println("int max:"+Integer.MAX_VALUE);
		System.out.println("------------------------------------------------------------------------------------------------");


		System.out.println("byte, short ,long 형으로 표현 가능한 최대값 출력");
		System.out.println("byte 최대값:"+Byte.MAX_VALUE);
		System.out.println("short 최대값:"+Short.MAX_VALUE);
		System.out.println("long 최대값:"+Long.MAX_VALUE);

		//byte(1byte, 8bit) :2^8=256  -- -2^7~2^7-1 -->-128~127 나머지는 부호 비트로쓰자. (음,양)
		/*	
		int a=100; // 정보 
		int b=20;
		int c= a+b; // 처리 
		*/
		// 산술 연산자 : +,-,/,*,%  ++, -- 
		
		short min=Short.MIN_VALUE;
	 	short max=Short.MAX_VALUE;
		int result=min+max;

		System.out.println("short 최소값:"+min+"~"+"short 최대값:"+max);
		System.out.println("short의 최소(min)최대(max) 의 합="+result);
		System.out.println(min+"+"+max+"="+result);
		System.out.println("printf 표현방식");
		System.out.printf("%d+%d=%d \n ",min,max,result);  // 재활용 못함.
		System.out.println("------------------------------------------------------------------------------------------------");

		String format ="%d+%d=%d \n";
                System.out.println("포맷방식");
		System.out.printf(format,min,max,result); 
		String fs =String.format(format,min,max,result); // 서식을 이용한 새 문자열 생성 
		System.out.println(fs);
		System.out.println("\n------------------------------------------------------------------------------------------------");

		System.out.println("키보드로부터 동적인 데이터를 받아오기");
		
		Scanner kbd =new Scanner(System.in);  // 메모리상에 만든 오브젝트를 참조 변수 kbd (메모리 주소)
		System.out.print("키보드로 수a 받기:");
		int a=kbd.nextInt(); // next 여러번 읽을 수 있다는 의미  키보드에서 정수 하나를 가져와서 a 에 저장 nextlnt:표현식 )
		// 입력받은 수를 기억 

		System.out.print("키보드로 수b 받기:");
		int b=kbd.nextInt();
		
		System.out.printf("%d+%d=%d %n",a,b,a+b); //%n 줄바꿈
		
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("실수 float(4byte) ,double(8)");
		
		//float pi= 3.14159201234567;  
		float pi= 3.14159201234567F;  
		//정밀한, 정밀도 개념. 
		double pi2= 3.14159201234567;

		System.out.println("pi="+pi);
                System.out.println("pi2="+pi2);
		System.out.println("------------------------------------------------------------------------------------------------");
		
		System.out.println("float 최소값:"+(-Float.MAX_VALUE)+"~~"+"float 최대값:"+Float.MAX_VALUE);
		System.out.printf("double 최소값:%f~~double 최대값:%f %n",-Double.MAX_VALUE,Double.MAX_VALUE);
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.print(" 반지름을 입력하세요:");
		float r=kbd.nextFloat();
		
		float S= r*r*3.141592F;
		System.out.printf("반지름이 %f인 원의 면적은 %f 입니다.\n",r,S);
		System.out.println("------------------------------------------------------------------------------------------------");
		
		System.out.print("아이디를 입력하세요:");
		String id = kbd.next();

		System.out.print("암호를 입력하세요:");
		String pw = kbd.next();
		
		System.out.printf("아이디:%s \n암호: %s \n",id,pw);
		
		System.out.println("------------------------------------------------------------------------------------------------");
		
		
		/* 
			참조형 비교는 equals 즉 문자열은 =  == 쓰면 안됨.
		
		*/ 
               	

		System.out.println("기본형 데이터 비교와 참조형 데이터 비교");
		
		boolean y= 1==2;  // >,<,!= 등등
		System.out.println(y);
		
		String aStr= "Hello";
		String bStr= "hello";
		boolean same =aStr.equals(bStr);				
		System.out.println("문자열 비교 결과:"+same);
		
		System.out.println("3항연산자 활용");
		// a?b:c  a는 비교문  참 b 불 c	
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("로그인 성공 실패 문제 알고리즘.");
		String id1 ="smith";
		String pw1="1234";
		while(true){
		System.out.print("아이디를 입력하세요:");
		String id2 = kbd.next();
		System.out.print("암호를 입력하세요:");
		String pw2 = kbd.next();
		 
		String login = (id1.equals(id2) && pw1.equals(pw2))?"로그인 성공":"로그인 실패";
		System.out.println(login);
			
		if(login.equals("로그인 성공")) break;
		}
		System.out.println("------------------------------------------------------------------------------------------------");
		// 문자, 문자열 (문자의 집합)
		// 문자열 " "  문자 char ' ' 
		char ch ='a';
		System.out.printf("문자 ch=%c 형변환int값:%d %n",ch,(int)ch);

		//ch=ch+1; //  4바이트 +4바이트가돼 그래서 4바이트를 2바이트에 못넣음 형변환 필요
		// ch=99; 0x63 =c 16진수 계산기 활용하면
		ch= (char)(ch+1);
			
		System.out.printf("ch+1=%c %n",ch);
		
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("랜덤");
		
		Random rd= new Random();
		
		
		System.out.println("두 정수를 추출하여 두수의 곱을 구하기");
		int rand1=rd.nextInt(10); // 0~9
		int rand2=rd.nextInt(10); // 0~9
		
		System.out.printf(" 두수의 곱; %d * %d=%d %n",rand1,rand2, rand1*rand2);

		System.out.println("10부터 20 사이의 숫자 ");
		rand1=rd.nextInt(11)+10;
		System.out.println(rand1);

		// 참조형 비교는 왜 equals()를 사용하는가
		String s= "jaeho"; //  liter Pool 
                 //String s2= "jaeho";
				// Literal Pool 에서는 동일한 문자열 저장하지 않도록 되어 있다.  s2 에는 s1주소 참조 
		//s ==s2 ->  연산자 비교  true   s.eqals(s2) true 참조비교  참조의 목적지 비교? =내용비교 
		//String s2=new String("jaeho"); // 현재 메모리에는 jaeho가 몇개 기억되어 있는가 // Heap 메모리 영역이달라 위에 주소랑 다름
		String s2=new String("jaeho"); // heep 동적 메모리 할당 프로그램이 돌아갈때 할당  
		System.out.println(s==s2);// 문자열이 동일해도 다름 주소값비교
		System.out.println(s.equals(s2));
		//a=1;
		System.out.println("알파벳 소문자 무작위로 5개 추출하여 화면에 표시 ");
		char ch22=(char)(a+120);  // 숫자는  왼쪽으로 갈수록 비중이 커짐 점점커짐 숫자는 비트 010101010 왼쪽으로갈수록
		//a가1이면 121 1바이트로 표현 가능  나머지 비트는 버려도 데이터 손실이 없음,, 
		//a가 10이면 130 데이터 손실?
		/*


                
		
		short sh=(short)(Short.MAX_VALUE+1);
		System.out.println(Short.MAX_VALUE+1+","+sh); // 넘치면 최저로 시작 
                */
		short sssss=4500; 
		//int aaa =s; //2바이트가 4바이트에 가면 비트 손실, 없어서 (int)s 안해도됨

		// 숫자 랜덤으로 뽑아서 알파벳 문자로 출력방법   100숫자 뽑고 char ch = 100  4바이트 int를 2바이트에 넣으려면 비트손실 
		//4바이트 중 2바이트를 버리기땜에 근데 100이라는 숫자는 손실? 잉 없다네   케스팅?테스팅?
		char test= (char)100;
		System.out.println("형변환후 "+(int)test); // 수치손실 없음 이럴때 케스팅 이럴때만 사용??




		System.out.println("------------------------------------------------------------------------------------------------");

  		System.out.println("알파벳 소문자 무작위로 5개 추출하여 화면에 표시 ");

		int randNumber =rd.nextInt(26)+97;
		int randNumber2 =rd.nextInt(26)+97;
		int randNumber3 =rd.nextInt(26)+97;
		int randNumber4 =rd.nextInt(26)+97;
		int randNumber5 =rd.nextInt(26)+97;
		System.out.printf("알파벳 소문자5개 :%c,%c,%c,%c,%c",(char)randNumber,(char)randNumber2,(char)randNumber3,(char)randNumber4,(char)randNumber5);



		/* char c1 =(char)(rd.nextInt(26)+97);  //교수왈 
		String rdStr = String.format("%c",c1)
		System.out.println(rdStr);
		*/ 






        }

}