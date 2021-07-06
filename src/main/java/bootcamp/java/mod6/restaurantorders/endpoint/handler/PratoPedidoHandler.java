package bootcamp.java.mod6.restaurantorders.endpoint.handler;

import bootcamp.java.mod6.restaurantorders.endpoint.form.PratoPedidoForm;
import bootcamp.java.mod6.restaurantorders.entity.Prato;
import bootcamp.java.mod6.restaurantorders.repository.PratoRepository;

import java.io.IOException;
import java.util.Map;

public class PratoPedidoHandler implements IHandler<Prato, PratoPedidoForm> {
    private PratoRepository repository;

    public PratoPedidoHandler(PratoRepository repository) {
        this.repository = repository;
    }

    @Override
    public PratoPedidoForm convert(Prato o) {
        return null; // DTO somente de input
    }

    @Override
    public Prato create(PratoPedidoForm o) throws IOException {
        Prato p = repository.getById(o.getId());
        return new Prato(p.getId(), p.getPreco(), p.getDescricao(), o.getQuantidade());
    }

    @Override
    public Map<String, String> validate(PratoPedidoForm o) {
        return null;
    }
}
