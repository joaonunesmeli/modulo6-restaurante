package bootcamp.java.mod6.restaurantorders.endpoint.form;

import bootcamp.java.mod6.restaurantorders.entity.IEntity;

import java.util.List;

public class PedidoForm implements IEntity {
    private int id;
    private int mesa;
    private List<PratoPedidoForm> pratos;

    public PedidoForm(int mesa, List<PratoPedidoForm> pratos) {
        this.pratos = pratos;
        this.mesa = mesa;
    }

    public List<PratoPedidoForm> getPratos() {
        return pratos;
    }

    public void setPratos(List<PratoPedidoForm> pratos) {
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
