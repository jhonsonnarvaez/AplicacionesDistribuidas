package suma;

import java.io.BufferedReader;
import java.io.FileReader;

public class Suma extends Thread {

    private String nombreArchivo;
    private String contLineas;
    private String primeraMitad;
    private String segundaParte;
    private int suma = 0;
    private int suma2 = 0;

    public int getSuma2() {
        return suma2;
    }

    public void setSuma2(int suma2) {
        this.suma2 = suma2;
    }

    public Suma() {
    }

    public Suma(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public String getContLineas() {
        return contLineas;
    }

    public String getPrimeraMitad() {
        return primeraMitad;
    }

    public String getSegundaParte() {
        return segundaParte;
    }

    public int getSuma() {
        return suma;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void setContLineas(String contLineas) {
        this.contLineas = contLineas;
    }

    public void setPrimeraMitad(String primeraMitad) {
        this.primeraMitad = primeraMitad;
    }

    public void setSegundaParte(String segundaParte) {
        this.segundaParte = segundaParte;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public int numeros() {
        int total = 0;

        try {
            FileReader fr = new FileReader(getNombreArchivo());
            BufferedReader br = new BufferedReader(fr);

            while ((contLineas = br.readLine()) != null) {
                total = total + 1;
            }

        } catch (Exception e) {
        }
        return total;
    }

    @Override
    public void run() {
        try {
            FileReader fr = new FileReader(getNombreArchivo());
            BufferedReader br = new BufferedReader(fr);

            for (int i = numeros()/2; i < numeros(); i++) {
                if ((contLineas = br.readLine()) != null) {

                    System.out.print(" " + contLineas);
                    suma = suma + (Integer.parseInt(contLineas));

                }
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Long timIni;
        Long timFn;
        Long timTo;
        timIni = System.currentTimeMillis();
        Suma numli = new Suma("num10.txt");
        for (int i = numli.numeros()/2; i < numli.numeros(); i++) {
            numli.numeros();
        }

        numli.start();
        numli.join();

        timFn = System.currentTimeMillis();
        System.out.println("\nLa suma total es de la primera mitad es: " + numli.getSuma());
        timTo = timFn - timIni;
        System.out.println("El Tiempo de ejecucion es :" + timTo);
    }

}
