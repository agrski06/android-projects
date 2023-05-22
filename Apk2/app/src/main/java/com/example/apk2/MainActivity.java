package com.example.apk2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_PHONE_CODE = 10;
    private static final int EDIT_PHONE_CODE = 11;

    private PhoneViewModel phoneViewModel;
    private PhoneListAdapter phoneListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddPhoneActivity.class);
            startActivityForResult(intent, ADD_PHONE_CODE);
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        phoneListAdapter = new PhoneListAdapter(this);
        recyclerView.setAdapter(phoneListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, AddPhoneActivity.class);
                Phone phone = phoneViewModel.getAllPhones().getValue().get(position);
                intent.putExtra("id", phone.getId());
                intent.putExtra("manufacturer", phone.getManufacturer());
                intent.putExtra("model", phone.getModel());
                intent.putExtra("version", phone.getAndroidVersion());
                intent.putExtra("website", phone.getWebPage());
                startActivityForResult(intent, EDIT_PHONE_CODE);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        phoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);

        // set elements in adapter, when changed in database
        phoneViewModel.getAllPhones().observe(this,
                elements -> phoneListAdapter.setElementList(elements));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PHONE_CODE) {
            if (resultCode == AddPhoneActivity.PHONE_ADDED_CODE) {
                if (data != null) {
                    Phone phone = new Phone(data.getExtras().getString("manufacturer"),
                            data.getExtras().getString("model"),
                            data.getExtras().getString("version"),
                            data.getExtras().getString("website"));

                    phoneViewModel.insert(phone);
                }
            }
        }
        if (requestCode == EDIT_PHONE_CODE) {
            if (resultCode == AddPhoneActivity.PHONE_EDITED_CODE) {
                if (data != null) {
                    phoneViewModel.update(data.getExtras().getLong("id"),
                            data.getExtras().getString("manufacturer"),
                            data.getExtras().getString("model"),
                            data.getExtras().getString("version"),
                            data.getExtras().getString("website"));
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.clear) {
            phoneViewModel.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}