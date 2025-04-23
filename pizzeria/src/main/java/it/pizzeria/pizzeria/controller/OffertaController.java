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

import it.pizzeria.pizzeria.model.Offerta;
import it.pizzeria.pizzeria.repository.OffertaRepository;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/offerte")
public class OffertaController {

    @Autowired
    private OffertaRepository repositoryOfferta;
   
    /*
    @Autowired
    private PizzaRepository pizzaRepo;
  
    @GetMapping("/create")
    public String create(@RequestParam Integer pizzaId, Model model) {
        Offerta offerta = new Offerta();
        Pizza pizza = pizzaRepo.findById(pizzaId).orElseThrow();
        offerta.setPizzaEl(pizza);
        model.addAttribute("offerta", offerta);
        return "offerte/edit";
    }
    */


    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offerta") Offerta formOfferta,
                         BindingResult bindingResult,
                         Model model) {

            if(bindingResult.hasErrors()) {
                model.addAttribute("editMode", false);
                model.addAttribute("offerta", formOfferta);
                return "/offerte/edit";
            }
            repositoryOfferta.save(formOfferta);

            return "redirect:/pizze/show/" + formOfferta.getPizzaEl().getId();
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Offerta offertaEl=repositoryOfferta.findById(id).get();
        model.addAttribute("offerta", offertaEl);
        model.addAttribute("editMode", true);
        return "offerte/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offerta") Offerta offerta,
                          BindingResult bindingResult, 
                          Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("editMode", true);
            model.addAttribute("offerta", offerta);
            return "/offerte/edit";
        }

         repositoryOfferta.save(offerta);

        // Redirect alla pagina della pizza collegata
        return "redirect:/pizze/show/" + offerta.getPizzaEl().getId();
    }
    
}

