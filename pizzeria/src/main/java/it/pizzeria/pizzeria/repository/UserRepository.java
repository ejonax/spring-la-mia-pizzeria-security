package it.pizzeria.pizzeria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.pizzeria.pizzeria.model.User;


public interface UserRepository extends JpaRepository<User, Integer>{

public Optional<User> findByUsername(String username);
}
