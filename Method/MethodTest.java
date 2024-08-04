import java.util.Scanner;

public class MethodTest {
               static int cnt=0;
               // class 안에 오는 변수 Member 변수 2가지 성격:
               // !! static(정적) variable( class 변수) : 클래스 생성시 만들어짐 자동으로 메모리에 로드됨 프로그램 시작시. 개발자가 명령못함
               // !! instance(동적)변수 : 객체 변수. (클래스의 인스턴스)가(=new) 생성될때 로드됨 .

               static  int[] number= new  int[10];          //  사용자 번호
               static  String[] name=new String[10];
               static  String[] phone= new String[10];
               static  Scanner kbd=new Scanner(System.in);
               // 지역변수는 프로그램이 끝나면 메모리에서 삭제됨


          public  static void  main(String[] args){
            /* 회원정보 관리 시스템
             *  번호, 이름, 전화번호
             *  배열은 자료형이 정해져있다는 것을 알고 있자.
             *  자바의 클래스 이름은 자료형
             */

            // 키보드에서 회원정보를 입력받아서 배열에 저장하고 회원의 목록을 표시하는 기능
            // 프로그램이 시작되면  번호 , 이름 , 전화번호를 입력 받을 수 있는 화면이 표시되고
            // 다수의 회원 정보를 입력할 수 있어야 하고 이용자가 아무런 값도 입력하지 않고 엔터를 누르면 = 입력의 종료 신호
            // 입력이 종료되면 지금까지 입력된 회원 목록을 화면에 표시
            // 목록(s), 추가(a), 수정(u), 삭제(d), 검색(f), 종료(x):

            //int n= Integer.parseInt(sNo); // 문자열로 포함된 숫자를 가감승제가 가능한 숫자로 변환하려면........
            while (true)
            {
                System.out.print("목록(s), 추가(a), 수정(u), 삭제(d), 검색(f), 종료(x):");

                String menu = kbd.nextLine();
                if (menu.equals("s"))         list();   // 목록보기
                else if (menu.equals("a"))    add();    // 추가
                else if (menu.equals("u"))    update(); // 수정
                else if (menu.equals("d"))    delete(); // 삭제
                else if (menu.equals("f"))    find();   // 검색 : 키워드 별로 검색 번호 이름 전화 번호
                else if (menu.equals("x"))  break; // 종료 return;
            } //end of while
              System.out.println("프로그램 종료");
        }// end of main





    // 기존 코드 매소드화
    private  static  void list(){

        System.out.println("사용자 회원 목록");
        if (cnt == 0) {
            System.out.println("---------사용자 회원 목록이 없습니다.");
        }
        for (int i = 0; i < cnt; i++) {
            System.out.printf("회원번호:%d\t이름:%s\t핸드폰:%s %n ", number[i], name[i], phone[i]);


        }

  }

    private  static  void add(){

    System.out.println("회원추가");

    // no 변수에 들어있는 번호가 이미 등록된 상태인지 아닌지 검사하는 메소드 검색 삭제 업데이트때 필요 : 코드 재활용
    // 이미 등록된 회원 번호라면 해당 인덱스를 리턴하고, 아니면(사용할 수 있는 번호라면) -1을 리턴
    //int res= isFound(no);
    int no =-1;
/*
    do
    {
        System.out.print("회원 번호를 입력해주세요:");
        String sNo = kbd.nextLine();
        no = Integer.parseInt(sNo);
        }while (isFound(no)!=-1);
*/
    while (true){


        System.out.print("회원 번호를 입력해주세요:");
        String sNo = kbd.nextLine();
        no = Integer.parseInt(sNo);

        if(isFound(no)!=-1){
            System.out.println("이미 등록된 번호입니다");
        }else break;


    }


    System.out.print("사용자 이름을 입력해주세요:");
    String nm = kbd.nextLine();

    System.out.print("사용자 전화번호를 입력해주세요:");
    String ph = kbd.nextLine();

    number[cnt] = no;
    name[cnt] = nm;
    phone[cnt] = ph;
    cnt++;



}
    /** 문서화 주석
     * number 배열을 검사하여 no 파라미터가 이미 존재하면 그 인덱스를 리턴함
     * @param  no number 배열에서 검사할 회원 번호
     * @return  no가 number 배열에서 발견되면 그 인덱스 , 아니면 -1
     */
    private static  int isFound(int no) {

        for(int i=0;i<number.length;i++)
        {
            if (no == number[i])
            {
                return i;
            }

        }
        return  -1;
    }
    private static  void update(){

        // 번호 입력 ,새 전화번호 입력
        // 번호로 검색해서 그 회원의 전화번호를 새로 갱신
        list();
        int idx=0;
        if(cnt!=0) {
            while(true) {
                System.out.print("갱신할 회원의 번호를 입력하세요:");
                int no = kbd.nextInt();
                kbd.nextLine();  // 중요
                idx = isFound(no);
                if (idx == -1) {
                    System.out.println("\n"+no+"(은)는 없는 회원번호입니다.");
                }else
                    break;

            }
            System.out.print("갱신할 회원의 전화번호를 입력해주세요:");
            String p = kbd.nextLine();
            phone[idx]=p;
            System.out.println("전화번호가 변경되었습니다.");

        }
    }
    private static  void delete() {

        list();

            int idx = -1;
            if (cnt != 0) {
                while (true) {
                    System.out.printf("삭제할 회원 번호를 입력해주세요 :");
                    int no = kbd.nextInt();
                    kbd.nextLine();
                    idx = isFound(no);

                    if (idx == -1) {
                        System.out.println("\n" + no + "(은)는 없는 회원번호입니다.");
                    } else
                        break;

                }
                for(int i=idx;i<cnt;i++) {
                    number[i] = number[i + 1];
                    name[i] = name[i + 1];
                    phone[i] = phone[i + 1];
                }
                cnt--;

                list();


            }
        }


