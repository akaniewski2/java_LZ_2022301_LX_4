package pl.arkani.LZ_2022301_LX.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
/*
import java.util.List;

public record UserDTO(
        long id,
        
        String username,
        String email

//       , List<String> roles



){

}
*/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private long id;
    //private UUID uuid;
    private String username;
    private String password;
    private String password2;
    private String roles;
    private String Code;
    private boolean enabled;
    private String authority;
    private String role;
    private String email;
    private List<Purchase> purchases;

  

    public User toEntity() {
        User user = new User();
        user.setId(this.getId());
    //    user.setUuid(this.getUuid());
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());
        user.setPassword2(this.getPassword2());
        user.setRoles(this.getRoles());
        user.setCode(this.getCode());
        user.setEnabled(this.isEnabled());
        user.setAuthority(this.getAuthority());
        user.setRole(this.getRole());
        user.setEmail(this.getEmail());
        user.setPurchases(this.getPurchases());
        return user;
    }

    public static UserDTO fromEntity(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        //  userDto.setUuid(user.getUuid());
        userDto.setUsername(user.getUsername());
        // userDto.setPassword(user.getPassword());
        //  userDto.setPassword2(user.getPassword2());
        userDto.setRoles(user.getRoles());
        userDto.setCode(user.getCode());
        userDto.setEnabled(user.isEnabled());
        userDto.setAuthority(user.getAuthority());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());
        userDto.setPurchases(user.getPurchases());
        return userDto;
    }
}
