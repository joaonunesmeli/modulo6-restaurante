package bootcamp.java.mod6.restaurantorders.controller;

import bootcamp.java.mod6.restaurantorders.fakedb.FakeDB;
import bootcamp.java.mod6.restaurantorders.service.MapResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaixaController {
    @GetMapping("/caixa")
    public MapResponse getBalance() {
        MapResponse r = new MapResponse();
        r.put("saldo", FakeDB.getCaixa());
        return r;
    }
}
