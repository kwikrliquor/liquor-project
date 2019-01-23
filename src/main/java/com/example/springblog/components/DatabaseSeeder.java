package com.example.springblog.components;

import com.example.springblog.models.Category;
import com.example.springblog.models.Product;
import com.example.springblog.models.User;
import com.example.springblog.models.UserRole;
import com.example.springblog.repo.CategoryRepository;
import com.example.springblog.repo.ProductRepository;
import com.example.springblog.repo.UserRepository;
import com.example.springblog.repo.UserRoleRepository;
import com.example.springblog.repo.Users;
import java.util.Arrays;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final UserRoleRepository roleRepo;
  private final CategoryRepository catRepo;
  private final ProductRepository prodRepo;
  private final UserRepository userRepo;
  private final PasswordEncoder passwordEncoder;
  private final Users users;

  @Value("development")
  private String environment;


  @Autowired
  public DatabaseSeeder(UserRoleRepository roleRepo,
      CategoryRepository catRepo, ProductRepository prodRepo,
      UserRepository userRepo, PasswordEncoder passwordEncoder,
      Users users) {
    this.roleRepo = roleRepo;
    this.catRepo = catRepo;
    this.prodRepo = prodRepo;
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
    this.users = users;
  }

//  @EventListener
//  public void seed(ContextRefreshedEvent event) {
//    seedRoles();
//    seedCategories();
//    seedUsers();
//    seedProducts();
//  }

  private void seedRoles() {
      UserRole roles[] = {
          new UserRole("ROLE_ADMIN",1),
          new UserRole("ROLE_DRIVER", 2),
          new UserRole("ROLE_CUSTOMER", 3)
      };
      roleRepo.save(Arrays.asList(roles));
  }

  private void seedCategories() {
      Category types[] = {
          new Category("beer"),
          new Category("wine"),
          new Category("liquor")
      };
      catRepo.save(Arrays.asList(types));
  }

  private void seedUsers() {
      User users[] = {
          new User("admin", "admin@gmail.com", passwordEncoder.encode("password")),
          new User("driver", "driver@gmail.com", passwordEncoder.encode("password")),
          new User("customer", "customer@gmail.com", passwordEncoder.encode("password"))
      };
      userRepo.save(Arrays.asList(users));
  }

  private void seedProducts() {
      Product products[] = {
          new Product("Beer", "12 pack of beer", catRepo.findByName("beer"), userRepo.findByUsername("admin")),
          new Product("Wine", "Big bottle of red wine", catRepo.findByName("wine"), userRepo.findByUsername("admin")),
          new Product("Liquor", "Russian Vodka", catRepo.findByName("liquor"), userRepo.findByUsername("admin"))
      };
      prodRepo.save(Arrays.asList(products));
  }

  @Override
  public void run(String... strings) throws Exception {
    if (! environment.equals("development")) {
      log.info("app.env is not development, doing nothing.");
      return;
    }
    log.info("Deleting products...");
    prodRepo.deleteAll();
    log.info("Deleting users...");
    userRepo.deleteAll();
    log.info("Deleting categories...");
    catRepo.deleteAll();
    log.info("Deleting roles...");
    roleRepo.deleteAll();
    log.info("Seeding roles...");
    seedRoles();
    log.info("Seeding categories...");
    seedCategories();
    log.info("Seeding users...");
    seedUsers();
    log.info("Seeding products...");
    seedProducts();
    log.info("Finished running seeders!");
  }

}
