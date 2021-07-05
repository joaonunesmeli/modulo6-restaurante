package bootcamp.java.mod6.restaurantorders.dto;

import bootcamp.java.mod6.restaurantorders.entity.IEntity;

public class PratoDTO implements IEntity {
    private int id;
    private double preco;
    private String descricao;

    public PratoDTO() {
    }

    public PratoDTO(int id, double preco, String descricao) {
        this.id = id;
        this.preco = preco;
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
