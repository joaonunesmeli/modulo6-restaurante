package bootcamp.java.mod6.restaurantorders.controller;

import bootcamp.java.mod6.restaurantorders.endpoint.dto.PratoDTO;
import bootcamp.java.mod6.restaurantorders.endpoint.handler.PratoHandler;
import bootcamp.java.mod6.restaurantorders.entity.Prato;
import bootcamp.java.mod6.restaurantorders.repository.PratoRepository;
import bootcamp.java.mod6.restaurantorders.service.IOService;
import bootcamp.java.mod6.restaurantorders.service.MapResponse;
import bootcamp.java.mod6.restaurantorders.service.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class PratoController {
    private IOService<Prato, PratoDTO> controller;

    public PratoController() {
        PratoHandler handler = new PratoHandler();
        PratoRepository pratoRepository = new PratoRepository();
        this.controller = new IOService<>(pratoRepository, handler);
    }

    @PostMapping("/pratos")
    public ResponseEntity<Response<Map<String, Object>>> create(@RequestBody PratoDTO dto, UriComponentsBuilder uriBuilder) throws IOException {
        return controller.create(dto, uriBuilder);
    }

    @GetMapping("/pratos/{id}")
    public Response<PratoDTO> get(@PathVariable int id) throws IOException {
        return controller.get(id);
    }

    @GetMapping("/pratos")
    public Response<List<PratoDTO>> getAlls() throws IOException {
        return controller.getAll();
    }

    @PutMapping("/pratos/{id}")
    public ResponseEntity<Response<Map<String, Object>>> update(@PathVariable int id, @RequestBody PratoDTO dto) throws IOException {
        return controller.update(id, dto);
    }

    @DeleteMapping("/pratos/{id}")
    public MapResponse delete(@PathVariable int id) throws IOException {
        return controller.delete(id);
    }
}
