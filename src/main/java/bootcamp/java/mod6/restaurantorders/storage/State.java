package bootcamp.java.mod6.restaurantorders.storage;

import bootcamp.java.mod6.restaurantorders.entity.Caixa;
import bootcamp.java.mod6.restaurantorders.entity.Mesa;
import bootcamp.java.mod6.restaurantorders.entity.Prato;

import java.util.HashMap;
import java.util.Map;

public class State {
    private Map<Integer, Caixa> caixas;
    private Map<Integer, Mesa> mesas;
    private Map<Integer, Prato> pratos;

    public State() {
        this.caixas = new HashMap<>();
        this.mesas = new HashMap<>();
        this.pratos = new HashMap<>();
    }

    public Map<Integer, Caixa> getCaixas() {
        return this.caixas;
    }

    public void setCaixa(Map<Integer, Caixa> caixa) {
        this.caixas = caixas;
    }

    public Map<Integer, Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(Map<Integer, Mesa> mesas) {
        this.mesas = mesas;
    }

    public Map<Integer, Prato> getPratos() {
        return pratos;
    }

    public void setPratos(Map<Integer, Prato> pratos) {
        this.pratos = pratos;
    }

    @Override
    public String toString() {
        return "State{" +
                "caixas=" + caixas +
                ", mesas=" + mesas +
                ", pratos=" + pratos +
                '}';
    }
}
