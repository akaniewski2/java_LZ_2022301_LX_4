package pl.arkani.LZ_2022301_LX.utils.Testowe;

public class rexepExample {

    public static void main(String[] args) {

        String input = "setCategory setcategory";
        String output = input.replaceAll("(?i)set(category)\\b", "setBook");

        System.out.println(output);

           input = "setCategory setcategory SetCategory Setcategory";
           output = input.replaceAll("set(Category)|set(category)","$1Book$2");


        System.out.println(output);

        //moje
        input = "setCategory setcategory";
        output = input.replaceAll("set(Category)|set(category)","$1Book$2");


        System.out.println(output);


    }


}
