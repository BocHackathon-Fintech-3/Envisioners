package io.finhash.core.service;

import io.finhash.core.domain.User;
import io.finhash.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class Du {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void t() {
        User user = new User("chris", "user", "chris@infinittex.com", "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=");
        user.setRoles(Arrays.asList(User.Role.ROLE_USER));
        //userRepository.save(user).subscribe();
        //System.out.printf("\n\n\n\nuser has been written....");
    }
}
