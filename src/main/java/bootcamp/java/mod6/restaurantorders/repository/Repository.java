package bootcamp.java.mod6.restaurantorders.repository;

import bootcamp.java.mod6.restaurantorders.entity.Caixa;
import bootcamp.java.mod6.restaurantorders.entity.IEntity;
import bootcamp.java.mod6.restaurantorders.storage.Storage;
import bootcamp.java.mod6.restaurantorders.storage.State;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Repository<T extends IEntity> {
    private int nextID;

    public T getById(int id) throws IOException {
        State s = Storage.getInstance().load();
        Map<Integer, T> entidades = this.getState(s);
        return entidades.get(id);
    }

    public List<T> getAll() throws IOException {
        State s = Storage.getInstance().load();
        Map<Integer, T> entidades = this.getState(s);
        return new ArrayList<>(entidades.values());
    }

    public int save(T p) throws IOException {
        State s = Storage.getInstance().load();
        Map<Integer, T> entidades = this.getState(s);

        int id = nextID++;
        p.setId(id);

        entidades.put(id, p);
        Storage.getInstance().flush();
        return id;
    }

    public int update(T p) throws IOException {
        State s = Storage.getInstance().load();
        Map<Integer, T> entidades = this.getState(s);

        entidades.put(p.getId(), p);
        Storage.getInstance().flush();
        return p.getId();
    }

    public void remove(int id) throws IOException {
        State s = Storage.getInstance().load();
        Map<Integer, T> entidades = this.getState(s);
        entidades.remove(id);
        Storage.getInstance().flush();
    }

    protected abstract Map<Integer, T> getState(State s);
}
