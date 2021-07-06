package bootcamp.java.mod6.restaurantorders.service;

import bootcamp.java.mod6.restaurantorders.dto.handler.IHandler;
import bootcamp.java.mod6.restaurantorders.dto.handler.IInputHandler;
import bootcamp.java.mod6.restaurantorders.dto.handler.IOutputHandler;
import bootcamp.java.mod6.restaurantorders.entity.IEntity;
import bootcamp.java.mod6.restaurantorders.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class IOService<T extends IEntity, U extends IEntity> {
    private OutputService<T, U> outputService;
    private InputService<T, U> inputService;

    public IOService(Repository<T> repository, IHandler<T, U> handler) {
        this.inputService = new InputService<>(repository, handler);
        this.outputService = new OutputService<>(repository, handler);
    }

    public IOService(Repository<T> repository, IInputHandler<T, U> inputHandler) {
        this.inputService = new InputService<>(repository, inputHandler);
    }

    public IOService(Repository<T> repository, IOutputHandler<T, U> outputHandler) {
        this.outputService = new OutputService<>(repository, outputHandler);
    }

    public ResponseEntity<Response<Map<String, Object>>> create(U dto, UriComponentsBuilder uriBuilder) throws IOException {
        return this.inputService.create(dto, uriBuilder);
    }

    public Response<U> get(int id) throws IOException {
        return this.outputService.get(id);
    }

    public Response<List<U>> getAll() throws IOException {
        return this.outputService.getAll();
    }

    public ResponseEntity<Response<Map<String, Object>>> update(int id, U dto) throws IOException {
        return this.inputService.update(id, dto);
    }

    public MapResponse delete(int id) throws IOException {
        return this.inputService.delete(id);
    }
}
