package edu.fiuba.algo3.aplicacion;

import edu.fiuba.algo3.aplicacion.Eventos.*;
import edu.fiuba.algo3.modelo.General.Juego;
import edu.fiuba.algo3.modelo.General.Jugador;
import edu.fiuba.algo3.modelo.Vehiculo.Vehiculo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private Stage stage;
    private Juego juego;
    private List<Jugador> jugadores;

    private int fila;
    private int columna;

    private double width = 500;
    private double height = 400;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        jugadores = new ArrayList<>();

        stage.setTitle("Juego GPS");
        StackPane layout = new StackPane();
        Label titulo = new Label();
        Label integrantes = new Label();

        Button comenzar = new Button("Comenzar");
        Button salir = new Button("Salir");

        comenzar.defaultButtonProperty().bind(comenzar.focusedProperty());
        salir.defaultButtonProperty().bind(salir.focusedProperty());

        BotonSalirEvento botonSalirEvento = new BotonSalirEvento();
        salir.setOnAction(botonSalirEvento);

        BotonComenzarEvento botonComenzarEvento = new BotonComenzarEvento(this);
        comenzar.setOnAction(botonComenzarEvento);

        StackPane.setAlignment(comenzar, Pos.BOTTOM_LEFT);

        titulo.setText("GPS Juego - Algo3");
        integrantes.setText("Integrantes:\n" + "Cristian Martin Lin\n" + "Martin Alejo Polese\n" + "Tomas Nahuel Olivera\n" + "Jorge Sedek\n" );

        StackPane.setAlignment(titulo, Pos.TOP_CENTER);
        StackPane.setAlignment(integrantes, Pos.BOTTOM_LEFT);

        HBox botonera = new HBox(comenzar, salir);
        botonera.setAlignment(Pos.CENTER);
        botonera.setSpacing(10);

        VBox contenedorPrincipal = new VBox(botonera);
        contenedorPrincipal.setAlignment(Pos.CENTER);

        layout.setPrefHeight(400);
        layout.setPrefWidth(500);
        layout.getChildren().addAll(integrantes, titulo, contenedorPrincipal);
        layout.setPadding(new Insets(10));

        var scene = new Scene(layout);
        //scene.getStylesheets().add("src/main/java/edu/fiuba/algo3/aplicacion/css/inicio-fondo.css");
        /*Background bi = new Background(new BackgroundImage(new Image("gps-fondo.jpg"),
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.CENTER,
        new BackgroundSize(width, height, false, false, false, false)));*/
        stage.setScene(scene);
        stage.show();
    }


    public void elegirCantidadJugadores(){
        StackPane layout = new StackPane();
        Button aceptar = new Button("Aceptar");
        Label pedirCantJugadores = new Label();

        pedirCantJugadores.setText("Seleccione la cantidad de jugadores: ");
        StackPane.setAlignment(pedirCantJugadores, Pos.TOP_CENTER);

        ChoiceBox<Integer> choiceBox = new ChoiceBox<>();

        choiceBox.getItems().add(1);
        choiceBox.getItems().add(2);
        choiceBox.getItems().add(3);

        BotonAceptarEvento accept = new BotonAceptarEvento(this, choiceBox);
        aceptar.setOnAction(accept);

        StackPane.setAlignment(choiceBox, Pos.CENTER);
        StackPane.setAlignment(aceptar, Pos.CENTER);

        VBox contenedor = new VBox(pedirCantJugadores, choiceBox, aceptar);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(10);

        Button salir = new Button("Salir");
        BotonSalirElegirJugadoresEvent botonSalir = new BotonSalirElegirJugadoresEvent(this);
        salir.setOnAction(botonSalir);
        salir.defaultButtonProperty().bind(salir.focusedProperty());

        layout.getChildren().addAll(contenedor, salir);
        layout.setPadding(new Insets(10));

        StackPane.setAlignment(salir, Pos.BOTTOM_LEFT);
        layout.setPrefHeight(400);
        layout.setPrefWidth(500);

        Scene scene = new Scene(layout);
        this.stage.setScene(scene);
    }


    public void getChoice(int cant_jugadores) {
        System.out.println(cant_jugadores);
    }
