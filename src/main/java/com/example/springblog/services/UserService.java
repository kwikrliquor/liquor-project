package com.example.springblog.services;

import com.example.springblog.models.UserWithRoles;
import com.example.springblog.repo.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.springblog.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("usersSvc")
public class UserService {

    UserRepository usersRepository;

    public UserService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean isLoggedIn() {
        boolean isAnonymousUser =
                SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
        return ! isAnonymousUser;
    }

    // Returns a user obj directly from the DB
    public User loggedInUser() {

        if (! isLoggedIn()) {
            return null;
        }

        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return usersRepository.findOne(sessionUser.getId()); //NEED TO GET ID LONG
    }

    // Checks if the user is the owner of the post
    public boolean isOwner(User postUser){
        if(isLoggedIn()){
            return (postUser.getUsername().equals(loggedInUser().getUsername()));
        }

        return false;

    }


    // Edit controls are being showed up if the user is logged in and it's the same user viewing the file
    public boolean canEditProfile(User profileUser){
        return isLoggedIn() && (profileUser.getId() == loggedInUser().getId());
    }

    public void authenticate(User user) {
        // Notice how we're using an empty list for the roles
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
