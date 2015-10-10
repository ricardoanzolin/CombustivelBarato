package br.com.ranz.combustivelbarato.networking;

import java.util.List;

import br.com.ranz.combustivelbarato.model.Preco;
import retrofit.http.GET;


/**
 * Created by Ricardo on 08/10/2015.
 */
public interface PrecosService {

    @GET("/combustivelbarato/precos/master/precos.json")
    List<Preco> getPrecos();
}
