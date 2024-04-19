package com.example.mutrasf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class TruckProfileActivity extends AppCompatActivity {
    private Button availableDateTimeButton;
    private Button reserveButton;

    private String selectedTime;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truckprofile);

        availableDateTimeButton = findViewById(R.id.availableDateTimeButton);
        reserveButton = findViewById(R.id.reservebtn);

        availableDateTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TruckProfileActivity.this, AvailableTimeDateActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createReservation();
            }
        });
    }

    private void createReservation() {

        String reservationId = generateReservationId();
        String selectedTime = getSelectedTime();
        String selectedDate = getSelectedDate();
        String truckId = getTruckId();
        String userId = getUserId();

        DBHelper dbHelper = new DBHelper(this);
        boolean isReservationCreated = dbHelper.createReservation(reservationId, selectedTime, selectedDate, truckId, userId);
        if (isReservationCreated) {

            Toast.makeText(this, "Reservation created successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {

            Toast.makeText(this, "Failed to create reservation", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            selectedDate = data.getStringExtra("selectedDate");
            selectedTime = data.getStringExtra("selectedTime");


        }
    }

    private String getSelectedTime() {
        return selectedTime;
    }

    private String getSelectedDate() {
        return selectedDate;
    }


    private String generateReservationId() {
        long timestamp = System.currentTimeMillis();
        int random = new Random().nextInt(10000);
        return "RES" + timestamp + random;
    }


    private String getTruckId() {
        return "TRUCK123"; //don't forget
    }

    private String getUserId() {
        return "USER456"; //don't forget
    }
}