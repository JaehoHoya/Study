import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UserIO {

    static Scanner kbd = new Scanner(System.in);
    static String menu = "추가(a) 목록(s) 검색(f) 수정(u) 삭제(d) 종료(x):";

    static String  showMenu() {

        System.out.println();
        System.out.print(menu);
        String m= kbd.nextLine().trim();
        return m;
    }



    public static void add()
    {
        System.out.print("번호:");
        int number=kbd.nextInt(); kbd.nextLine();
        System.out.print("종:");
        String species=kbd.nextLine();
        System.out.print("무게:");
        int weight =kbd.nextInt(); kbd.nextLine();
        System.out.print("가격");
        int price=kbd.nextInt(); kbd.nextLine();

        PetVO pet = new PetVO();
        pet.setNumber(number);
        pet.setSpecies(species);
        pet.setWeight(weight);
        pet.setPrice(price);

        //boolean save =FileIO.savePet(pet);
        boolean added=FileIO.addObject(pet);  // 객체 직렬화 버전
        if(added) System.out.println("\t\t저장완료");
    }
    public  static void list()
    {
        //List<PetVO> list= FileIO.list();
        List<PetVO> list= FileIO.getList();
        Collections.sort(list);
        for(int i=0; i<list.size();i++) {
            System.out.println(list.get(i));
        }
    }
    public  static void find()
    {
        System.out.print("검색할 번호:");
        int no = kbd.nextInt();     kbd.nextLine();
        PetVO p =FileIO.findByNo(no);

        System.out.println(p);
    }
    public static void update()
    {
        System.out.print("검색할 번호:");
        int no = kbd.nextInt();     kbd.nextLine();

        System.out.print("무게 변경:");
        int weight =kbd.nextInt(); kbd.nextLine();
        System.out.println("가격 변경:");
        int price =kbd.nextInt();kbd.nextLine();
        PetVO p= new PetVO(no,weight,price);
        boolean update =FileIO.update(p);

        if(update) System.out.println("업데이트 성공");
        else System.out.println("업데이트 실패");
    }
    public static void delete()
    {
        System.out.print("삭제할 번호:");
        int number=kbd.nextInt(); kbd.nextLine();

        boolean deleted = FileIO.delete(number);
        if(deleted) System.out.println("삭제 성공");
        else System.err.println("삭제 실패");
    }

}
