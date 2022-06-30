package edu.fiuba.algo3.aplicacion.Eventos;

import edu.fiuba.algo3.aplicacion.App;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonJugarEvento implements EventHandler<ActionEvent> {

    private App app;

    public BotonJugarEvento(App app){
        this.app = app;
    }

    public void handle(ActionEvent actionEvent){
        this.app.mostrarTablero();
    }
}