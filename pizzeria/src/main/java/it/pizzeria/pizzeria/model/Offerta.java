package it.pizzeria.pizzeria.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;


@Entity
public class Offerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La data di inizio è obbligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInizio;

    @NotNull(message = "La data di di fine è obbligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFine;

    private String titolo;
    
    @ManyToOne
    @JoinColumn(name = "pizza_ID", nullable = false)
    @JsonBackReference /* per non avere la ricursione durante le restAPI*/
    private Pizza pizzaEl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Pizza getPizzaEl() {
        return pizzaEl;
    }

    public void setPizzaEl(Pizza pizzaEl) {
        this.pizzaEl = pizzaEl;
    }

}
