package com.example.hw14.Controller;

import com.example.hw14.ApiResoonse.ApiResponse;
import com.example.hw14.Model.EmployeesManagement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employeesManagement/")
public class EmployeesManagementController {

    ArrayList<EmployeesManagement> employeesManagements = new ArrayList<>();

//    Get all the Employees
   @GetMapping("get")
    public ArrayList<EmployeesManagement> getEmployeesManagements() {
        return employeesManagements;
    }


//    Add new Employee
   @PostMapping("add")
    public ResponseEntity addEmployee(@RequestBody @Valid EmployeesManagement employee , Errors errors){
       if(errors.hasErrors()){
           String message = errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(message);
       }
       employeesManagements.add(employee);
       return ResponseEntity.status(200).body( new ApiResponse("Employee has been added"));
    }


//    Update Employee
    @PutMapping("update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index, @RequestBody @Valid EmployeesManagement employee, Errors errors) {
    if (errors.hasErrors()) {
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
    employeesManagements.set(index, employee);
    return ResponseEntity.status(200).body(new ApiResponse("Employee data has been updated"));
}


//    Delete Employee
    @DeleteMapping("delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index) {
    employeesManagements.remove(index);
    return ResponseEntity.status(200).body(new ApiResponse("Employee has been deleted"));
}



//    Employees apply for an annual leave and turn their onLeave status to true and reduce annualLeave by 1 (Check if employee is on leave return bad request, if employee applies for leave and has 0 days return bad request)
    @PostMapping("annualLeave/{index}")
    public ResponseEntity annualLeave(@PathVariable int index, @RequestBody @Valid EmployeesManagement employee){

        employee.setPosition(employeesManagements.get(index).getPosition());
        employee.setID(employeesManagements.get(index).getID());
        employee.setName(employeesManagements.get(index).getName());
        employee.setAge(employeesManagements.get(index).getAge());
        employee.setEmploymentYear(employeesManagements.get(index).getEmploymentYear());

        if(employee.getAnnualLeave() > 0 && employee.getAnnualLeave() <= employeesManagements.get(index).getAnnualLeave()){
            employee.setOnLeave(true);
            employee.setAnnualLeave(employeesManagements.get(index).getAnnualLeave() - employee.getAnnualLeave());
            employeesManagements.set(index, employee);
            return ResponseEntity.status(200).body(new ApiResponse("The annual leave has been approved"));

        } else return ResponseEntity.status(400).body(new ApiResponse("Annual leave is greater than available:" + employeesManagements.get(index).getAnnualLeave() ));
    }
}
