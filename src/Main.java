import java.util.ArrayList;
import java.util.Random;

//TIP To run code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) throws InterruptedException {
        int total_pasajeros = 352;

        ArrayList<Pasajero> listaPasajeros = new ArrayList<>();
        Random rand = new Random();

        for (int i = 1; i <= total_pasajeros; i++) {
            int prioridad = rand.nextInt(4) + 1;
            listaPasajeros.add(new Pasajero(i, prioridad));
        }
        System.out.println("Se ha generado la tripulación de " + total_pasajeros + " personas.");

        Barco laAlianza = new Barco(listaPasajeros);

        Balsa acasta = new Balsa("Acasta", 1, 10);
        Balsa banff = new Balsa("Banff", 2, 21);
        Balsa cadiz = new Balsa("Cadiz", 3, 32);
        Balsa deimos = new Balsa("Deimos", 4, 44);
        Balsa expedicion = new Balsa("Expedición", 5, 80);

        ArrayList<Balsa> balsas = new ArrayList<>();
        balsas.add(acasta);
        balsas.add(banff);
        balsas.add(cadiz);
        balsas.add(deimos);
        balsas.add(expedicion);

        ArrayList<Thread> hilosRescate = new ArrayList<>();
        System.out.println("INICIANDO OPERACIÓN DE RESCATE CON 5 BALSAS");

        for (Balsa balsa : balsas) {
            Thread hilo = new Thread(new Rescate(laAlianza, balsa), "Hilo-" + balsa.getNombre());
            hilosRescate.add(hilo);
            hilo.start();
        }

        for (Thread hilo : hilosRescate) {
            hilo.join();
        }

        System.out.println("\nOPERACIÓN DE RESCATE FINALIZADA");
        System.out.println("Pasajeros restantes en el barco: " + laAlianza.getPasajerosRestantes());
    }
}