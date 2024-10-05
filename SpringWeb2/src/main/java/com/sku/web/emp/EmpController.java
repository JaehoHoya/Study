package com.sku.web.emp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpDAO empDAO;

    @GetMapping("Detail/{empno}")
    public String empDetail(@PathVariable("empno") int empno, Model model)
    {

        Emp emp = empDAO.empDetail(empno);
        model.addAttribute("emp", emp);
        return "th/mb/mbEmpDetail";
    }

    @GetMapping("addForm")
    public String addForm()
    {
        return "booAddForm";
    }


    @GetMapping("updateForm/{empno}")
    public  String  updateForm(@PathVariable("empno") int empno, Model model)
    {
        Emp  Detail= empDAO.empDetail(empno);
        model.addAttribute("detail",Detail);
        return "th/updateForm";
    }
    @PostMapping("update")
    public String update(Emp emp)
    {
        boolean updated =empDAO.update(emp);
        if (updated) return "redirect:/emps/Detail/"+emp.getEmpno();

        else  return "redirect:th/updateForm/"+emp.getEmpno();
    }

    @GetMapping("/del/{empno}")
    @ResponseBody
    public Map<String,Boolean> del(@PathVariable int empno){
        boolean deleted = empDAO.deleteEmp(empno);
        Map<String,Boolean> map = new HashMap<>();
        map.put("deleted", deleted);
        return map;
    }


}
