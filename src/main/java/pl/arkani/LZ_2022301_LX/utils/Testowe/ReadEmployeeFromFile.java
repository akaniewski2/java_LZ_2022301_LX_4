package pl.arkani.LZ_2022301_LX.utils.Testowe;

import pl.arkani.LZ_2022301_LX.model.Employee;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class ReadEmployeeFromFile {
// to przklad z innego projektu wiec nie wszystki sie zgadza
    public static void main(String[] args) throws IOException {
        Path inputPAth= Paths.get("src/main/EmployeeData.txt");
        List<String> lines= Files.readAllLines(inputPAth, Charset.forName("UTF-8"));

        List<Employee> employees=new ArrayList<>();

        //   lines.forEach(System.out::println);


        for (int i = 0; i < lines.size(); i++) {
            if(lines.get(i).equals("EmployeeData")) {
                String[] name = lines.get(i + 1).split(",");
                int age=Integer.parseInt(lines.get(i+2));
                boolean fullTime = lines.get(i+3).equals("y");

           //     Employee emp = new Employee(name[0],name[1],age,fullTime);
            //    employees.add(emp);

            }
        }

        //employees.get(0).setLastName("Modor");

        Path outputFile=Paths.get("src/main/EmployeeData.json");
//        File f = new File("C:\\EmployeeData.json");
//        Path outputFile=f.toPath();
      //  Files.write(outputFile,toJson(employees).getBytes( Charset.forName("UTF-8")));

    }

//    private static String toJson(List<Employee> employees) {
//        String empl=employees.stream()
//                 .map(Employee::toJson)
//                .collect(Collectors.joining(","));
//        return "{\"employees\": ["+empl+"]}";
//    }
}
