package com.example.uts_praktikum_1;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

public class kebutuhanFragment extends BaseFragment {
    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater i, @Nullable ViewGroup c, @Nullable Bundle s) {
        View v = i.inflate(R.layout.fragment_kebutuhan, c, false);
        recyclerView = v.findViewById(R.id.rv_kebutuhan);
        adapter = new ItemAdapter(getData(R.array.nama_kebutuhan, R.array.kebutuhan_description, R.array.foto_kebutuhan, R.array.harga_kebutuhan, "kebutuhan"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnQuantityChangeListener(() -> {
            if (getActivity() instanceof WelcomeActivity) ((WelcomeActivity) getActivity()).updateTotalPrice(getTotalPrice());
        });
        return v;
    }
}