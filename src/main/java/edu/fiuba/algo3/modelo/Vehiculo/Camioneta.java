package edu.fiuba.algo3.modelo.Vehiculo;

import edu.fiuba.algo3.modelo.General.ObjetoUrbano;
import edu.fiuba.algo3.modelo.General.Ubicacion;
import edu.fiuba.algo3.modelo.Obstaculos.Piquete;
import edu.fiuba.algo3.modelo.Obstaculos.Pozo;

import java.util.HashMap;

public class Camioneta extends Vehiculo {
    private int pozos_atravesados;

    public Camioneta(Ubicacion ubicacion) {
        super(ubicacion);
    }

    protected Vehiculo recibePozo(ObjetoUrbano x) {
        pozos_atravesados++;
        if (pozos_atravesados > 3) {
            this.incrementarMovimientos(3);
        }
        return this;
    }

    protected Vehiculo recibePiquete(ObjetoUrbano x) {
        return this;
    }

    protected Vehiculo recibeFavorable(ObjetoUrbano x) {
        int movimientosReducidos = this.movimientos() / 5;
        this.disminuirMovimientos(movimientosReducidos);
        return this;
    }

    protected Vehiculo recibeDesfavorable(ObjetoUrbano x) {
        int movimientosAumentados = this.movimientos() / 4;
        this.incrementarMovimientos(movimientosAumentados);
        return this;
    }

    protected Vehiculo recibeCambioVehiculo(ObjetoUrbano x) {
        Vehiculo nuevoVehiculo = new Moto(this.obtenerUbicacion());
        //this.cambiarVehiculo(nuevoVehiculo);
        nuevoVehiculo.asignarMovimientos(this.movimientos());
        return (nuevoVehiculo);
    }
}
