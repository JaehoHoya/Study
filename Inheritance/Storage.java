package inheritance;

public class Storage<T>{
    //유연성 안전성 제공
    private  T item;

    public void  setItem(T item){
        this.item=item;

    }
    public T getItem(){
        return item;
    }

}
