/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebCam;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Vector;
import javax.media.Buffer;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.control.FormatControl;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;


/**
 * Nos permite obtener imágenes de la webcam siempre que queramos y que se
 * encuentren disponibles. Nos aleja del trato con las clases del JMF.
 * 
 * @author <a href="http://programmingheroes.blogspot.com">ProgramminHeroes</a>
 */
public class WebCam implements Runnable {

    /**
     * Indica si la cámara ha sido iniciada con éxito y por lo tanto, si se
     * pueden capturar imágenes.
     * 
     * @see #getImage()
     */
    private volatile boolean cameraAvailable, starting;

    /**
     * Contiene el hilo que se va a encargar de iniciar la cámara.
     */
    private Thread initCam;

    /**
     * Encargado de controlar el data stream (DataSource) procedente,
     * en este caso, de la webcam.
     */
    private Player player;

    /**
     * Control del {@link #player} que nos permite capturar imágenes en el
     * momento que las necesitemos.
     */
    private FrameGrabbingControl fgc;

    /**
     * Inicia en un nuevo hilo la búsqueda y preparación de la webcam para
     * que comienze a capturar imágenes. El nuevo hilo es completamente
     * independiente de la aplicación en la que se utiliza. Esto puede ser un
     * problema, ya que la ejecución después de la creación del objeto <code>
     * WebCam</code> continua pero la cámara realmente todavía no está
     * disponible para realizar capturas (el método {@link #getImage()}
     * retornaría <code>null</code>). Ese nuevo hilo no se inicia hasta que no
     * se llama al método {@link #start()}.
     */
    public WebCam() {
        cameraAvailable = false;
    } // fin de WebCam();

    /**
     * Inicia el nuevo hilo que buscará la cámara web y realizará una conexión
     * con ella.
     */
    public void start() {
        if (!starting && !cameraAvailable) {
            initCam = new Thread(this);
            initCam.start();
        } else if (cameraAvailable && player.getState() != Player.Started) {
            player.start();
        }
    } // fin de start();
    
    /**
     * Detiene la conexión con la cámara de la forma correcta.
     */
    public void close() {
        if (cameraAvailable) {
            cameraAvailable = false;
            player.close();
        } else {
            starting = false;
        }
    } // fin de stop();

    /**
     * Detiene la captura de imágenes de la cámara pero mantiene la conexión
     * con ella.
     */
    public void stop() {
        if (cameraAvailable) {
            player.stop();
        }
    } // fin de stop();

    /**
     * Indica si está o no disponible la cámara para la toma de imágenes.
     * 
     * @return <code>true</code> si está disponible y <code>false</code>
     * si no lo está.
     */
    public boolean isAvailable() {
        return cameraAvailable;
    } // fin de boolean isAvailable();

    /**
     * Si está disponible la cámara web retorna la imagen que captura en ese
     * mismo momento.
     * 
     * @return La imagen que recibe la cámara en el instante de la llamada
     * o <code>null</code> en caso de que todavía no se encuentre preparado
     * el dispositivo.
     */
    public BufferedImage getImage() {
        if (!cameraAvailable) {
            return null;
        }
        Image img;
        Buffer buf = fgc.grabFrame();
        VideoFormat bufFormat = (VideoFormat) buf.getFormat();
        BufferToImage bti = new BufferToImage(bufFormat);
        img = bti.createImage(buf);
        if (img == null) {
            return null;
        }
        return (BufferedImage) img;
    } // fin de BufferedImage getImage();

