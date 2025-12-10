import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Balsa {

    private String nombre;
    private int capacidad;
    private int tiempo;
    private ArrayList<Pasajero> pasajerosABordo = new ArrayList<>();
    private Semaphore semaforo = new Semaphore(1);

    public Balsa(String nombre, int capacidad, int tiempo) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTiempo() {
        return tiempo;
    }

    public  ArrayList<Pasajero> getPasajerosABordo() {
        try {
            semaforo.acquire();
        } catch (InterruptedException e) {
            return new ArrayList<>();
        }

        ArrayList<Pasajero> copiaLista = new ArrayList<>(pasajerosABordo);

        semaforo.release();
        return copiaLista;
    }


    public boolean cargarPasajero(Pasajero pasajero) {
        try {
            semaforo.acquire();
        } catch (InterruptedException e) {
            return false;
        }

        if (pasajerosABordo.size() < capacidad) {
            pasajerosABordo.add(pasajero);
        }

        boolean estaLlena = (pasajerosABordo.size() == capacidad);

        semaforo.release();
        return estaLlena;
    }


    public void realizarViaje() throws InterruptedException {
        System.out.println("------------- Balsa " + nombre + " INICIA VIAJE de RESCATE (Cap: " + capacidad + ", T: " + tiempo + "s) -----------------");

        Thread.sleep(tiempo * 100L);

        semaforo.acquire();

        ArrayList<String> idsRescatados = new ArrayList<>();
        ArrayList<String> prioridadRescatados = new ArrayList<>();
        for (Pasajero p : pasajerosABordo) {
            idsRescatados.add(String.valueOf(p.getId()));
            prioridadRescatados.add(String.valueOf(p.getPrioridad()));
        }

        System.out.println("--------------- Balsa " + nombre + " HA REGRESADO --------------");
        System.out.println("   Pasajeros rescatados: " + pasajerosABordo.size() + "/" + capacidad);
        System.out.println("   " + nombre + " IDs Rescatadas: " + idsRescatados +" Prioridad" +prioridadRescatados);

        pasajerosABordo.clear();

        semaforo.release();
    }
}