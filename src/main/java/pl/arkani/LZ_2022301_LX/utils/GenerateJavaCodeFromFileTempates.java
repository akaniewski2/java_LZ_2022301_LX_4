package pl.arkani.LZ_2022301_LX.utils;


import pl.arkani.LZ_2022301_LX.model.ImportantDates;
import pl.arkani.LZ_2022301_LX.model.MusicTest;
import pl.arkani.LZ_2022301_LX.model.UserDTO;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

        UserDTO ft = new UserDTO();

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
//------------------------------------------------------------------------------------------------------------------------------------
//--# HTML ---------------------------------------------------------------------------------------------------------------------------
        System.out.println("-------------------------------------------------------------------");
        System.out.println("<!-- # HTML -->");
        System.out.println("<!-- # VIEW -->");

        String path="src/main/"+JavaCodeTemplateFolder+"/html/";
        //String file = "viewHtmlTemplate.html";
        //String fullPath = path+file;


       // inputPath= Paths.get(fullPath);
        //-----------------------------------------------------------------------------------------
        //konwersja sciezki z projektu do sciezki fizycznej na kompie
        String inputPathUriStr = Paths.get(path).toUri().toString();

        // rozwiazania na linuxa , cos nie dziala jak na windows
        /*
        final Path dir = Paths.get(path);
        final DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir);
        final Stream<Path> stream = Files.list(new File(inputPathUriStr).toPath());
        */
        //---
      HashMap<String,String> HtmlMainFileReplacedList = new HashMap<>();

        //lista plików z folderu HTML
        File filePath = new File(path);
        File[] files = filePath.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) { //this line weeds out other directories/folders
               // files[i].setReadOnly();
                String fileNm = files[i].getName();
                Path filePathIter = files[i].getAbsoluteFile().toPath();
                   System.out.println("# fileNm: "+fileNm + ", "+ filePathIter.toString());

                // #
                String HtmlMainFileReplaced = getHtmlMainFileReplaced(filePathIter,ClassNm, classNm,  GetMapping);
                HtmlMainFileReplacedList.put(fileNm,HtmlMainFileReplaced);

            }
        }
    //    HtmlMainFileReplacedList.entrySet().forEach(x-> System.out.println(x.getKey()));
    //    System.out.println();

        //-----------------------------------------------------------------------------------------
        System.out.println("-------------------------------------------------------------------");
        System.out.println("<!-- # VIEW TABLE FRAGMENT-->");

        // read files to Listand next to HashMap
        /*String*/ path="src/main/"+JavaCodeTemplateFolder+"/html/fragment/";
        HashMap<String,List<String>> HtmlFragmentFileList = new HashMap<>();

        //lista plików
        /*File*/ filePath = new File(path);
        /*File[]*/ files = filePath.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) { //this line weeds out other directories/folders
                // files[i].setReadOnly();
                String fileNm = files[i].getName();
                Path filePathIter = files[i].getAbsoluteFile().toPath();
                System.out.println("# fileNm: "+fileNm + ", "+ filePathIter.toString());

                // # read file only


                List<String> HtmlFragmentLinesList =new ArrayList<>();
                HtmlFragmentLinesList = Files.readAllLines(filePathIter, Charset.forName("UTF-8"));

                HtmlFragmentFileList.put(fileNm,HtmlFragmentLinesList);

            }
        }


        // table record
        List<String> inputFormTrFragment = HtmlFragmentFileList.get("inputFormTrFragment.html");

        // replace fragment TR

        //get fields from class
        List<String> fieldNameThList = getFildsFromClass(pcg, fields);

        //replace generate Fragment (element)
        //replace generate Fragment (element)
        String fieldNmTr="";
        String fieldNmTh="";
        String fieldNmTd="";
        for (String fieldNmTmp : fieldNameThList) {

            String inputFormTrFragmentStr = inputFormTrFragment.stream().collect(Collectors.joining("\n"));

            fieldNmTr = fieldNmTr.concat( inputFormTrFragmentStr.replace("<fieldNm>",fieldNmTmp) );


        }
        List<String> inputFormFragment = HtmlFragmentFileList.get("inputFormFragment.html");
       // String inputFormFragmentStr = inputFormFragment.stream().collect(Collectors.joining("\n"));

        String finalFieldNmTr = fieldNmTr;
        List<String> inputFormFragmentList = inputFormFragment.stream().map(x->x.replace(" <inputFormTrFragment>", finalFieldNmTr)).collect(Collectors.toList());

        // replace fragment to real data
     //   inputFormFragmentStr = inputFormFragmentStr.replace(" <inputFormTrFragment>",fieldNmTr);



        //replace fieldd in inputFormFragmen
        String lineInputFormFragmentOutput;

        lines = inputFormFragmentList;
        lineInputFormFragmentOutput= replaceLineByMainParemetes(ClassNm, classNm, lines, targetFolder, GetMapping);




        System.out.println("-------------------------------------------------------------------");
        System.out.println("<!-- # VIEW TABLE FRAGMENT-->");
