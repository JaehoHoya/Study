import java.io.Serializable;
import java.text.NumberFormat;

public class PetVO implements  Comparable<PetVO>, Serializable  {   // 이 클래스는 객체 직렬화를 허용합니다
                                                                    // Serializable은 메소드 구현 필요 없음 마크(도장) 같은 의미
    int number;
    String species;
    int weight;

    @Override
    public int compareTo(PetVO other) {
           //왼쪽의 객체가 크면 양수, 동일하면 0, 작으면 음수
       if (this.getNumber()>other.getNumber()) return 1;
       else if (this.getNumber()==other.getNumber()) return 0;
       else return -1;
    }

    int price;

    @Override
    public boolean equals(Object obj) {

        PetVO other =(PetVO) obj;

        return this.getNumber()==other.getNumber();
    }

    @Override
    public String toString() {
        NumberFormat nf= NumberFormat.getInstance();
        String str =String.format("번호:%d\t종:%s\t무게:%dkg\t가격?:%s원",getNumber(),getSpecies(),getWeight(),nf.format(getPrice()));

        return str;
    }
    public  PetVO(){};
    public  PetVO(int number){
        setNumber(number);
    }
    public PetVO(String line){
        String[] token=line.split("\\|");

        setNumber(Integer.parseInt(token[0]));
        setSpecies(token[1]);
        setWeight(Integer.parseInt(token[2]));
        setPrice(Integer.parseInt(token[3]));

    }
    public  PetVO(int number,int weight,int price){
        setNumber(number);
        setWeight(weight);
        setPrice(price);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
