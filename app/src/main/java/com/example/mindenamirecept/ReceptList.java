package com.example.mindenamirecept;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ReceptList extends ArrayAdapter<Recept> {

    private Activity context;
    private List<Recept> receptList;
    TextView txtRecept0;

    public ReceptList(Activity context, List<Recept> receptList){
        super(context, R.layout.activity_eloetelek, receptList);
        this.context = context;
        this.receptList = receptList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_eloetelek, null, true);

        //TextView txtRecept0 = (TextView) listViewItem.findViewById(R.id.txtRecept0);

        Recept recept = receptList.get(position);

        txtRecept0.setText(recept.getReceptNev());

        return listViewItem;
    }


}
