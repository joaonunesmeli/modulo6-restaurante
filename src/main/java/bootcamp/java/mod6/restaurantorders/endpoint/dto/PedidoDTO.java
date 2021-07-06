package bootcamp.java.mod6.restaurantorders.endpoint.dto;

import bootcamp.java.mod6.restaurantorders.entity.IEntity;

import java.util.List;

public class PedidoDTO implements IEntity {
    private int id;
    private double valor;
    private List<PratoDTO> pratos;

    public PedidoDTO(int id, double valor, List<PratoDTO> pratos) {
        this.id = id;
        this.valor = valor;
        this.pratos = pratos;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<PratoDTO> getPratos() {
        return this.pratos;
    }

    public void setPratos(List<PratoDTO> pratos) {
        this.pratos = pratos;
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
