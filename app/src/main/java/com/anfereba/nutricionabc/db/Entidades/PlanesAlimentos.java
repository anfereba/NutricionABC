package com.anfereba.nutricionabc.db.Entidades;
public class PlanesAlimentos{
    private int idPlanAlimento;
    private int idPlanNutricional;
    private int idAlimento;
    private int Dia;
    private String NombrePlanesAlimentos;


    public int getDia() {
        return Dia;
    }

    public void setDia(int dia) {
        Dia = dia;
    }



    public int getIdPlanAlimento() {
        return idPlanAlimento;
    }

    public void setIdPlanAlimento(int idPlanAlimento) {
        this.idPlanAlimento = idPlanAlimento;
    }

    public int getIdPlanNutricional() {
        return idPlanNutricional;
    }

    public void setIdPlanNutricional(int idPlanNutricional) {
        this.idPlanNutricional = idPlanNutricional;
    }

    public int getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }

    public String getNombrePlanesAlimentos() {
        return NombrePlanesAlimentos;
    }

    public void setNombrePlanesAlimentos(String nombrePlanesAlimentos) {

        NombrePlanesAlimentos = nombrePlanesAlimentos;
    }
}
