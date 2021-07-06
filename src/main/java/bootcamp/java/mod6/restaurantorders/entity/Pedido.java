package bootcamp.java.mod6.restaurantorders.entity;

import java.util.List;

public class Pedido implements IEntity {
    private int id;
    private Mesa mesa;
    private List<Prato> pratos;
    private double valor;

    public Pedido() {}

    public Pedido(int id, Mesa mesa, List<Prato> pratos, double valor) {
        this.id = id;
        this.mesa = mesa;
        this.pratos = pratos;
        this.valor = valor;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mesa getMesa() {
        return this.mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
