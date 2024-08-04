package program;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//0711
public class StreamMain {


    public static void main(String[] args) {
        /*
          java의 Stream API 다루기
          Stream: 데이터의 흐름
          System.out.printLn(name) : 메모리에 있는 데이터를 모니터에 표시
          new Scanner(System.in)   : 키보드에서 들어오는 데이터를 메모리에 복사


          문자 데이터(텍스트): Character stream, 바이너리 데이터(비문자: 사운드,파일...- 글자로 변환 x):Byte Stream

          문자데이터를 다루는 스트림: Reader,Writer
          바이너리 데이터를 다루는 스트림: InputStream ,OutputStream ->문자가 아닌 데이터를 다루는 스트림


          스트림은 파이프처럼 연결하여 사용할 수 있다
          :Node Stream : 데이터가 흐르기 시작하는 위치, 데이터가 흐름을 마치는 끝에 위치하는 스트림
           Filter Stream :Node의 위치에 올 수 없다 (중간?)


          입력스트림 , 출력 스트림
          입력스트림(Reader,InputSteam) : 데이터를 가져오는 스트림
          출력스트림(Writer,OutputSteam): 데이터를 특정 장소로 보내는 스트림


        */
        //TextInput();
        //TextInput2();
        //TextInput3();
        //TextInput4();
        //TextInput5();
        //Member();
        //textWrite();
        //textUpdate();
        remove();
    }

    /* 텍스트파일을 읽어서 그 내용을 화면에 표시한다. (입력스트림을 이용해서 출력스트림을)
       1.텍스트 파일(File)을 읽을때는(Reader) FileReader -> text data
       2.화면에 표시 :System.out.println(data)
     */
    private static void TextInput() {
        try {
            FileReader fr = new FileReader("/Users/jeongjaeho/Desktop/풀스택 교육과정 /0711/SetMap/src/Collection/test.txt"); //Source Stream
            int ch = fr.read(); //1.파이프를 통해서 읽어와?  // 글자한개를 리턴해주는 오류 ->예외처리  글자 한개 일때
            //2
            System.out.println("읽어온 데이터:" + (char) ch); //가공
            fr.close(); //닫기

        } catch (Exception e) {  //FileNotFoundException 에서 ->부모로 바꿔서 fr.read try.catch 문 생략
            e.printStackTrace();
        }
        //read라는 메서드는 Reads a single character 한글자? 읽어올수 없는 환경에는 -1 로 표현
        //The character read, or -1 if the end of the stream has been reached
        // 파일의 끝에 도달하면 -1
        //출력문
        /*
         *   읽어온 데이터:p
         */
    }

    private static void TextInput2() {

        try {
            FileReader fr = new FileReader("/Users/jeongjaeho/Desktop/풀스택 교육과정 /0711/SetMap/src/Collection/test.txt"); //Source Stream

            char[] cbuf = new char[12];
            int cnt = fr.read(cbuf); //1.파이프를 통해서 읽어와?  // 글자한개를 리턴해주는 오류 ->예외처리
            String sdata = new String(cbuf);

            //fr. 하면 읽어오는 방법이 소개되어 있음
            //2
            System.out.println("읽어온 데이터:" + sdata + "읽어온 갯수:" + cnt); //가공
            fr.close(); //닫기

        } catch (Exception e) {  //FileNotFoundException 에서 ->부모로 바꿔서 fr.read try.catch 문 생략
            e.printStackTrace();
        }


        //출력문
        /*
         *   읽어온 데이터:[C@30f39991읽어온 갯수:12
         */
    }

    private static void TextInput3() {

        try {
            FileReader fr = new FileReader("/Users/jeongjaeho/Desktop/풀스택 교육과정 /0711/SetMap/src/Collection/test.txt");
            char[] cbuf = new char[12];  // 맨 마지막은 12개에 못미침  마지막에 쓰레기데이터 포함 출력됨>?
            int cnt = 0;
            do {
                cnt = fr.read(cbuf);
                if (cnt != -1) {
                    String sdata = new String(cbuf, 0, cnt); // char[] value,int offset, int count // 이렇게하면 쓰레기 값없이 출력됨
                    //0~cnt 만큼
                    System.out.print(sdata);
                }
            } while (cnt != -1);
            System.out.println("읽기완료");
            fr.close();
            // 파일의 끝에 도달하면 -1


        } catch (Exception e) {
            e.printStackTrace();
        }


        //출력문
        /*
         * 전체 출력됨
         */


    }

