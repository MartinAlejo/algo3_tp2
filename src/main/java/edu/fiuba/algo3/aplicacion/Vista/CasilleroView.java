package edu.fiuba.algo3.aplicacion.Vista;

 import edu.fiuba.algo3.aplicacion.App;
 //import edu.fiuba.algo3.aplicacion.Vista.ObstaculosView.CreadorObstaculosView;
 import edu.fiuba.algo3.aplicacion.Vista.ObstaculosView.CreadorObstaculosView;
 import edu.fiuba.algo3.aplicacion.Vista.ObstaculosView.ObstaculoView;
 import edu.fiuba.algo3.modelo.General.Casillero;
 import edu.fiuba.algo3.modelo.Obstaculos.Obstaculo;
 import edu.fiuba.algo3.modelo.Obstaculos.Piquete;
 import javafx.scene.layout.Pane;
 import javafx.scene.paint.Color;
 import javafx.scene.Group;
 import javafx.scene.shape.Rectangle;

public class CasilleroView extends Pane {

    private int filas;
    private int columnas;
    private double height;
    private double width;
    private CreadorObstaculosView creadorObstaculosView;

    private Obstaculo obstaculo;

    public CasilleroView(int filas, int columnas, double height, double width) {
        this.filas = filas;
        this.columnas = columnas;
        this.height = height;
        this.width = width;
        this.creadorObstaculosView = new CreadorObstaculosView();
    }

    public void dibujarCasillero(float fila, float columna, Casillero casillero, Pane root){
        double alto = width/(double)filas;
        double ancho = height/(double)columnas;

        Obstaculo obstaculo = casillero.obtenerObstaculo();
        ObstaculoView obstaculoView = creadorObstaculosView.crear(obstaculo, casillero, alto, ancho);
        root.getChildren().add(obstaculoView.dibujar());

    }

}