package com.example.uts_praktikum_1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class CartViewModel extends ViewModel {
    private final MutableLiveData<Map<String, Integer>> itemQuantities = new MutableLiveData<>(new HashMap<>());
    private final MutableLiveData<Integer> totalHarga = new MutableLiveData<>(0);

    public LiveData<Map<String, Integer>> getItemQuantities() {
        return itemQuantities;
    }

    public LiveData<Integer> getTotalHarga() {
        return totalHarga;
    }

    public void updateQuantity(Item item, int quantity) {
        Map<String, Integer> currentMap = itemQuantities.getValue();
        if (currentMap != null) {
            currentMap.put(item.getName(), quantity);
            itemQuantities.setValue(currentMap);
        }
    }
    
    public void setTotal(int total) {
        totalHarga.setValue(total);
    }
}