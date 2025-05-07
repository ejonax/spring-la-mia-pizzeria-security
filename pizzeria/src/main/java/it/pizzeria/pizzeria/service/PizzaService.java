package it.pizzeria.pizzeria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.pizzeria.pizzeria.model.Offerta;
import it.pizzeria.pizzeria.model.Pizza;
import it.pizzeria.pizzeria.repository.IngredientiRepository;
import it.pizzeria.pizzeria.repository.OffertaRepository;
import it.pizzeria.pizzeria.repository.PizzaRepository;

@Service
public class PizzaService {

    private PizzaRepository pizzaRepository;
    private IngredientiRepository ingredientiRepository;
    private OffertaRepository offertaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, IngredientiRepository ingredientiRepository,
            OffertaRepository offertaRepository) {
        this.pizzaRepository = pizzaRepository;
        this.ingredientiRepository = ingredientiRepository;
        this.offertaRepository = offertaRepository;
    }

    /*lista delle pizze */
    public List<Pizza> findPizzaList(String nome){
        List<Pizza> pizzaResult=new ArrayList<>();

        try {
            if(nome!=null && !nome.isBlank()){
                pizzaResult=pizzaRepository.findByNomeContainingIgnoreCase(nome);
            }
            else {
                pizzaResult=pizzaRepository.findAll();
            }
            
        } catch (Exception e) {
            System.err.println("Errore durante la lettura delle info delle pizze: "+ e);
        }
     
        return pizzaResult;
    }

      /*lista delle pizze */
    public List<Pizza> findAllPizza(){
        List<Pizza> pizzaResult=new ArrayList<>();
        pizzaResult=pizzaRepository.findAll();

        return pizzaResult;
    }

    /*cercare la pizza con ID */
    public Optional<Pizza> findPizzaById (Long id){
        try {
            return pizzaRepository.findById(id);
        } catch (Exception e) {
            System.err.println("errore durante la lettura della pizza con ID: "+ id);
            return null;
        }
    }

    /*salvare una nuova pizza */
    public Pizza create(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    /*aggiornare una pizza */
    public Pizza update(Pizza pizza){
        Optional<Pizza> pizzaOptional=pizzaRepository.findById(pizza.getId());

        if (pizzaOptional.isEmpty()){
            throw new IllegalArgumentException("Impossibile aggiornale la pizza senza l'id");
        }

        return pizzaRepository.save(pizza);
    }

    /* cancelare una  pizza */
    public void deleteById(Long id){
        Pizza pizza=pizzaRepository.findById(id).get();
        /* canceliamo prime le offerte collegate a questa pizza */
        for (Offerta offertaElemento : pizza.getOfferte()) {
            offertaRepository.deleteById(offertaElemento.getId());
        }

        pizzaRepository.deleteById(id);
    }

}
