package com.user.database.repository;

import com.user.database.model.UserMasterData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserMasterData, Long> {


    @Query(value = "SELECT * FROM user_master WHERE  LOWER(email) LIKE LOWER(CONCAT('%', :email, '%')) ", nativeQuery = true)
    UserMasterData findByAndEmail(
            @Param("email") String email);

    @Query(value = "SELECT * FROM user_master WHERE _id = :userId LIMIT 1", nativeQuery = true)
    UserMasterData findByMainId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_master um WHERE  um._id = :userId ", nativeQuery = true)
    int removeUserDetail(@Param("userId") Long userId);


}