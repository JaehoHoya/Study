
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

    static String fpath = "/Users/jeongjaeho/Desktop/hoho/pet.txt";
    static String Serpath="/Users/jeongjaeho/Desktop/hoho/pet.ser";

    public static boolean addObject(PetVO pet){
        List<PetVO> list= getList();
        list.add(pet);
        return  overwrite2(list);
    }

    private static  boolean overwrite2(List<PetVO> list){
        try {
            ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(Serpath));
            oos.writeObject(list);
            oos.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public  static  List<PetVO> getList(){    //가져오기 파일에 있는 내용 list에 담기
        try {
            File ser =new File(Serpath);
            if(!ser.exists()) //파일이 없으면
            {
                List<PetVO> list =new ArrayList<>();
                overwrite2(list);
            }
            ObjectInputStream ois =new ObjectInputStream(new FileInputStream(Serpath));
            List<PetVO> list =(List<PetVO>)ois.readObject();
            return  list;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    public  static void update(){

    }
    public  static void delete(){

    }
/*
    public static boolean savePet(PetVO p) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(fpath, true));

            NumberFormat s =NumberFormat.getNumberInstance();


            String line = String.format("%d|%s|%d|%s%n", p.getNumber(), p.getSpecies(), p.getWeight(), p.getPrice());
            pw.println(line);
            pw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }

*/
/*
    public static List<PetVO> list() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fpath));
            String line = null;
            List<PetVO> list = new ArrayList<>();
            while((line = br.readLine()) != null) {
                PetVO p = new PetVO(line);
                list.add(p);

            }
            br.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }
*/
    public  static PetVO findByNo(int no){

        List<PetVO> p = list();
        PetVO key =new PetVO(no);

        if(p.contains(key)){
            PetVO pet= p.get(p.indexOf(key));
            return  pet;
        }
        return  null;
    }
    /*
    private  static  boolean overwrite(List<PetVO> modified){
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(fpath));
            for(int i=0;i<modified.size();i++){
                PetVO p= modified.get(i);
                String line = String.format("%d|%s|%d|%s",p.getNumber(),p.getSpecies(), p.getWeight(), p.getPrice());
                pw.println(line);
            }
            pw.close();
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
            return false;
    }
*/
    public  static boolean update(PetVO p) {

        List<PetVO> list= list();
        if(list.contains(p)){

            PetVO found =list.get(list.indexOf(p));
            found.setPrice(p.getPrice());
            found.setWeight(p.getWeight());
            return overwrite(list);

        }


        return  false;
    }

    public  static boolean delete(int number){
        List<PetVO> list= list();
        PetVO key= new PetVO(number);

        if(list.contains(key)){
            list.remove(key);

            return  overwrite(list);
        }
        return  false;
    }
}