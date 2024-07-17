// 키보드에서 입력된 아이디, 암호를 사용하여 user 클래스의 인스턴스를 초기화하고
// boolean login(User u) 메소드를 작성하여 로그인 성공여부를 확인하는 기능을 작성해보세요 은닉성 적용
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        System.out.println("테스트");


        Scanner kbd = new Scanner(System.in);
        System.out.print("아이디를 입력해주세요:");
        String a = kbd.nextLine();

        System.out.print("비밀번호를 입력해주세요:");
        String b = kbd.nextLine();

        User u = new User(a, b);

        int  result =login(u);

        if(result==1) System.out.println("로그인 성공");
        else System.out.println("로그인 실패");

    } //메인 메소드 끝


    private static int login(User u)
    {
        if (u.getId().equals("smith") && u.getPwd().equals("1234"))
            return 1;
        return 0;
    }


} //Test 클래스 끝

class User
        {
        private String  id;
        private String pwd;


        public User(String id ,String pwd){
            this.id=id;
            //setId(id);
          setPwd(pwd);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }


/*
*  private static User getUser()
* {
*       // 이것이 객체지향 언어
*       syout 아이디 패스워드 입력:
*       String input = kbd,nextLine().trim();
*       String[] data = input.split("\\s+")
*       User u = new User(data[o],data[1]);
*       return u;
*
*       main
*
*       User u =getUser();
*       String msg= login(u) ? "로그인 성공":"로그인 실패"
*
*
*      login
*           -boolean
*
*
*
*
*    게시판을 구성하는 객체는 하나의 클래스 안에 모으기
*    클래스 생성시에는 실무를 모방 실제사용하는 것을 만든다??
* }
* */