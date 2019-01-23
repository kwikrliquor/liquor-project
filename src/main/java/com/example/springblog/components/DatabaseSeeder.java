package com.example.springblog.components;

import com.example.springblog.models.UserRole;
import com.example.springblog.repo.ProductRepository;
import com.example.springblog.repo.UserRepository;
import com.example.springblog.repo.UserRoleRepository;
import com.example.springblog.repo.Users;
import java.util.Arrays;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {
  private final UserRoleRepository roleRepo;
  private final ProductRepository prodRepo;
  private final UserRepository userRepo;
  private final PasswordEncoder passwordEncoder;
  private final Users users;

  @Autowired
  public DatabaseSeeder(UserRoleRepository roleRepo, ProductRepository prodRepo,
      UserRepository userRepo, PasswordEncoder passwordEncoder,
      Users users) {
    this.roleRepo = roleRepo;
    this.prodRepo = prodRepo;
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
    this.users = users;
  }

  @EventListener
  public void seed(ContextRefreshedEvent event) {
    seedRoles();
    seedCategories();

  }

  private void seedRoles() {
    long count = StreamSupport.stream(
        roleRepo.findAll().spliterator(),
        false)
        .count();
    if (count < 3) {
      UserRole roles[] = {
          new UserRole("ROLE_ADMIN"),
          new UserRole("ROLE_DRIVER"),
          new UserRole("ROLE_CUSTOMER")
      };
      roleRepo.save(Arrays.asList(roles));
    }
  }





}
