package it.pizzeria.pizzeria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.pizzeria.pizzeria.model.Ingredienti;

public interface IngredientiRepository extends JpaRepository<Ingredienti, Long>{

    Optional<Ingredienti> findByIngredienteIgnoreCase(String ingrediente);
}
