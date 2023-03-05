package pl.arkani.LZ_2022301_LX.repo;

import pl.arkani.LZ_2022301_LX.model.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EmployeeRepository {

    private static Map<String, Employee> employees = new HashMap<>();

    static {
        employees.put("Pawel",new Employee("Pawel",32));
        employees.put("Dawid",new Employee("Dawid",29));

    }

    public static Optional<Employee> find(String name) {
//        return Optional.of(employees.get(name));
//        return Optional.ofNullable(employees.get(name));
        return Optional.ofNullable(employees.get(name));
    }


}
