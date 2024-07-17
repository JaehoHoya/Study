import java.util.*;

// 7/9일 화
public class oopMain {

    public  static void main(String[] args){

    Board b= new Board(-1,"게시판","smith"); // 인스턴스 생성(객체 생성) Heap에 로드됨.

    Board b2= new Board(2,"테스트","jaeho"); // new 할때마다 생성됨  = 클래스의 인스턴스

    //b.title="왜 마음데로 바꾸냐";  이렇게 하지 못하게 -> 접근제한자 바꿀것  = 개터로 읽기만 가능하게?,세터

    Board[] barr;
    barr= new Board[2];
    barr[0]=b;
    barr[1]=b2;

    for(int i=0; i<barr.length;i++){
        System.out.printf("%d,%s,%s %n",
                barr[i].getNum(),
                barr[i].getTitle(),
                barr[i].getAuthor()
                          );
    }
    }

}

class  Board{   //  Inheritance, Polymorphism, Encapsulation
    //static이 아니라 메모리에 로드할 수 없음  로드 명령 new
    //번호, 제목, 작성자, 본문, 작성일, 히트수
    private int num; // Access Modifier (public , protected , private, default)
    private String title;
    private String author;
    private String contents;
    private Date Date;
    private int hits;

    //생성된 인스턴스 맴법 변수를 초기화하는 생성자
    //인스턴스가 생성된 직후에 자동으로 호출됨
    public  Board(int num,String title, String author){  // 기본 생성자(constructor)
        /* set메서드 전
        this.num=num;
        this.title=title;
        this.author=author; //초기화
         */
        setNum(num);
        setTitle(title);
        setAuthor(author);
    }
    public Board(){}





    // 데이터 회손은 못하지만 읽게는 해주겠다 get
    // 데이타 필터링 할때 쓰는 검증  set
    // 은닉성을 위헤
    public int getHits() {
        return hits;
    }

    public int getNum() {
        return num;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContents() {
        return contents;
    }

    public java.util.Date getDate() {
        return Date;
    }








    public void setNum(int num) {    //set메서드는 데이터가 나오는게 없음 들어가는 메서드는 이기에 규칙?
        if(num<=0){
            System.err.println("num 초기화 실패 ..글번호는 0보다 커야합니다.");
            return;
        }
        this.num = num;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // default  다른 패키지에서는 사용할 수 없음  같은 패키지에서는 상관 x
    // protected default랑 같지만 다른 패케지에 속한 클래스가 해당 클래스의 자식 클래스면 호출 가능
    // private  클래스 내부만
    // public  다른 패케지 접근 가능

    // static(정적) 주로 클래스들이 할당  모든 객체가 메모리를 공유  공용
    //자바 클래스에는 항상 생성자가 있어야함 개발자가 생성자를 정의하지 않으면 기본 생성자를 만들어줌 Board(){}

    //객체지향 언어에서는 생성자를 클래스안에 다수개 정의할수 있음
}
