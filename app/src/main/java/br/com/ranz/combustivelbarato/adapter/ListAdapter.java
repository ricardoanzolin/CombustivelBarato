package br.com.ranz.combustivelbarato.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.List;
import java.util.Random;

import br.com.ranz.testetoolbar.R;
import br.com.ranz.combustivelbarato.model.Preco;


/**
 * Created by Ricardo on 08/10/2015.
 */
public class ListAdapter extends ArrayAdapter<Preco> {
    private final Context context;
    private final List<Preco> precos;

    public ListAdapter(Context context, List<Preco> precos) {
        super(context, R.layout.item_list_adapter);
        this.context = context;
        this.precos = precos;
    }

    @Override
    public int getCount(){
        return precos.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_list_adapter, parent, false);

        Preco preco = precos.get(position);

        TextView textViewPosto = (TextView) rowView.findViewById(R.id.textViewPosto);
        textViewPosto.setText(preco.posto);

        TextView textViewEndereco = (TextView) rowView.findViewById(R.id.textViewEndereco);
        textViewEndereco.setText(preco.endereco);

        TextView textViewGasolinaComum = (TextView) rowView.findViewById(R.id.textViewGasolinaComum);
        textViewGasolinaComum.setText("R$ " + String.valueOf(preco.precoGasolinaComum));

        TextView textViewGasolinaAditivada = (TextView) rowView.findViewById(R.id.textViewGasolinaAditivada);
        textViewGasolinaAditivada.setText("R$ " + String.valueOf(preco.precoGasolinaAditivada));

        TextView textViewAlcool = (TextView) rowView.findViewById(R.id.textViewAlcool);
        textViewAlcool.setText("R$ " + String.valueOf(preco.precoAlcool));

        TextView textViewDiesel = (TextView) rowView.findViewById(R.id.textViewDiesel);
        textViewDiesel.setText("R$ " + String.valueOf(preco.precoDiesel));

        TextDrawable drawable = TextDrawable.builder().buildRound(preco.posto.split(" ")[1].subSequence(0,1).toString(), generateRandomColor());
        ImageView image = (ImageView) rowView.findViewById(R.id.imageView);
        image.setImageDrawable(drawable);

        return rowView;
    }



    public int generateRandomColor() {
        final Random mRandom = new Random(System.currentTimeMillis());

        // This is the base color which will be mixed with the generated one
        final int baseColor = Color.WHITE;

        final int baseRed = Color.red(baseColor);
        final int baseGreen = Color.green(baseColor);
        final int baseBlue = Color.blue(baseColor);

        final int red = (baseRed + mRandom.nextInt(256)) / 2;
        final int green = (baseGreen + mRandom.nextInt(256)) / 2;
        final int blue = (baseBlue + mRandom.nextInt(256)) / 2;

        return Color.rgb(red, green, blue);
    }

}

