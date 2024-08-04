package inheritance.PetStore;

public class Pet {

    int price;

    String species;

    float size ;


    public  Pet(){};

    public Pet(int price, String species, float size) {
        this.price = price;
        this.species = species;
        this.size = size;
    }


    @Override
    public String toString() {

        return String.format("\t%s\t\t\t%.2f\t\t\t%d",species,size,price);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
