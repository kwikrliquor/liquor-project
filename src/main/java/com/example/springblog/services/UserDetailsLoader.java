package com.example.springblog.services;

import com.example.springblog.models.User;
import com.example.springblog.models.UserWithRoles;
import com.example.springblog.repo.UserRepository;
import com.example.springblog.repo.UserRoleRepository;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {

  private final UserRepository userRepository;
  private final UserRoleRepository roles;

  public UserDetailsLoader(UserRepository userRepository, UserRoleRepository roles) {
    this.userRepository = userRepository;
    this.roles = roles;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("No user found for " + username);
    }

    List<String> userRoles = roles.ofUserWith(username);
    return new UserWithRoles(user, userRoles);
  }

}
