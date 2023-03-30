package pl.arkani.LZ_2022301_LX.utils;

import pl.arkani.LZ_2022301_LX.model.User;

import java.lang.reflect.Field;



public class GetClassFields {



    private static Field[] declaredFields;



    public static void main(String args[]) {

        User ft = new User();

        Class ftClass = ft.getClass();

        String pcg = ftClass.getPackage()+"."+ftClass.getSimpleName()+".";

        pcg=pcg.replace("package ","") ;

        System.out.println(pcg);

        String f;

        Field[] fields = ftClass.getDeclaredFields();

        //Field[] fields = ftClass.getFields();



        for (int i = 0; i < fields.length; i++) {

            f=fields[i].toString().replace(pcg,"");





        //    System.out.println("declared field: " + f);
            String name = f.split(" ")[2];
            String type ;
            if (!f.contains("."))
            {
               type= f.split(" ")[1];
            } else {
                type= (f.split(" ")[1]).split("\\.")[2];
            }


            System.out.println(name);
            System.out.println(type);

        }

    }

}