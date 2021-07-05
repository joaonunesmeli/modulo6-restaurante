package bootcamp.java.mod6.restaurantorders.controller;

import bootcamp.java.mod6.restaurantorders.dto.PratoDTO;
import bootcamp.java.mod6.restaurantorders.dto.handler.PratoHandler;
import bootcamp.java.mod6.restaurantorders.entity.Prato;
import bootcamp.java.mod6.restaurantorders.fakedb.FakeDB;
import bootcamp.java.mod6.restaurantorders.fakedb.Repository;
import bootcamp.java.mod6.restaurantorders.service.IOService;
import bootcamp.java.mod6.restaurantorders.service.MapResponse;
import bootcamp.java.mod6.restaurantorders.service.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
public class PratoController {
    private IOService<Prato, PratoDTO> controller;

    public PratoController() {
        PratoHandler handler = new PratoHandler();
        Repository<Prato> repository = FakeDB.getPratos();
        this.controller = new IOService<>(repository, handler);
    }

    @PostMapping("/pratos")
    public ResponseEntity<Response<Map<String, Object>>> create(@RequestBody PratoDTO dto, UriComponentsBuilder uriBuilder) {
        return controller.create(dto, uriBuilder);
    }

    @GetMapping("/pratos/{id}")
    public Response<PratoDTO> get(@PathVariable int id) {
        return controller.get(id);
    }

    @GetMapping("/pratos")
    public Response<List<PratoDTO>> getAlls() {
        return controller.getAll();
    }

    @PutMapping("/pratos/{id}")
    public ResponseEntity<Response<Map<String, Object>>> update(@PathVariable int id, @RequestBody PratoDTO dto) {
        return controller.update(id, dto);
    }

    @DeleteMapping("/pratos/{id}")
    public MapResponse delete(@PathVariable int id) {
        return controller.delete(id);
    }
}
