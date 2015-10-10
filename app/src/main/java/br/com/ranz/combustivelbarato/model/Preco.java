package br.com.ranz.combustivelbarato.model;

import java.io.Serializable;

/**
 * Created by Ricardo on 08/10/2015.
 */
public class Preco implements Serializable {
    public String posto;
    public double precoGasolinaComum;
    public double precoGasolinaAditivada;
    public double precoAlcool;
    public double precoDiesel;
    public String endereco;
    public double latitude;
    public double longitude;
}
