package com.example.apk1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ButtonPressedActivity extends AppCompatActivity {

    private Button button;
    private ArrayList<ModelOceny> mDane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_pressed);
        button = findViewById(R.id.finishButton);

        Bundle extras = getIntent().getExtras();
        int ile = Integer.parseInt(extras.get("ile").toString());

        //tworzenie danych
        mDane = new ArrayList<>();
        String[] nazwyPrzedmiotow
                = {"Angielski", "Matematyka", "Polski", "Fizyka", "Niemiecki", "Chemia"};
        for (int i = 0; i < ile && i < nazwyPrzedmiotow.length; i++)
            mDane.add(new ModelOceny(nazwyPrzedmiotow[i], 2));
        
        //tworzenie Adaptera
        InteraktywnyAdapterTablicy adapter = new InteraktywnyAdapterTablicy(this, mDane);
        
        //znalezienie referencji do obiektu RecyclerView
        RecyclerView mListaOcen = findViewById(R.id.lista_ocen_rv);

        //połączenie listy z recycler view (danymi)
        mListaOcen.setAdapter(adapter);

        //ustawienie layoutu rozmieszczającego elementy w RecyclerView
        //layout liniowy
        mListaOcen.setLayoutManager(new LinearLayoutManager(this));

        button.setOnClickListener(v -> {
            Intent data = new Intent();

            double avg = mDane.stream().mapToInt(ModelOceny::getOcena).sum()
                    / (1.0 * mDane.size());

            data.putExtra("avg", avg);
            setResult(RESULT_OK, data);
            finish();
        });

    }

}
