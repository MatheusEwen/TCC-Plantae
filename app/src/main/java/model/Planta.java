package model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

import config.ConfiguracaoFirebase;
import helper.Base64Custom;

public class Planta implements Serializable {

    private  String nomePlanta;
    private  String especie;
    private  String luz;
    private  String inf;
    private  String af;
    private  String dias;
    private  String cop;
    private  String url;
    private String chave;



    public Planta() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNomePlanta() {
        return nomePlanta;
    }

    public void setNomePlanta(String nomePlanta) {
        this.nomePlanta = nomePlanta;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getLuz() {
        return luz;
    }

    public void setLuz(String luz) {
        this.luz = luz;
    }

    public String getInf() {
        return inf;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }

    public String getAf() {
        return af;
    }

    public void setAf(String af) {
        this.af = af;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getCop() {
        return cop;
    }

    public void setCop(String cop) {
        this.cop = cop;
    }

    public String getChave() {
        return chave;
    }

    public String setChave(String chave) {
        this.chave = chave;
        return chave;
    }

    public void gerarPushKey(){
        DatabaseReference referencia = ConfiguracaoFirebase.getFirebaseDatabase();
        String chave = referencia.push().getKey();
        this.setChave(chave);
    }

    public void salvar() {
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());

        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("planta")
                .child(idUsuario)
                .child(this.chave)
                .setValue(this);
    }

    public Planta(String nomePlanta, String especie, String luz, String inf, String af, String dias, String cop, String url) {
        this.nomePlanta = nomePlanta;
        this.especie = especie;
        this.luz = luz;
        this.inf = inf;
        this.af = af;
        this.dias = dias;
        this.cop = cop;
        this.url = url;
    }
}
