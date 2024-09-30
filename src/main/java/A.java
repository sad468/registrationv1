import com.api.Employee;
import com.api.EmployeeDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A {


    private static Employee employee;

    public static void main(String[] args) {
        List<Employee> emp = Arrays.asList(

                new Employee(1, "mike"),
                new Employee(2, "john"),
                new Employee(3, "stallin")
        );

       List<EmployeeDto> dtos = emp.stream().map(A::mapToDto).collect(Collectors.toList());

       System.out.println(dtos);
    }

       static EmployeeDto mapToDto(Employee employee){

            EmployeeDto dto =new EmployeeDto();
            dto.setId(employee.getId());
            dto.setName(employee.getName());
            return  dto;
        }
    }

