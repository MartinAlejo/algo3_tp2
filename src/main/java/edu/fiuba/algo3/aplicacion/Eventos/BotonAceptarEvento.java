package edu.fiuba.algo3.aplicacion.Eventos;

import edu.fiuba.algo3.aplicacion.App;
import edu.fiuba.algo3.aplicacion.Vista.PantallaInicioView.PantallaElegirVehiculoYJugadorView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;

public class BotonAceptarEvento implements EventHandler<ActionEvent> {

    private App app;
    private ChoiceBox<Integer> choiceBox;

    public BotonAceptarEvento(App app, ChoiceBox<Integer> choiceBox){
        this.app = app;
        this.choiceBox = choiceBox;
    }

    public void handle(ActionEvent actionEvent){
        PantallaElegirVehiculoYJugadorView pantallaElegirVehiculoYJugadorView = new PantallaElegirVehiculoYJugadorView(this.app,this.app.getEscenarioView());
        pantallaElegirVehiculoYJugadorView.ingresarNombresYVehiculo(this.choiceBox.getValue());
    }
}
