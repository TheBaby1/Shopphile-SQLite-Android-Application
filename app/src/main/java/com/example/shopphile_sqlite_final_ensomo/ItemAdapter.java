package com.example.shopphile_sqlite_final_ensomo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> itemList;
    private DatabaseHandler dbHandler;
    private Context context;

    public ItemAdapter(List<Item> itemList, DatabaseHandler dbHandler, Context context) {
        this.itemList = itemList;
        this.dbHandler = dbHandler;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.brandName.setText(item.getBrandName());
        holder.productName.setText(item.getProductName());
        holder.productPrice.setText(item.getProductPrice());
        holder.productImage.setImageResource(item.getProductImage());  // Set the product image

        holder.addToCartButton.setOnClickListener(v -> {
            dbHandler.addToCart(item);
            Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView brandName, productName, productPrice;
        public ImageView productImage;
        public Button addToCartButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.brandName);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);  // Initialize the ImageView
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }
}
