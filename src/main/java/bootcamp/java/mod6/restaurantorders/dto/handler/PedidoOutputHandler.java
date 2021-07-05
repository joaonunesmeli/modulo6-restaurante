package bootcamp.java.mod6.restaurantorders.dto.handler;

import bootcamp.java.mod6.restaurantorders.dto.PedidoOutputDTO;
import bootcamp.java.mod6.restaurantorders.dto.PratoDTO;
import bootcamp.java.mod6.restaurantorders.entity.Pedido;
import bootcamp.java.mod6.restaurantorders.entity.Prato;

import java.util.ArrayList;
import java.util.List;

public class PedidoOutputHandler implements IOutputHandler<Pedido, PedidoOutputDTO> {
    private PratoHandler pratoHandler;

    public PedidoOutputHandler(PratoHandler pratoHandler) {
        this.pratoHandler = pratoHandler;
    }

    @Override
    public PedidoOutputDTO convert(Pedido o) {
        List<PratoDTO> pratos = new ArrayList<>();
        for (Prato p : o.getPratos()) {
            pratos.add(this.pratoHandler.convert(p));
        }
        return new PedidoOutputDTO(o.getId(), o.getValor(), pratos);
    }
}
