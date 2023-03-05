package pl.arkani.LZ_2022301_LX.model;


import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TechPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long id;
    // @Column(name="`desc`")
    @NotBlank(message = "name is mandatory")
    private String name;

    private String classNm;

    String project;

    //String title;

    String url;

    String tag;

    String button;

    String description;
    String header;

    String content;

    int orderRow;

    int activeRow;

    //String rolesOld;
//
//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false /*optional = false nie pozowli zapisac course bez teacher */)
//    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
//    @ToString.Exclude
//    private TechPageRole role;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name="TechPage_TechPageRole",
            joinColumns = @JoinColumn(name="page_id ",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName ="ID" )
    )
    @ToString.Exclude
    private Set<TechPageRole> roles = new HashSet<>();




}
