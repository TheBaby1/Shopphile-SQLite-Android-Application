package com.example.shopphile_sqlite_final_ensomo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private DatabaseHandler dbHandler;

    ImageButton backButton;
    Button checkOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dbHandler = new DatabaseHandler(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backButton = (ImageButton)findViewById(R.id.backButton);
        checkOutButton = (Button)findViewById(R.id.checkoutButton);

        List<Item> cartItems = dbHandler.getAllCartItems();
        cartAdapter = new CartAdapter(cartItems, dbHandler);
        recyclerView.setAdapter(cartAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent homeIntent = new Intent(CartActivity.this, HomeActivity.class);
                startActivity(homeIntent);
            }
        });

        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent orderIntent = new Intent(CartActivity.this, OrderActivity.class);
                startActivity(orderIntent);
            }
        });

    }
}