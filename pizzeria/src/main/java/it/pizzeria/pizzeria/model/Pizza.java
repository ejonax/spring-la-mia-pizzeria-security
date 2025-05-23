package it.pizzeria.pizzeria.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true,nullable=false)
   // @NotBlank(message = "Il nome è obbligatorio")
    private String nome;
    
    @Column(length=80)
    //@NotBlank(message = "Bisognerebbe che listi gli ingredienti della pizza!")
    private String descrizione;

    private String foto;

    @Column(nullable=false)
   // @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di 0")
    private Double prezzo;

    @OneToMany(mappedBy="pizzaEl")
    private List<Offerta> offerteList;

    @ManyToMany()
    @JoinTable(
            name = "pizza_ingredienti",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingredienti> ingredienti;

    public Long getId() {
        return id;
    }

    /*  id è PK ed autoincrement, non si deve set up-are */
    public void setId(Long id) {
        this.id = id;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }


    public List<Offerta> getOfferte() {
        return offerteList;
    }


    public void setOfferte(List<Offerta> offerte) {
        this.offerteList = offerte;
    }

    public List<Ingredienti> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<Ingredienti> ingredienti) {
        this.ingredienti = ingredienti;
    }

}
