package textio;

/* 프로그램 코드 분리 예 
*  main loop, UserIo, DataIo, Vo(value Object) 
*  
*  main loop: 프로그램 흐름 전체 정리
*  UserIo: 이용자로부터 입력 및 이용자에게 츨력(키보드, 모니터)
*  DataIo: 데이터 파일 입,출력,수정,삭제,검색
*  VO(Value Objcet) : 데이터 모델 
*/

/* 게시판 프로그램 
 * BoardVo(번호, 제목, 작성자, 작성일, 히트수 ,본문)
 *
 * 깃허브 커맨드: MVC패턴 파악.
 */
public class TextIoMain {   
    public static void main(String[] args){
            boolean go =true;
            while (go) {
            String m= UserIo.showMenu();    //메뉴를 보여준다 
            switch (m) {
                case "a":UserIo.add(); break;
                case "s":UserIo.list(); break;
                case "f":UserIo.search(); break;
                case "u":UserIo.update(); break;
                case "d":UserIo.delete(); break;
                case "x": go=false; break;
                
            }
            }
            System.out.println("프로그램 종료");
    }

}