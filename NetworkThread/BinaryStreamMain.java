import java.io.*;
import java.util.Scanner;

public class BinaryStreamMain {
//7/18
        static  Scanner kbd = new Scanner(System.in);
    public static void main(String[] args) {
        //Text,None-Text
        /*
         *  character Stream :문자를 다루는 스트림
         *  특징: Reader, Writer 가 붙음
         *
         *  Binary Stream(byte Stream)
         *  특징: Input ,OutputStream 으로 끝남(붙음)
         *      *
         *  변환 스트림
         *  InputStreamReader
         *  OutputStreamWriter
         *  한 바이트 한 바이트 받아서 문자열로 바꿈.
         *  문자열을 받아서 바이트로 변환.
         *
         *  네트웤크 통신
         *  문자 메세지 -1-> 바이트 데이터 -2-> 문자 데이터
         *  1: outputStreamWriter                       *
         *  2: InputSteamReader                         *
         *
         *
         */
        // binaryTest01();
        // binaryTest02();
        // binaryTest03();
        // binaryTest04();
        // binaryTest05();

        //conversionTest01();
        //conversionTest02();
        conversionTest03();
    }

    private static void binaryTest01() {
        // byte- 1 short- 2 int- 4 long -8
        String imgPath = "/Users/jeongjaeho/Downloads/winter.jpeg";
        try {
            FileInputStream fin = new FileInputStream(imgPath);
            while (true) {


                int data = fin.read();  // 이미지 한 바이트 읽기 (1픽셀:3~4바이트)
                if (data == -1) break;
                System.out.print(data + ","); //한 바이트가 나옴 0~255
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void binaryTest02() {
        // byte- 1 short- 2 int- 4 long -8
        String imgPath = "/Users/jeongjaeho/Downloads/winter.jpeg";
        try {
            FileInputStream fin = new FileInputStream(imgPath);
            // 이미지 한 바이트 읽을시 (1픽셀:3~4바이트)
            byte[] imgData = fin.readAllBytes();
            System.out.println("이미지 읽기 완료:" + imgData.length); //한 바이트가 나옴 0~255

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void binaryTest03() {
        // byte- 1 short- 2 int- 4 long -8

        //한번에 읽기
        String imgPath = "/Users/jeongjaeho/Downloads/winter.jpeg";
        try {
            FileInputStream fin = new FileInputStream(imgPath);
            byte[] imgData = fin.readAllBytes(); // 서버에서는 한번에 읽으면 서버 망함 조금씩 읽기 .
            fin.close();

            String imgDest = "/Users/jeongjaeho/Downloads/winter_copy.jpeg";
            FileOutputStream fout = new FileOutputStream(imgDest);
            fout.write(imgData);
            fout.close();

            System.out.println("이미지 파일 복사완료");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void binaryTest04() {
        // byte- 1 short- 2 int- 4 long -8

        // 파일 끊어서 읽기
        String imgPath = "/Users/jeongjaeho/Downloads/winter.jpeg";
        String imgDest = "/Users/jeongjaeho/Downloads/winter_copy2.jpeg";
        try {
            FileInputStream fin = new FileInputStream(imgPath); //입
            FileOutputStream fout = new FileOutputStream(imgDest); //츨
            byte[] buf = new byte[256];
            while (true) {
                int cnt = fin.read(buf);
                if (cnt == -1) break;         // 멘 마지막은 쓰레기 데이터가 있을 수 있기에;
                fout.write(buf, 0, cnt); // 읽어온 만큼만 출력스트림에 보낸다
            }

            fin.close();
            fout.close();

            System.out.println("이미지 파일 복사완료");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void binaryTest05() {
        // byte- 1 short- 2 int- 4 long -8

        // 이미지를 반복하여 읽어서 메모리에 데이터를 누적하는 방법

        String imgPath = "/Users/jeongjaeho/Downloads/winter.jpeg";
        String imgDest = "/Users/jeongjaeho/Downloads/winter_copy3.jpeg";
        try {
            FileInputStream fin = new FileInputStream(imgPath); //입
            FileOutputStream fout = new FileOutputStream(imgDest); //츨
            //ByteArrayInputStream bin =new ByteArrayInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(); // 바이트 배열 출력  : 메모리에 바이트배열을 누적하겠다

            byte[] buf = new byte[256];
            while (true) {
                int cnt = fin.read(buf);
                if (cnt == -1) break;         // 멘 마지막은 쓰레기 데이터가 있을 수 있기에;
                bout.write(buf, 0, cnt); // 파일에서 읽은걸 바이트 배열에 쓴다
            }
            byte[] img = bout.toByteArray();
            fout.write(img);
            bout.close();
            fin.close();
            fout.close();

            System.out.println("이미지 파일 복사완료");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void conversionTest01() {    //변환 스트림
        Scanner kbd = new Scanner(System.in);
        // 키보드에서 문자열을 받아서  파일에 저장할때 문자 스트림이 아닌 byteStream 을 사용 ( 네트워크 )
        try {                   // 문자 스트림 ?? 바이트스트림
            PrintWriter pw = new PrintWriter(new FileOutputStream("/Users/jeongjaeho/Desktop/012/conv.txt"));
            // 반복하여 키보드에서 입력을 받고 그 데이터를 파일에 저장
            //이용자가 그냥 엔터를 치면 입력 완료로 간주하고 그간 누적된 텍스트 파일을 읽어서 화면에 표시
            while (true) {
                System.out.print("하고 싶은말 적어줘:");
                String str = kbd.nextLine().trim();
                if (str.equals("")) break;
                pw.println(str);
            }
            pw.close();

            BufferedReader br = new BufferedReader(new FileReader("/Users/jeongjaeho/Desktop/012/conv.txt"));
            String line = null;
            while (true) {
                line = br.readLine();
                if (line == null) break;
                System.out.println(line);
            }
            br.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void conversionTest02() {
        String fpath = "/Users/jeongjaeho/Desktop/012/ex.txt";
        try {
            //네트워크 프로그램 적용 변환스트림
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fpath));
            BufferedReader br = new BufferedReader(isr);

            // 버퍼드리더는 자동으로 안해줘서 우리가 해줘야 함
            String line = null;
            while (true) {
                line = br.readLine();
                if (line == null) break;
                System.out.println(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void conversionTest03() {
        String fpath ="/Users/jeongjaeho/Desktop/012/conv.txt";
        try {                   // 문자 스트림 ?? 바이트스트림
            OutputStreamWriter osw =new OutputStreamWriter(new FileOutputStream(fpath));

            PrintWriter pw = new PrintWriter(osw);
            // 반복하여 키보드에서 입력을 받고 그 데이터를 파일에 저장
            //이용자가 그냥 엔터를 치면 입력 완료로 간주하고 그간 누적된 텍스트 파일을 읽어서 화면에 표시
            while (true) {
                System.out.print("하고 싶은말 적어줘:");
                String str = kbd.nextLine().trim();
                if (str.equals("")) break;
                pw.println(str);
            }
            pw.close();

        }catch (Exception e){
            e.printStackTrace();
        }




    }
}