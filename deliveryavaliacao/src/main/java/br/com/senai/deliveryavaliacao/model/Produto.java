package br.com.senai.deliveryavaliacao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;

@Entity 
@Table (name = "produto")
public class Produto {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank (message = "O nome do produto é obrigatório")
    private String nome;

    @Column(precision = 10, scale = 2)
    @NotNull (message = "O preço do produto é obrigatório")
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    @NotNull (message = "O ID do restaurante é obrigatório")
    private Restaurante restaurante;

    public Produto() {
    }

    public Produto(Integer id, String nome, BigDecimal preco, Restaurante restaurante) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.restaurante = restaurante;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
