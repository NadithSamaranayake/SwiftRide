package com.testing.bussbooking;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        // Find the button and EditText in the layout
        Button dobButton = findViewById(R.id.btn_dob);  // Button to trigger the DatePickerDialog
        final EditText dobEditText = findViewById(R.id.et_dob);  // EditText to display the selected date

        // Set an OnClickListener for the Date of Birth button
        dobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Create the DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,  // Context for the dialog
                        (view, selectedYear, selectedMonth, selectedDay) -> {
                            // Format the selected date and display it in the EditText
                            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            dobEditText.setText(selectedDate);  // Set the selected date to the EditText
                        },
                        year, month, day  // Set the initial date to the current date
                );

                // Show the DatePickerDialog
                datePickerDialog.show();
            }
        });
    }
}
