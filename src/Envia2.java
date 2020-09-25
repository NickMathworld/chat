import java.io.*;
import java.net.*;
public class Envia2{
	public boolean enviarArchivo(File f,MulticastSocket s){
		try{
                    String dir = "229.1.2.3";
                    InetAddress grupo= InetAddress.getByName(dir);
                    String nombre = f.getName();
                    String path = f.getAbsolutePath();
                    Long tam = f.length();
                    DataInputStream dis = new DataInputStream(new FileInputStream(path));
                    byte[] b;
                    // Enviamos el nombre del archivo
                    b = nombre.getBytes();
                    DatagramPacket p = new DatagramPacket(b, b.length,grupo, 8887);
                    s.send(p);
                    
                    // Creamos un buffer para enviar pedazo a pedazo el archivo
                    byte[] buf = new byte[2000];
                    ByteArrayOutputStream bay = new ByteArrayOutputStream();
                    DataOutputStream dos = new DataOutputStream(bay);
                    
                    dos.writeLong(tam);
                    dos.flush();
                    
                    byte[] btba = bay.toByteArray();

                    p = new DatagramPacket(btba, btba.length, grupo, 8887);
                    s.send(p);
                    //Enviamos el tamaño
                    long enviados = 0;
                    int i = 0;
                    int cont = 0;
                    //System.out.println("Tamaño : " + tam);

                    while (enviados < tam) {
                        i = dis.read(buf);
                        p = new DatagramPacket(buf, i, grupo, 8887);
                        s.send(p);
                        enviados += i;
                        //System.out.println("Enviado " + cont++ + ": " + i);
                    }

                    //System.out.println("Archivo enviado...");
                    System.out.println("Archivo "+ nombre +" enviado");
                    dis.close();
                    dos.close();
                    return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}