//
        //inputPath= Paths.get("src/main/"+JavaCodeTemplateFolder+"/html/fragment/viewTableFragment.html");
        //lines= Files.readAllLines(inputPath, Charset.forName("UTF-8"));
        List<String> viewTableFragment = HtmlFragmentFileList.get("viewTableFragment.html");
        targetFolder = "html";


        lines= viewTableFragment;
        String lineViewTableFragmentOutput= replaceLineByMainParemetes(ClassNm, classNm, lines, targetFolder, GetMapping);

        //replace generate Fragment (element)
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

        String viewHtmlTemplate= HtmlMainFileReplacedList.get("viewHtmlTemplate.html");
        viewHtmlTemplate= viewHtmlTemplate.replace("<viewTableFragment>",lineViewTableFragmentOutput);

        System.out.println(viewHtmlTemplate);

        System.out.println("-------------------------------------------------------------------");
        System.out.println("<!-- # HTML -->");
        System.out.println("<!-- # UPDATE FINISH -->");


        String updateHtmlTemplate= HtmlMainFileReplacedList.get("updateHtmlTemplate.html");
        updateHtmlTemplate= updateHtmlTemplate.replace("<inputFormFragment>",lineInputFormFragmentOutput);
        System.out.println(updateHtmlTemplate);

    }

    private static String replaceLineByMainParemetes(String ClassNm, String classNm, List<String> lines, String targetFolder, String GetMapping) {
        String replacedLineByMainParameters="";
        for (String line : lines) {

            line = line.replace("JAVA_CODE_TEMPLATEST", targetFolder);
            line = line.replace("<ClassNm>", ClassNm);
            line = line.replace("<classNm>", classNm);
            line = line.replace("<@RequestMapping>","/arkani2/");
            line = line.replace("<@GetMapping>", GetMapping);


            replacedLineByMainParameters=replacedLineByMainParameters.concat(line+"\n");

        }
        return replacedLineByMainParameters;
    }

    private static List<String> getFildsFromClass(String pcg, Field[] fields) {
        String field;
        //get fields from class
        List<String> fieldNameThList= new ArrayList<>();
        List<String> fieldNameTdList= new ArrayList<>();
        List<String> fieldTypeList= new ArrayList<>();

        for (int i = 0; i < fields.length; i++) {

        // for (Field field : fields) {

             field= fields[i].toString().replace(pcg,"");


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
        return fieldNameThList;
    }

    private static String getHtmlMainFileReplaced(Path inputPath,String ClassNm, String classNm,  String GetMapping) throws IOException {
        List<String> lines;
        String targetFolder;
        lines= Files.readAllLines(inputPath, Charset.forName("UTF-8"));

        targetFolder = "html";
        String tabTilte = ClassNm;
        String headerTilte = ClassNm;

        //Repo
        String fileReplaced="";
        for (String line : lines) {
            line = line.replace("JAVA_CODE_TEMPLATEST", targetFolder);
            line = line.replace("<ClassNm>", ClassNm);
            line = line.replace("<classNm>", classNm);
            line = line.replace("<@RequestMapping>","/arkani2/");
            line = line.replace("<@GetMapping>", GetMapping);
            line = line.replace("<tabTilte>", tabTilte);
            line = line.replace("<headerTilte>", headerTilte);
           // System.out.println(line);

            fileReplaced=fileReplaced.concat(line+"\n");

        }
        return fileReplaced;
    }

}

