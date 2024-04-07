package com.example.mutrasf;
        import static com.example.mutrasf.DBHelper.COLUMN_RESERVEDID;
        import static com.example.mutrasf.DBHelper.COLUMN_USERID;
        import static com.example.mutrasf.DBHelper.COLUMN_TIME;
        import static com.example.mutrasf.DBHelper.COLUMN_DATE;
//import static com.example.mutrasf.DBHelper.COLUMN_FOODTRUCK_PHOTO;
        import static com.example.mutrasf.DBHelper.COLUMN_TRUCK_ID;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.annotation.SuppressLint;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class myreservations extends AppCompatActivity {

    DBHelper db;

    ArrayList<String> name, date, time, id;
    ArrayList<byte[]> photo;

    RecyclerView recycler;
    TruckAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreservations);

        recycler = findViewById(R.id.recycler);
        db = new DBHelper(this);
        name = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();
        id = new ArrayList<>();
        //photo = new ArrayList<>();

        //adapter = new TruckAdapter(this, name, price, photo, phone, id);
        adapter = new TruckAdapter(this, name, date, time, id);
        recycler.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(gridLayoutManager);

        displayData();
    }

    private void displayData() {
        //Intent intent = getIntent();
        //String userEmail = intent.getStringExtra("userEmail");
        int userId = db.getUserIdFromName("phoneNumber");

        Cursor cursor = db.getMYreservation(userId);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "You Do not have any reservation", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                try {
                    id.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESERVEDID)));
                    name.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERID)).concat(" "));
                    date.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
                    time.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)));
                    //photo.add(cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_FOODTRUCK_PHOTO)));

                } catch (Exception e) {
                    // Handle the exception
                }
            }
        }
    }
}