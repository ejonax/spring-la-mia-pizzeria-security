package it.pizzeria.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.pizzeria.pizzeria.model.Ingredienti;
import it.pizzeria.pizzeria.service.IngredienteService;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {

  @Autowired
   private IngredienteService ingredienteService;
   
   /* 
   public IngredienteController(IngredientiRepository ingredienteRepository){
       this.ingredienteRepository=ingredienteRepository;

   }
    */

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("list", ingredienteService.findAllIngredienti());
        model.addAttribute("ingredienteObj", new Ingredienti());
        return "/ingredienti/index";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredienteObj") Ingredienti ingredienti,
            BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            ingredienteService.create(ingredienti);
        }
        return "redirect:/ingredienti";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {

        //dopo che abbiamo delete ingredientID dalle pizze che lo contenevano allora canceliamo ingrediente dalla lista degli ingredienti
        ingredienteService.deleteById(id);
        return "redirect:/ingredienti";
    }

}
