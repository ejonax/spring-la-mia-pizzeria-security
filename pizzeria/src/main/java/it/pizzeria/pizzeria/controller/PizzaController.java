package it.pizzeria.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.pizzeria.pizzeria.model.Offerta;
import it.pizzeria.pizzeria.model.Pizza;
import it.pizzeria.pizzeria.repository.PizzaRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizze")
public class PizzaController {

    @Autowired
   private PizzaRepository pizzaRepository;
   
   public PizzaController(PizzaRepository pizzaRepository){
       this.pizzaRepository=pizzaRepository;

   }

    @GetMapping
    public String index(Model model) {
        List<Pizza> result=pizzaRepository.findAll();
        model.addAttribute("listaPizze",result);

        return "pizze/index";
    }
    

    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Optional<Pizza> optPizza = pizzaRepository.findById(id);
        if (optPizza.isPresent()) {
            model.addAttribute("pizza", pizzaRepository.findById(id).get());
            return "/pizze/show";
        }

        model.addAttribute("errorCause",
                "Non esiste una pizza con id " + id);
        model.addAttribute("errorMessage",
                "Errore di ricerca della pizza");

        return "/errors/error";
    }

    /* START edit session */

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        return "/pizze/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
           @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult,
            Model model) {
       
        Optional<Pizza> optPizza = pizzaRepository.findById(formPizza.getId());

        if (optPizza.isPresent()) {

             /* nome non è vuoto */
            if (formPizza.getNome().equals("") && formPizza.getNome().trim().isEmpty()) {
                bindingResult.addError(new ObjectError("nome", "Bisognerebbe che scrivi un nome per la pizza!"));
           
            }

            /* prezzo non è <=0 */
            if (formPizza.getPrezzo()<=0){
                bindingResult.addError(new ObjectError("prezzo", "Il prezzo della pizza deve essere maggiore di 0!"));
            }

            /* gli ingredienti non sono vuoti */
            if (formPizza.getDescrizione().equals("") && formPizza.getDescrizione().trim().isEmpty()) {
                bindingResult.addError(new ObjectError("descrizione", "Bisognerebbe che listi gli ingredienti della pizza!"));
           
            }

            /* l'url non è campo vuoto */
            if (formPizza.getFoto().equals("") && formPizza.getFoto().trim().isEmpty()) {
                bindingResult.addError(new ObjectError("foto", "Bisognerebbe che scrivi un url per la foto!"));
           
            }
        
        }
        if (bindingResult.hasErrors()) {
            return "/pizze/edit";
        }
        pizzaRepository.save(formPizza);

        return "redirect:/pizze";
    }

    /* END edit session */

    /* START create session */
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/pizze/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

 
        /* il nome non è vuoto */
        if (formPizza.getNome() == null || formPizza.getNome().trim().isEmpty()) {
            bindingResult.rejectValue("nome", "invalid", "Bisognerebbe che inserisci il nome della pizza!");
        }

        if (pizzaRepository.existsByNome(formPizza.getNome()))
        {
            bindingResult.rejectValue("nome", "duplicate", "Questa pizza esiste già, cambi il nome");
        }

         /* prezzo non è <=0 */
         if (formPizza.getPrezzo() == null || formPizza.getPrezzo() <= 0) {
            bindingResult.rejectValue("prezzo", "invalid", "Il prezzo della pizza deve essere maggiore di 0!");
        }
        

        /* gli ingredienti non sono vuoti */
        if (formPizza.getDescrizione() == null || formPizza.getDescrizione().trim().isEmpty()) {
            bindingResult.rejectValue("descrizione", "invalid", "Bisognerebbe che listi gli ingredienti della pizza!");
        }
        

        /* l'url non è campo vuoto */
        if (formPizza.getFoto().equals("") && formPizza.getFoto().trim().isEmpty()) {
            bindingResult.rejectValue("foto", "invalid", "Bisognerebbe che scrivi un url per la foto!");
       
        }

        if (bindingResult.hasErrors()) {
            return "/pizze/create";
        }
    
        pizzaRepository.save(formPizza);
        redirectAttributes.addFlashAttribute("successMessage", "Nuova pizza creata!");
        return "redirect:/pizze";

        }
       
    
     /* END create session */

     /* START delete session */
     @PostMapping("/delete/{id}")
     public String delete(@PathVariable("id") Integer id) {
 
        // Pizza pizza = pizzaRepository.findById(id).get();
        
         pizzaRepository.deleteById(id);
         return "redirect:/pizze";
     }

      /* END delete session */

      /* create form per una nuova offerta quando siamo dentro il pizza/show */
      @GetMapping("/{id}/offerta")
      public String offerta(@PathVariable Integer id, Model model) {
          Offerta offerta = new Offerta();
          offerta.setPizzaEl(pizzaRepository.findById(id).get());
        
  
          model.addAttribute("offerta", offerta);
          model.addAttribute("editMode", false);
          return "/offerte/edit";
      }
}
