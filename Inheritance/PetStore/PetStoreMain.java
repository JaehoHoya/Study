package inheritance.PetStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetStoreMain {

    /*
        pet Store
        Cat, Dog, Snake, Hamster :Pet
                                  Pet 속성 : float age, int price, String species:품종

        Cat(String Pattern,     float age,   int price, String species:품종)
        Dog(float   weight,     float age,   int price, String species:품종)
        Snake(String Patter, float size  --- )
        Hamster(             float size,     int price, String species:품종)


        pet 관리 시스템
        추가(a) cat(c) dog(d) snake(s) hamster(h)
        목록보기(s) 품종 가격 나이 무늬 체중 길이

    */

    public static  void main(String[] args){
        System.out.println("동물 입양 보호소");
        List<Pet>  store = new ArrayList<>();

        Cat c= new Cat(450000,"잡종", 4.5F,"똥무늬",4);

        Dog d= new Dog(500000,"푸들",8,8,7);
        Snake s= new Snake(250000,"잡종",5,"비단",6);

        store.add(c);  //store형으로 형변환 발생  (up casting): 부모 클래스에 선언된 변수/ 메소드만 사용가능
        store.add(d);
        store.add(s);

        Scanner kbd= new Scanner(System.in);

        while(true){
            System.out.print("추가(a) 목록 (s) 종료(x)");
            String menu = kbd.nextLine();

            if(menu.equals("a")){
                System.out.println("cat(c) dog(d) snake(s)");
                menu = kbd.nextLine();
                if(menu.equals("c")){
                    System.out.print("품종 사이즈 입양비 줄무늬 나이 ");
                    String a = kbd.next().trim();
                    String b = kbd.next().trim();
                    String e = kbd.next().trim();
                    String f = kbd.next().trim();
                    String g = kbd.next().trim();
                    kbd.nextLine();
                    
                }
                else if(menu.equals("d")){

                }
                else if(menu.equals("s")){

                }

            }
            if(menu.equals("s")){
                System.out.println("반려동물\t\t품종\t\t\t사이즈\t\t\t입양비\t\t\t줄무늬\t\t\t무게\t\t\t나이");
                for(int i=0;i<store.size();i++){
                    System.out.println(store.get(i));
                }
            }
            if(menu.equals("x")){
                break;
            }
        }




    }
}
