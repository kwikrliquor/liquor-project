package com.example.springblog.repo;

import com.example.springblog.models.UserRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
//    @Query("select r.role from Role r, User u where u.username=?1 and r.id = u.role.id")
//    public List<String> ofUserWith(String username);
  @Query("select ur.role from UserRole ur, User u where u.username=?1 and ur.userId = u.id")
  List<String> ofUserWith(String username);

}
