package com.example.shopphile_sqlite_final_ensomo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    ImageButton cartButton, orderButton;
    CardView newArrivalsCard, popularProductsCard;
    Button shopNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        orderButton = findViewById(R.id.bellButton);
        cartButton = (ImageButton)findViewById(R.id.cartButton);
        newArrivalsCard = (CardView)findViewById(R.id.cardView3);
        shopNowButton = (Button)findViewById(R.id.shopNowButton);
        popularProductsCard = (CardView)findViewById(R.id.popularProductsCard);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cartIntent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(cartIntent);
            }
        });

        newArrivalsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newArrivalsIntent = new Intent(HomeActivity.this, ArrivalsActivity.class);
                startActivity(newArrivalsIntent);
            }
        });

        shopNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newArrivalsIntent = new Intent(HomeActivity.this, ArrivalsActivity.class);
                startActivity(newArrivalsIntent);
            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent popularProductsIntent = new Intent(HomeActivity.this, OrderActivity.class);
                //startActivity(popularProductsIntent);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}