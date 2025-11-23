import java.util.ArrayList;
import java.util.List;

public class Barco {

    private final List<Pasajero> pasajeros = new ArrayList<>();

    public Barco(List<Pasajero> pasajeros) {
        this.pasajeros.addAll(pasajeros);
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public boolean hayPasajeros(){
        return !pasajeros.isEmpty();
    }

}