    private static void TextInput4() {
        // 한행씩 읽기
        // 한글자씩 읽어서 한행을 만듬 별도의 스트림

        try {
            FileReader fr = new FileReader("/Users/jeongjaeho/Desktop/풀스택 교육과정 /0711/SetMap/src/Collection/test.txt");
            BufferedReader br = new BufferedReader(fr); // 한글자 한글자 읽어서 한행을 만든다?  버퍼링해서 한행을 만든다
            //String line= br.readLine(); // 한행=문자열 string

            // 참조형을 리턴하는거라 다 읽으면(파일 끝0 null이 나옴
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("읽기완료");
            br.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


        //출력문
        /*
         * 전체 출력됨
         */

    }

    //0712
    private static void TextInput5()        // 전체 내용 읽어오기
    {
        // 파일을 읽기전에 크기를 알고  크기만큼의 메모리를 , 전체 내용을 문자열로 표현
        File textFile = new File("/Users/jeongjaeho/Desktop/풀스택 교육과정 /0711/SetMap/src/Collection/test.txt");
        // 파일의 크기를 구한다
        int len = (int) textFile.length();

        try {
            FileInputStream fin = new FileInputStream(textFile);
            byte[] buf = new byte[len]; // 파일 크기 만큼의 메모리 공간 준비
            fin.read(buf);
            String str = new String(buf);
            System.out.println(str);
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     *  members.txt
     *  한 행에 한 회원의 정보가 기록되어 있다
     *  번호|이름|전화|이메일    - 한행에  쪼개서 한행을 맴버 클래스에 저장
     *
     *  String[] token = line.split("\\|");  | 기준으로 쪼개기
     *  List<Member> list;
     *
     *  ---> 텍스트 파일에서 목록을 가져와서 화면에 표시한다.
     * */


    private static void Member() {
        List<Member> members = new ArrayList<>();


        try {
            FileReader fr = new FileReader("/Users/jeongjaeho/Desktop/풀스택 교육과정 /0711/SetMap/src/Collection/members.txt");
            BufferedReader br = new BufferedReader(fr);


            String line = null;
            while ((line = br.readLine()) != null) {

                String[] token = line.split("\\|");
                Member member = new Member(token);
                members.add(member);


            }
            for (int i = 0; i < members.size(); i++) {
                System.out.println(members.get(i));

            }

            System.out.println("읽기완료");
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    // 강사님 :: 메소드화 필수  재사용하기 위헤
    /*

    *   List<Member> list =listFromFile();
    *
    *   private static List<Member> listFormFile()

        File f = new File(".txt");
        try(
              BufferedReader br = new BufferedReader(new FileReader(f));
              String line =null;
              List<Member> members =new ArrayList<>();
              while((line==br.readLine())!=null)
              {
                list.add(new Member(line))
              }
              br.close();
              retrun list;
       catch(Exception e){e.printStackTrace()}
       return null;
    *
    *
    *
    *
    *
    *
    *
    * */

    private static void textWrite() {
        // 메모리에 문자열 --> 퍼알에 기록    변수를 다른곳으로 내보내는(출력 스트림)  메모리에서 디스크로 데이터로 이동   영구적 저장
        // 문자열을 파일(File)에 출력한다(기록)(write)  글을 해석해서 코딩 :FileWrite(노드 스트림)
        //System.out.println();  // printSteam.println()
        try {
            String fname = "/Users/jeongjaeho/Desktop/풀스택 교육과정 /0711/SetMap/src/Collection/write.txt";
            //한 행씩 파일에 적는
            PrintWriter pw = new PrintWriter(new FileWriter(fname, true));   //FileWriter  tre 안하면 덮어쓰기
            pw.println("hello word");
            pw.println("wow java");
            pw.close();
            System.out.println("파일에 한행 쓰기 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void textUpdate() {
        // 텍스트를 로드 -> 메모리에서 수정 > 메모리에서 수정된 메모리에서 수정된 내용을 기존 파일에 덮어쓰기한다
        String fname = "/Users/jeongjaeho/Desktop/풀스택 교육과정 /0711/SetMap/src/Collection/members.txt";
        File f = new File(fname);

        if (!f.exists()) {
            System.err.println("지정된 파일이 없습니다");
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = null;
            List<Member> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {

                String[] token = line.split("\\|");
                Member member = new Member(token);
                list.add(member);


            }
            br.close();  //text load
            //1번 회원의 전화번호 010-3333-7777 변경
            Member key = new Member(1);
            if (list.contains(key)) {
                list.get(list.indexOf(key)).setPhone("010-4500-4222");
            }


            //메모리에서 변경된 데이터를 파일에 덮어쓰기
            PrintWriter out = new PrintWriter((new FileWriter(f, false)));
            for (int i = 0; i < list.size(); i++) {
                Member m = list.get(i);
                out.printf("%d|%s|%s|%s%n", m.getNumber(), m.getName(), m.getPhone(), m.getEmail());
                out.flush();
            }
            out.close();
            System.out.println("전화번호 변경 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private  static  void remove(){

        //지우기

        String fname = "/Users/jeongjaeho/Desktop/풀스택 교육과정 /0711/SetMap/src/Collection/members.txt";
        File f = new File(fname);

        if (!f.exists()) {
            System.err.println("지정된 파일이 없습니다");
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = null;
            List<Member> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {

                String[] token = line.split("\\|");
                Member member = new Member(token);
                list.add(member);


            }
            br.close();  //text load

            Member key = new Member(2);
            if (list.contains(key)) {
                list.remove(list.indexOf(key));
                //ist.remove(key);
            }


            //메모리에서 변경된 데이터를 파일에 덮어쓰기
            PrintWriter out = new PrintWriter((new FileWriter(f, false)));
            for (int i = 0; i < list.size(); i++) {
                Member m = list.get(i);
                out.printf("%d|%s|%s|%s%n", m.getNumber(), m.getName(), m.getPhone(), m.getEmail());
                out.flush();
            }
            out.close();
            System.out.println("삭제");
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}



