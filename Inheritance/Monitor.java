package inheritance;

public class Monitor extends Item{

    float inch;


    public  Monitor(){};

    public Monitor(String name, String made, int price, String sDate, float inch) {
        super(name, made, price, sDate);
        this.inch = inch;
    }

    @Override
    public String toString() {
        String str=String.format(super.toString()+"\t인치:%.2f",inch);
        return str;
    }

    public float getInch() {
        return inch;
    }

    public void setInch(float inch) {
        this.inch = inch;
    }
}
