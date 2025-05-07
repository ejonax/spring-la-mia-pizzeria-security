package it.pizzeria.pizzeria.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.pizzeria.pizzeria.model.Pizza;
import it.pizzeria.pizzeria.service.PizzaService;
import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/pizze")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> getAllPizze(@RequestParam(name="keyword", required=false) String param) {
        if(param !=null){
            return pizzaService.findPizzaList(param);
        }
        return pizzaService.findAllPizza();
    }
    
    @GetMapping("{id}")
    public Pizza getPizzaByID(@PathVariable Long id) {
        Pizza pizza = pizzaService.findPizzaById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return pizza;
    }
    
    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        return pizzaService.create(pizza);
    }

    @PutMapping("/{id}")
    public Pizza put(@PathVariable Long id, @RequestBody Pizza pizza) {
       return pizzaService.update(pizza);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        pizzaService.deleteById(id);
    }
    

}
