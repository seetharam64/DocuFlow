package com.docuflow.userauthentication.repository;

import com.docuflow.userauthentication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    public UserEntity findByuserName(String userName);
}
