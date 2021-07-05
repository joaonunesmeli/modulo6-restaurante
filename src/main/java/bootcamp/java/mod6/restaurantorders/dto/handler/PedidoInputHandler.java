package bootcamp.java.mod6.restaurantorders.dto.handler;

import bootcamp.java.mod6.restaurantorders.dto.PedidoInputDTO;
import bootcamp.java.mod6.restaurantorders.dto.PratoPedidoInputDTO;
import bootcamp.java.mod6.restaurantorders.entity.Mesa;
import bootcamp.java.mod6.restaurantorders.entity.Pedido;
import bootcamp.java.mod6.restaurantorders.entity.Prato;
import bootcamp.java.mod6.restaurantorders.fakedb.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PedidoInputHandler implements IInputHandler<Pedido, PedidoInputDTO> {
    private Repository<Mesa> mesaRepository;

    private PratoPedidoHandler pratoPedidoHandler;

    public PedidoInputHandler(Repository<Mesa> mesaRepository, PratoPedidoHandler pratoPedidoHandler) {
        this.pratoPedidoHandler = pratoPedidoHandler;
        this.mesaRepository = mesaRepository;
    }

    @Override
    public Pedido create(PedidoInputDTO o) {
        List<Prato> pratos = new ArrayList<>();
        for (PratoPedidoInputDTO ppi : o.getPratos()) {
            pratos.add(pratoPedidoHandler.create(ppi));
        }

        double total = 0.0;
        for (Prato p : pratos) {
            total += p.getQuantidade() * p.getPreco();
        }

        Mesa m = this.mesaRepository.getById(o.getMesa());
        return new Pedido(o.getId(), m, pratos, total);
    }

    @Override
    public Map<String, String> validate(PedidoInputDTO o) {
        return null;
    }
}
