package pl.arkani.LZ_2022301_LX.utils;

import pl.arkani.LZ_2022301_LX.model.Employee;
import pl.arkani.LZ_2022301_LX.repo.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalIntro {

    public static void main(String[] args) {

        System.out.println("--------- OPTIONAL -----------------------------------------");

        Optional<Employee> emp = EmployeeRepository.find("Pawel");

        if(emp.isPresent()) {
            Employee e = emp.get();
            System.out.println("# 1 isPresent(): "+ e.getName());
        }

        emp.ifPresent(e-> {
            System.out.println("# 2 isPresent(): "+e.getName());
            System.out.println("# 2 isPresent(): "+e.getAge());
            //do something else

        });


        System.out.println("# 3a map,filter,ifPresent");
        emp
            .filter(e->e.getAge()>30)
            .map(Employee::getName)
            .map(String::toUpperCase)
            .ifPresent(System.out::println);




        System.out.println("# 3b map,filter,ifPresent + orElse..");
        String imie = emp
                .filter(e -> e.getAge() > 20)
                .map(Employee::getName)
                .map(String::toUpperCase)
                .orElse("Brak pracownika");//oir else konczy streem i nie mozna uzuc juz ifPresent, wiec trzeba przypisac wynik do zminnej
               // .orElseThrow(()->new RuntimeException("Brak pracowanika o podanym imieniu"));
//.ifPresent(System.out::println);

        System.out.println(imie);

        // --java 9
        System.out.println("# 4a map,filter,ifPresent + stream..");

        String  imie2 = String.valueOf(emp
                .filter(e -> e.getAge() > 20)
                .map(Employee::getName)
                .map(String::toUpperCase)
                .stream() //(nowe) mozliwosc stworzenia strema , tu jest jesden element ,alemoglba by byc to lista elementów
//                .count()
                .findFirst());






        // --- FINALNE ROZWIĄZANIA -------------------------------------------------
        System.out.println("# 3aa map,filter,ifPresent"); //od java 9
        emp
                .or(()->Optional.of(new Employee("Jacek",25)))//mozliwosc defaultowego obiektu,jeli wyszukiwany obiekt nie istnieje
                .filter(e->e.getAge()>30)
                .map(Employee::getName)
                .map(String::toUpperCase)

                //.ifPresent(System.out::println);
                .ifPresentOrElse(
                        System.out::println,
                        ()->System.out.println("Brak pracownika o podanym imieniu i wieku")
                );//od java 9, jesli nic nie znajdzie mozna zwrócic alternatywe




        System.out.println("# 3aaa map,filter,ifPresent,STREAM"); //od java 9
        emp     .stream()
                .filter(e->e.getAge()>20)
                .findFirst()
                .or(()->Optional.of(new Employee("Jacek",25)))//mozliwosc defaultowego obiektu,jeli wyszukiwany obiekt nie istnieje
                .filter(e->e.getAge()>30)
                .map(Employee::getName)
                .map(String::toUpperCase)

                //.ifPresent(System.out::println);
                .ifPresentOrElse(
                        System.out::println,
                        ()->System.out.println("Brak pracownika o podanym imieniu i wieku")
                );//od java 9, jesli nic nie znajdzie mozna zwrócic alternatywe


        Optional<Employee> first =
                emp.stream()
                        .filter(e -> e.getAge() > 20)
                        .findFirst();

        first.ifPresent(e->{
            e.setName("Monika");
            System.out.println(e.toString() );

        });

        List<Employee> collect = emp.stream()
                .filter(e -> e.getAge() > 20)
                .collect(Collectors.toList());





    }


}
