package program;

public class Member {


    private  int number;
    private  String name;
    private  String phone;
    private  String email;



    public Member(String[] token){

            int number=Integer.parseInt(token[0]);
            String name=token[1];
            String phone=token[2];
            String email=token[3];

            setNumber(number);
            setName(name);
            setPhone(phone);
            setEmail(email);


    }
    public  Member(int number){
       this.number=number;
    }

    @Override
    public String toString() {

        String str =String.format("번호:%d\t이름:%s\t전화번호:%s\t이메일:%s",
                this.number,
                this.name,
                this.phone,
                this.email
                );

        return str;
    }

    @Override
    public boolean equals(Object obj) {
        Member other= (Member) obj;
        return this.getNumber()==other.getNumber();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
