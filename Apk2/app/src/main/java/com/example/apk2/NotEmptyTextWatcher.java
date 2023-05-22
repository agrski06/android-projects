package com.example.apk2;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class NotEmptyTextWatcher implements TextWatcher {
    private final EditText input;

    public NotEmptyTextWatcher(EditText input) {
        this.input = input;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() == 0) {
            input.setError("Fill this field!");
        }
    }

    @Override
    public void afterTextChanged(Editable s) {}
}
