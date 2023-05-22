package com.example.apk2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneListAdapter extends
        RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder> {
    private final LayoutInflater layoutInflater;
    private List<Phone> phones;

    public PhoneListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.phones = null;
    }

    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = layoutInflater.inflate(R.layout.phone_row, parent, false);
        return new PhoneViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        Phone phone = phones.get(position);
        holder.manufacturer.setText(phone.getManufacturer());
        holder.model.setText(phone.getModel());
        holder.manufacturer.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context , AddPhoneActivity.class);
            intent.putExtra("edit", true);
            intent.putExtra("id", phone.getId());
            intent.putExtra("manufacturer", phone.getManufacturer());
            intent.putExtra("model", phone.getModel());
            intent.putExtra("version", phone.getAndroidVersion());
            intent.putExtra("website", phone.getWebPage());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (phones != null)
            return phones.size();
        return 0;
    }

    public void setElementList(List<Phone> phoneList) {
        this.phones = phoneList;
        notifyDataSetChanged();
    }

    public static class PhoneViewHolder extends RecyclerView.ViewHolder {

        TextView manufacturer;
        TextView model;

        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            manufacturer = itemView.findViewById(R.id.manufacturer);
            model = itemView.findViewById(R.id.model);
        }

    }

}
