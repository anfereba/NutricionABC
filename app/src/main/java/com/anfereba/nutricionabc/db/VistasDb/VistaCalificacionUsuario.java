package com.anfereba.nutricionabc.db.VistasDb;


public class VistaCalificacionUsuario {
    private int IdCalificacion;
    private int IdPadre;
    private int IdNutriologo;
    private String nombrePadre;
    private String apellidosPadre;
    private String CorreoPadre;
    private byte[] fotoPadre;
    private String nombreNutriologo;
    private String apellidosNutriologo;
    private String CorreoNutriologo;
    private byte[] fotoNutriologo;
    private Double Puntuacion;
    private String Comentario;

    public VistaCalificacionUsuario(int idCalificacion, int idPadre, int idNutriologo, String nombrePadre, String apellidosPadre, String correoPadre, byte[] fotoPadre, String nombreNutriologo, String apellidosNutriologo, String correoNutriologo, byte[] fotoNutriologo, Double puntuacion, String comentario) {
        IdCalificacion = idCalificacion;
        IdPadre = idPadre;
        IdNutriologo = idNutriologo;
        this.nombrePadre = nombrePadre;
        this.apellidosPadre = apellidosPadre;
        CorreoPadre = correoPadre;
        this.fotoPadre = fotoPadre;
        this.nombreNutriologo = nombreNutriologo;
        this.apellidosNutriologo = apellidosNutriologo;
        CorreoNutriologo = correoNutriologo;
        this.fotoNutriologo = fotoNutriologo;
        Puntuacion = puntuacion;
        Comentario = comentario;
    }
    public VistaCalificacionUsuario(){

    }

    public int getIdCalificacion() {
        return IdCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        IdCalificacion = idCalificacion;
    }

    public int getIdPadre() {
        return IdPadre;
    }

    public void setIdPadre(int idPadre) {
        IdPadre = idPadre;
    }

    public int getIdNutriologo() {
        return IdNutriologo;
    }

    public void setIdNutriologo(int idNutriologo) {
        IdNutriologo = idNutriologo;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public String getApellidosPadre() {
        return apellidosPadre;
    }

    public void setApellidosPadre(String apellidosPadre) {
        this.apellidosPadre = apellidosPadre;
    }

    public String getCorreoPadre() {
        return CorreoPadre;
    }

    public void setCorreoPadre(String correoPadre) {
        CorreoPadre = correoPadre;
    }

    public byte[] getFotoPadre() {
        return fotoPadre;
    }

    public void setFotoPadre(byte[] fotoPadre) {
        this.fotoPadre = fotoPadre;
    }

    public String getNombreNutriologo() {
        return nombreNutriologo;
    }

    public void setNombreNutriologo(String nombreNutriologo) {
        this.nombreNutriologo = nombreNutriologo;
    }

    public String getApellidosNutriologo() {
        return apellidosNutriologo;
    }

    public void setApellidosNutriologo(String apellidosNutriologo) {
        this.apellidosNutriologo = apellidosNutriologo;
    }

    public String getCorreoNutriologo() {
        return CorreoNutriologo;
    }

    public void setCorreoNutriologo(String correoNutriologo) {
        CorreoNutriologo = correoNutriologo;
    }

    public byte[] getFotoNutriologo() {
        return fotoNutriologo;
    }

    public void setFotoNutriologo(byte[] fotoNutriologo) {
        this.fotoNutriologo = fotoNutriologo;
    }

    public Double getPuntuacion() {
        return Puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        Puntuacion = puntuacion;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }
}
