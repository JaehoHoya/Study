package textio;
import java.util.Scanner;
import java.util.Date;
import java.util.List;
public class UserIo {
    // new 로 호출할 필요 없음 
    static Scanner kbd = new Scanner(System.in);
    static String menu = "추가(a) 목록(s) 검색(f) 수정(u) 삭제(d) 종료(x)";

    static String showMenu(){
        
        System.out.print(menu);
        String m =kbd.nextLine().trim();
        return m; 
    }
    public static void add(){   // 입력 -> 파일에 저장 -> 성공 실패
        System.out.print("글번호:");
        int no= kbd.nextInt(); kbd.nextLine();
        System.out.print("글제목:");
        String title =kbd.nextLine();

        System.out.print("작성자:");
        String author =kbd.nextLine();

        Date regDate =new Date();

        System.out.print("글내용:");
        String contents =kbd.nextLine();

        //vo에 담아서 파일에 저장하도록 한다.(Vo,dataIo)

        BoardVo board =new BoardVo();
        board.setNo(no);
        board.setTitle(title);
        board.setAuthor(author);
        board.setContents(contents);
        board.setRegDate(regDate);
        board.setHits(0);

        boolean saved= DataIo.saveBoard(board);

        if(saved) System.out.println("\t\t게시글 작성성공");
        else System.err.println("\t\t게시글 작성실패");
    }

    public static void list() {

        List<BoardVo> list =DataIo.list();
        if(!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }else System.out.println("\t\t게시물이 존재하지 않습니다.");
    }

    public  static  void search(){

        System.out.print("검색할 게시물 번호를 입력해주세요:");
        int no = kbd.nextInt(); kbd.nextLine();
        List<BoardVo> list =DataIo.list();  // BoardVo =DataIo.findbyno(no)
        BoardVo key = new BoardVo(no);
        if(list.contains(key)){    // 이거를 메소드 만든걸 findbyno에 만들기
            int idx=list.indexOf(key);
            System.out.println(list.get(idx));
        }else {
            System.out.println("존재하는 번호가 아닙니다.");
        }

    }

    public static  void update(){
        System.out.println("게시물 번호로 수정할거면(a) ,제목으로 수정할거면(b)");
        String menu =kbd.nextLine();
        if(menu.equals("a")) {
            System.out.print("수정할 게시물의 번호를 입력해주세요:");
            int no = kbd.nextInt();
            kbd.nextLine();
            List<BoardVo> list = DataIo.list();

            System.out.print("제목 수정:");
            String title = kbd.nextLine();

            System.out.print("내용 수정");
            String content = kbd.nextLine();

            BoardVo key = new BoardVo(no, title, content);

            if (list.contains(key)) {
                int idx = list.indexOf(key);
                list.get(idx).setTitle(key.getTitle());
                list.get(idx).setContents(key.getContents());
                DataIo.updateDelete(list);
            } else System.out.println("\t\t존재하지 않는 게시물입니다.");


        } else if (menu.equals("b")) {
            System.out.print("수정할 게시물의 제목을 입력해주세요:");
            String title =kbd.nextLine();
            System.out.print("수정할 제목을 입려:");
            String newTitle=kbd.nextLine();

            List<BoardVo> list = DataIo.list();
            BoardVo key = new BoardVo(title);

            if(list.contains(key)){


            }

        }
    }

    public static  void delete(){

        System.out.print("삭제할 게시물의 번호를 입력해주세요:");
        int no=kbd.nextInt(); kbd.nextLine();
        List<BoardVo> list =DataIo.list();
        BoardVo key = new BoardVo(no);
        if(list.contains(key)){
            int idx= list.indexOf(key);
            list.remove(idx);

            DataIo.updateDelete(list);
        }else System.out.println("\t\t존재하지 않는 게시물 번호입니다");

    }
}