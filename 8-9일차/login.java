package program;

//0712
/*
*   파일을 사용한 로그인 기능
*   users.txt
*   smith:1234 형식으로 5개 행 생성
*   이용자가 키보드에서 로그인하면 User class의 인스턴스를 사용하요 id,pwd를 저장하고
*   파일에서 로드된 List<User> 자료구조와 비교하여 회원 정보에 존재하는지 확인
*   로그인 실패 성공 메세지
*
*/


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class login {

    static Scanner kbd = new Scanner(System.in);
    static int Fail = 0;

    public static void main(String[] args)    //메인 메소드

    {
        userList();
        //program();
    }

    /*
        private static void program() {

            users = listFromFile();

            while (Fail != 3) {
                System.out.println("로그인");
                System.out.print("아이디를 입력하세요:");
                String id = kbd.nextLine();
                System.out.print("비밀번호를 입력하세요:");
                String password = kbd.nextLine();

                User key = new User(id, password);
                if (users.contains(key)) {
                    System.out.println("로그인 성공");
                    while (true) {
                        System.out.println("회원 정보 보기(r),추가(a),종료(x)");
                        String menu = kbd.nextLine();
                        if (menu.equals("r")) {
                            userList();
                        } else if (menu.equals("a")) {
                           addUser();
                        } else if (menu.equals("x")) {
                            break;
                        }

                    }

                } else {
                    System.out.println("로그인 실패");
                    Fail++;
                    if (Fail == 3) {
                        System.out.println("3분후에 다시 로그인 해주세요");
                        break;
                    }
                }

            }

            System.out.println("프로그램 종료");

        }
    */
    private static List<User> listFromFile() {

        List<User> users= new ArrayList<>();

        File f = new File("/Users/jeongjaeho/Desktop/풀스택 교육과정 /0712/program/users.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = null;

            while ((line = br.readLine()) != null) {
                users.add(new User(line));
            }
            br.close();
            return users;


        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;

    }


    private static void userList() {

        List<User> users =listFromFile();

        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
    }


/*
    //키보드에서 아이디,패스워드 압력받아서 파일에 한행을 추가
    //회원 정보추가
    private static void addUser() {
        System.out.println("회원 정보 추가하기");
        System.out.print("아이디 암호:");
        String uId = kbd.next();
        String pwd = kbd.next();
        kbd.nextLine();
        User user = new User(uId, pwd);
        boolean added = appendUser(new User(uId,pwd));

        System.out.println(added ? "추가 성공" : "실패");
    }
*/



/*
    private static boolean appendUser(User user) {


        try {
            String fname = "/Users/jeongjaeho/Desktop/풀스택 교육과정/0712/program/users.txt";
            //한 행씩 파일에 적는
            PrintWriter pw = new PrintWriter(new FileWriter(fname, true));   //FileWriter  tre 안하면 덮어쓰기
            pw.printf("%s:%s%n", user.getId(), user.getPassword());
            pw.close();
            //users.add(new User(user.getId(), user.getPassword()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

*/
}









/*
    private  static void addUser2(){

        System.out.println("회원 정보 추가하기");
        System.out.print("아이디를 입력해주세요:");
        String addId =kbd.nextLine();
        System.out.println("비밀번호를 입력해주세요:");
        String addPwd=kbd.nextLine();

        try {
            String fname= "/Users/jeongjaeho/Desktop/풀스택 교육과정 /0712/program/users.txt";
            //한 행씩 파일에 적는
            PrintWriter pw = new PrintWriter(new FileWriter(fname,true));   //FileWriter  tre 안하면 덮어쓰기
            pw.println(String.format("%s:%s",addId,addPwd));

            pw.close();
            users.add(new User(addId, addPwd));
            System.out.println("회원정보 추가 성공");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}


*/