package org.tku.database.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.tku.database.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
