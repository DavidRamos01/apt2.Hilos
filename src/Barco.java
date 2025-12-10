import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Barco {

    private ArrayList<Pasajero> pasajeros;
    private Semaphore semaforo = new Semaphore(1);

    public Barco(ArrayList<Pasajero> listaPasajeros) {
        this.pasajeros = new ArrayList<>();
        this.pasajeros.addAll(listaPasajeros);
    }

    public Pasajero sacarSiguientePasajero() {
        try {
            semaforo.acquire(); // 1. Bloqueamos
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }

        if (pasajeros.isEmpty()) {
            semaforo.release();
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

        semaforo.release();
        return pasajeroPrioritario;
    }

    public boolean hayPasajeros() {
        try {
            semaforo.acquire();
        } catch (InterruptedException e) {
            return false;
        }

        boolean hayGente = !pasajeros.isEmpty();

        semaforo.release();
        return hayGente;
    }

    public int getPasajerosRestantes() {
        try {
            semaforo.acquire();
        } catch (InterruptedException e) {
            return 0;
        }

        int total = pasajeros.size();

        semaforo.release();
        return total;
    }
}