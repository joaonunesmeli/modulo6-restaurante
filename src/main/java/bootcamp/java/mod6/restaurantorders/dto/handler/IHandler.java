package bootcamp.java.mod6.restaurantorders.dto.handler;

import java.util.Map;

public interface IHandler<T, U> extends IInputHandler<T, U>, IOutputHandler<T, U> {
}
