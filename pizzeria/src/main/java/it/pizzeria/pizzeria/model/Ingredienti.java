package it.pizzeria.pizzeria.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ingredienti {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Ingrediente non puo essere vuoto!")
    private String ingrediente;

   @ManyToMany(mappedBy="ingredienti")
   @JsonBackReference
   private List<Pizza> pizze;

   public Long getId() {
    return id;
   }

   public void setId(Long id) {
    this.id = id;
   }

   public String getIngrediente() {
    return ingrediente;
   }

   public void setIngrediente(String ingrediente) {
    this.ingrediente = ingrediente;
   }

   public List<Pizza> getPizze() {
    return pizze;
   }

   public void setPizze(List<Pizza> pizze) {
    this.pizze = pizze;
   }
}
