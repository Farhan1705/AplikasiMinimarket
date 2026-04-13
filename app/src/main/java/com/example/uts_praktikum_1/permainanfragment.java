package com.example.uts_praktikum_1;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

public class permainanfragment extends BaseFragment {
    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater i, @Nullable ViewGroup c, @Nullable Bundle s) {
        View v = i.inflate(R.layout.fragment_permainan, c, false);
        recyclerView = v.findViewById(R.id.rv_permainan);
        adapter = new ItemAdapter(getData(R.array.nama_permainan, R.array.permainan_description, R.array.foto_permainan, R.array.harga_permainan, "permainan"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnQuantityChangeListener(() -> {
            if (getActivity() instanceof WelcomeActivity) ((WelcomeActivity) getActivity()).updateTotalPrice(getTotalPrice());
        });
        return v;
    }
}