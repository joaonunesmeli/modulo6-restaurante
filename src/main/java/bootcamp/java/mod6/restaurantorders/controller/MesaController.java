package bootcamp.java.mod6.restaurantorders.controller;

import bootcamp.java.mod6.restaurantorders.dto.MesaDTO;
import bootcamp.java.mod6.restaurantorders.dto.PedidoInputDTO;
import bootcamp.java.mod6.restaurantorders.dto.PedidoOutputDTO;
import bootcamp.java.mod6.restaurantorders.dto.handler.*;
import bootcamp.java.mod6.restaurantorders.entity.Caixa;
import bootcamp.java.mod6.restaurantorders.entity.Mesa;
import bootcamp.java.mod6.restaurantorders.entity.Pedido;
import bootcamp.java.mod6.restaurantorders.repository.CaixaRepository;
import bootcamp.java.mod6.restaurantorders.repository.MesaRepository;
import bootcamp.java.mod6.restaurantorders.repository.PratoRepository;
import bootcamp.java.mod6.restaurantorders.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MesaController {
    private IOService<Mesa, MesaDTO> mesaCrud;

    public MesaController() {
        PratoHandler pratoHandler = new PratoHandler();
        MesaHandler handler = new MesaHandler(new PedidoOutputHandler(pratoHandler));
        MesaRepository mesaRepository = new MesaRepository();
        this.mesaCrud = new IOService<>(mesaRepository, handler);
    }

    @PostMapping("/mesas")
    public ResponseEntity<Response<Map<String, Object>>> create(UriComponentsBuilder uriBuilder) throws IOException {
        return mesaCrud.create(null, uriBuilder);
    }

    @GetMapping("/mesas/{id}")
    public Response<MesaDTO> get(@PathVariable int id) throws IOException {
        return mesaCrud.get(id);
    }

    @GetMapping("/mesas/")
    public Response<List<MesaDTO>> getAll() throws IOException {
        return mesaCrud.getAll();
    }

    @DeleteMapping("/mesas/{id}")
    public MapResponse delete(@PathVariable int id) throws IOException {
        return mesaCrud.delete(id);
    }

    // Pedidos ---------------------------------------------------------------------------------------------------------

    @PostMapping("/mesas/{mid}/pedidos")
    public MapResponse createOrder(@PathVariable int mid, @RequestBody PedidoInputDTO dto) throws IOException {
        MapResponse r = new MapResponse(HttpStatus.CREATED);
        MesaRepository mesaRepository = new MesaRepository();
        Mesa mesa = mesaRepository.getById(mid);
        if (mesa == null) {
            r.addError("mesa", "Mesa não encontrada");
            return r;
        }

        List<Pedido> pedidos = mesa.getPedidos();

        if (pedidos == null) {
            pedidos = new ArrayList<>();
            mesa.setPedidos(pedidos);
        }
        int newID = pedidos.size() < 1 ? 0 : 1 + pedidos.get(pedidos.size() - 1).getId();

        PratoPedidoHandler pratoPedidoHandler = new PratoPedidoHandler(new PratoRepository());
        PedidoInputHandler pedidoInputHandler = new PedidoInputHandler(mesaRepository, pratoPedidoHandler);
        dto.setMesa(mid);
        dto.setId(newID);
        pedidos.add(pedidoInputHandler.create(dto));
        mesaRepository.update(mesa);

        r.put("mesa", mid);
        r.put("id", newID);
        return r;
    }

    @GetMapping("/mesas/{mid}/pedidos/{id}")
    public Response<PedidoOutputDTO> getOrder(@PathVariable int mid, @PathVariable int id) throws IOException {
        MesaRepository mesaRepository = new MesaRepository();
        Response<PedidoOutputDTO> r = new Response<PedidoOutputDTO>();
        Mesa mesa = mesaRepository.getById(mid);
        if (mesa == null) {
            r.addError("mesa", "Mesa não encontrada");
            return r;
        }

        Optional<Pedido> opt = mesa.getPedidos().stream().filter(p -> p.getId() == id).findFirst();
        if (!opt.isPresent()) {
            r.addError("pedido", "Pedido não encontrada");
            return r;
        }

        PedidoOutputHandler pedidoOutputHandler = new PedidoOutputHandler(new PratoHandler());
        r.setData(pedidoOutputHandler.convert(opt.get()));
        mesaRepository.update(mesa);
        return r;
    }

    @DeleteMapping("/mesas/{mid}/pedidos/{id}")
    public MapResponse deleteOrder(@PathVariable int mid, @PathVariable int id) throws IOException {
        MapResponse r = new MapResponse();
        MesaRepository mesaRepository = new MesaRepository();
        Mesa mesa = mesaRepository.getById(mid);
        if (mesa == null) {
            r.addError("mesa", "Mesa não encontrada");
            return r;
        }

        mesa.getPedidos().removeIf(p -> p.getId() == id);
        mesaRepository.update(mesa);
        r.put("mesa", mid);
        r.put("id", id);
        return mesaCrud.delete(id);
    }

    @GetMapping("/mesas/{mid}/fechar")
    public MapResponse getBill(@PathVariable int mid) throws IOException {
        MapResponse r = new MapResponse();
        MesaRepository mesaRepository = new MesaRepository();
        Mesa mesa = mesaRepository.getById(mid);
        if (mesa == null) {
            r.addError("mesa", "Mesa não encontrada");
            r.put("total", 0.0);
            return r;
        }

        double total = 0.0;
        List<Pedido> pedidos = mesa.getPedidos();
        if (pedidos != null) {
            for (Pedido p : mesa.getPedidos()) {
                total += p.getValor();
            }
        }

        mesa.setPedidos(new ArrayList<>());
        mesaRepository.update(mesa);
        r.put("total", total);
        CaixaRepository caixaRepository = new CaixaRepository();
        double saldo = caixaRepository.getById(CaixaRepository.DEFAULT_ID).getSaldo();
        caixaRepository.save(new Caixa(total + saldo));
        return r;
    }
}
