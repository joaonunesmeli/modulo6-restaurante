package bootcamp.java.mod6.restaurantorders.dto.handler;

import bootcamp.java.mod6.restaurantorders.dto.PratoPedidoInputDTO;
import bootcamp.java.mod6.restaurantorders.entity.Prato;
import bootcamp.java.mod6.restaurantorders.repository.PratoRepository;

import java.io.IOException;
import java.util.Map;

public class PratoPedidoHandler implements IHandler<Prato, PratoPedidoInputDTO> {
    private PratoRepository repository;

    public PratoPedidoHandler(PratoRepository repository) {
        this.repository = repository;
    }

    @Override
    public PratoPedidoInputDTO convert(Prato o) {
        return null; // DTO somente de input
    }

    @Override
    public Prato create(PratoPedidoInputDTO o) throws IOException {
        Prato p = repository.getById(o.getId());
        return new Prato(p.getId(), p.getPreco(), p.getDescricao(), o.getQuantidade());
    }

    @Override
    public Map<String, String> validate(PratoPedidoInputDTO o) {
        return null;
    }
}
