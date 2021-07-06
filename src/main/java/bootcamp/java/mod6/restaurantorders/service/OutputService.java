package bootcamp.java.mod6.restaurantorders.service;

import bootcamp.java.mod6.restaurantorders.endpoint.handler.IOutputHandler;
import bootcamp.java.mod6.restaurantorders.entity.IEntity;
import bootcamp.java.mod6.restaurantorders.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class OutputService<T extends IEntity, U extends IEntity> {
    private Repository<T> repository;
    private IOutputHandler<T, U> outputHandler;

    public OutputService(Repository<T> repository, IOutputHandler<T, U> outputHandler) {
        this.repository = repository;
        this.outputHandler = outputHandler;
    }

    public Response<U> get(int id) throws IOException {
        Response<U> r = new Response<U>();
        T o = repository.getById(id);
        if (o != null) {
            r.setData(outputHandler.convert(o));
        }
        return r;
    }

    public Response<List<U>> getAll() throws IOException {
        List<U> list = new ArrayList<>();
        for (T o : repository.getAll()) {
            list.add(outputHandler.convert(o));
        }
        Response<List<U>> r = new Response<List<U>>();
        r.setData(list);
        return r;
    }
}
