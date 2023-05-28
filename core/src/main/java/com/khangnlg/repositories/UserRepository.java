package com.khangnlg.repositories;

import com.khangnlg.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("Select u From user u Where u.username = :username")
    Optional<UserEntity> findByUserName(@Param("username") String username);
}
