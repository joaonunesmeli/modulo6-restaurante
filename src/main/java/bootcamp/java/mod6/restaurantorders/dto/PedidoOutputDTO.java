package bootcamp.java.mod6.restaurantorders.dto;

import bootcamp.java.mod6.restaurantorders.entity.IEntity;

import java.util.List;

public class PedidoOutputDTO implements IEntity {
    private int id;
    private double valor;
    private List<PratoDTO> pratos;

    public PedidoOutputDTO(int id, double valor, List<PratoDTO> pratos) {
        this.id = id;
        this.valor = valor;
        this.pratos = pratos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<PratoDTO> getPratos() {
        return pratos;
    }

    public void setPratos(List<PratoDTO> pratos) {
        this.pratos = pratos;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }
}
