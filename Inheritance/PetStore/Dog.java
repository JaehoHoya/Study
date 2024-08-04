package inheritance.PetStore;

public class Dog extends  Pet{

    float weight;

    int age;


    public Dog(int price, String species, float size, float weight, int age) {
        super(price, species, size);
        this.weight = weight;
        this.age = age;
    }


    @Override
    public String toString() {
        String str =String.format("개\t\t%s\t\t\t%s\t\t\t\t\t\t\t%d살",super.toString(),weight,age);
        return str;
    }


    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
