package bootcamp.java.mod6.restaurantorders.repository;

import bootcamp.java.mod6.restaurantorders.entity.Caixa;
import bootcamp.java.mod6.restaurantorders.storage.State;

import java.io.IOException;
import java.util.Map;

public class CaixaRepository extends Repository<Caixa> {
    public static final int DEFAULT_ID = 0;

    public CaixaRepository() throws IOException {
        Caixa c = this.getById(CaixaRepository.DEFAULT_ID);
        if (c == null) {
            this.save(new Caixa(CaixaRepository.DEFAULT_ID));
        } else {
            this.save(c);
        }
    }

    @Override
    public Caixa getById(int id) throws IOException {
        return super.getById(CaixaRepository.DEFAULT_ID);
    }

    @Override
    public int save(Caixa p) throws IOException {
        return this.update(p);
    }

    @Override
    public int update(Caixa p) throws IOException {
        p.setId(CaixaRepository.DEFAULT_ID);
        return super.update(p);
    }

    @Override
    public void remove(int id) throws IOException {
        super.remove(CaixaRepository.DEFAULT_ID);
    }

    @Override
    protected Map<Integer, Caixa> getState(State s) {
        return s.getCaixas();
    }
}
