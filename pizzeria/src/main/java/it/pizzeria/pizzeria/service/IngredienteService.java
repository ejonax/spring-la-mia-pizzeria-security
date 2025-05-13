package it.pizzeria.pizzeria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.pizzeria.pizzeria.model.Ingredienti;
import it.pizzeria.pizzeria.model.Pizza;
import it.pizzeria.pizzeria.repository.IngredientiRepository;

@Service
public class IngredienteService {

    @Autowired
    private IngredientiRepository ingredientiRepository;

    public List<Ingredienti> findAllIngredienti(){
        return ingredientiRepository.findAll();
    }

    public Optional<Ingredienti> findById(Long id){
        return ingredientiRepository.findById(id);
    }

    public boolean existsByNome(String ingrediente) {
        return ingredientiRepository.findByIngredienteIgnoreCase(ingrediente).isPresent();
    }

    public Ingredienti create(Ingredienti ingredienti){
        return ingredientiRepository.save(ingredienti);
    }

    public Ingredienti update(Ingredienti ingredienti){
        return ingredientiRepository.save(ingredienti);
    }

    public void deleteById(Long id){

        Ingredienti ing=ingredientiRepository.findById(id).get();

        for (Pizza pizzaElement : ing.getPizze()) {
            pizzaElement.getIngredienti().remove(ing);
            
        }
        
        ingredientiRepository.deleteById(id);
    }

}
