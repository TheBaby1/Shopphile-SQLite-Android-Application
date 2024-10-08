package com.example.shopphile_sqlite_final_ensomo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cartManager";
    private static final String TABLE_CART = "cart";

    private static final String KEY_ID = "id";
    private static final String KEY_BRAND = "brandName";
    private static final String KEY_PRODUCT = "productName";
    private static final String KEY_PRICE = "productPrice";
    private static final String KEY_IMAGE = "productImage";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_BRAND + " TEXT,"
                + KEY_PRODUCT + " TEXT,"
                + KEY_PRICE + " TEXT,"
                + KEY_IMAGE + " INTEGER" + ")";  // Add product image column
        db.execSQL(CREATE_CART_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    // Add item to cart
    public void addToCart(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BRAND, item.getBrandName());
        values.put(KEY_PRODUCT, item.getProductName());
        values.put(KEY_PRICE, item.getProductPrice());
        values.put(KEY_IMAGE, item.getProductImage());  // Add image to the cart
        db.insert(TABLE_CART, null, values);
        db.close();
    }


    // Get all cart items
    public List<Item> getAllCartItems() {
        List<Item> itemList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CART;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4)  // Get image from the database
                );
                itemList.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return itemList;
    }

    public void deleteCartItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

}