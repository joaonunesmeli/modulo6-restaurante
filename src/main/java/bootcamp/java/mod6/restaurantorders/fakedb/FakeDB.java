package bootcamp.java.mod6.restaurantorders.fakedb;

import bootcamp.java.mod6.restaurantorders.entity.Mesa;
import bootcamp.java.mod6.restaurantorders.entity.Prato;

public final class FakeDB {
    private FakeDB() {}

    private static double caixa = 0.0;
    private static Repository<Mesa> mesas = new Repository<>();
    private static Repository<Prato> pratos = new Repository<>();

    public static void pagar(double x) {
        caixa += x;
    }

    public static double getCaixa() {
        return caixa;
    }

    public static Repository<Mesa> getMesas() {
        return mesas;
    }

    public static Repository<Prato> getPratos() {
        return pratos;
    }
}
