package bootcamp.java.mod6.restaurantorders.endpoint.form;

import bootcamp.java.mod6.restaurantorders.entity.IEntity;

public class PratoPedidoForm implements IEntity {
    private int id;
    private int quantidade;

    public PratoPedidoForm() {
    }

    public PratoPedidoForm(int id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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
