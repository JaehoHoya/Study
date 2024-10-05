package com.sku.web.mb;


import com.sku.web.emp.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper
{
    //public 안써도 됨 기본값임
    public List<Emp> getList();

    public Emp getEmpByNo(int empno);

    public int updateEmp(Emp emp);

    public int deleteEmp(int empno);

    public int addAndGetKey(Emp emp);
}
