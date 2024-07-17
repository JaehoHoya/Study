package program;

public class User {

    private  String id ;
    private  String password;



    public  User(String line){     // 파일 데이터 저장

        String[] data =line.split(":");

        setId(data[0].trim());
        setPassword(data[1].trim());
    }


    public  User(String id ,String password){    // 로그인할때

        setId(id);
        setPassword(password);

    }

    @Override
    public boolean equals(Object obj) {
        User other =(User)obj;

        return this.getId().equals(other.getId())&&this.getPassword().equals(other.getPassword());
    }


    @Override
    public String toString() {
        return String.format("id:%s\tpassword:%s\t",this.getId(),this.getPassword());
    }

    //Getter Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

