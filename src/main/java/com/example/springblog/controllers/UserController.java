package com.example.springblog.controllers;

import com.example.springblog.models.User;
import com.example.springblog.models.UserRole;
import com.example.springblog.repo.UserRepository;
import com.example.springblog.repo.UserRoleRepository;
import com.example.springblog.services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

  @Autowired
  private UserRepository usersRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRoleRepository userRoles;

  @Autowired
  private UserService userService;

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

  @GetMapping("/profile/edit")
  public String editUser(Model model){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User userC = usersRepository.findOne(user.getId());
    model.addAttribute("user", userC);
    return "users/profile-edit";
  }

  @GetMapping("/profile")
  public String userProfile(@ModelAttribute User thisUser, Model model){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User userC = usersRepository.findOne(user.getId());
    User userInfo = usersRepository.findOne(user.getId());
    model.addAttribute("user", userC);
    model.addAttribute("userInfo", userInfo);

    return "users/profile";
  }

  @PostMapping("/profile")
  public String idUpdate(@RequestParam("upload_url") String idFile) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User userDetails = usersRepository.findOne(user.getId());
    userDetails.setImg_url(idFile);
    userDetails.setAgeVerified(false);
    usersRepository.save(userDetails);
    return "redirect:/profile";
  }

    @PostMapping("/profile/edit")
    public String editUser(User editedUser){
      User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      editedUser.setId(sessionUser.getId());
      usersRepository.save(editedUser);
      return "redirect:/profile";
    }

  // Edit controls are being showed up if the user is logged in and it's the same user viewing the file
  public Boolean checkEditAuth(User user){
    return userService.isLoggedIn() && (user.getId() == userService.loggedInUser().getId());
  }

}