package com.anfereba.nutricionabc.db.Entidades;

public class PlanesDiarios {
    private Integer idCumplimientoPlanDiario;
    private Integer idHijo;
    private  Integer idPlanAlimento;
    private Integer Cumplimiento;
    private String NombreAlimento;
    private Integer PlanNutricional;
    private Integer idAlimento;

    public Integer getIdCumplimientoPlanDiario() {
        return idCumplimientoPlanDiario;
    }

    public void setIdCumplimientoPlanDiario(Integer idCumplimientoPlanDiario) {
        this.idCumplimientoPlanDiario = idCumplimientoPlanDiario;
    }

    public Integer getIdHijo() {
        return idHijo;
    }

    public void setIdHijo(Integer idHijo) {
        this.idHijo = idHijo;
    }

    public Integer getIdPlanAlimento() {
        return idPlanAlimento;
    }

    public void setIdPlanAlimento(Integer idPlanAlimento) {
        this.idPlanAlimento = idPlanAlimento;
    }

    public Integer getCumplimiento() {
        return Cumplimiento;
    }

    public void setCumplimiento(Integer cumplimiento) {
        Cumplimiento = cumplimiento;
    }

    public String getNombreAlimento() {
        return NombreAlimento;
    }

    public void setNombreAlimento(String nombreAlimento) {
        NombreAlimento = nombreAlimento;
    }

    public Integer getPlanNutricional() {
        return PlanNutricional;
    }

    public void setPlanNutricional(Integer planNutricional) {
        PlanNutricional = planNutricional;
    }

    public Integer getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(Integer idAlimento) {
        this.idAlimento = idAlimento;
    }
}
