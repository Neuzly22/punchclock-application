package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.PrincipalUser;
import ch.zli.m223.punchclock.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public void create(ApplicationUser applicationUser) {
        this.userRepository.saveAndFlush(applicationUser);
    }

    public List<ApplicationUser> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public PrincipalUser loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new PrincipalUser(user.getUsername(), user.getPassword(), user.getId(),
            Collections.emptyList());
    }
}
