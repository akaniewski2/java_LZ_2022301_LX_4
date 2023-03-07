package pl.arkani.LZ_2022301_LX.Examples;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

//@Component
//        class SqlRepoExecTest {
//
//
//        private SqlRepoExec sqlRepoExec;
//
//            SqlRepoExecTest(SqlRepoExec sqlRepoExec) {
//                this.sqlRepoExec = sqlRepoExec;
//            }
//
//            @EventListener(ApplicationReadyEvent.class)
//            public void runExample() throws UnknownHostException {
//
//                System.out.println("# sqlRepoExec.SqlRepoExecTest()");
//                sqlRepoExec.SqlRepoExecTest();
//            }
//        }