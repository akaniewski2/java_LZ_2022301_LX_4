package pl.arkani.LZ_2022301_LX.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arkani.LZ_2022301_LX.model.Person;
import pl.arkani.LZ_2022301_LX.model.User;

import java.lang.reflect.Field;


public class GenerateJavaCode {



    private static Field[] declaredFields;



    public static void main(String args[]) {

        User ft = new User();

        Class ftClass = ft.getClass();
        String classNm = ftClass.getSimpleName();
        String packageNm = ftClass.getPackage().toString();

        System.out.println(packageNm);

        String pcg = ftClass.getPackage()+"."+ftClass.getSimpleName()+".";

        pcg=pcg.replace("package ","") ;

        System.out.println(pcg);

        String field;

        Field[] fields = ftClass.getDeclaredFields();

        //Field[] fields = ftClass.getFields();




        for (int i = 0; i < fields.length; i++) {

            field=fields[i].toString().replace(pcg,"");





        //    System.out.println("declared field: " + field);
            String name = field.split(" ")[2];
            String type ;
            if (!field.contains("."))
            {
               type= field.split(" ")[1];
            } else {
                type= (field.split(" ")[1]).split("\\.")[2];
            }

            System.out.println(name);
            System.out.println(type);



        }

        System.out.println("-----------------------------------------------------------");
        String repo =
                "import org.springfra" +
                        "mework.data.jpa.repository.JpaRepository;\n"+
                        "import org.springframework.stereotype.Repository;\n"+
                        "import "+pcg +";\n\n" +

                "@Repository\n" +
                "public interface PersonRepository extends JpaRepository<"+classNm+", Long> {}";
      // String controller =


        System.out.println(repo);

        System.out.println("-----------------------------------------------------------");


    }

}