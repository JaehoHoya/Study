package com.sku.web.mb;

import com.sku.web.emp.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mb")
public class EmpMyBatisController {

    @Autowired
    private EmpMapper empMapper;

    @GetMapping("")
    @ResponseBody
    public String index() {
        return "EmpMyBatisController";
    }
    @GetMapping("/list")
    public String getlist(Model model) {

        List<Emp> list=empMapper.getList();
        model.addAttribute("emps",list);

        return "th/mb/mbEmpList";
    }

    @GetMapping("Detail/{empno}")
    public String empDetail(@PathVariable("empno") int empno, Model model)
    {

        Emp emp =empMapper.getEmpByNo(empno);
        model.addAttribute("emp",emp);

        return "th/mb/mbEmpDetail";
    }


    @GetMapping("updateForm/{empno}")
    public  String  updateForm(@PathVariable("empno") int empno, Model model)
    {
        Emp emp =empMapper.getEmpByNo(empno);
        model.addAttribute("emp",emp);
        return "th/mb/mbEmpUpdateForm";
    }
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Boolean> update(@ModelAttribute("emp") Emp emp){
        int rows = empMapper.updateEmp(emp);
        Map<String,Boolean> map = new HashMap<>();
        map.put("updated", rows > 0);
        return map;
    }

    @GetMapping("/del/{empno}")
    @ResponseBody
    public Map<String,Boolean> del(@PathVariable int empno){
       int rows = empMapper.deleteEmp(empno);
        Map<String,Boolean> map = new HashMap<>();
        map.put("deleted", rows>0);
        return map;
    }

    @GetMapping("/add")
    @ResponseBody
    public Map<String,Boolean> insertEmp(Emp emp){
        int rows = empMapper.addAndGetKey(emp);

        int generatedKey= emp.getEmpno(); // 시퀸스가 생성된사번
        Map<String,Boolean> map = new HashMap<>();
        map.put("inserted", rows>0);
        return map;
    }


}
