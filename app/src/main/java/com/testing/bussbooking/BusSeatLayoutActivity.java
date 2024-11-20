package com.testing.bussbooking;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BusSeatLayoutActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private Button selectedSeatButton = null; // To keep track of the selected seat

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_booking); // Set the layout containing the GridLayout for seats

        gridLayout = findViewById(R.id.seat_layout); // Assuming GridLayout in bus_seat_layout.xml

        int totalSeats = 20;

        for (int i = 1; i <= totalSeats; i++) {
            Button seatButton = new Button(this);
            seatButton.setText(String.valueOf(i));
            seatButton.setId(i); // Set unique ID
            seatButton.setBackgroundColor(Color.GRAY); // Default color for vacant seats

            // Set layout parameters
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            params.setMargins(8, 8, 8, 8);
            seatButton.setLayoutParams(params);

            // Add to GridLayout
            gridLayout.addView(seatButton);

            // Attach click listener
            seatButton.setOnClickListener(v -> {
                Button clickedButton = (Button) v;

                // Check if the seat is already booked (i.e., background color is RED)
                if (clickedButton.getBackground() instanceof ColorDrawable) {
                    ColorDrawable background = (ColorDrawable) clickedButton.getBackground();
                    if (background.getColor() == Color.RED) {
                        Toast.makeText(BusSeatLayoutActivity.this, "Seat already booked!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // Highlight the clicked seat
                if (selectedSeatButton != null) {
                    selectedSeatButton.setBackgroundColor(Color.GRAY); // Reset previously selected seat
                }
                clickedButton.setBackgroundColor(Color.BLUE); // Highlight selected seat
                selectedSeatButton = clickedButton;

                // Show confirmation dialog
                showConfirmationDialog(clickedButton.getText().toString());
            });
        }

        // Example of how you can mark booked seats (from an external data source)
        // For demo purposes, we'll hardcode the booked seats
        int[] bookedSeats = {2, 5, 8, 12}; // Example booked seats
        for (int seatId : bookedSeats) {
            Button seatButton = findViewById(seatId);
            if (seatButton != null) {
                seatButton.setBackgroundColor(Color.RED); // Mark as booked
                seatButton.setEnabled(false); // Disable click for booked seats
            }
        }
    }

    // Show confirmation dialog when a seat is selected
    private void showConfirmationDialog(String seatNumber) {
        Toast.makeText(this, "Confirm booking for seat: " + seatNumber, Toast.LENGTH_SHORT).show();
    }
}
