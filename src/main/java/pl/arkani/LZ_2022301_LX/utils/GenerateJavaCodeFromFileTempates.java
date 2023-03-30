package pl.arkani.LZ_2022301_LX.utils;


import pl.arkani.LZ_2022301_LX.model.MusicTest;
import pl.arkani.LZ_2022301_LX.model.User;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class GenerateJavaCodeFromFileTempates {

    private static String smallFirstLetter(final String line) {
        return Character.toLowerCase(line.charAt(0)) + line.substring(1);
    }

    public static String reverseStr(String originalStr)  {

        String reversedStr = "";

        for (int i = 0; i < originalStr.length(); i++) {
            reversedStr = originalStr.charAt(i) + reversedStr;
        }

        return reversedStr;
    }

    private static Field[] declaredFields;
    private static int GENERATE_REPO =0;
    private static int GENERATE_CONTROLLER =0;
    private static int GENERATE_HTML_VIEW =0;


    public static void main(String[] args) throws IOException {



        System.out.println("-------------------------------------------------------------------");
        System.out.println("-- # CLASS INFO");

        MusicTest ft = new MusicTest();

        Class ftClass = ft.getClass();
        String ClassNm = ftClass.getSimpleName();
        String classNm = smallFirstLetter(ClassNm);
        String classnm = ClassNm.toLowerCase();
        String packageNm = ftClass.getPackage().toString();

        System.out.println("ClassNm: "+ ClassNm);
        System.out.println("classNm: "+ classNm);
        System.out.println("packageNm: "+ packageNm);

        //to do przejrzenia
        String pcg = ftClass.getPackage()+"."+ftClass.getSimpleName()+".";
        pcg=pcg.replace("package: ","") ;
        System.out.println("pcg: "+pcg);

        String field;
        Field[] fields = ftClass.getDeclaredFields();

        System.out.println("-------------------------------------------------------------------");
        System.out.println("-- # JAVA_CODE_TEMPLATEST INFO");


        String JavaCodeTemplateFolder ="JAVA_CODE_TEMPLATEST";
        Path inputPath;
        List<String> lines;

        System.out.println("-------------------------------------------------------------------");
        System.out.println("-- # Global Variables");

        String targetFolder;

        System.out.println("-------------------------------------------------------------------");
        System.out.println("// -- # REPO");





            inputPath= Paths.get("src/main/"+JavaCodeTemplateFolder+"/Repo.java");
            lines= Files.readAllLines(inputPath, Charset.forName("UTF-8"));

            targetFolder = "repo";
            String repoClassSufix = "Repo";


            String ClassNmRepo = ClassNm + repoClassSufix;
            String classNmRepo = classNm + repoClassSufix;

            //Repo
            for (String line : lines) {
                line = line.replace("JAVA_CODE_TEMPLATEST", targetFolder);
                line = line.replace("<ClassNm>", ClassNm);
                line = line.replace("<ClassNmRepo>", ClassNmRepo);
                // line= line.replace("<@RequestMapping>","/arkani2/");
                System.out.println(line);

            }


        System.out.println("-------------------------------------------------------------------");
        System.out.println("// -- # CONTROLLER");



            inputPath = Paths.get("src/main/" + JavaCodeTemplateFolder + "/Controller.java");
            lines = Files.readAllLines(inputPath, Charset.forName("UTF-8"));

            targetFolder = "Controller";


            String RequestMapping = "/arkani2/";
            String GetMapping = classnm;

            String htmlFolder = classnm;
            String htmlFilePrefix = classnm;

            //Repo
            for (String line : lines) {
                line = line.replace("JAVA_CODE_TEMPLATEST", targetFolder);
                line = line.replace("<ClassNm>", ClassNm);
                line = line.replace("<classNm>", classNm);

                line = line.replace("<@RequestMapping>", RequestMapping);
                line = line.replace("<@GetMapping>", GetMapping);

                line = line.replace("<ClassNmRepo>", ClassNmRepo);
                line = line.replace("<classNmRepo>", classNmRepo);

                line = line.replace("<htmlFolder>", htmlFolder);
                line = line.replace("<htmlFilePrefix>", htmlFilePrefix);

                // line= line.replace("<@RequestMapping>","/arkani2/");
                System.out.println(line);

            }

        System.out.println("-------------------------------------------------------------------");
        System.out.println("<!-- # HTML -->");
        System.out.println("<!-- # VIEW -->");


        inputPath= Paths.get("src/main/"+JavaCodeTemplateFolder+"/html/classnm___.html");
        lines= Files.readAllLines(inputPath, Charset.forName("UTF-8"));

        targetFolder = "html";
        String tabTilte = ClassNm;
        String headerTilte = ClassNm;

        //Repo
        String lineOutput="";
        for (String line : lines) {
            line = line.replace("JAVA_CODE_TEMPLATEST", targetFolder);
            line = line.replace("<ClassNm>", ClassNm);
            line = line.replace("<classNm>", classNm);
            line = line.replace("<@RequestMapping>","/arkani2/");
            line = line.replace("<@GetMapping>", GetMapping);
            line = line.replace("<tabTilte>", tabTilte);
            line = line.replace("<headerTilte>", headerTilte);
           // System.out.println(line);

            lineOutput=lineOutput.concat(line+"\n");

        }

        System.out.println("-------------------------------------------------------------------");
        System.out.println("<!-- # VIEW TABLE FRAGMENT-->");

        inputPath= Paths.get("src/main/"+JavaCodeTemplateFolder+"/html/fragment/viewTable.html");
        lines= Files.readAllLines(inputPath, Charset.forName("UTF-8"));

        targetFolder = "html";

        //Repo
        String lineViewTableFragmentOutput="";
        for (String line : lines) {

            line = line.replace("JAVA_CODE_TEMPLATEST", targetFolder);
            line = line.replace("<ClassNm>", ClassNm);
            line = line.replace("<classNm>", classNm);
            line = line.replace("<@RequestMapping>","/arkani2/");
            line = line.replace("<@GetMapping>", GetMapping);


            lineViewTableFragmentOutput=lineViewTableFragmentOutput.concat(line+"\n");
        }


        List<String> fieldNameThList= new ArrayList<>();
        List<String> fieldNameTdList= new ArrayList<>();
        List<String> fieldTypeList= new ArrayList<>();

       for (int i = 0; i < fields.length; i++) {

       // for (Field field : fields) {

            field=fields[i].toString().replace(pcg,"");


            //    System.out.println("declared field: " + field);
          //  String fieldName = field.split(" ")[2];
           String fieldName;
           fieldName=reverseStr(reverseStr(field).split("\\.")[0]);

           fieldNameThList.add(fieldName);


            String fieldType;
            if (!field.contains(".")) {
                fieldType = field.split(" ")[1];
            } else {
               // fieldType = (field.split(" ")[1]).split("\\.")[2];
             fieldType=reverseStr(reverseStr(field.replaceAll("private|public|protected|package","").trim().split(" ")[0]).split("\\.")[0]);
            }
            fieldTypeList.add(fieldType);

            //System.out.println("fieldName:"+fieldName);
            //System.out.println("fieldType:"+fieldType);


        }

        String fieldNmTh="";
        String fieldNmTd="";
        for (String fieldNmTmp : fieldNameThList) {



            fieldNmTh = fieldNmTh.concat( "<th class=\"menu\">"+fieldNmTmp+"</th>\n");
            fieldNmTd = fieldNmTd.concat( "<td class=\"menu\" th:text=\"${s."+fieldNmTmp+"}\"></td>\n");

        }
      //  System.out.println(fieldNmTh);
      //  System.out.println(fieldNmTd);




        lineViewTableFragmentOutput= lineViewTableFragmentOutput.replace("<fieldNmTh>",fieldNmTh);
        lineViewTableFragmentOutput= lineViewTableFragmentOutput.replace("<fieldNmTd>",fieldNmTd);


        //System.out.println(lineViewTableFragmentOutput);

        System.out.println("-------------------------------------------------------------------");
        System.out.println("<!-- # HTML -->");
        System.out.println("<!-- # VIEW FINISH -->");

        lineOutput= lineOutput.replace("<viewTableFragment>",lineViewTableFragmentOutput);


        System.out.println(lineOutput);

    }

 }

