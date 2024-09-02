package com.darkthor.Repository;

import com.darkthor.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(@Param("email") String email);

}
