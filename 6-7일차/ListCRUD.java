import java.text.SimpleDateFormat;
/*
List Collection API를 사용한 CURD 실습

**사원 관리 시스템**

사원: Employee(데이터 모델)

     int empNumber  사번
     String empName 사번 이름
     int deptNumber 부서번호
     int sal        급여
     Date hireDate 입사 날짜

     추가(a) 목록(s) 검색(f) 수정(u) 삭제(d) 종료(x)

*/
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/*
    날짜 다루기
    java.utill.Date hiredate= new java.utill.Date();

        String sDate= "2020-10-21";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date hiredate =sdf.parse(sDate);  //문자열로 표현된 날짜를 Date 오브젝트 형식으로
            sDate=sdf.format(hiredate);       //Date 오브젝트를 문자욜로 변환

        }catch (ParseException e){
            e.printStackTrace();
        }

*/

public class ListCRUD {

    static Scanner kbd = new Scanner(System.in);

    static List<Employee> employees= new ArrayList<>();



/*
    static
    {
        employees.add(new Employee(1,"1",1,1000,"2020-12-24"));
    }
*/

     public  static  void  main(String[] args){                 //메인 메소드

         System.out.println("프로그램 실행");
         ESystem();
         System.out.println("프로그램 종료");
     }                                                         //메인 메소드 끝


    private  static void ESystem()
    {  // ESystem  사원 프로그램 메소드
        //0711
        Set<Employee> Test =new HashSet<>(); //Hash코드가 쓰임 그럼 오버라이드 필요
        Employee emp1= new Employee(11);
        Employee emp2 =new Employee(11);

        Test.add(emp1);
        Test.add(emp2); //set은 equals 비교 hash비교 2번 한다는 것.
        // 비교하면 같은 번호인데 set왜 중복 허용을 하고 있는가.
        System.out.println("원소의 개수:"+Test.size()); // ??? 중복 허용? set인데?
        // 해결 방법 해쉬코드도 오버라이드  2개에서 1개로 바뀜 set 특성 성립됨 번호로

        while (true)
        {
            System.out.print("등록(a) 목록(s) 검색(f) 수정(u) 삭제(d) 종료(x):");
            String menu = kbd.nextLine();

            if      (menu.equals("a")) inputMember() ;     //사원 등록
            else if (menu.equals("s")) memberList()  ;     //사원 목록 검색
            else if (menu.equals("f")) memberSearch();     //사원 번호->사원 검색
            else if (menu.equals("u")) memberUpdate();     //사원 번호->부서 번호,급여 수정
            else if (menu.equals("d")) deleteMember();   //삭제할 사원
            else if (menu.equals("x"))  break;

        }


    }
   private  static  void memberList()     //등록된 사원 목록 불러오기
   {

        if(!employees.isEmpty())
        {
            System.out.println("사원 리스트");
            for(int i=0;i<employees.size();i++)
            {
                System.out.println(employees.get(i));
            }

        }
        else System.out.println("등록된 사원이 없습니다 ");
   } // 목록보기 메소드 끝
   private  static  void inputMember()    //사원 등록하기
   {
       System.out.println("사원등록");
       while (true)
       {
           System.out.print("사번 이름 부서번호 급여 입사날짜:");
           String input =kbd.nextLine().trim();
           if(input.equals("")) break;                      // 아무런 값도 입력하지 않을때.
           String[] token =input.split("\\s+");
           if(token.length !=5){
               System.err.println("입력 항목이 부족합니다.");
               return;
           }
           Employee employee = new Employee(token);
           employees.add(employee);

       }


   }  // 사원 등록 메소드 끝
   private static   void memberSearch()   //사원 번호로 검색하기
   {
       System.out.println("사원 검색");
       System.out.printf("사원 번호를 입력해주세요:");
       int number= kbd.nextInt(); kbd.nextLine();
       Employee key= new Employee(number); //number만 받는 생성자

       if(employees.contains(key)){ // 검색한 사원 번호가 있을때
           int idx=employees.indexOf(key); // index 위치 가져오기
           System.out.println(employees.get(idx));
       }

   }
   private static  void memberUpdate()    //사원 정보 수정 (급여)
   {
       System.out.println("사원 수정");
       System.out.print("수정할 사원 번호를 입력해주세요:");
       int number=kbd.nextInt(); kbd.nextLine();
       System.out.printf("%d번 사원의 수정할 부서:",number);
       int newDeptNumber= kbd.nextInt(); kbd.nextLine();

       System.out.printf("%d번 사원의 수정할 급여:",number);
       int newSal= kbd.nextInt(); kbd.nextLine();

       Employee key=new Employee(number,newDeptNumber,newSal);

       if(employees.contains(key)) // 입력받은 번호가 존재하는지
       {
           int idx=employees.indexOf(key);
           employees.get(idx).setDeptNumber(key.getDeptNumber());
           employees.get(idx).setSal(key.getSal());
           System.out.printf("%d번 사원의 급여 변경 완료 %n",number);
       }else System.out.println("존재하지 않은 사원 번호");


   }
   private static  void deleteMember()    //사원 삭제하기
   {
       System.out.println("사원 삭제");
       System.out.print("삭제할 사원 번호를 입력헤주세요:");
       int number= kbd.nextInt(); kbd.nextLine();
       Employee key= new Employee(number);
       if(employees.contains(key)){
           int idx=employees.indexOf(key);
           employees.remove(key);
           System.out.printf("%d번 사원이 삭제 되었습니다.\n",number);
           System.out.println("변경된 사원 목록");
           memberList();
       }

   }
}     //  ListCRUD 클래스 끝



