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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.pizzeria.pizzeria.model.Ingredienti;
import it.pizzeria.pizzeria.service.IngredienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ingredienti")
public class IngredientiRestController {

    @Autowired
    private IngredienteService ingredienteServiceService;

    @GetMapping
    public List<Ingredienti> getAllIngredienti() {
        return ingredienteServiceService.findAllIngredienti();
    }

    @GetMapping("{id}")
    public Ingredienti getIngredientiByID(@PathVariable Long id) {
        Ingredienti ingredienti = ingredienteServiceService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ingredienti;
    }

    @PostMapping
    public Ingredienti Ingredienti(@Valid @RequestBody Ingredienti ingredienti) {
        return ingredienteServiceService.create(ingredienti);
    }

    @PutMapping("/{id}")
    public Ingredienti put(@PathVariable Long id, @RequestBody Ingredienti ingredienti) {
       return ingredienteServiceService.update(ingredienti);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        ingredienteServiceService.deleteById(id);
    }

}
