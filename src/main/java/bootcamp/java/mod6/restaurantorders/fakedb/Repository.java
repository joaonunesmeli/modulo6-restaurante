package bootcamp.java.mod6.restaurantorders.fakedb;

import bootcamp.java.mod6.restaurantorders.entity.IEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository<T extends IEntity> {
    private Map<Integer, T> entidades = new HashMap<>();
    private int nextID;

    public T getById(int id) {
        return entidades.get(id);
    }

    public List<T> getAll() {
        return new ArrayList<>(entidades.values());
    }

    public int save(T p) {
        int id = nextID++;
        p.setId(id);

        entidades.put(id, p);
        return id;
    }

    public int update(T p) {
        entidades.put(p.getId(), p);
        return p.getId();
    }

    public void remove(int id) {
        entidades.remove(id);
    }
}
