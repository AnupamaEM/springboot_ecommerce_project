package com.productproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productproject.demo.entity.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

}
