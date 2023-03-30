package pl.arkani.LZ_2022301_LX.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.arkani.LZ_2022301_LX.model.UserDTO;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;
import pl.arkani.LZ_2022301_LX.service.UserService;

import java.net.PortUnreachableException;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserApi {

    private UserService userService;
    private final UserRepo userRepo;

    public UserApi(UserService userService,
                   UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {return userService.getAllUsersDTO();
    }

    @GetMapping ("/users/{userId}")
    public UserDTO getUser(@PathVariable("userId") long userId) {
        return userService.getUserDTO(userId);
    }
}

