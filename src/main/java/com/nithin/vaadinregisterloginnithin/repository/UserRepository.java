package com.nithin.vaadinregisterloginnithin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nithin.vaadinregisterloginnithin.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
