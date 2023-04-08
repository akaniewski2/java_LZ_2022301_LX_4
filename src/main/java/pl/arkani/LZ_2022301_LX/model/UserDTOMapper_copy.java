package pl.arkani.LZ_2022301_LX.model;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper_copy implements Function<User, UserDTO> {
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

    @Override
    public UserDTO apply(User user) {
        return UserDTO.fromEntity(user);

    }




}



