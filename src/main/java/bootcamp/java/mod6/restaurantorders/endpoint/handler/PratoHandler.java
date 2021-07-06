package bootcamp.java.mod6.restaurantorders.endpoint.handler;

import bootcamp.java.mod6.restaurantorders.endpoint.dto.PratoDTO;
import bootcamp.java.mod6.restaurantorders.entity.Prato;

import java.util.Map;

public class PratoHandler implements IHandler<Prato, PratoDTO> {
    @Override
    public PratoDTO convert(Prato o) {
        return new PratoDTO(o.getId(), o.getPreco(), o.getDescricao());
    }

    @Override
    public Prato create(PratoDTO o) {
        return new Prato(o.getId(), o.getPreco(), o.getDescricao(), 0);
    }

    @Override
    public Map<String, String> validate(PratoDTO o) {
        return null;
    }
}
