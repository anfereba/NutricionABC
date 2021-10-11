package com.anfereba.nutricionabc.db.Entidades;

public class Hijos {
    private  Integer IdHijos;
    private byte[] FotoHijos;
    private String NombreHijos;
    private String EstaturaHijos;
    private Integer EdadHijos;
    private Integer PesoHijos;

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
}
