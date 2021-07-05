package bootcamp.java.mod6.restaurantorders.dto.handler;

import java.util.Map;

public interface IInputHandler<T, U> {
    T create(U o);

    Map<String, String> validate(U o);
}
