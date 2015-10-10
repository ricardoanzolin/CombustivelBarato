package br.com.ranz.combustivelbarato.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

import br.com.ranz.combustivelbarato.model.Preco;
import br.com.ranz.combustivelbarato.networking.PrecosService;
import br.com.ranz.combustivelbarato.receiver.GenericResultReceiver;
import retrofit.RestAdapter;

/**
 * Created by Ricardo on 10/10/2015.
 */
public class GenericService extends IntentService {

    Bundle b = new Bundle();

    public GenericService(){
        super("GenericService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        receiver.send(GenericResultReceiver.RUNNING, Bundle.EMPTY);

        try {
            RestAdapter retrofit = new RestAdapter
                    .Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("https://raw.githubusercontent.com")
                    .build();

            PrecosService service = retrofit.create(PrecosService.class);
            List<Preco> precos = service.getPrecos();
            b.putSerializable("precos", (Serializable) precos);
            receiver.send(GenericResultReceiver.FINISHING, b);
        }catch (Exception e){
            b.putSerializable("error", e);
            receiver.send(GenericResultReceiver.ERROR, b);
        }
    }
}