    /**
     * Inicia la búsqueda y posterior preparación de la cámara.
     */
    @Override
    public void run() {
        // Iniciamos la búsqueda de la webcam.
        starting = true;
        try {
            // Obtenemos el CaptureDeviceInfo de la primera cámara
            // que encontremos en el equipo.
            CaptureDeviceInfo camera = getCamera();
            if (camera == null) {
                System.out.println("No se ha encontrado una webcam :v.");
                System.exit(0);
            }
            
            // Buscamos formato de vídeo con la mayor resolución.
            VideoFormat vFormat = getLargestVF(camera);
            if (vFormat == null) {
                System.out.println("\nError en los formatos");
                System.exit(-1);
            }
            
            // Creamos el Player a partir del MediaLocater del
            // CaptureDeviceInfo que representa a la cámara.
            MediaLocator locator = camera.getLocator();
            player = Manager.createRealizedPlayer(locator);
            
            // Obtenemos los controles que necesitamos.
            //    FormatControl -> cambiar el formato de vídeo.
            FormatControl fc = (FormatControl) player.getControl(
                    "javax.media.control.FormatControl");
            if (fc == null) {
                System.out.println("Control de formato no encontrado.");
                System.exit(-1);
            }
            fc.setFormat(vFormat);
            //    FrameGrabbingControl -> capturar imágenes de la cámara.
            fgc = (FrameGrabbingControl) player.getControl(
                    "javax.media.control.FrameGrabbingControl");
            if (fgc == null) {
                System.out.println("Control de captura no encontrado.");
                System.exit(-1);
            }
            
            // Iniciamos el player.
            if (starting) {
                player.start();
                if (starting) {
                    cameraAvailable = true;
                    starting = false;
                    return;
                }
            }
            // Si no se ha salido de la función todavía...
            player.close();
        } catch (Exception ex) {
            System.err.println("Error iniciando la WebCam: "+ex);
            System.exit(-1);
        }
    } // fin del run();

    /**
     * Busca en la lista de dispositivos conectados al equipo la primera
     * cámara que encuentre y la devuelve.
     * 
     * @return <code>CaptureDeviceInfo</code> que corresponde a la cámara.
     */
    private CaptureDeviceInfo getCamera() {
        // Obtenermos una lista con todos los dispositivos
        // multimedia del equipo para encontrar la cámara.
        Vector vector = CaptureDeviceManager.getDeviceList(null);
        // Recorremos el vector de dispositivos y guardamos la cámara.
        for (int i = 0; i < vector.size(); i++) {
            CaptureDeviceInfo device = (CaptureDeviceInfo) vector.elementAt(i);
            if (device.getName().startsWith("vfw")) {
                return device;
            }
        }
        return null;
    } // fin de CaptureDeviceInfo getCamera();

    /**
     * Entre los formatos disponibles de un <code>CaptureDeviceInfo</code> de
     * una cámara encuentra el de mayor resolución.
     * 
     * @param device Dispositivo del cual se quiere obtener la mayor
     * resolución. Tiene que tratarse de una cámara.
     * @return El <code>VideoFormat</code> con mayor resolución.
     */
    private VideoFormat getLargestVF(CaptureDeviceInfo device) {
        VideoFormat vFormat = null;
        Format[] formats = device.getFormats();
        for (int i = 0; i < formats.length; i++) {
            if (formats[i] instanceof VideoFormat) {
                VideoFormat vf = (VideoFormat) formats[i];
                if (vFormat != null) {
                    if (vFormat.getSize().height * vFormat.getSize().width
                            < vf.getSize().height * vf.getSize().width) {
                        vFormat = vf;
                    }
                } else {
                    vFormat = vf;
                }
            }
        }
        return vFormat;
    } // fin de VideoFormat getLargestVF(CaptureDeviceInfo);

    /**
     * Entre los formatos disponibles de un <code>CaptureDeviceInfo</code> de
     * una cámara encuentra el de menor resolución.
     * 
     * @param device Dispositivo del cual se quiere obtener la menor
     * resolución. Tiene que tratarse de una cámara.
     * @return El <code>VideoFormat</code> con menor resolución.
     */
    private VideoFormat getSmallestVF(CaptureDeviceInfo device) {
        VideoFormat vFormat = null;
        Format[] formats = device.getFormats();
        for (int i = 0; i < formats.length; i++) {
            if (formats[i] instanceof VideoFormat) {
                VideoFormat vf = (VideoFormat) formats[i];
                if (vFormat != null) {
                    if (vFormat.getSize().height * vFormat.getSize().width
                            > vf.getSize().height * vf.getSize().width) {
                        vFormat = vf;
                    }
                } else {
                    vFormat = vf;
                }
            }
        }
        return vFormat;
    } // fin de VideoFormat getSmallestVF(CaptureDeviceInfo);

} // fin de la clase WebCam

// fin de WebCam.java ----------------------------------------------------------