/*
    public void agregarJugadores(){
        StackPane layout = new StackPane();
        Label pedirNombre = new Label();
        Label pedirVehiculo = new Label();
        Label error = new Label();
        TextField nombreJugador = new TextField();
        Button siguiente = new Button("Siguiente");

        ChoiceBox<String> choiceBox1 = new ChoiceBox<>();
        choiceBox1.getItems().addAll("Moto", "Auto", "Camioneta");
        choiceBox1.setValue("Moto");

        pedirNombre.setText("Ingrese el nombre del jugador");
        pedirNombre.setAlignment(Pos.CENTER);

        pedirVehiculo.setText("Eliga un vehiculo para el jugador");
        pedirVehiculo.setAlignment(Pos.CENTER);

        error.setText("");

        //BotonSiguienteEvento botonSiguiente = new BotonSiguienteEvento(this, choiceBox1, nombreJugador, error);
        //siguiente.setOnAction(botonSiguiente);

        VBox contenedorNombre = new VBox(pedirNombre, nombreJugador);
        contenedorNombre.setAlignment(Pos.CENTER);
        contenedorNombre.setSpacing(10);

        VBox contenedorVehiculo = new VBox(pedirVehiculo, choiceBox1);
        contenedorVehiculo.setAlignment(Pos.CENTER);
        contenedorVehiculo.setSpacing(10);

        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        HBox contenedorBoton = new HBox(error, region1, siguiente);
        contenedorBoton.setSpacing(0);

        VBox contenedor = new VBox(contenedorNombre, contenedorVehiculo, contenedorBoton);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(10);

        layout.getChildren().add(contenedor);
        layout.setPadding(new Insets(10));

        layout.setPrefHeight(100);
        layout.setPrefWidth(100);

        Scene scene = new Scene(layout);
        this.stage.setScene(scene);
    }

    public void elegirTamanioEscenario(){
        StackPane layout = new StackPane();
        Label pedirFila = new Label();
        Label pedirColumna = new Label();
        Label error = new Label();
        TextField fila = new TextField();
        TextField columna = new TextField();
        Button continuar = new Button("Continuar");

        pedirFila.setText("Ingrese la cantidad de entre calles por fila");
        pedirFila.setAlignment(Pos.CENTER);
        pedirColumna.setText("Pedir la cantidad de entre calles por columna");
        pedirColumna.setAlignment(Pos.CENTER);

        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        error.setText("");

        HBox contenedorBoton = new HBox(error, region1, continuar);
        contenedorBoton.setAlignment(Pos.CENTER);
        contenedorBoton.setSpacing(10);

        VBox contenedorDatos = new VBox(pedirFila, fila, pedirColumna, columna);
        contenedorDatos.setAlignment(Pos.CENTER);
        contenedorDatos.setSpacing(10);

        VBox contenedor = new VBox(contenedorDatos, contenedorBoton);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(10);

        layout.getChildren().add(contenedor);
        layout.setPadding(new Insets(10));

        layout.setPrefHeight(100);
        layout.setPrefWidth(100);

        Scene scene = new Scene(layout);
        this.stage.setScene(scene);
    }

    public void ingresarNombresYVehiculo(int cant_jugadores){
        StackPane layout = new StackPane();
        //Button  = new Button("Siguiente");

        for(int i=0; i<cant_jugadores; i++){
            agregarJugadores();
        }

        Scene scene = new Scene(layout);
        this.stage.setScene(scene);
    }*/

    /*
    public void guardarJugadores(String nombre, Vehiculo vehiculo){
        Jugador jugador = new Jugador(nombre, vehiculo);
        jugadores.add(jugador);
    }*/

    public Stage getStage(){
        return this.stage;
    }

    public static void main(String[] args) {
        launch();
    }
/*
    public void setFilaYColumna(int fila, int columna) {
        if(fila%2 == 0){
            fila--;
        }else if(columna%2 == 0){
            columna --;
        }

        this.fila = fila;
        this.columna = columna;

    }*/


}