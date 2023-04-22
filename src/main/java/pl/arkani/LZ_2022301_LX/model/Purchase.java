package pl.arkani.LZ_2022301_LX.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

//@Table(name="zakupy2")
public class Purchase {



    public static class View {
        public interface Id{}
        public interface Basic extends Id{}
        public interface Extended extends Basic{}

    }

//    public interface NewPurchaseValidation {}
//    @JsonView(View.Id.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

  //  @Column(name="towar")
//    @JsonView(View.Basic.class)
    @NotNull
    private String name;
    private String subCategory;

    //  @Column(name="kupione")
//  @JsonView(View.Basic.class)
    private String purchased ;

    String measure;

    double quantity;

   // @Column(name="dt_dod")
//   @JsonView(View.Extended.class)
    private long addDt;

   // @Column(name="dt_zm")
//   @JsonView(View.Extended.class)
    private long modDt;


  //  @Column(name="stan")
  //@JsonView(View.Basic.class)
//  @JsonIgnore
    private String state ;


   // public String getCategory() {
    //    return this.purchaseCategory.getName();
   // }

    //    @Column(name="kategoria")
//    @JsonView(View.Basic.class)
    private String category ;

//    @Column(name="usun")
//@JsonView(View.Extended.class)
    private int toDelete ;

  //  @Column(name="uwagi")
//  @JsonView(View.Basic.class)
    private String comment ;

//    public String getTmp() {
//
//       return user.getUsername();
//
//    }


  //  @Column(name="user_dod")
//  @JsonView(View.Extended.class)
    private String addBy ;

 //   @Column(name="user_zm")
// @JsonView(View.Extended.class)
    private String modBy ;

  //  @Column(name="user_zm_ostatni")
//  @JsonView(View.Extended.class)
    private String lastModBy;

 //   @Column(name="tmp")

    @Nullable
//    @Null(groups = NewPurchaseValidation.class)
    private String tmp ;


//https://www.epochconverter.com/
    public void setAddDt() {
        long epoch = System.currentTimeMillis()/1000;
        this.addDt = epoch;
    }

    public void setModDt() {
        long epoch = System.currentTimeMillis()/1000;
        this.modDt = epoch;
    }


    private String formatEpochDate (long epochDate) {
        String formattedDt = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date (epochDate*1000));
        //Epoch in seconds, remove '*1000' for milliseconds.
        return formattedDt;
    }

    public String getAddDtFormatted()  {

        if (addDt>0) return  formatEpochDate(addDt);
        else return null;
    }

    public String getModDtFormatted()  {
        if (modDt>0) return  formatEpochDate(modDt);
        else return null;
    }

    //Convert from human-readable date to epoch
    //long epoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse("01/01/1970 01:00:00").getTime() / 1000; Timestamp in seconds, remove '/1000' for milliseconds.

    //    pub
//    long unixSeconds = 1372339860;
//    // convert seconds to milliseconds
//    Date date = new java.util.Date(unixSeconds*1000L);
//    // the format of your date
//    SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
//// give a timezone reference for formatting (see comment at the bottom)
//sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
//    String formattedDate = sdf.format(date);
////system.out.println(formattedDate);



    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST,optional = false /*optional = false nie pozowli zapisac course bez teacher */)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ToString.Exclude
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false /*optional = false nie pozowli zapisac course bez teacher */)
    @JoinColumn(name = "PURCHASE_CATEGORY_ID", referencedColumnName = "ID")
    @ToString.Exclude
    @JsonIgnore
    private PurchaseCategory purchaseCategory;




    //testowa metoda
//    @JsonView(View.Basic.class)
   public String upperName() {
       return this.name.toUpperCase();

   }






}
