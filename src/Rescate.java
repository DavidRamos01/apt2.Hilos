public class Rescate implements Runnable {

    private Barco barco;
    private Balsa balsa;

    public Rescate(Barco barco, Balsa balsa) {
        this.barco = barco;
        this.balsa = balsa;
    }

    @Override
    public void run() {
        try {
            while (barco.hayPasajeros() || balsa.getPasajerosABordo().size() > 0) {

                Pasajero pasajero = barco.sacarSiguientePasajero();

                if (pasajero != null) {
                    boolean balsaLlena = balsa.cargarPasajero(pasajero);

                    if (balsaLlena) {
                        balsa.realizarViaje();
                    }
                } else {
                    if (!barco.hayPasajeros()) {
                        if (balsa.getPasajerosABordo().size() > 0) {
                            balsa.realizarViaje();
                        }
                        break;
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Rescate de la Balsa " + balsa.getNombre() + " interrumpido.");
        } finally {
            System.out.println("--- Balsa " + balsa.getNombre() + " ha finalizado su labor. ---");
        }
    }
}
