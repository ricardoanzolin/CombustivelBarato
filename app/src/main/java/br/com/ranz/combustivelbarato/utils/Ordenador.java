package br.com.ranz.combustivelbarato.utils;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ricardo on 10/10/2015.
 */
public class Ordenador {

    public <T extends Object> List<T> ordena(List<T> lista, String nomeDaPropriedade, Boolean ordemNatural) {
        PropertyComparator comparator = new PropertyComparator(nomeDaPropriedade, ordemNatural);
        Collections.sort(lista, comparator);

        return lista;
    }

    public <T extends Object> List<T> ordena(List<T> lista, String[] nomeDasPropriedades, Boolean ordemNatural) {
        PropertyComparator comparator = new PropertyComparator(nomeDasPropriedades, ordemNatural);
        Collections.sort(lista, comparator);

        return lista;
    }

    public <T extends Object> List<T> ordena(List<T> lista, String[] nomeDasPropriedades, boolean[] ordensNaturais) {
        PropertyComparator comparator = new PropertyComparator(nomeDasPropriedades, ordensNaturais);
        Collections.sort(lista, comparator);

        return lista;
    }

}
