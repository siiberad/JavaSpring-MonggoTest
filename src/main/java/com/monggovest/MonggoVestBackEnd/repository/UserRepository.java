package com.monggovest.MonggoVestBackEnd.repository;

import com.monggovest.MonggoVestBackEnd.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}