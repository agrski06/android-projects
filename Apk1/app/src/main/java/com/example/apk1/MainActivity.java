package com.example.apk1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText gradeNumberInput;
    private Button button;
    private TextView avg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNameInput = findViewById(R.id.name);
        lastNameInput = findViewById(R.id.lastName);
        gradeNumberInput = findViewById(R.id.gradeNumber);
        button = findViewById(R.id.przycisk);
        avg = findViewById(R.id.avg);

        firstNameInput.setOnFocusChangeListener((view, hasFocus) -> {
            if (firstNameInput.getText().toString().equals("") && !hasFocus){
                firstNameInput.setError(getString(R.string.komunikatName));
            }
            setButtonVisibility();
        });

        lastNameInput.setOnFocusChangeListener((view, hasFocus) -> {
            if (lastNameInput.getText().toString().equals("") && !hasFocus) {
                lastNameInput.setError(getString(R.string.komunikatLastName));
            }
            setButtonVisibility();

        });

        lastNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setButtonVisibility();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        gradeNumberInput.setOnFocusChangeListener((view, hasFocus) -> {
            if (gradeNumberInput.getText().toString().equals("") && !hasFocus) {
                gradeNumberInput.setError(getString(R.string.komunikatGrade));
            }
            setButtonVisibility();
        });

        gradeNumberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setButtonVisibility();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ButtonPressedActivity.class);
            intent.putExtra("ile", Integer.parseInt(gradeNumberInput.getText().toString()));
            startActivityForResult(intent, 0);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            double avg = data.getExtras().getDouble("avg");
            this.avg.setText(Double.toString(avg));
            this.avg.setVisibility(View.VISIBLE);
        }
    }

    private void setButtonVisibility() {
        boolean isGradeCountValid = true;

        int gradeCount = gradeNumberInput.getText().toString().isEmpty()
                ? 0
                : Integer.parseInt(gradeNumberInput.getText().toString());

        if (gradeCount < 5 || gradeCount > 15)
            isGradeCountValid = false;

        if (firstNameInput.getText().toString().isEmpty() ||
                lastNameInput.getText().toString().isEmpty() ||
                !isGradeCountValid) {
            button.setVisibility(View.INVISIBLE);
        } else {
            button.setVisibility(View.VISIBLE);
        }
    }

}