package pl.arkani.LZ_2022301_LX.model;


import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TechPageRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long id;
    // @Column(name="`desc`")
    @NotBlank(message = "name is mandatory")
    private String method;
    private String role;

//    String role;

//    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
//    @ToString.Exclude
//    private List<TechPage> pages;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name="TechPage_TechPageRole",
            joinColumns = @JoinColumn(name="role_id ",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="page_id",referencedColumnName ="ID" )
    )
    @ToString.Exclude
    private Set<TechPage> pages = new HashSet<>();







}
