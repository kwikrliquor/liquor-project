package com.example.springblog.services;

import com.example.springblog.models.User;
import com.example.springblog.models.UserWithRoles;
import com.example.springblog.repo.UserRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("usersSvc")
public class UserService {

  private UserRepository usersRepository;

  @Autowired
  public UserService(UserRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  public List<User> getAll() {

    return usersRepository.findAll();
  }

  public boolean isLoggedIn() {
    boolean isAnonymousUser =
        SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
    return ! isAnonymousUser;
  }

  public User loggedInUser() {
    if (!isLoggedIn()) {
      return null;
    }
    User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return usersRepository.findOne(sessionUser.getId()); //NEED TO GET ID LONG
  }

  public boolean isOwner(User postUser){
    if(isLoggedIn()){
      return (postUser.getUsername().equals(loggedInUser().getUsername()));
    }
    return false;
  }

  public boolean canEditProfile(User profileUser){
    return isLoggedIn() && (profileUser.getId() == loggedInUser().getId());
  }

  public void authenticate(User user) {
    UserDetails userDetails = new UserWithRoles(user, Collections.emptyList());
    Authentication auth = new UsernamePasswordAuthenticationToken(
        userDetails,
        userDetails.getPassword(),
        userDetails.getAuthorities()
    );
    SecurityContext context = SecurityContextHolder.getContext();
    context.setAuthentication(auth);
  }

  public User edit(User user) {
    usersRepository.save(user);
    return user;
  }

}
