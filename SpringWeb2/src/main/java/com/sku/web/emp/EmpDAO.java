package com.sku.web.emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    //함수형 인터페이스 : 메소드가 하나 -> 람다식 표현가능
    public List<Emp> empList()
    {
        String sql = "SELECT * FROM emp2";
       List<Emp> list= jdbcTemplate.query(sql,(rs,i)->{
            Emp e=new Emp();
            e.setEmpno(rs.getInt("EMPNO"));
            e.setEname(rs.getString("ENAME"));
            e.setJob(rs.getString("JOB"));
            // emp를 하나하나 받아서 list에 리턴
            return e;
        });
    return list;
    }

    public  Emp empDetail ( int empno)
    {
        String sql = "SELECT * FROM emp2 WHERE EMPNO=?";
        Emp emp =jdbcTemplate.queryForObject(sql,
                (rs,i)->new Emp(rs.getInt("EMPNO"),
                                rs.getString("ENAME"),
                                rs.getString("JOB"),
                                rs.getInt("MGR"),
                                rs.getDate("HIREDATE"),
                                rs.getInt("SAL"),
                                rs.getInt("COMM"),
                                rs.getInt("DEPTNO")),
                                empno);

        return emp;
    }

    public  boolean update(Emp e)
    {

        String sql="UPDATE emp2 SET deptno=?,sal=? WHERE empno=?";
        int rows= jdbcTemplate.update(sql,e.getDeptno(),e.getSal(),e.getEmpno());
        return rows>0;

    }

    public  boolean deleteEmp(int empno)
    {
        String sql="DELETE FROM emp2 WHERE empno=?";
        int rows= jdbcTemplate.update(sql,empno);
        return  rows>0;
    }
}
