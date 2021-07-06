package bootcamp.java.mod6.restaurantorders.controller;

import bootcamp.java.mod6.restaurantorders.entity.Caixa;
import bootcamp.java.mod6.restaurantorders.repository.CaixaRepository;
import bootcamp.java.mod6.restaurantorders.repository.Repository;
import bootcamp.java.mod6.restaurantorders.service.MapResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CaixaController {

    @GetMapping("/caixa")
    public MapResponse getBalance() throws IOException {
        Repository<Caixa> repo = new CaixaRepository();
        MapResponse r = new MapResponse();
        double saldo = repo.getById(CaixaRepository.DEFAULT_ID).getSaldo();
        r.put("saldo", saldo);
        return r;
    }
}
