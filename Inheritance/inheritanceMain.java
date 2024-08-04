package inheritance;

import java.util.ArrayList;
import java.util.List;

//0719 오후
public class inheritanceMain  {

    public  static  void main(String[] args){

        // 상속(inheritance)      구현(implements)
        // extends
        /* 왜! 상속을 하는가. -> 쉽게 하위 클래스를 파생하여 사용할 수 있다.
                             클래스간의 관계(Parent/Child)를 설정할 수 있다.
                             Is- A Relationship (A is a B : A는 B이다=같다.) 역은 성립 x
                             OOP 3대 특징  Inheritance , Polymorphism(다형성)- , Encapsulation(은닉성) ( = 이 특징을 잘살려야  객체지향 코딩 )
                             다형성 2가지 방법 : 오버라이드, 오버로드
                             은닉성: private set get 메서드
         */


        //inheritanceTest01();
        //inheritanceTest02();
        //inheritanceTest03();

        List<String> names =List.of("asdasd","sadasda","sadad");
        int idx= findIndex(names,"asdasd");
        System.out.println("검색결과"+idx);

        List<Integer> nums =List.of(1,2,3,4);
       idx= findIndex(nums,1);
        System.out.println("검색결과"+idx);
    }

    static void genericClassExample()
    {
        Storage<Integer> intStorage =new Storage<>();
        intStorage.setItem(123);
        System.out.println("값"+intStorage.getItem());
    }

    static <T> int findIndex(List<T> list, T elem){ // 타입을 지정하지 않았구나
        return  list.indexOf(elem);
    }

    private  static  void inheritanceTest01(){
        Item i =new Item();
        i.setName("메모리");
        i.setMade("엔비디아");
        i.setpDate("2022-12-02");
        i.setPrice(240000);
        System.out.println(i);

    }

    public  static  void inheritanceTest02(){

        Mouse m =new Mouse("G502","로지텍",50000,"2020-12-22","무선");

        System.out.println(m);  // mouse.tostring 호출됨

        Monitor mt=new Monitor("게이밍 모니터","LG",250000,"2024-05-22", 24.5F);
        System.out.println(mt);
    }

    public  static  void inheritanceTest03(){          //다형성
        // 이용자가 마우스,모니터 한꺼번에 구입한 경우
        List<Item> cart= new ArrayList<>();  // 모니터 is a  item
        // Generics (메소드, 클래스)
        Mouse m =new Mouse("G502","로지텍",50000,"2020-12-22","무선");
        Monitor mt=new Monitor("게이밍 모니터","LG",250000,"2024-05-22", 24.5F);
        cart.add(m);
        cart.add(mt); // 업 캐스팅


        for(Item item :cart) // 개선된 for 루프
        {
            if(item instanceof Mouse) {
                Mouse m1 = (Mouse) item;
                System.out.printf("%s \t %s %n", item.name, m1.wirelees);
            }
                else if(item instanceof  Monitor){
                    Monitor moni =(Monitor) item;
                System.out.printf("%s \t %s %n",item.name,moni.inch);
                }
            }



        for(int i=0;i<cart.size();i++) {
            System.out.println(cart.get(i));
        }
    }
}
