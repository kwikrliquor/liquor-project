package com.example.springblog.controllers;

import com.example.springblog.models.User;
import com.example.springblog.models.UserRole;
import com.example.springblog.repo.UserRepository;
import com.example.springblog.repo.UserRoleRepository;
import com.example.springblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository userRoles;

    @Autowired
    private UserService usersService;

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@Valid User user, Errors validation, Model m){
        String username = user.getUsername();
        User existingUsername = usersRepository.findByUsername(username);
        User existingEmail = usersRepository.findByEmail(user.getEmail());


        if(existingUsername != null){

            validation.rejectValue("username", "user.username", "Duplicated username " + username);

        }

        if(existingEmail != null){

            validation.rejectValue("email", "user.email", "Duplicated email " + user.getEmail());

        }

        if (validation.hasErrors()) {
            m.addAttribute("errors", validation);
            m.addAttribute("user", user);
            return "users/sign-up";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Custom validation if the username is taken

        User newUser = usersRepository.save(user);

        UserRole ur = new UserRole();
        ur.setRole("ROLE_CUSTOMER");
        ur.setUserId(newUser.getId());
        userRoles.save(ur);

        m.addAttribute("user", user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }

    @GetMapping("/profile/edit/{id}")
    public String editUser(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("sessionUser", usersService.loggedInUser());
        model.addAttribute("showEditControls", usersService.canEditProfile(user));
        return "users/profile";
    }

    @PostMapping("/profile/edit/{id}")
    public String editUser(@Valid User editedUser, Errors validation, Model m){

//        editedUser.setId(id);

//        if (validation.hasErrors()) {
//            m.addAttribute("errors", validation);
//            m.addAttribute("user", editedUser);
//            m.addAttribute("showEditControls", checkEditAuth(editedUser));
//            return "users/edit";
//        }
        editedUser.setPassword(passwordEncoder.encode(editedUser.getPassword()));
        usersRepository.save(editedUser);
        return "redirect:/products";
    }

    // Edit controls are being showed up if the user is logged in and it's the same user viewing the file
    public Boolean checkEditAuth(User user){
        return usersService.isLoggedIn() && (user.getId() == usersService.loggedInUser().getId());
    }
}