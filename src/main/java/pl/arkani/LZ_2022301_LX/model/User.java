package pl.arkani.LZ_2022301_LX.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Table(name = "user")
public class User implements UserDetails {

    public User(String name, String email) {
        this.username = name;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private UUID uuid;
    
    @NotBlank(message = "Name is mandatory")
    @Column(unique=true)
    private String username;



   // @Pattern(regexp ="(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$" )
    @Size( min=6,max=20)
    private String password;

    @Transient
    private String password2;


    private String roles;


    private String Code;
    private boolean enabled;


    private String authority;


//    @Enumerated(EnumType.STRING)
//    private Role role;
    private String role ="USER_ROLE";

    @NotBlank(message = "Email is mandatory")
    private String email;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(role));
    }

//    @Override
//    public String getUsername() {
//        return email;
//    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    @Override
//    public boolean isEnabled() {
//        return true;
//    }


    // standard constructors / setters / getters / toString

    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Purchase> purchases;
}