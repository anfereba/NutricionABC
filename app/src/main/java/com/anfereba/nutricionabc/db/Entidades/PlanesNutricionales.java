package com.anfereba.nutricionabc.db.Entidades;

public class PlanesNutricionales {
    private Integer idPlanNutricional;
    private Integer idUsuario;
    private String NombrePlan;

    public Integer getIdPlanNutricional() {
        return idPlanNutricional;
    }

    public void setIdPlanNutricional(Integer idPlanNutricional) {
        this.idPlanNutricional = idPlanNutricional;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombrePlan() {
        return NombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        NombrePlan = nombrePlan;
    }
}
