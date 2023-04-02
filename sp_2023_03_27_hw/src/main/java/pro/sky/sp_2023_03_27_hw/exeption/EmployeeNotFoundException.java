package pro.sky.sp_2023_03_27_hw.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pro.sky.sp_2023_03_27_hw.model.Employee;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{
    private final Employee employee;

    public EmployeeNotFoundException(Employee employee){
        this.employee = employee;
    }

    public Employee getEmployee(){
        return employee;
    }
}
