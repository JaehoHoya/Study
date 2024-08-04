package PDS;

import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class PDSClient {

    static Scanner kbd= new Scanner(System.in);

    public  static  void main(String[] args)
    {

        try
        {


            Socket s = new Socket("localhost",1234);
            InputStream in=s.getInputStream();
            ObjectInputStream oin= new ObjectInputStream(in);


            OutputStream out=s.getOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(out);



            while (true)
            {

                PDSResponse resMenu=(PDSResponse) oin.readObject();
                System.out.println(resMenu.Menu);
                String m= kbd.nextLine();

                if(m.equals("u"))
                {
                    System.out.println("파일 업로드시 입력사항을 작성해주세요.");
                    System.out.print("파일명:");
                    String fname=kbd.nextLine();
                    System.out.print("작성자:");
                    String author=kbd.nextLine();
                    System.out.print("설명:");
                    String desc=kbd.nextLine();

                    byte[] fdata =new PDSFileIO().load(fname);
                    PDSRequest req= new PDSRequest();
                    if(fdata !=null)
                    {
                        req.fname=fname;
                        req.fdata=fdata;
                    }
                    else
                    {
                        System.out.println("파일을 입력하지 않았거나, 파일이 없습니다.\n 다시 작성해주세요");
                        continue;

                    }
                    req.author=author;
                    req.desc=desc;
                    req.upload=true;
                    oos.writeObject(req);
                    oos.flush();

                    PDSResponse res=(PDSResponse) oin.readObject();
                    System.out.printf(res.response);
                }
                else if(m.equals("s"))
                {
                    PDSRequest req=new PDSRequest();
                    req.showFiles=true;
                    oos.writeObject(req);

                    PDSResponse res=(PDSResponse) oin.readObject();
                    List<PDSVO> list= res.list;
                    for(int i= 0; i<list.size(); i++)
                    {
                        System.out.printf("%d번	파일명:%-10s \n",list.get(i).getNo(),list.get(i).getFname());
                    }

                }
                else if(m.equals("i"))
                {
                    PDSRequest req=new PDSRequest();
                    req.viewDetails=true;
                    oos.writeObject(req);

                    PDSResponse res=(PDSResponse) oin.readObject();
                    List<PDSVO> list= res.list;
                    for (PDSVO l :list){
                        System.out.println(l);
                    }
                }
                else if(m.equals("f"))
                {
                    System.out.print("검색할 파일명을 입력해주세요 ");
                    String fname =kbd.nextLine().trim();
                    PDSRequest req = new PDSRequest();
                    req.search=true;
                    req.fname=fname;
                    oos.writeObject(req);
                    oos.flush();

                    PDSResponse res =(PDSResponse)oin.readObject() ;
                    List<PDSVO> data =res.list;

                    if(data !=null)System.out.println(data.get(0));
                    else System.out.println(res.response);

                }
                else if(m.equals("m"))
                {
                    System.out.print("수정할 파일 이름:");
                    String fname =kbd.nextLine();
                    System.out.print("수정 내용 입력:");
                    String desc= kbd.nextLine();
                    PDSRequest req= new PDSRequest();
                    req.fname=fname;
                    req.desc=desc;
                    req.update=true;
                    oos.writeObject(req);

                    PDSResponse res=(PDSResponse)oin.readObject();
                    System.out.println(res.response);

                }
                else if(m.equals("d"))
                {
                    System.out.print("삭제할 파일 번호:");
                    int no= kbd.nextInt(); kbd.nextLine();

                    PDSRequest req= new PDSRequest();
                    req.no=no;
                    req.delete= true;
                    oos.writeObject(req);
                    oos.flush();


                    PDSResponse res=(PDSResponse) oin.readObject();
                    System.out.println(res.response);
                }
                else if(m.equals("x"))
                {
                    System.out.println("종료");

                }
            }


        }
        catch (Exception e)
        {
           e.printStackTrace();
        }


    }


}
