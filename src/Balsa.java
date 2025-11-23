import java.util.ArrayList;

public class Balsa {

    private String nombre;
    private int capacidad;
    private int tiempo;
    private ArrayList<Pasajero> pasajerosABordo = new ArrayList<>();

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

    public synchronized ArrayList<Pasajero> getPasajerosABordo() {
        return pasajerosABordo;
    }


    public synchronized boolean cargarPasajero(Pasajero pasajero) {
        if (pasajerosABordo.size() < capacidad) {
            pasajerosABordo.add(pasajero);
            return pasajerosABordo.size() == capacidad;
        }
        return false;
    }


    public void realizarViaje() throws InterruptedException {
        System.out.println("------------- Balsa " + nombre + " INICIA VIAJE de RESCATE (Cap: " + capacidad + ", T: " + tiempo + "s) -----------------");

        Thread.sleep(tiempo * 100L);

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
    }
}