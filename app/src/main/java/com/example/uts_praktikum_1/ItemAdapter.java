package com.example.uts_praktikum_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private ArrayList<Item> listItem;
    private OnItemClickCallback onItemClickCallback;
    private OnQuantityChangeListener onQuantityChangeListener;
    private int[] quantities;

    // Interface didefinisikan di sini agar tidak perlu file terpisah
    public interface OnItemClickCallback {
        void onItemClicked(Item data);
    }

    public ItemAdapter(ArrayList<Item> list) {
        this.listItem = list;
        this.quantities = new int[list.size()];
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setOnQuantityChangeListener(OnQuantityChangeListener onQuantityChangeListener) {
        this.onQuantityChangeListener = onQuantityChangeListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        Item item = listItem.get(position);
        holder.imgPhoto.setImageResource(item.getPhoto());
        holder.tvName.setText(item.getName());
        holder.tvDescription.setText(item.getDescription());
        holder.tvPrice.setText(item.getPrice());
        
        holder.tvQuantity.setText(String.valueOf(quantities[position]));

        holder.btnPlus.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                quantities[pos]++;
                holder.tvQuantity.setText(String.valueOf(quantities[pos]));
                if (onQuantityChangeListener != null) {
                    onQuantityChangeListener.onQuantityChanged();
                }
            }
        });

        holder.btnMinus.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION && quantities[pos] > 0) {
                quantities[pos]--;
                holder.tvQuantity.setText(String.valueOf(quantities[pos]));
                if (onQuantityChangeListener != null) {
                    onQuantityChangeListener.onQuantityChanged();
                }
            }
        });

        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION && onItemClickCallback != null) {
                onItemClickCallback.onItemClicked(listItem.get(pos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < listItem.size(); i++) {
            String priceStr = listItem.get(i).getPrice().replaceAll("[^0-9]", "");
            if (!priceStr.isEmpty()) {
                total += Integer.parseInt(priceStr) * quantities[i];
            }
        }
        return total;
    }

    public void resetQuantities() {
        for (int i = 0; i < quantities.length; i++) {
            quantities[i] = 0;
        }
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDescription, tvPrice, tvQuantity, btnPlus, btnMinus;

        ItemViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvPrice = itemView.findViewById(R.id.tv_item_price);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            btnPlus = itemView.findViewById(R.id.btn_plus);
            btnMinus = itemView.findViewById(R.id.btn_minus);
        }
    }

    public interface OnQuantityChangeListener {
        void onQuantityChanged();
    }
}