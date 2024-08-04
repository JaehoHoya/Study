package serialization;

import java.io.Serializable;

public class Emp implements Serializable {


       private int id;
       private String empno;
       private int deptno;
       private int sal;


        @Override
        public boolean equals(Object obj) {
               Emp other= (Emp)obj;

               return  this.getEmpno()==other.getEmpno();
        }


        public Emp(int id, String empno, int deptno, int sal) {
                this.id = id;
                this.empno = empno;
                this.deptno = deptno;
                this.sal = sal;
        }

        public Emp(){};

        public Emp(int id) {
                this.id = id;
        }

        public Emp(String id ,String empno) {

        }

        public int getId() {
                return id;
        }
        public void setId(int id) {
                this.id = id;
        }
        public String getEmpno() {
                return empno;
        }
        public void setEmpno(String pwd) {
                this.empno = empno;
        }
        public int getDeptno() {
                return deptno;
        }
        public void setDeptno(int deptno) {
                this.deptno = deptno;
        }
        public int getSal() {
                return sal;
        }
        public void setSal(int sal) {
                this.sal = sal;
        }

}

