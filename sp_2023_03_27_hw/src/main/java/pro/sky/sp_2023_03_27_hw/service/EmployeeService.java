package pro.sky.sp_2023_03_27_hw.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pro.sky.sp_2023_03_27_hw.exeption.EmployeeAlreadyAddedException;
import pro.sky.sp_2023_03_27_hw.exeption.EmployeeNotFoundException;
import pro.sky.sp_2023_03_27_hw.exeption.EmployeeStorageIsFullException;
import pro.sky.sp_2023_03_27_hw.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    private static final int SIZE = 3;
    private final List<Employee> employees = new ArrayList<>(SIZE);

    @PostConstruct
    public void init() {
        employees.add(new Employee("Олег", "Олегов"));
        employees.add(new Employee("Максим", "Максимов"));
    }

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() < SIZE) {
            for (Employee emp : employees) {
                if (emp.equals(employee)) {
                    throw new EmployeeAlreadyAddedException(employee);
                }
            }
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException(employee);
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException(employee);
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.remove(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException(employee);
    }

    public List<Employee> list(){
        return Collections.unmodifiableList(employees);
    }
}


