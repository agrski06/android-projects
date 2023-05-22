package com.example.apk2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddPhoneActivity extends AppCompatActivity {

    public static final int PHONE_ADDED_CODE = 10;
    public static final int PHONE_EDITED_CODE = 11;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone);

        EditText manufacturer = findViewById(R.id.manufacturerInput);
        EditText model = findViewById(R.id.modelInput);
        EditText version = findViewById(R.id.versionInput);
        EditText website = findViewById(R.id.websiteInput);

        manufacturer.addTextChangedListener(new NotEmptyTextWatcher(manufacturer));
        model.addTextChangedListener(new NotEmptyTextWatcher(model));
        version.addTextChangedListener(new NotEmptyTextWatcher(version));
        website.addTextChangedListener(new NotEmptyTextWatcher(website));

        if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
            manufacturer.setText(getIntent().getExtras().getString("manufacturer"));
            model.setText(getIntent().getExtras().getString("model"));
            version.setText(getIntent().getExtras().getString("version"));
            website.setText(getIntent().getExtras().getString("website"));
        }

        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> finish());

        Button webButton = findViewById(R.id.webButton);
        webButton.setOnClickListener(v -> {
            if (!website.getText().toString().isEmpty()) {
                String url = website.getText().toString();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> {
            String manufacturerText = manufacturer.getText().toString();
            String modelText = model.getText().toString();
            String versionText = version.getText().toString();
            String websiteText = website.getText().toString();

            //check if fields not empty
            if (!manufacturerText.isEmpty() && !modelText.isEmpty() && !versionText.isEmpty() &&
                    !websiteText.isEmpty()) {
                if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
                    Intent intent = new Intent();
                    intent.putExtra("id", getIntent().getExtras().getLong("id"));
                    intent.putExtra("manufacturer", manufacturerText);
                    intent.putExtra("model", modelText);
                    intent.putExtra("version", versionText);
                    intent.putExtra("website", websiteText);
                    setResult(PHONE_EDITED_CODE, intent);
                    finish();
                }
                Intent intent = new Intent();
                intent.putExtra("manufacturer", manufacturerText);
                intent.putExtra("model", modelText);
                intent.putExtra("version", versionText);
                intent.putExtra("website", websiteText);
                setResult(PHONE_ADDED_CODE, intent);
                finish();
            }
        });
    }

}
