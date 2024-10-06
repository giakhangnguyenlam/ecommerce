package com.khangnlg.repositories;

import com.khangnlg.entities.UserEntity;
import com.khangnlg.models.UserCommentCus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("Select u From user u Where u.username = :username")
    Optional<UserEntity> findByUserName(@Param("username") String username);

    @Query("Select u, c\n" +
            "From user u left join comment c on u.id = c.username\n" +
            "Where u.address = :username")
    List<Object[]> getCommentByUserName(@Param("username") String username);

    @Query(value = "select u.id, u.username, c.comment \n" +
            "from user u, comment c  \n" +
            "where u.id in \n" +
            "(select c.username \n" +
            "where c.username = 21\n" +
            "union\n" +
            "select c.star  \n" +
            "where u.id  = 22)", nativeQuery = true)
    List<UserCommentCus> findUserCommentCus();


}
