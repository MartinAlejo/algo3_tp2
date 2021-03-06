package edu.fiuba.algo3.aplicacion.Vista.PantallaInicioView;

import edu.fiuba.algo3.aplicacion.App;
import edu.fiuba.algo3.aplicacion.Eventos.BotonEscenarioChicoEvento;
import edu.fiuba.algo3.aplicacion.Eventos.BotonEscenarioGrandeEvento;
import edu.fiuba.algo3.aplicacion.Eventos.BotonSalirElegirJugadoresEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PantallaElegirEscenarioView {

    private App app;

    public PantallaElegirEscenarioView(App app){
        this.app = app;
    }

    public void elegirTamanioEscenario(){
        StackPane layout = new StackPane();
        Label pedirTamanio = new Label();
        Button Grande = new Button("Grande");
        Button Chico = new Button("Chico");
        this.app.getStage().setFullScreenExitHint("");

        pedirTamanio.setText("Elija la medida del escenario");
        pedirTamanio.setAlignment(Pos.CENTER);

        BotonEscenarioGrandeEvento botonEscenarioGrandeEvento = new BotonEscenarioGrandeEvento(this.app);
        Grande.setOnAction(botonEscenarioGrandeEvento);

        BotonEscenarioChicoEvento botonEscenarioChicoEvento = new BotonEscenarioChicoEvento(this.app);
        Chico.setOnAction(botonEscenarioChicoEvento);

        HBox contenedorBoton = new HBox(Chico, Grande);
        contenedorBoton.setAlignment(Pos.CENTER);
        contenedorBoton.setSpacing(10);

        VBox contenedor = new VBox(pedirTamanio, contenedorBoton);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(10);

        Button salir = new Button("Salir");
        BotonSalirElegirJugadoresEvent botonSalir = new BotonSalirElegirJugadoresEvent(this.app);
        salir.setOnAction(botonSalir);
        salir.defaultButtonProperty().bind(salir.focusedProperty());

        layout.getChildren().addAll(contenedor, salir);
        layout.setPadding(new Insets(10));
        layout.setPrefHeight(1080);
        layout.setPrefWidth(1920);
        StackPane.setAlignment(salir, Pos.BOTTOM_LEFT);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("file:../algo3_tp2/src/main/java/edu/fiuba/algo3/aplicacion/css/principal.css");
        this.app.getStage().setScene(scene);
        this.app.getStage().setFullScreen(true);
        this.app.getStage().setFullScreenExitHint("");
        this.app.getStage().centerOnScreen();
    }


}
