package bootcamp.java.mod6.restaurantorders.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

class FileStorage implements IStorage {
    private File file = new File("db.json");
    private ObjectMapper mapper = new ObjectMapper();
    private State state;

    @Override
    public State load() throws IOException {
        if (!this.file.exists()) {
            this.state = new State();
            return this.state;
        }

        FileInputStream is = new FileInputStream(file);
        TypeReference<State> tr = new TypeReference<State>() {};
        this.state = mapper.readValue(is, tr);
        is.close();
        return this.state;
    }

    @Override
    public void flush() throws IOException {
        mapper.writeValue(file, state);
    }
}
