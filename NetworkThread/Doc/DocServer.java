package Doc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DocServer {

    /* 1.DocServer
          + "서버 대기중"
          + 무한대기 accept()
          + "클라이언트 접속"
          + userWorkThread.start(); 작업을 수행해줄 쓰레드
       2.DocClient
          + 서버에 접속
          + 클라이언트 종료

       3.메뉴:업로드 a 목록 s 검색 f 수정 u 삭제 d 종료 x
          +업로드(a)
            -파일명: sample.jpg
            -파일을 메모리에 로드(byte[]) ,파일명(fname) ,--> ChatMsg
            -서버에 전송 (upload ture,fname="sample.txt",fdata=fileData)
            -서버쪽에서 병행처리해줘야함

      4.파일 업로드 성공시마다 속성들을 파일에 저장한다
        +번호, 파일명, 작성자, 파일크기, 날짜, 설먕
        +FileInfo 클래스
        +List<FileInfo> 구조로 파일에 저장(직렬화)
        +파일명: list_fileinfo.ser
      5.if(cm.upload)
        { //파일 수신/ 서비스시스템에 저장 }
        else if(cm.list)
            list_fileinfo.ser을 로드하여 fileList 뱐수에 할당

      6. 서버에 파일을 저장하는 절차
         +클라이언트가 전송한 파일을 서버측 파일 시스템에 저장한다
         -FileOutputStream fout =new FileOutputStream("C:/+cm.fname")
         -fout.write(cm.fdata)
         -fout.close
         +서버에 저장한 파일의 정보를 FileInfo 클래스의 인스턴스에 저장한다
         -FileInfo fi= new FileInfo()l
         -fi.setFname(cm.fname)
         .
         .
         .
         +직렬화된 List<FileInfo> 데이터를 역직렬화해서 List<FileInfo>의 참조를 구한다
         -FileIo fio=new FileIO();
         +List<FileInfo>의 참조를 사용하여 새로 추가괸 파일의 정보를 리스트에 저장한다
         -list.add(fi)
         +List<FileInfo>가 직렬화되어 저장되는 파일의 이름은 list_fileInfo.ser로한다
         +내용이 변경된 list를 다시 파일에 직렬화하여 저장한다
         -FileIo fio =new FileIO();
         -fio.serialize(list)
         +클라이언트에서 업로드 성공 메세지를 출력
         -ChatMsg cm =new Chet();
         cm.msg="업로드 성공"
         -oos.writeObject(cm)
    */


    public  static  void main(String[] args){

        try {
            ServerSocket ss =new ServerSocket(1234);

            while (true)
            {
                System.out.println("서버 대기중");
                Socket s= ss.accept();
                System.out.println("클라이언트 접속");
                new UserWorkThread(s).start();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
