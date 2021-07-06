package bootcamp.java.mod6.restaurantorders.entity;

import java.util.List;

public class Mesa implements IEntity {
    private int id;
    private List<Pedido> pedidos;

    public Mesa() {}

    public Mesa(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
