package com.example.uts_praktikum_1;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;

public class semuanyaFragment extends BaseFragment {
    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_semua, container, false);
        recyclerView = v.findViewById(R.id.rv_items);
        ArrayList<Item> list = new ArrayList<>();
        list.addAll(getData(R.array.nama_makanan, R.array.makanan_description, R.array.foto_makanan, R.array.harga_makanan, "makanan"));
        list.addAll(getData(R.array.nama_kebutuhan, R.array.kebutuhan_description, R.array.foto_kebutuhan, R.array.harga_kebutuhan, "kebutuhan"));
        list.addAll(getData(R.array.nama_permainan, R.array.permainan_description, R.array.foto_permainan, R.array.harga_permainan, "permainan"));
        
        adapter = new ItemAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnQuantityChangeListener(() -> {
            if (getActivity() instanceof WelcomeActivity) ((WelcomeActivity) getActivity()).updateTotalPrice(getTotalPrice());
        });
        return v;
    }
}