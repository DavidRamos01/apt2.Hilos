import java.util.ArrayList;

public class Barco {

    private ArrayList<Pasajero> pasajeros;

    public Barco(ArrayList<Pasajero> listaPasajeros) {
        this.pasajeros = new ArrayList<>();
        this.pasajeros.addAll(listaPasajeros);
    }


    public synchronized Pasajero sacarSiguientePasajero() {
        if (pasajeros.isEmpty()) {
            return null;
        }

        Pasajero pasajeroPrioritario = null;
        int prioridadMasAlta = 4;

        for (Pasajero p : pasajeros) {
            if (p.getPrioridad() < prioridadMasAlta) {
                prioridadMasAlta = p.getPrioridad();
            }
        }

        for (int i = 0; i < pasajeros.size(); i++) {
            Pasajero p = pasajeros.get(i);
            if (p.getPrioridad() == prioridadMasAlta) {
                pasajeroPrioritario = pasajeros.remove(i);
                break;
            }
        }

        return pasajeroPrioritario;
    }

    public synchronized boolean hayPasajeros() {
        return !pasajeros.isEmpty();
    }

    public synchronized int getPasajerosRestantes() {
        return pasajeros.size();
    }
}