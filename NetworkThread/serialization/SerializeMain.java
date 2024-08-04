package serialization;

import java.io.*;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializeMain {


        public  static  void  main(String[] args){
            // 문서 관리 시스템
            // 문서를 업로드하여 다른 이용자가 목록을 보고 골라서 파일을 다운로드 할 수 있다
            // 업로드 검색 다운로드
            // 소캣 서버 소캣 직렬화
            // 한 문서에 포함되는 속성: 번호 제목 파일명 날짜 작성자 파일 크기
            // List<Document>
            // 디렉토리에 포함된 파일의 리스트 추출하는 방법
            // listForFiles();
            // searchFile();
            // listSerialization();
            // deserialization();
            //updateList();
            update02();
        }

        private  static  void  listForFiles(){
            String path="/Users/jeongjaeho/Desktop";
            //여기 안에있는 모든파일의 이름을 가져오기
            File f =new File(path);
            //String[] files= f.list();
            File[] files =f.listFiles();

            for(int i=0;i<files.length;i++){
                if(files[i].isDirectory()){
                    System.out.println(files[i].getName()+"->Dir");
                }else {
                    System.out.println(files[i].getName()+"->File");
                }
            }
        }
        private  static  void searchFile(){
            String key="011";
            String path="/Users/jeongjaeho/Desktop";

            File f= new File(path);
            File[] files =f.listFiles();
            for(int i=0; i<files.length;i++){
                if(files[i].getName().equals(key)){
                    System.out.printf("%s\t %d",files[i].getName(),files[i].length());
                }
            }


        }

        private  static void listSerialization(){

            //리스트 직렬화
            List<String> names= List.of("강호동","정복래","이수근","홍길동");
            //List.of() 메소드로 생성된 리스트는 변경할 수 없는 (immutable) 리스트입니다.
            //따라서 set 메소드로 요소를 변경할 수 없습니다. 이는 해당 리스트가 변경 불가능하다는 의미입니다.
            //set 안먹힘
            for(int i=0;i<names.size();i++){
                System.out.println(names.get(i));
            }
            try {
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("/Users/jeongjaeho/Desktop/0722/serialization/list_names.ser"));
                oos.writeObject(names);
                //oos.flush(); 안해도됨
                oos.close();
                System.out.println("리스트 직렬화 파일에 저장 ");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("파일에 저장 실패");
            }


        }

        private  static void deserialization(){

            String path ="/Users/jeongjaeho/Desktop/0722/serialization/list_names.ser";

            try{
                ObjectInputStream ois =new ObjectInputStream(new FileInputStream(path));
                List<String> list =(List<String>)ois.readObject();
                ois.close();


                System.out.println("\t역직렬화 후의 리스트 내용보기");
                for(int i=0;i<list.size();i++){
                    System.out.println(list.get(i));
                }

            }catch (Exception e){
                System.err.println("파일 읽기 실패");
                e.printStackTrace();
            }


        }

        private  static void updateList(){

            // 강호동 --> 곽두팔로 변경하여 파일에 저장하고 다시 역직렬화하여 변경된 내용확인

            String path ="/Users/jeongjaeho/Desktop/0722/serialization/list_names.ser";

            List<String> names= new ArrayList<>();
            names.add("강호동");
            names.add("정복래");
            names.add("이수근");
            names.add("홍길동");
            System.out.println("\t초기 리스트 내용보기");
            for(String name:names){
                System.out.println(name);
            }


            try{
                names.set(0,"곽두팔");

                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(path));
                oos.writeObject(names);
                oos.close();

                System.out.println("\t업데이트된 리스트 내용보기");
                for(int i=0;i<names.size();i++){
                    System.out.println(names.get(i));
                }

            }catch (Exception e){
                System.err.println("실패");
                e.printStackTrace();
            }

        }

        private  static  void update02(){


            List<Emp> list= new ArrayList<>();
            Emp u1 = new Emp(11,"유재석",33,5500);
            Emp u2 = new Emp(12,"홍길동",35,2500);
            Emp u3 = new Emp(13,"곽두팔",32,3500);

            list.add(u1);
            list.add(u2);
            list.add(u3);

            //급여 변경
            Emp key =new Emp(13);
            if (list.contains(key)){
                int idx=list.indexOf(key);
                Emp found=list.get(idx);
                found.setSal(found.getSal()+200);
            }

            // 곽두팔의 부서를 50번으로 바꾸기
            Emp key2= new Emp(13);
            if(list.contains(key2)){
                int idx =list.indexOf(key2);
                Emp find=list.get(idx);
                find.setDeptno(50);
            }

            //파일에 직렬화
            try {

                ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream("/Users/jeongjaeho/Desktop/0722/serialization/empList.text"));
                oos.writeObject(list);

            }catch (Exception e){
                e.printStackTrace();
            }
            //파일삭제

            File f= new File("/Users/jeongjaeho/Desktop/0722/serialization/aaa");
            if(f.exists()){
                boolean deleted =f.delete();
                if(deleted){
                    System.out.println("삭제완료");
                }
            
        }


    }

}


