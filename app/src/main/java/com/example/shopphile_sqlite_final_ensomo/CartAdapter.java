package com.example.shopphile_sqlite_final_ensomo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<Item> itemList;
    private DatabaseHandler dbHandler;
    /*
    public CartAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }
    */
    public CartAdapter(List<Item> itemList, DatabaseHandler dbHandler) {
        this.itemList = itemList;
        this.dbHandler = dbHandler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.brandName.setText(item.getBrandName());
        holder.productName.setText(item.getProductName());
        holder.productPrice.setText(item.getProductPrice());
        holder.productImage.setImageResource(item.getProductImage());  // Set the product image

        // Handle the delete button click
        holder.deleteButton.setOnClickListener(v -> {
            // Remove item from the database
            dbHandler.deleteCartItem(item.getId());

            // Remove the item from the list and notify the adapter
            itemList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, itemList.size());
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView brandName, productName, productPrice;
        public ImageView productImage;
        public Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.brandName);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);  // Initialize the ImageView
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}

