package com.example.shopphile_sqlite_final_ensomo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ArrivalsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> itemList;
    private DatabaseHandler dbHandler;

    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrivals);

        dbHandler = new DatabaseHandler(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backButton = (ImageButton)findViewById(R.id.backButton);

        itemList = new ArrayList<>();
        // Add sample items
        itemList.add(new Item("Uniqlo", "Green Hoodie", "$10", R.drawable.jacket));
        itemList.add(new Item("Penshoppe", "Wide Pants", "$20", R.drawable.widepants));
        itemList.add(new Item("Shein", "Joggers", "$22", R.drawable.joggers));
        itemList.add(new Item("HammerHead", "Slim Jeans", "$13", R.drawable.slimjeans));
        itemList.add(new Item("Penshoppe", "Short Shorts", "$13", R.drawable.shorts));
        itemList.add(new Item("Shein", "Midi Skirt", "$32", R.drawable.skirt));
        itemList.add(new Item("Uniqlo", "Sneakers", "$23", R.drawable.shoes));

        itemAdapter = new ItemAdapter(itemList, dbHandler, this);
        recyclerView.setAdapter(itemAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent homeIntent = new Intent(ArrivalsActivity.this, HomeActivity.class);
                startActivity(homeIntent);
            }
        });

    }
}