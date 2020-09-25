import java.io.*;
import java.net.*;

public class Recibe2{
	
	public boolean recibe ( MulticastSocket s){
		try{
                    System.out.println("Inicia descarga...");
                    DatagramPacket p = new DatagramPacket(new byte[2000], 2000);
                    s.receive(p);

                    String nombre = new String(p.getData(), 0, p.getLength());
                    System.out.println("Nombre:" + nombre);

                    p = new DatagramPacket(new byte[2000], 2000);
                    s.receive(p);

                    DataInputStream dis = new DataInputStream(new ByteArrayInputStream(p.getData()));
                    long tam = 0;
                    tam = dis.readLong();
                    System.out.println("Tamaño: " + tam);
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream("descargas/"+nombre) );

                    int n = 0;
                    long leidos = 0;
                    byte[] buf = new byte[2000];
                    int cont = 0;

                    while (leidos < tam) {
                        p = new DatagramPacket(new byte[2000], 2000);
                        s.receive(p);
                        dis = new DataInputStream(new ByteArrayInputStream(p.getData()));
                        n = dis.read(buf);
                        //System.out.println("Recibido " + cont++ + ": " + n);
                        dos.write(buf, 0, n);
                        dos.flush();
                        leidos = leidos + n;
                    }
                    //System.out.println("Archivo recibido");
                    System.out.println("Archivo " + nombre + " recibida");
                     
                     dis.close();
                     dos.close();
                    
                       return true;
		}
		catch( Exception e ){
			e.printStackTrace();
                        return false;
		}
	}
}