class  Employee {              //사원 클래스

    private int empNumber;    //사번
    private String empName;   //사번 이름
    private int deptNumber;   //부서번호
    private int sal;          //급여
    private Date hireDate;    //입사 날짜

    // 생성자 -------------------------------------------------//

    public Employee(String[] token) {
        // 키보드 입력받은거 대입
        int empNumber = Integer.parseInt(token[0]);
        String empName = token[1];
        int deptNumber = Integer.parseInt(token[2]);
        int sal =Integer.parseInt(token[3]);
        String hireDate=token[4];

        //Setter
        setEmpNumber(empNumber);
        setEmpName(empName);
        setDeptNumber(deptNumber);
        setSal(sal);
        setHireDate(hireDate);

    }

    public  Employee( int number){   // 사원 검색 ,삭제
        setEmpNumber(number);
    }

    public  Employee(int number,int deptNumber,int sal, String shirDate){
        //여기서  마지막 값 String ->Date
    }
    public  Employee(int number,int deptNumber,int sal) // 사원 번호 받아서 부서,급여 수정
    {
        setEmpNumber(number);
        setSal(sal);
        setDeptNumber(deptNumber);
    }
    //Override   오버라이드 -----------------------------------//
    @Override
    public String toString() {

        String str =String.format("사번:%d\t이름:%s\t부서번호:%s\t급여:%s\t입사 날짜:%s",
                                    this.getEmpNumber(),
                                    this.getEmpName(),
                                    this.getDeptNumber(),
                                    this.getSal(),
                                    this.getHireDate());

        return str;
    }

    @Override
    public int hashCode() {  //해쉬코드 객체가 저장된 메모리 위치
       return Objects.hashCode(this.getEmpNumber());
    }
    // equals로 같다고 리턴된 값은 헤쉬코드 값도 같아야 한다  해쉬코드도 오버라이드 해야함
    // 비교대상이 같으면 해쉬코드도 같아야한다
    // equals로 한거는 해쉬코드도 오버라이드 하라는거임 set Test 실습 .
    @Override
    public boolean equals(Object obj) {   // 문자열 비교 오버라이드
        Employee other =(Employee)obj;
        return this.getEmpNumber()==other.getEmpNumber();
    }

    //Getter-----------------------------------------Setter//


    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(int empNumber) {
        this.empNumber = empNumber;
    }

    public int getDeptNumber() {
        return deptNumber;
    }

    public void setDeptNumber(int deptNumber) {
        this.deptNumber = deptNumber;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public Date getHireDate() {

        return hireDate;
    }

    public void setHireDate(String hireDate) {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date a =sdf.parse(hireDate); //문자열로 표현된 날짜를 Date 오브젝트 형식으로

           this.hireDate=a;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
