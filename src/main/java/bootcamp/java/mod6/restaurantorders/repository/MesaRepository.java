package bootcamp.java.mod6.restaurantorders.repository;

import bootcamp.java.mod6.restaurantorders.entity.Mesa;
import bootcamp.java.mod6.restaurantorders.storage.State;

import java.util.Map;

@org.springframework.stereotype.Repository
public class MesaRepository extends Repository<Mesa> {
    @Override
    protected Map<Integer, Mesa> getState(State s) {
        return s.getMesas();
    }
}
