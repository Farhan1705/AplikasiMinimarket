package com.example.uts_praktikum_1;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

public class makananfragment extends BaseFragment {
    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater i, @Nullable ViewGroup c, @Nullable Bundle s) {
        View v = i.inflate(R.layout.fragment_makanan, c, false);
        recyclerView = v.findViewById(R.id.makanan);
        adapter = new ItemAdapter(getData(R.array.nama_makanan, R.array.makanan_description, R.array.foto_makanan, R.array.harga_makanan, "makanan"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnQuantityChangeListener(() -> {
            if (getActivity() instanceof WelcomeActivity) ((WelcomeActivity) getActivity()).updateTotalPrice(getTotalPrice());
        });
        return v;
    }
}