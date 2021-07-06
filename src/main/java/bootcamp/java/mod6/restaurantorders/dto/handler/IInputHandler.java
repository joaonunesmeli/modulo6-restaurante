package bootcamp.java.mod6.restaurantorders.dto.handler;

import java.io.IOException;
import java.util.Map;

public interface IInputHandler<T, U> {
    T create(U o) throws IOException;

    Map<String, String> validate(U o);
}
