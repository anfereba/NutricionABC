package com.anfereba.nutricionabc.db.Entidades;

public class Hijos {
    private  Integer IdHijos;
    private byte[] FotoHijos;
    private String NombreHijos;
    private String EstaturaHijos;
    private Integer EdadHijos;
    private Integer PesoHijos;
    private Integer idUsuario3;
    private Integer idPlanNutricional3;


    public Integer getIdHijos() {
        return IdHijos;
    }

    public void setIdHijos(Integer idHijos) {
        IdHijos = idHijos;
    }

    public byte[] getFotoHijos() {
        return FotoHijos;
    }

    public void setFotoHijos(byte[] fotoHijos) {
        FotoHijos = fotoHijos;
    }

    public String getNombreHijos() {
        return NombreHijos;
    }

    public void setNombreHijos(String nombreHijos) {
        NombreHijos = nombreHijos;
    }

    public String getEstaturaHijos() {
        return EstaturaHijos;
    }

    public void setEstaturaHijos(String estaturaHijos) {
        EstaturaHijos = estaturaHijos;
    }

    public Integer getEdadHijos() {
        return EdadHijos;
    }

    public void setEdadHijos(Integer edadHijos) {
        EdadHijos = edadHijos;
    }

    public Integer getPesoHijos() {
        return PesoHijos;
    }

    public void setPesoHijos(Integer pesoHijos) {
        PesoHijos = pesoHijos;
    }

    public Integer getIdUsuario3() {
        return idUsuario3;
    }

    public void setIdUsuario3(Integer idUsuario3) {
        this.idUsuario3 = idUsuario3;
    }

    public Integer getIdPlanNutricional3() {
        return idPlanNutricional3;
    }

    public void setIdPlanNutricional3(Integer idPlanNutricional3) {
        this.idPlanNutricional3 = idPlanNutricional3;
    }
}
