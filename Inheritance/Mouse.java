package inheritance;

import java.util.Date;

public class Mouse extends Item {

    String wirelees;


    public Mouse() {
    }

    ;

    public Mouse(String name, String made, int price, Date pDate, String wirelees) {
        super(name, made, price, pDate);  // 부모의 생성자를 빌리기 super(): 상위 클래스의 생성자를 가르킴    super.: 매소드 호출
        // this.wirelees = wirelees;
        setWirelees(wirelees);
    }

    public Mouse(String name, String made, int price, String sDate, String wirelees) {
        super(name, made, price, sDate);

        setWirelees(wirelees);
    }

    public String getWirelees() {
        return wirelees;
    }

    public void setWirelees(String wirelees) {
        this.wirelees = wirelees;
    }

    @Override
    public String toString() {
        String str =String.format(super.toString()+"\t기타:%s",wirelees);
        return str;
    }
}