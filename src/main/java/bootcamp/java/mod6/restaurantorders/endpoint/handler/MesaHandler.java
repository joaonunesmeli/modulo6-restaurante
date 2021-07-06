package bootcamp.java.mod6.restaurantorders.endpoint.handler;

import bootcamp.java.mod6.restaurantorders.endpoint.dto.MesaDTO;
import bootcamp.java.mod6.restaurantorders.endpoint.dto.PedidoDTO;
import bootcamp.java.mod6.restaurantorders.entity.Mesa;
import bootcamp.java.mod6.restaurantorders.entity.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MesaHandler implements IHandler<Mesa, MesaDTO> {
    private PedidoOutputHandler pedidoOutputHandler;

    public MesaHandler(PedidoOutputHandler pedidoOutputHandler) {
        this.pedidoOutputHandler = pedidoOutputHandler;
    }

    @Override
    public Mesa create(MesaDTO o) {
        return new Mesa();
    }

    @Override
    public Map<String, String> validate(MesaDTO o) {
        return null;
    }

    @Override
    public MesaDTO convert(Mesa o) {
        double total = 0.0;
        List<PedidoDTO> pedidos = new ArrayList<>();
        if (o.getPedidos() != null) {
            for (Pedido p : o.getPedidos()) {
                pedidos.add(this.pedidoOutputHandler.convert(p));
                total += p.getValor();
            }
        }
        return new MesaDTO(o.getId(), total, pedidos);
    }
}
