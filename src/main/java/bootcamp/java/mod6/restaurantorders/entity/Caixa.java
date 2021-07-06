package bootcamp.java.mod6.restaurantorders.entity;

public class Caixa implements IEntity {
    private int id;
    private double saldo;

    public Caixa() {
    }

    public Caixa(int id) {
        this.id = id;
    }

    public Caixa(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Caixa{" +
                "id=" + id +
                ", saldo=" + saldo +
                '}';
    }
}
