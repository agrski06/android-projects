package com.example.apk1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InteraktywnyAdapterTablicy
        extends RecyclerView.Adapter<InteraktywnyAdapterTablicy.OcenyViewHolder> {

    private final List<ModelOceny> mListaOcen;

    //odwołanie do layoutu
    private final LayoutInflater inflater;

    public InteraktywnyAdapterTablicy(Activity kontekst, List<ModelOceny> listaOcen) {
        inflater = kontekst.getLayoutInflater();
        this.mListaOcen = listaOcen;
    }

    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //utworzenie layoutu wiersza na podstawie XMLa
        View wiersz = inflater.inflate(R.layout.wiersz_oceny, parent,false);

        //zwrócenie nowego obiektu holdera
        return new OcenyViewHolder(wiersz);
    }

    @Override
    public void onBindViewHolder(@NonNull OcenyViewHolder holder, int position) {
        holder.text.setText(mListaOcen.get(position).getNazwa());
        holder.group.check(R.id.button2);
        holder.group.setTag(mListaOcen.get(position));
        holder.group.setOnCheckedChangeListener(holder);
    }

    @Override
    public int getItemCount() {
        return mListaOcen.size();
    }

    class OcenyViewHolder
            extends RecyclerView.ViewHolder
            implements RadioGroup.OnCheckedChangeListener {

        RadioGroup group;
        TextView text;

        public OcenyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.group = itemView.findViewById(R.id.radioGroup);
            this.text = itemView.findViewById(R.id.textView);
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            ModelOceny model = (ModelOceny) group.getTag();
            RadioButton button = itemView.findViewById(checkedId);
            int number = Integer.parseInt(button.getText().toString());
            model.setOcena(number);
            //mListaOcen.set(getAdapterPosition(), model);
        }
    }

    public List<ModelOceny> getmListaOcen() {
        return mListaOcen;
    }
}
