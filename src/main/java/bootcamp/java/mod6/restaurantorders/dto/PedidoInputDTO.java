package bootcamp.java.mod6.restaurantorders.dto;

import bootcamp.java.mod6.restaurantorders.entity.IEntity;

import java.util.List;

public class PedidoInputDTO implements IEntity {
    private int id;
    private int mesa;
    private List<PratoPedidoInputDTO> pratos;

    public PedidoInputDTO(int mesa, List<PratoPedidoInputDTO> pratos) {
        this.pratos = pratos;
        this.mesa = mesa;
    }

    public List<PratoPedidoInputDTO> getPratos() {
        return pratos;
    }

    public void setPratos(List<PratoPedidoInputDTO> pratos) {
        this.pratos = pratos;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
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