    private  static void find(){
        if(cnt!=0) {
            System.out.print("회원번호 검색(n), 전화번호 검색(P)");
            String menu2 = kbd.nextLine();
            // int idx =-1;
            if (menu2.equals("n"))   searchByNo(); //회원 번호로 검색
            else if (menu2.equals("p")) searchByPhone(); // 전화번호로 검색
            //if(idx !=-1) print number[idx] ~~~ else 검색 실패
        }else System.out.println("---------사용자 회원 목록이 없습니다.");
    }
    private static  void searchByNo(){

        System.out.print("회원 번호를 입력해주세요:");
        int n = kbd.nextInt();
        kbd.nextLine();
        boolean search = false;
        for (int i = 0; i < cnt; i++) {
            if (n == number[i]) {
                //idx=i;  break;
                System.out.printf("회원번호:%d\t 회원이름:%s\t 회원전화번호:%s %n", number[i], name[i], phone[i]);
                search = true;
            }
        }
        if (!search) System.err.println("등록된 회원번호가 없습니다");




    }
    private static  void searchByPhone(){

        System.out.print("전화번호를 입력해주세요:");
        String ph = kbd.nextLine();
        boolean search = false;
        for (int i = 0; i < cnt; i++) {
            if (ph.equals(phone[i])) {
                //idx=i; break;
                System.out.printf("회원번호:%d\t 회원이름:%s\t 회원전화번호:%s %n", number[i], name[i], phone[i]);
                search = true;
            }
        }
        if (!search) System.err.println("등록된 회원 전화번호가 없습니다");

    }


    /*
    *   System.out.print("전화번호를 입력해주세요:");
        String ph = kbd.nextLine();
        int idx =-1;
        for (int i = 0; i < cnt; i++) {
            if (ph.equals(phone[i])) {
                idx =i;
                break;
            }
        }

        return idx;
    }
*/

    }



/*  원래 update code
{
                    // 번호 입력 ,새 전화번호 입력
                    // 번호로 검색해서 그 회원의 전화번호를 새로 갱신
                    list();
                    if(cnt!=0) {


                        System.out.print("갱신할 회원의 번호를 입력하세요:");
                        int n = kbd.nextInt();
                        kbd.nextLine();  // 중요
                        System.out.print("갱신할 회원의 전화번호를 입력해주세요:");
                        String p = kbd.nextLine();
                        boolean updated = false;
                        for (int i = 0; i < cnt; i++) {
                            if (n == number[i]) {
                                phone[i] = p;
                                System.out.println("등록성공");
                                updated = true;
                                break;
                            }

                        }
                        if (!updated) System.err.println("등록된 회원번호가 없습니다");



                    }
                }
*/

/*  원래 delete 코드

            System.out.printf("삭제할 회원 번호를 입력해주세요 :");
            int n = kbd.nextInt();
            kbd.nextLine();
            boolean deleted = false;
            for (int i = 0; i < cnt; i++) {
                if (n == number[i]) {
                    for (int j = i; j < cnt - 1; j++) {
                        number[j] = number[j + 1];
                        name[j] = name[j + 1];
                        phone[j] = phone[j + 1];
                    }
                    cnt--;
                    deleted = true;
                    System.out.println("삭제 성공");
                    break;
                }
            }


            if (!deleted) System.err.println("등록된 회원번호가 없습니다");
            else {
                System.out.println("수정된 사용자 회원 목록");
                for (int i = 0; i < cnt; i++) {
                    System.out.printf("번호:%d\t이름:%s\t핸드폰:%s %n ", number[i], name[i], phone[i]);

                }
            }
        }



    }
*/