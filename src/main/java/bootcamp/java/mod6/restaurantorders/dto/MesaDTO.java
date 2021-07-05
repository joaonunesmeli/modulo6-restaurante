package bootcamp.java.mod6.restaurantorders.dto;

import bootcamp.java.mod6.restaurantorders.entity.IEntity;

import java.util.List;

public class MesaDTO implements IEntity {
    private int id;
    private double valorTotalConsumido;
    private List<PedidoOutputDTO> pedidos;

    public MesaDTO() {
    }

    public MesaDTO(int id, double valorTotalConsumido, List<PedidoOutputDTO> pedidos) {
        this.id = id;
        this.pedidos = pedidos;
        this.valorTotalConsumido = valorTotalConsumido;
    }

    public List<PedidoOutputDTO> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoOutputDTO> pedidos) {
        this.pedidos = pedidos;
    }

    public double getValorTotalConsumido() {
        return valorTotalConsumido;
    }

    public void setValorTotalConsumido(double valorTotalConsumido) {
        this.valorTotalConsumido = valorTotalConsumido;
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
