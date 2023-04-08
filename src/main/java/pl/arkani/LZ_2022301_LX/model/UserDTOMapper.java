package pl.arkani.LZ_2022301_LX.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserDTOMapper {
//    @Override
//    public UserDTO apply(User user) {
//        return new UserDTO(
//                user.getId(),
//                user.getEmail(),
//                user.getUsername()//,
////                user.getAuthorities()
////                    .stream()
////                    .map(GrantedAuthority::getAuthority)
////                    .collect(Collectors.toList())
//        );
//
//
//
//   }
//
//    @Override
//    public UserDTO apply(User user) {
//        return UserDTO.fromEntity(user);
//
//    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        //    user.setUuid(this.getUuid());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setPassword2(userDTO.getPassword2());
        user.setRoles(userDTO.getRoles());
        user.setCode(userDTO.getCode());
        user.setEnabled(userDTO.isEnabled());
        user.setAuthority(userDTO.getAuthority());
        user.setRole(userDTO.getRole());
        user.setEmail(userDTO.getEmail());
        user.setPurchases(userDTO.getPurchases());
        return user;
    }

    public  UserDTO toDTO(User user) {
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



