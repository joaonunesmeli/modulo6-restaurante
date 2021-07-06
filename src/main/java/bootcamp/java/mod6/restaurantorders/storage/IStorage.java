package bootcamp.java.mod6.restaurantorders.storage;

import java.io.IOException;

interface IStorage {
    State load() throws IOException;
    void flush() throws IOException;
}
