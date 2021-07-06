package bootcamp.java.mod6.restaurantorders.repository;

import bootcamp.java.mod6.restaurantorders.entity.Prato;
import bootcamp.java.mod6.restaurantorders.storage.State;

import java.util.Map;

@org.springframework.stereotype.Repository
public class PratoRepository extends Repository<Prato> {
    @Override
    protected Map<Integer, Prato> getState(State s) {
        return s.getPratos();
    }
}
