package bootcamp.java.mod6.restaurantorders.controller;

import bootcamp.java.mod6.restaurantorders.dto.MesaDTO;
import bootcamp.java.mod6.restaurantorders.dto.PedidoInputDTO;
import bootcamp.java.mod6.restaurantorders.dto.PedidoOutputDTO;
import bootcamp.java.mod6.restaurantorders.dto.handler.*;
import bootcamp.java.mod6.restaurantorders.entity.Mesa;
import bootcamp.java.mod6.restaurantorders.entity.Pedido;
import bootcamp.java.mod6.restaurantorders.fakedb.FakeDB;
import bootcamp.java.mod6.restaurantorders.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MesaController {
    private IOService<Mesa, MesaDTO> mesaCrud;

    public MesaController() {
        PratoHandler pratoHandler = new PratoHandler();
        PedidoOutputHandler pedidoOutputHandler = new PedidoOutputHandler(pratoHandler);
        MesaHandler handler = new MesaHandler(pedidoOutputHandler);
        this.mesaCrud = new IOService<>(FakeDB.getMesas(), handler);
    }

    @PostMapping("/mesas")
    public ResponseEntity<Response<Map<String, Object>>> create(UriComponentsBuilder uriBuilder) {
        return mesaCrud.create(null, uriBuilder);
    }

    @GetMapping("/mesas/{id}")
    public Response<MesaDTO> get(@PathVariable int id) {
        return mesaCrud.get(id);
    }

    @GetMapping("/mesas/")
    public Response<List<MesaDTO>> getAll() {
        return mesaCrud.getAll();
    }

    @DeleteMapping("/mesas/{id}")
    public MapResponse delete(@PathVariable int id) {
        return mesaCrud.delete(id);
    }

    // Pedidos ---------------------------------------------------------------------------------------------------------

    @PostMapping("/mesas/{mid}/pedidos")
    public MapResponse createOrder(@PathVariable int mid, @RequestBody PedidoInputDTO dto) {
        MapResponse r = new MapResponse(HttpStatus.CREATED);
        Mesa mesa = FakeDB.getMesas().getById(mid);
        if (mesa == null) {
            r.addError("mesa", "Mesa não encontrada");
            return r;
        }


        PratoPedidoHandler pratoPedidoHandler = new PratoPedidoHandler(FakeDB.getPratos());
        PedidoInputHandler handler = new PedidoInputHandler(FakeDB.getMesas(), pratoPedidoHandler);
        List<Pedido> pedidos = mesa.getPedidos();

        if (pedidos == null) {
            pedidos = new ArrayList<>();
            mesa.setPedidos(pedidos);
        }
        int newID = pedidos.size() < 1 ? 0 : 1 + pedidos.get(pedidos.size() - 1).getId();

        dto.setMesa(mid);
        dto.setId(newID);
        pedidos.add(handler.create(dto));

        r.put("mesa", mid);
        r.put("id", newID);
        return r;
    }

    @GetMapping("/mesas/{mid}/pedidos/{id}")
    public Response<PedidoOutputDTO> getOrder(@PathVariable int mid, @PathVariable int id) {
        Response<PedidoOutputDTO> r = new Response<PedidoOutputDTO>();
        Mesa mesa = FakeDB.getMesas().getById(mid);
        if (mesa == null) {
            r.addError("mesa", "Mesa não encontrada");
            return r;
        }

        Optional<Pedido> opt = mesa.getPedidos().stream().filter(p -> p.getId() == id).findFirst();
        if (!opt.isPresent()) {
            r.addError("pedido", "Pedido não encontrada");
            return r;
        }

        PratoHandler pratoHandler = new PratoHandler();
        PedidoOutputHandler handler = new PedidoOutputHandler(pratoHandler);
        r.setData(handler.convert(opt.get()));
        return r;
    }

    @DeleteMapping("/mesas/{mid}/pedidos/{id}")
    public MapResponse deleteOrder(@PathVariable int mid, @PathVariable int id) {
        MapResponse r = new MapResponse();
        Mesa mesa = FakeDB.getMesas().getById(mid);
        if (mesa == null) {
            r.addError("mesa", "Mesa não encontrada");
            return r;
        }

        mesa.getPedidos().removeIf(p -> p.getId() == id);
        r.put("mesa", mid);
        r.put("id", id);
        return mesaCrud.delete(id);
    }

    @GetMapping("/mesas/{mid}/fechar")
    public MapResponse getBill(@PathVariable int mid) {
        MapResponse r = new MapResponse();
        Mesa mesa = FakeDB.getMesas().getById(mid);
        if (mesa == null) {
            r.addError("mesa", "Mesa não encontrada");
            r.put("total", 0.0);
            return r;
        }

        double total = 0.0;
        List<Pedido> pedidos = mesa.getPedidos();
        if (pedidos == null) {
            for (Pedido p : mesa.getPedidos()) {
                total += p.getValor();
            }
        }

        mesa.setPedidos(new ArrayList<>());
        r.put("total", total);
        FakeDB.pagar(total);
        return r;
    }
}
