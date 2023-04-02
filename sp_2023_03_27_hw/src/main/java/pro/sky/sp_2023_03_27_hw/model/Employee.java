package pro.sky.sp_2023_03_27_hw.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Employee {
    @JsonProperty("firstName")
    private final String name;
    @JsonProperty("lastName")
    private final String surname;

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Employee employee = (Employee) obj;
        return name.equals(employee.name) && surname.equals(employee.surname);
    }

    @Override
    public String toString() {
        return surname + " " + name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
