package archivos;

import java.io.BufferedReader;
import java.io.FileReader;

public class ContarArchivos extends Thread {

    private String archivo;
    private long numLineas = 0;

    public long getNumLineas() {
        return numLineas;
    }

    public ContarArchivos() {
    }

    public ContarArchivos(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public void run() {
        try {
            FileReader fr = new FileReader(getArchivo());
            BufferedReader br = new BufferedReader(fr);

            long numeroLineas = 0;

            String sCadena;

            while ((sCadena = br.readLine()) != null) {
                numeroLineas++;
            }

            this.numLineas = numeroLineas;

        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        ContarArchivos[] cont = new ContarArchivos[args.length];

        for (int i = 0; i < args.length; i++) {
            cont[i] = new ContarArchivos(args[i]);
            cont[i].start();
        }
        
        long suma=0;
        long l;
         for (int i = 0; i < cont.length; i++) {
             try {
                 cont[i].join();
                 
                 l = cont[i].getNumLineas();
                 
                 suma+=l;
                 System.out.println(args[i]+":"+l);
             } catch (Exception e) {
             }
        }
         
         System.out.println("Total: " + suma);
    }
}
