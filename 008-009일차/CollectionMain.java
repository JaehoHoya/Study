package program;// 010-5047-8424 김창운 강사님
//
import java.util.*;

/*
*  Set Map
*  List: 유순, 중복
*  Set : 무순, 중복 불가
*  Map : Key와 Value 쌍으로 저장하는 자료구조
*
*
*
*/
public class CollectionMain {


    public static void main(String[] args) {

        mapTest();
        /*
        Set<String> set =new HashSet<>();
        set.add("jaeho");
        set.add("one");
        set.add("two");
        set.add("jaeho");



        System.out.println("원소의 개수:"+set.size());  // 3개 중복 x
        //순서가 없어서 루프를 돌릴수 없음  가공을 해줘야함
        Iterator<String> it= set.iterator();

        while(it.hasNext()){ //boolean 표현식
            String value = it.next();
            System.out.println(value);
        }
         */


        // collection 은 오브젝트만 원소로 받는다
        // 그러므로 원칙적으로 ㄱ본형 데이터를 저장할 수 없다
        //Set<int> iset = new HashSet<>();  에러 -> 정수를 인티저 객체로 만들어서
        Integer one =Integer.valueOf(1); //정수를 기본형이 아니라  정수 오브젝트로 만듬 int가 아니라
        // 즉 아래와 같이  기본형 데이터는 각 자료형의 Wrapper 오브젝트를 생성하여 컬렉션에 저장할 수 있다
        Set<Integer> iSet= new HashSet<>();
        iSet.add(one); // 원칙적으로는 이렇게 해야함  업데이트됨. 외부에서 오브젝트로 만들어서 해줌
        iSet.add(2);   //?????? 기본형 데이터가 들어가네
        // Auto-Boxing(기본형 데이터를 그대로 저장하는 것이 아니라 내부에서 Wrapper 클래스를 사용하여 오브젝트를 생성하고 그 참조 컬랙션에 저장한다
        // iSet.add(Integer.valueOf(2))

        /* 응용*****응용 */
        // 무작위 정수를 중복되지 않도록 추출 10게!

        Set<Integer> numbers = new HashSet<>();
        Random rd =new Random();
        while(numbers.size()<10)
        {

            int number =rd.nextInt(20)+1;

            numbers.add(number);
        }
        List<Integer> list= new ArrayList<>(numbers);  //리스트로 만들기
        Collections.sort(list);  // 정렬
        System.out.println(Arrays.toString(list.toArray()));


        // 중복이 되지 않게 Employee 오브젝트를 저장하려고 한다 .
        // Employee 오브젝트를 2개를 생성할때 사번을 동일하게 설정하여 Set에 저장
    }

    private  static  void mapTest()
    {   // 리스트는 순차적 검색 , map 검색 속도 빠름 ?
        //map: key, value가 연결되어 쌍으로 저장되는 자료구조
        // key를 해싱하여 value가 저장되는 위치를 계산한다    값을 메모리에 저장하는데 저장되는 위치를 key로 계산

        Map<String,String> map=new HashMap<>();
        //map은 key가 중복되는 것을 막는 로직이 있음   equals 비교 hash코드 비교  해쉬코드 오버라이드 해야함

        map.put("jaeho","010-4588-4553");  //  add 넣다 :큰 통에 넣다 ///put 둔다: key에 value를 둔다
        map.put("james","010-1234-4553");
        map.put("laura","010-4588-5678");
        map.put("blake","010-9101-4553");
        map.put("adam","010-4588-8705");
        //key 중복되면 에러
        String phone = map.get("blake");  //key는  value의 위치
        System.out.println("전화번호:"+phone);
    }


}

