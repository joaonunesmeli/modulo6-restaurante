package bootcamp.java.mod6.restaurantorders.storage;

import java.io.*;

public class Storage {
    private static Storage instance = new Storage();

    // private IStorage storage = new MemoryStorage();
    private IStorage storage = new FileStorage();

    private Storage() {}

    public State load() throws IOException {
        return this.storage.load();
    }

    public void flush() throws IOException {
        this.storage.flush();
    }

    public static Storage getInstance() {
        return instance;
    }
}
