//package pl.arkani.LZ_2022301_LX.model;
//
//import jakarta.persistence.*;
//
//
//@Entity
//public class Token {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String value;
//
//    @OneToOne
//    private User user;
//
//    public Token() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
//
//    public User getAppUser() {
//        return user;
//    }
//
//    public void setAppUser(User user) {
//        this.user = user;
//    }
//}
//
