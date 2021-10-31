package com.anfereba.nutricionabc.db.Entidades;

public class HistorialPlanes {

    private String NombreHijo;
    private Integer idHijo;
    private String NombrePlanNutricional;
    private Integer idPlanNutricional;
    private Integer CumplimientoDelPlan;
    private String VistoBueno;
    private byte[] FotoHijos;

    public byte[] getFotoHijos() {
        return FotoHijos;
    }

    public void setFotoHijos(byte[] fotoHijos) {
        FotoHijos = fotoHijos;
    }



    public String getNombreHijo() {
        return NombreHijo;
    }

    public void setNombreHijo(String nombreHijo) {
        NombreHijo = nombreHijo;
    }

    public Integer getIdHijo() {
        return idHijo;
    }

    public void setIdHijo(Integer idHijo) {
        this.idHijo = idHijo;
    }

    public String getNombrePlanNutricional() {
        return NombrePlanNutricional;
    }

    public void setNombrePlanNutricional(String nombrePlanNutricional) {
        NombrePlanNutricional = nombrePlanNutricional;
    }

    public Integer getIdPlanNutricional() {
        return idPlanNutricional;
    }

    public void setIdPlanNutricional(Integer idPlanNutricional) {
        this.idPlanNutricional = idPlanNutricional;
    }

    public Integer getCumplimientoDelPlan() {
        return CumplimientoDelPlan;
    }

    public void setCumplimientoDelPlan(Integer cumplimientoDelPlan) {
        CumplimientoDelPlan = cumplimientoDelPlan;
    }

    public String getVistoBueno() {
        return VistoBueno;
    }

    public void setVistoBueno(String vistoBueno) {
        VistoBueno = vistoBueno;
    }
}
