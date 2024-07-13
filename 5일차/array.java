import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class array {
    public static void main(String[] args) {

        //arrayTest(); // 배열 기본 테스트
        //hello("jaeho");

        //add(4, 5);

        //int result = add2(4, 10);
        //System.out.println(result);

        //String result2 = greet("jaeho2");
        //System.out.println(result2);
        //int[] a = new int[]{1, 2, 3, 4, 5};


        //int result3 = sum(a);
        //System.out.println(result3);

        int[] arr = getNums(5);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
         System.out.println(arr[0]);
        //int res = sum(arr);
        //System.out.println("합계:" + res);
        //arrSort();

        //System.out.println(Arrays.toString(sortIntArr(5)));


        //arrayCRUD();





    }

    //배열 Array
    /*
     * 자료구조 중에서 데이터를 순서대로 순화하면서 보여줄 때 가장 빠른 성능을 보여준다
     * 배열을 사용할때 는 배열변수 선언, 메모리할당, 초기화,배열사용 순으로 해야한다.
     */
    private static void arrayTest() {

        int[] nums;                            // 배열 변수 선언
        nums = new int[5];                      // 정수 5개 공간 확보(할당)
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 3;
        nums[3] = 4;
        nums[4] = 5;
        for (int i = 0; i < nums.length; i++)       // CRUD  create, read, update, delete
        {
            System.out.printf("%d", nums[i]);
        }

    }

    private static void arrayCRUD() {
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
        Scanner kbd = new Scanner(System.in);
        int cnt = 0;

        int[] number;          //  사용자 번호
        number = new int[10];

        String[] name;          // 사용자 이름
        name = new String[10];

        String[] phone;        // 사용자 전화번호
        phone = new String[10];   // 지역변수는 프로그램이 끝나면 메모리에서 삭제됨


        //int n= Integer.parseInt(sNo); // 문자열로 포함된 숫자를 가감승제가 가능한 숫자로 변환하려면........
        while (true) {
            System.out.print("목록(s), 추가(a), 수정(u), 삭제(d), 검색(f), 종료(x):");

            String menu = kbd.nextLine();
            if (menu.equals("s")) // 목록보기
            {
                System.out.println("사용자 회원 목록");
                if (cnt == 0) {
                    System.out.println("---------사용자 회원 목록이 없습니다.");
                }
                for (int i = 0; i < cnt; i++) {
                    System.out.printf("회원번호:%d\t이름:%s\t핸드폰:%s %n ", number[i], name[i], phone[i]);


                }
            } else if (menu.equals("a")) //추가
            {
                System.out.println("회원추가");
                System.out.print("회원 번호를 입력해주세요:");
                String sNo = kbd.nextLine();

                int no = Integer.parseInt(sNo);


                System.out.print("사용자 이름을 입력해주세요:");
                String nm = kbd.nextLine();

                System.out.print("사용자 전화번호를 입력해주세요:");
                String ph = kbd.nextLine();

                number[cnt] = no;
                name[cnt] = nm;
                phone[cnt] = ph;
                cnt++;


            } else if (menu.equals("u")) // 수정
            {
                // 번호 입력 ,새 전화번호 입력
                // 번호로 검색해서 그 회원의 전화번호를 새로 갱신
                System.out.println("기존 회원 목록");
                for (int i = 0; i < cnt; i++) {
                    System.out.printf("회원번호:%d\t이름:%s\t핸드폰:%s %n ", number[i], name[i], phone[i]);
                }


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

            } else if (menu.equals("d")) // 삭제
            {
                System.out.println("사용자 회원 목록");
                for (int i = 0; i < cnt; i++) {
                    System.out.printf("번호:%d\t이름:%s\t핸드폰:%s %n ", number[i], name[i], phone[i]);

                }
                System.out.println("삭제할 번호를 입력해주세요 :");
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
            } else if (menu.equals("f")) // 검색 : 키워드 별로 검색 번호 이름 전화 번호
            {
                System.out.print("회원번호 검색(n), 전화번호 검색(P)");
                String menu2 = kbd.nextLine();
                // int idx =-1;
                if (menu2.equals("n")) {
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
                } else if (menu2.equals("p")) {
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
                //if(idx !=-1) print number[idx] ~~~ else 검색 실패


            } else if (menu.equals("x")) // 종료
            {
                //return;
                break;
            }


        } //end of while

        System.out.println("프로그램 종료");
    }// end of method

    // 메소드(Method: 코드의 집합에 이름을 붙인 것)
    private static void hello(String name) //function : 기능 (함수)
    {
        System.out.println("hello \t" + name);

    }

    private static void add(int x, int y) // 입력 파라미터가 있지만 리턴 데이터는 없다 (void)
    {
        System.out.printf("%d + %d =%d %n", x, y, x + y);

    }

    private static int add2(int x, int y) {  // 정수를 리턴  int :리턴 타입
        return x + y; //

    }


    // 메소드 만들기 요구사항: greet 메소드에 회원의 이름을 전달하면 그 이름에 "hello"가 추가되어 리턴되고
    // 리턴된 문자열을 화면에 표시하는 기능을 작성

    private static String greet(String name) {
        return name + "  hello";
    }


    // sum이라는 메소드를 정의하고 배열을 받아서 그 배열의 합을 리턴하는 기능  (정수배열)

    private static int sum(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }


    private static int[] getNums(int cnt) {  //메소드를 호출하면 무작위 정수를 원소로 하는 배열이 리턴 되도록  파라미터로 전달하는 숫자만큼 베열의 원소수를 지정하여  배열이 생성되도록 sum에 전달

        Random rd = new Random();

        int[] arr = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            arr[i] = rd.nextInt(50);
        }
        return arr;
    }
    // 선택 정렬 알고리즘이란 ...selection Sort  1.처음 하나 선택후 나머지와 다 비교 2중 for loop

    private static int[] arrSort() {  // 선택정렬 알고리즘을 사용해서 무작위로 추출된 배열의 원소를 오름차순으로 정렬해보세요.

        //swap 알고리즘
        int a = 5;
        int b = 3;

        int tmp = a;

        a = b;
        b = tmp;
        //a=b; b=a; ??? 둘다 3이 되버림.

        // 무작위 정수 배열 원소 10개 준비.
        int[] arr = new int[10];
        Random rd = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(20); // 정렬이 되지 않은 배열
        }
        System.out.println("정렬 전:" + Arrays.toString(arr)); // 이배열의 원소들이 문자열로 표현해라 시각해라. -정렬 전 상태

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) { //현재 선택된 값과 비교할 우측값
                if (arr[i] > arr[j]) {           //정렬 대상자인지 확인
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("정렬 후:" + Arrays.toString(arr)); // 정렬 후 상태


        return null;
    }


    // sortIntArr 메소드를 작성하고 작성하고 파라미터로 정수를 전달하면
    // 파라미터가 의미하는 갯수만틈 정수 배열을 생성하고 정렬된 배열을 리턴
    // 하는 메소드를 선언하고 호출하여 그 리턴값을 화면에 표시해보세요
    //선택정렬알고리즘을 구현한 메소드를 선언하고 slectionSort를 선언하고 활용
    private static int[] slectionSort(int[] arr) {


        System.out.println("정렬 전:" + Arrays.toString(arr));

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) { //현재 선택된 값과 비교할 우측값
                if (arr[i] > arr[j]) {           //정렬 대상자인지 확인
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }


        return arr;

    }


    private static int[] sortIntArr(int length) {

        int[] arr = new int[length];
        Random rd= new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(20); // 정렬이 되지 않은 배열
        }


        return slectionSort(arr);
    }











}