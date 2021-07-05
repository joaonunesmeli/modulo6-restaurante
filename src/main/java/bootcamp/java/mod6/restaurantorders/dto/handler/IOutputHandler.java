package bootcamp.java.mod6.restaurantorders.dto.handler;

public interface IOutputHandler<T, U> {
    U convert(T o);
}
