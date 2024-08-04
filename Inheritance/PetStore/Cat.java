package inheritance.PetStore;

public class Cat extends Pet{

    String pattern;

    int age;



    public Cat(int price, String species, float size, String pattern, int age) {
        super(price, species, size);
        this.pattern = pattern;
        this.age = age;
    }

    @Override
    public String toString() {
        String str =String.format("고양이\t%s\t\t\t%s\t\t\t\t\t\t%d살",super.toString(),pattern,age);
        return str;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public float getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
