package bootcamp.java.mod6.restaurantorders.service;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class MapResponse extends Response<Map<String, Object>> {
    public MapResponse() {
        super();
        setData(new HashMap<>());
    }

    public MapResponse(HttpStatus status) {
        super(status);
        super.setData(new HashMap<>());
    }

    public void put(String k, Object v) {
        getData().put(k, v);
    }
}
