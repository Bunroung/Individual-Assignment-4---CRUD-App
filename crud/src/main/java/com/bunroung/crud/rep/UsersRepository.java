package com.bunroung.crud.rep;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bunroung.crud.models.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

}
