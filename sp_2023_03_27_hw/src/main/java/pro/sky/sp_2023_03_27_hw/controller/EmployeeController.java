package pro.sky.sp_2023_03_27_hw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.sp_2023_03_27_hw.exeption.EmployeeAlreadyAddedException;
import pro.sky.sp_2023_03_27_hw.exeption.EmployeeNotFoundException;
import pro.sky.sp_2023_03_27_hw.exeption.EmployeeStorageIsFullException;
import pro.sky.sp_2023_03_27_hw.model.Employee;
import pro.sky.sp_2023_03_27_hw.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName, @RequestParam String lastName) {
        return  employeeService.add(firstName, lastName);
    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName) {
        return  employeeService.remove(firstName, lastName);
    }
    @GetMapping("/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName) {
        return  employeeService.find(firstName, lastName);
    }

    @GetMapping
    public List<Employee> list() {
        return  employeeService.list();
    }
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public ResponseEntity<String> EmployeeAlreadyAddedExceptionHandler (EmployeeAlreadyAddedException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Сотрудник " + e.getEmployee() + " уже есть в списке!");
    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> EmployeeNotFoundExceptionHandler (EmployeeNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Сотрудник " + e.getEmployee() + " не найден!");
    }

    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public ResponseEntity<String> EmployeeStorageIsFullExceptionHandler (EmployeeStorageIsFullException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Список полон - сотрудника " + e.getEmployee() + " не добавить!");
    }

}
