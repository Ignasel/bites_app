package com.dinner.dinner;

import java.io.Serializable;

public class Dinner implements Serializable {
    private int id;
    private String pletimasType;
    private String motinele;
    private String medus;
    private String komentaras;
    private String seimosNr;

    public Dinner(int id, String pletimasType, String motinele, String komentaras, String medus, String seimosNr) {
        this.id = id;
        this.pletimasType = pletimasType;
        this.motinele = motinele;
        this.komentaras = komentaras;
        this.seimosNr = seimosNr;
        this.medus = medus;
    }

    public Dinner(String pletimasType, String motinele, String medus, String seimosNr, String komentaras) {
        this.pletimasType = pletimasType;
        this.motinele = motinele;
        this.komentaras = komentaras;
        this.medus = medus;
        this.seimosNr = seimosNr;
    }

    public int getId() {
        return id;
    }

    public String getPletimasType() {
        return pletimasType;
    }

    public void setpletimasType(String pletimasType) {
        this.pletimasType = pletimasType;
    }

    public String getMotinele() {
        return motinele;
    }

    public void setMotinele(String motinele) {
        this.motinele = motinele;
    }

    public String getKomentaras() {
        return komentaras;
    }

    public void setKomentaras(String komentaras) {
        this.komentaras = komentaras;
    }

    public String getMedus() {
        return medus;
    }

    public void setMedus(String medus) {
        this.medus = medus;
    }

    public String getSeimosNr() {
        return seimosNr;
    }

    public void setSeimosNr(String seimosNr) {
        this.seimosNr = seimosNr;
    }
}
