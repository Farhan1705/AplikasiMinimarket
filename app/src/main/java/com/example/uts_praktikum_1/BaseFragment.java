package com.example.uts_praktikum_1;

import android.content.res.TypedArray;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public abstract class BaseFragment extends Fragment {
    protected ItemAdapter adapter;
    protected RecyclerView recyclerView;

    public int getTotalPrice() { return adapter != null ? adapter.getTotalPrice() : 0; }
    public void resetItems() { if (adapter != null) adapter.resetQuantities(); }
    public RecyclerView getRecyclerView() { return recyclerView; }

    protected ArrayList<Item> getData(int names, int descs, int photos, int prices, String cat) {
        String[] n = getResources().getStringArray(names), d = getResources().getStringArray(descs), p = getResources().getStringArray(prices);
        TypedArray ph = getResources().obtainTypedArray(photos);
        ArrayList<Item> list = new ArrayList<>();
        for (int i = 0; i < n.length; i++) list.add(new Item(n[i], d[i], ph.getResourceId(i, -1), cat, p[i]));
        ph.recycle();
        return list;
    }
}