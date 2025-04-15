package it.pizzeria.pizzeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.pizzeria.pizzeria.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{
    
    public List<Pizza> findByNomeContainingIgnoreCase(String nome);

    boolean existsByNome(String nome);


}
