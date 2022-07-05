package edu.fiuba.algo3.aplicacion.Vista.VehiculosView;

import edu.fiuba.algo3.modelo.Direccion.*;
import edu.fiuba.algo3.modelo.Vehiculo.Camioneta;
import edu.fiuba.algo3.modelo.Vehiculo.Moto;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class CamionetaView implements VehiculoView{
    protected Camioneta camioneta;
    protected Pane root;
    protected ImageView imagenCamioneta;

    public double ANCHO_CAMIONETA;
    public double ALTO_CAMIONETA;





    protected String imagenPath ;

    public CamionetaView(Camioneta camioneta, Pane root, double alto, double ancho){
        ANCHO_CAMIONETA = ancho;
        ALTO_CAMIONETA = alto;
        this.camioneta = camioneta;
        this.imagenPath = "file:../algo3_tp2/src/main/java/edu/fiuba/algo3/aplicacion/imagenes/camionetaImagenes/camionetaIcono.png";
        this.imagenCamioneta = new ImageView(new Image(imagenPath,ANCHO_CAMIONETA*0.6, ALTO_CAMIONETA*0.6, false, false));;
        this.root = root;


    }


    public void dibujar(){

        this.posicionarImagen();
        root.getChildren().add(imagenCamioneta);

    }

/*

    public void mover(){

            Direccion direccion = camioneta.obtenerDireccion();
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), imagenCamioneta);

            if (direccion instanceof DireccionArriba) {
                translateTransition.setByY(-100);
            }

            if (direccion instanceof DireccionAbajo) {
                translateTransition.setByY(100);
            }

            if (direccion instanceof DireccionDerecha) {
                translateTransition.setByX(100);

            }

            if (direccion instanceof DireccionIzquierda) {
                translateTransition.setByX(-100);


            }
            else{

            }


            translateTransition.setCycleCount(1);
            translateTransition.play();

        }


*/






    public void posicionarImagen(){

        imagenCamioneta.setLayoutY((camioneta.obtenerUbicacion().obtenerFila() - 2 ) * ANCHO_CAMIONETA + ANCHO_CAMIONETA*1.2);
        imagenCamioneta.setLayoutX((camioneta.obtenerUbicacion().obtenerColumna() - 2) * ALTO_CAMIONETA + ALTO_CAMIONETA*1.2);

    }



    public void actualizar(){
        this.posicionarImagen();

    }
}
