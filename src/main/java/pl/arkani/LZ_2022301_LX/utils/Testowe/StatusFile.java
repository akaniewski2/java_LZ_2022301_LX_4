package pl.arkani.LZ_2022301_LX.utils.Testowe;

import java.io.File;

public class StatusFile {

//    public static void main(String[] args) {
//        changeToFalse();
//    }

    public  static  void setReadOnly   () {
        String filepath = "D:/PROGRAMOWANIE/Intellij_IDEA/_Kursy/Udemy/Spring/todo-app/src/main/resources/db/migration";
        //filepath="./db/migration/";
        File path = new File(filepath);

        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) { //this line weeds out other directories/folders
                files[i].setReadOnly();
             //   System.out.println(files[i]);


            }
        }
    }

}