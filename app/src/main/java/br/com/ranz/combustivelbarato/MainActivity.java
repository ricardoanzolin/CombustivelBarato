package br.com.ranz.combustivelbarato;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.List;

import br.com.ranz.combustivelbarato.receiver.GenericResultReceiver;
import br.com.ranz.combustivelbarato.service.GenericService;
import br.com.ranz.testetoolbar.R;
import br.com.ranz.combustivelbarato.adapter.ListAdapter;
import br.com.ranz.combustivelbarato.model.Preco;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity implements GenericResultReceiver.Receiver {

    private  Toolbar mToolbar;
    private ListView mListView;
    private List<Preco> mPrecos;
    private GenericResultReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Paper.init(this);

        mReceiver = new GenericResultReceiver(new Handler());
        mReceiver.setReceiver(this);

        initComponents();

        if (Paper.book().exist("precos")){
            mPrecos = Paper.book().read("precos");
            initListAdapter();
        }

        getPrecos();
    }

    private void getPrecos(){
        final Intent intent = new Intent(Intent.ACTION_SYNC, null, this, GenericService.class);
        intent.putExtra("receiver", mReceiver);
        startService(intent);
    }

    private void initListAdapter() {
        ListAdapter adapter = new ListAdapter(this, mPrecos);
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initComponents() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        mListView = (ListView) findViewById(R.id.listView);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case GenericResultReceiver.RUNNING:
                break;
            case GenericResultReceiver.FINISHING:
                mPrecos = (List<Preco>) resultData.getSerializable("precos");
                Paper.book().delete("precos");
                Paper.book().write("precos", mPrecos);
                initListAdapter();
                break;
            case GenericResultReceiver.ERROR:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mReceiver.setReceiver(null);
    }

}
