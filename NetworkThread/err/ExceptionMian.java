package err;
//17일 직렬?처리
//18 예외처리


import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ExceptionMian {
    static   Scanner kbd = new Scanner(System.in);
    static String fpath ="/Users/jeongjaeho/Desktop/012/err/user.txt";
    public static void main(String[] args)  {    //err04 throws -> mian에서 쓸때는 감싸줘야함 근데 Mian을  throws 하면 비정상 종료

        //에러 예외
        //error:
        //Exception : Mild EEror 에러보단 순한?!
        //소프트웨어 에러 (실행중 에러)
        //비정상 종료
        //error01();
        //error02(); // 키보드에서 정수 a b를 받아서 나눗셈을 하고 결과 식을 화면에 표시  4/3=1
        //error03();   // sample.txt 파일을 열고 그 안에 저장된 텍스트를 화면에 표시해보세요.
        /*
        try {
            error04();
        } catch (Exception e) {

            System.out.println("파일 없음");
        }
        */
        /*
        try {
            error05();
        } catch (PasswordException e) {
            System.err.println(e.getMessage());
        }

        */
        try {
            errror06();
        } catch (LoginException login) {
            System.err.println(login.getMessage());
        }
    }


    private static void error01()
    {
        int a = 5;
        int b = 0; // Exception in thread "main" java.lang.ArithmeticException: / by zero  0으로 나눴을때에 대한 산순 에러
        int c = 0;

        try {
            c = a / b;  // 에러가 발생했을때 catch 블록 실행
            System.out.println("나눈결과:" + c); //안에 변수 선언하면 지역변수됨 try 예외 발생 가능한 코드가 있는곳

        } catch (ArithmeticException ae) {
            System.err.println(ae.getMessage());
            System.out.println("0으로 수를 나눌 수는 없습니다."); //예외가 발생했을때 돌아가는곳

            return; // 에러가 나면 종료   메소드 종료 실행한됨 finally 는 실행됨
        } finally { //예외 발생과 무관하게 항상 실행 되는 블럭
            // 굳이? 있어야하나
            System.out.println("finally block");
        }
        System.out.println("메소드 종료"); // 에러가 나도 비정상종료가 안된다
    }

    private static void error02()
    {
        while (true) {
            System.out.println("정수 a, b를 입력해주세요:");
            try {

                int a = kbd.nextInt();
                int b = kbd.nextInt();

                int c = a / b;
                System.out.printf("%d/%d=%d", a, b, c);
                break;

            } catch (Exception e){
                //에러의 타입을 확인.
                if(e instanceof ArithmeticException){
                    System.out.println(e.getMessage());
                    System.err.println("0으로 나눌 수 없습니다.");
                }
                if(e instanceof InputMismatchException){
                    System.out.println(e.getMessage());
                    System.err.println("정수가 아닙니다.");
                }
            }
            /*catch (ArithmeticException ae) {
                System.out.println(ae.getMessage());
                System.out.println("0으로 나눌 수 없습니다.");

            } catch (InputMismatchException ie) {
                System.out.println(ie.getMessage());
                System.out.println("정수가 아닙니다.");

            }*/
            finally {
                kbd.nextLine(); //잘못 입력 했을때 버퍼에 남은 문자를 제거 해줘야함
            }

        }
    }
    private  static  void  error03()
    {
        BufferedReader br =null;
        try {
            FileReader fr =new FileReader("/Users/jeongjaeho/Desktop/012/sample.txt");
            br =new BufferedReader(fr);
            String line= null;
            while ((line=br.readLine())!=null){
                System.out.println(line);
            }
            br.close();
        }catch (FileNotFoundException fe){  // 컴파일러가 채크한 예외처리
            System.out.println("파일을 찾을 수 없습니다.");
        }catch (IOException ioe){
            ioe.printStackTrace(); // printStackTrace 개발자가 보기위한 . 사용자 시점 x
        }finally {
            try {
                if(br !=null)
                br.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        System.out.println("종료");
    }

    private  static  void  error04() throws Exception
    {

        BufferedReader br =null;
        FileReader fr =new FileReader("/Users/jeongjaeho/Desktop/012/sample.txt");
        br =new BufferedReader(fr);
        String line= null;
        while ((line=br.readLine())!=null){
            System.out.println(line);
        }
        br.close();

        //반드시 trycatch 하는 것은 아닌다
        //but error04() 반드시 try catch

    }

    private  static  void  error05() throws PasswordException {
        System.out.print("Id Password :");
       String id = kbd.next();
       String pwd =kbd.nextLine();


             if(pwd.length()<5){
                // throw  new Exception("암호는 5자 이상입니다");
                 throw  new PasswordException("암호는 5자 이상입니다");
             }
        System.out.println("메소드 종료");



    }


    private  static  void    errror06() throws LoginException{

        /*
        User Text파일에 아이디 , 암호를 5개 저장하고
        키보드에서 로그인할때 로그인에 실패할 경우 UserLoginException이 발생하도록 하고
        발생화 예외를 처리하여 이용자에게  적절한 메세지를 표시하여 다시로그인
        할수 있도록 반복 로그인할 수 있도록 작성하기
        */
        System.out.println("회원 로그인");
        System.out.print("아이디 비밀번호 :");
        String id= kbd.next().trim();
        String pwd= kbd.next().trim();

        boolean ok =false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fpath));
            String line = null;

            while ((line=br.readLine())!=null){
                String[] fdata =line.split("\\|");
                if(fdata[0].equals(id)&&fdata[1].equals(pwd)){
                    System.out.println("로그인 성공");
                    ok=true;
                    break;
                }
            }
             br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!ok){
            throw  new LoginException("로그인 실패");
        }
    }



}