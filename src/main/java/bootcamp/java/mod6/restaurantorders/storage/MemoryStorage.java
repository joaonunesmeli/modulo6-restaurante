package bootcamp.java.mod6.restaurantorders.storage;

import java.io.IOException;

class MemoryStorage implements IStorage {
    private State state = new State();

    @Override
    public State load() throws IOException {
        return this.state;
    }

    @Override
    public void flush() throws IOException {
        // não faz nada
    }
}
