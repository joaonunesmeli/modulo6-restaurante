package bootcamp.java.mod6.restaurantorders.service;

import bootcamp.java.mod6.restaurantorders.endpoint.handler.IInputHandler;
import bootcamp.java.mod6.restaurantorders.entity.IEntity;
import bootcamp.java.mod6.restaurantorders.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

public final class InputService<T extends IEntity, U extends IEntity> {
    private Repository<T> repository;
    private IInputHandler<T, U> inputHandler;

    public InputService(Repository<T> repository, IInputHandler<T, U> inputHandler) {
        this.repository = repository;
        this.inputHandler = inputHandler;
    }

    public ResponseEntity<Response<Map<String, Object>>> create(U dto, UriComponentsBuilder uriBuilder) throws IOException {
        MapResponse resp = new MapResponse(HttpStatus.CREATED);
        resp.setError(inputHandler.validate(dto));
        if (resp.hasError()) {
            return resp.createResponseEntity();
        }

        int id = repository.save(inputHandler.create(dto));
        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(id).toUri();
        resp.put("id", id);
        return resp.createResponseEntity(uri);
    }

    public ResponseEntity<Response<Map<String, Object>>> update(int id, U dto) throws IOException {
        MapResponse resp = new MapResponse();
        resp.setError(inputHandler.validate(dto));
        if (resp.hasError()) {
            return resp.createResponseEntity();
        }

        dto.setId(id);
        repository.update(inputHandler.create(dto));
        resp.put("id", id);
        return resp.createResponseEntity();
    }

    public MapResponse delete(int id) throws IOException {
        MapResponse resp = new MapResponse();
        repository.remove(id);
        resp.put("id", id);
        return resp;
    }
}
