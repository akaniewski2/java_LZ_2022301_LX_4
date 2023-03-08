package pl.arkani.LZ_2022301_LX.Examples;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;



@Service
public class SqlRepoExec {


    private SqlRepo sqlRepo;

    public SqlRepoExec(SqlRepo sqlRepo) {
        this.sqlRepo = sqlRepo;
    }




        public List<String> getTableHeaders(/*@Param("tableName")*/String tableName) {


            List <String> row ;//= new ArrayList<>();

            List<List<String>> rows = new ArrayList<List<String>>();

        String sql = """
                select column_name,"cos"
                from  information_schema.COLUMNS c
                where table_schema='arkani_1' and table_name=':tableName'     
                order by ordinal_position
                """  ;
         sql=sql.replace(":tableName",tableName);

            System.out.println("# sql:"+sql);

            List<Object[]> wynik;
            wynik = sqlRepo.SqlSelectAll(sql);


            List<String> columns = new ArrayList<>();
            for (Object[] a:wynik) {
                columns.add(String.valueOf(a[0]));


            }

            System.out.println("# columns:" );
            columns.forEach( System.out::println);
            return  columns;



        }
         public List<List<String>> getTableData (String sql) {
        List <String> row ;//= new ArrayList<>();

        List<List<String>> rows = new ArrayList<List<String>>();
        List <String> rows2 = new ArrayList<>();




        //List <String> rows2 = new ArrayList<>();






        List<Object[]> wynik;
        wynik = sqlRepo.SqlSelectAll(sql);

      //  wynik.forEach(System.out::println);


        int aLen;
        for (Object[] a:wynik) {

           aLen =  a.length;


            row = new ArrayList<>();
           // row.clear();
            for (int i = 0; i <a.length ; i++) {



//
//
//                System.out.println( "----------------------------");
//                System.out.println(a[i]);
//                System.out.println("MOD:" +(i % a.length-1));
//                System.out.println( "----------------------------");
                row.add(String.valueOf(a[i]));




            }
//            System.out.println( "----------------------------");
            rows.add(row);



        }


   //     System.out.println( "--------------------");
       // row.forEach(System.out::println);
     //   System.out.println( "---------------");
   //     rows.forEach(System.out::println);


        return rows;
    }

//    @Component
//    class SqlRepoExecTest {
//
//
//        private SqlRepoExec sqlRepoExec;
//
//        SqlRepoExecTest(SqlRepoExec sqlRepoExec) {
//            this.sqlRepoExec = sqlRepoExec;
//        }
//
//        @EventListener(ApplicationReadyEvent.class)
//        public void runExample() throws UnknownHostException {
//
//            System.out.println("# sqlRepoExec.SqlRepoExecTest()");
//            sqlRepoExec.sqlRepoExecTest("select * from arkani_1.user");
//
//
//        }
//    }




}
