package pl.arkani.LZ_2022301_LX.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Table(name="zakupy")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="towar")
    private String name;

    @Column(name="kupione")
    private String purchased ;

    @Column(name="dt_dod")
    private long addDt;

    @Column(name="dt_zm")
    private long modDt;


    @Column(name="stan")
    private String state ;


    @Column(name="kategoria")
    private String category ;

    @Column(name="usun")
    private int toDelete ;

    @Column(name="uwagi")
    private String comment ;

    @Column(name="user_dod")
    private String addBy ;

    @Column(name="user_zm")
    private String modBy ;



//    pub
//    long unixSeconds = 1372339860;
//    // convert seconds to milliseconds
//    Date date = new java.util.Date(unixSeconds*1000L);
//    // the format of your date
//    SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
//// give a timezone reference for formatting (see comment at the bottom)
//sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
//    String formattedDate = sdf.format(date);
//System.out.println(formattedDate);










}
