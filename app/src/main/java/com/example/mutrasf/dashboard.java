package com.example.mutrasf;

import static com.example.mutrasf.DBHelper.COLUMN_ID;
import static com.example.mutrasf.DBHelper.COLUMN_USER_ID;
import static com.example.mutrasf.DBHelper.COLUMN_CONTACT_PHONE;
import static com.example.mutrasf.DBHelper.COLUMN_FOODTRUCK_NAME;
//import static com.example.mutrasf.DBHelper.COLUMN_FOODTRUCK_PHOTO;
import static com.example.mutrasf.DBHelper.COLUMN_FOODTRUCK_PRICE;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {

    DBHelper db;

    ArrayList<String> name, price, phone, id;
    ArrayList<byte[]> photo;

    RecyclerView recycler;
    TruckAdapter adapter;
    TextView userNameTextView;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("phoneNumber");

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homemenu) {
                    startActivity(new Intent(dashboard.this, dashboard.class));
                    return true;
                } else if (item.getItemId() == R.id.wishlist) {
                    startActivity(new Intent(dashboard.this, wishlist.class));
                    return true;
                } else if (item.getItemId() == R.id.my_reservations) {
                    startActivity(new Intent(dashboard.this, myreservations.class));
                    return true;
                } else if (item.getItemId() == R.id.logout) {
                    // Handle logout menu item click
                    // Perform logout actions
                    return true;
                }
                return false;

            }
        });


        recycler = findViewById(R.id.recycler);
        db = new DBHelper(this);
        name = new ArrayList<>();
        price = new ArrayList<>();
        phone = new ArrayList<>();
        id = new ArrayList<>();
        //photo = new ArrayList<>();
        int userId = db.getUserIdFromName(userName);
        String userName1 = db.GetUserName(userId);
        userNameTextView = findViewById(R.id.HelloText);
        userNameTextView.setText("Hi "+ userName1);

        //adapter = new TruckAdapter(this, name, price, photo, phone, id);
        adapter = new TruckAdapter(this, name, price, phone, id);
        recycler.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(gridLayoutManager);

        displayData();
    }

    private void displayData() {
        //Intent intent = getIntent();
        //String userEmail = intent.getStringExtra("userEmail");

        Cursor cursor = db.getFoodTrucks();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No food truck to reserve", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                try {
                    id.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                    name.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FOODTRUCK_NAME)).concat(" "));
                    price.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FOODTRUCK_PRICE)));
                    phone.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTACT_PHONE)));
                    //photo.add(cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_FOODTRUCK_PHOTO)));

                } catch (Exception e) {
                    // Handle the exception
                }
            }
        }
    }

    /*
    Button getUsernameButton = findViewById(R.id.get_username_button);


getUsernameButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int userID = 123; // Replace with the actual user ID you want to retrieve the name for
        String userName = GetUserName(userID);

        // Update the TextView with the retrieved username
        userNameTextView.setText(userName);
    }
});
        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("UserName");
        TextView userNameTextView = findViewById(R.id.HelloText);
        userNameTextView.setText(userName);
     */
}