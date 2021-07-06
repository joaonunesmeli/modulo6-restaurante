package bootcamp.java.mod6.restaurantorders.endpoint.handler;

public interface IOutputHandler<T, U> {
    U convert(T o);
}
