package edu.fiuba.algo3.modelo;

public class Calle {
    private EntreCalle primerEntreCalle;
    private EntreCalle segundaEntreCalle;

    public Calle(){

    }
    public Calle(EntreCalle unaEntreCalle, EntreCalle otraEntreCalle){
        primerEntreCalle = unaEntreCalle;
        segundaEntreCalle = otraEntreCalle;
    }

    public void asignarEntreCalles(EntreCalle unaEntreCalle, EntreCalle otraEntreCalle){
        primerEntreCalle = unaEntreCalle;
        segundaEntreCalle = otraEntreCalle;
    }
}
