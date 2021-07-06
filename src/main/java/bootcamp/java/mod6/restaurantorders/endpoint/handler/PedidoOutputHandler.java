package bootcamp.java.mod6.restaurantorders.endpoint.handler;

import bootcamp.java.mod6.restaurantorders.endpoint.dto.PedidoDTO;
import bootcamp.java.mod6.restaurantorders.endpoint.dto.PratoDTO;
import bootcamp.java.mod6.restaurantorders.entity.Pedido;
import bootcamp.java.mod6.restaurantorders.entity.Prato;

import java.util.ArrayList;
import java.util.List;

public class PedidoOutputHandler implements IOutputHandler<Pedido, PedidoDTO> {
    private PratoHandler pratoHandler;

    public PedidoOutputHandler(PratoHandler pratoHandler) {
        this.pratoHandler = pratoHandler;
    }

    @Override
    public PedidoDTO convert(Pedido o) {
        List<PratoDTO> pratos = new ArrayList<>();
        for (Prato p : o.getPratos()) {
            pratos.add(this.pratoHandler.convert(p));
        }
        return new PedidoDTO(o.getId(), o.getValor(), pratos);
    }
}
