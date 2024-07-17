package textio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import  java.util.Date;

public class BoardVo {
    
    private int no;
    private String title;
    private String author;
    private Date regDate;
    private int hits;
    private String contents;

    public BoardVo(String line)  {
        String[] str =line.split("\\|");
        setNo(Integer.parseInt(str[0]));
        setTitle(str[1]);
        setAuthor(str[2]);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date =sdf.parse(str[3]);
            setRegDate(date);
        }catch (Exception e){

        }
        setHits(Integer.parseInt(str[4]));
        setContents(str[5]);
    }
    public  BoardVo(int number){
        setNo(number);
    }
    public  BoardVo() {};
    // 생성자가 한개도 없으면 자동을 기본 생성자를 만들어줌  클래스에는 반드시 생성자가 있어야 함


    @Override
    public boolean equals(Object obj) {
        BoardVo other =(BoardVo)obj;

        return this.getNo()==other.getNo();
    }

    public  BoardVo(int number,String title,String content){
        setNo(number);
        setTitle(title);
        setContents(content);
    }
    @Override
    public String toString() {

        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        String sdate= sdf.format(regDate);

        String s=String.format("번호:%d\t제목:%-22s\t작성자:%s\t작성일:%s\t조회:%d\t내용:%s",
                                no,title,author,sdate,hits,contents);
        return s;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits =hits;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
