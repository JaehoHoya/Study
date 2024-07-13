import java.util.*;

// 0710
/*
 * 키보드에서 회원의 번호 ,이름, 전화, 이메일을 입력받아서
 * Member 인스턴스를 생성하고 배열에 저장한 후에 이용자가 아무것도 입력하지 않고
 * 그냥 엔터키를 누르면 지금까지 입력된 회원 정보를 화면에 목록으로 표시하는 기능
 *
 * 오버로드 오버라이드 차이
 * */
public class Test2 {

    static  Scanner kbd= new Scanner(System.in);
   // static  Member[] mems= new Member[5];

    static  List<Member> mems= new ArrayList<>();

    static  int cnt =0;

    public  static  void main(String[] args){

               // inputMember();
               // memList();
               // 리스트에서 검색하여 번호가 11번인 회원정보를 화면에 표시헤보세요
               // listSearch();
               // listRemove();
               // listUpdate();
                updatePhoneByNo();
                //listTest();
    }
    private  static  void updatePhoneByNo()
    {
        //전화번호 갱신 대상 회원의 번호를 입력받아 새전화 번호로

        System.out.println("회원 등록");
        inputMember();
        System.out.println("회원 목록");
        memList();

        System.out.print("변경대상 회원 번호를 입력하세요:");
        int number = kbd.nextInt(); kbd.nextLine();

        System.out.printf("변경할 전화번호 입력:");
        String newPhone =kbd.nextLine();

        Member key= new Member(number,newPhone);
        if(mems.contains(key))
        {
            int idx=mems.indexOf(key);
            mems.get(idx).setPhone(key.getPhone());
            System.out.println("변경 성공");
            System.out.println("변경된 회원  목록");
            memList();

            
        }else System.out.println("변경실패");
        
    }
    
    
    private  static  void listUpdate()
    {
        inputMember();
        System.out.println("기존 회원 목록");
        memList();
        Member key = new Member(1,"1","010-4588-4553","1");


        if(mems.contains(key)){

            int idx =mems.indexOf(key);

            mems.set(idx,key);

            System.out.println("수정된 회원 목록");
            memList();
        }


    }

    private  static  void listRemove()
    {
        inputMember();
        //특정 번호를 가진 회원 정보를 리스트에서 삭제
        System.out.println("삭제 전");
        memList();
        Member key= new Member(11);
        mems.remove(key);
        System.out.println("삭제 후");
        memList();
    }

    private  static  void  listSearch()
    {
        inputMember();
        // Member key = new Member(1,"","","");
        Member key = new Member(1); // 생성자 하나 더 만들어서 번호만 입력받은 객체로 찾기
        if(mems.contains(key))
        {
            int idx= mems.indexOf(key);
            System.out.println("found"+mems.get(idx));
        }else System.out.println("존재하지 않는 회원 번호 입니다.");


    }




    private  static  void  inputMember()

    {
        while (true)
        {
            System.out.print("번호 이름 전화 이메일:");
            String input = kbd.nextLine().trim();
            if (input.equals("")) break;
            String[] token= input.split("\\s+");
            /*
            int no =Integer.parseInt(token[0]);
            String name=token[1];
            String phone=token[2];
            String email=token[3];
            Member m= new Member(no,name,phone,email);
            mems[cnt++]= m;
            */
            Member m= new Member(token); // 새로운 생성자로
            //mems[cnt++]= m;
            mems.add(m);
        }
    }

// 1 jaeho  01045884553 1010wogh@naver.com
// 2 hoya  01045884553 1010wogh@naver.com
    private  static  void memList()

    {
            for(int i=0;i<mems.size();i++){       //cnt
                System.out.println(mems.get(i));  //mems[i]
                //System.out.println(mems[i]);
                //Member@4f023edb @4f023edb 에 만들어진 맴버 오브젝트
                // println 에 참조를 넣으면 tostring매소드 결과가 나옴  객체를 문자열로 표현됨  -개조한다면?(메소드 오버라이드)
                // 아래와 같이 출력될 수 있게  toString()메소드에 오버라이드가 요구됨  =기능을 개조 덮어씌우다
                /*
                System.out.printf("회원 아이디:%d\t이름:%s\t핸드폰:%s\t이메일:%s \n",
                        mems[i].getNumber(),
                        mems[i].getName(),
                        mems[i].getPhone(),
                        mems[i].getEmail()
                );
                 */
            }
    }
    private  static  void listTest(){

        // CRUD : Create
        //List 사용하기 <>안에 자료형      객체지향 특징
        List<String> sList= new ArrayList<>();  //ArrayList<>()   에서
        sList.add("hello");
        sList.add("hi");   // 순서 유지
        sList.add("hi");   // 중복
        Object obj = "Hello"; //??? string 은 object에서 상속  부모 자식간에는 성립  그래서 오류 없음
        //String str= new Object(); 오류


        // Read
        for(int i=0; i<sList.size();i++){
            String v=sList.get(i);
            System.out.println(v);
        }

        System.out.println(sList.contains("hi"));  //boolean  검색   인덱스 하나하나 돌면서  값을 찾음  equals를 돌림  자료형이 Member에도 equals 있음 왜냐 Object

        // 들어 있는 값의 인덱스 값을 구하기
        int idx= sList.indexOf("hello"); //hello의 방번호  저장
        String value= sList.get(idx);    // 방번호에 있는 값을 저장
        System.out.println(value);


        sList.set(1,"jaeho");                      //update
        System.out.println(Arrays.toString(sList.toArray()));

        sList.remove("hi");                    //delete
        System.out.println(Arrays.toString(sList.toArray()));

    }
}
// 맴버라는 클래스는 자바의 최상의 클래스 Object 를 가져와서 넣는다?  맴버라는 클래스는 오브젝트를 확장해서 만들어진 클래스   기본적인 상속  (상속 )
class Member extends Object{

    private int number;
    private String name;
    private String phone;
    private String email;


    public Member(int number,String name, String phone , String email){

        setNumber(number);
        this.name=name;
        this.phone=phone;
        this.email=email;


    }
        //생성자는 리턴 타입이 있으면 안돼!
    public Member(String[] token) {

        // 객체지향 언어에서 생성자를 여러개 만들 수 있음  생성자 오버로드 개념 파라미터 다르게

        int no =Integer.parseInt(token[0]);
        String name=token[1];
        String phone=token[2];
        String email=token[3];

        setNumber(no);
        setName(name);
        setPhone(phone);
        setEmail(email);

    }

    public  Member(int number)
    {
        setNumber(number);
    }
    public  Member(int number,String newphone ){
        setNumber(number);
        setPhone(newphone);

    }
    
    @Override
    public String toString()
    {

        String str =String.format("회원 아이디:%d\t이름:%s\t전화:%s\t이메일:%s",
                this.getNumber(),
                this.getName(),
                this.getPhone(),
                this.getEmail());
        return str;
    }

    @Override
    public boolean equals(Object obj) {  //Object obj 로 해도 Member가 들어감

        Member other = (Member)obj;  // obj에 있던것을  자식 형을 바꿈


        return this.getNumber()==other.getNumber();
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }


    /* Collection Api

    다수개의 객체를 저장 및 관리하는 자료구조
    List set map
    List: 저장할때의 순서가 유지됨, 동일 객체의 중복 저장 가능
    set: 순서 유지 안됨, 동일 객체의 중복 저장 x
    map: 값만 저장하지 않고 값에 꼬리표(key)를 붙여서 함께 저장 (key ,value 쌍으로 저장 됨)
     */
}