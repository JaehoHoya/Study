package textio;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataIo {

    static String fpath= "/Users/jeongjaeho/Desktop/풀스택 교육과정 /0715/textio/board.txt";

    public static boolean saveBoard(BoardVo b){
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(fpath, true)); //이어쓰기
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(b.getRegDate());
            String line = String.format("%d|%s|%s|%s|%d|%s",
                    b.getNo(),b.getTitle(),b.getAuthor(),sDate,b.getHits(),b.getContents());
            pw.println(line);
            pw.close();
            return  true;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return  false;
    }

    public  static List<BoardVo> list(){

        try {
            FileReader fr= new FileReader(fpath);
            BufferedReader br= new BufferedReader(fr);
            String line =null;
            List<BoardVo> list = new ArrayList<>();
            while ((line=br.readLine())!=null){
                BoardVo b= new BoardVo(line);
                list.add(b);
            }
            br.close();
            return list;

        }catch (Exception e){
            e.printStackTrace();
        }



       return null;

    }

    public static void updateDelete(List<BoardVo> list ){



        try {
             PrintWriter out = new PrintWriter(new FileWriter(fpath,false));

            for (int i = 0; i <list.size(); i++) {
                BoardVo  b =list.get(i);
                SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
                String sDate = sdf.format(b.getRegDate());
                out.printf("%d|%s|%s|%s|%d|%s%n",b.getNo(),b.getTitle(),b.getAuthor(),sDate,b.getHits(),b.getContents() );
                out.flush();
            }
            out.close();
            System.out.println("\t\t게시물 변경완료");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

/*
    public  static BoardVo findbyno(int no){
        List<BoardVo> list =list();
        BoardVo key= new BoardVo(no);
        if(list.contains(key)){
            return  list.get(
        }
    }

 */
  /*
    public  static BoardVo findbyTitle(String title){
        List<BoardVo> list =list();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getTitle().contains(title)){
                return  list.get(i);
            }
        }
        return null;
    }
    
   */
}
