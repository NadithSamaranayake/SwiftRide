package com.testing.bussbooking;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.testing.bussbooking.R;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class PassengerDashboard extends AppCompatActivity {

    private MapView mapView;
    private TableLayout busInfoTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize OSMDroid configuration
        Configuration.getInstance().setUserAgentValue("com.testing.bussbooking");

        // Set the content view
        setContentView(R.layout.passenger_dashboard);

        // Initialize the MapView
        mapView = findViewById(R.id.map);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        // Initialize the TableLayout
        busInfoTable = findViewById(R.id.bus_info_table);

        // Set fixed location coordinates (Example: a specific location)
        double latitude = 6.9094292188763555;
        double longitude = 79.8632807247173;

        // Create a GeoPoint for the fixed location
        GeoPoint fixedLocation = new GeoPoint(latitude, longitude);

        // Set the map center and zoom level
        mapView.getController().setCenter(fixedLocation);
        mapView.getController().setZoom(15.0);

        // Create a Marker for the fixed location
        Marker locationMarker = new Marker(mapView);
        locationMarker.setPosition(fixedLocation);
        locationMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        locationMarker.setIcon(getResources().getDrawable(R.drawable.ic_location));

        // Set a title and snippet for the marker
        locationMarker.setTitle("Fixed Location");
        locationMarker.setSnippet("This is a fixed location marker.");

        // Set a click listener on the marker to show bus information
        locationMarker.setOnMarkerClickListener((marker, mapView) -> {
            displayBusInfo();
            return true;
        });

        // Add the marker to the map
        mapView.getOverlays().add(locationMarker);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume(); // Resume map view (required for OSMDroid)
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause(); // Pause map view (required for OSMDroid)
    }

    /**
     * Displays the bus information in the table below the map when a marker is clicked.
     */
    private void displayBusInfo() {
        // Show the bus info table
        busInfoTable.setVisibility(View.VISIBLE);

        // Clear any existing rows except for the header
        int rowCount = busInfoTable.getChildCount();
        if (rowCount > 1) {
            busInfoTable.removeViews(1, rowCount - 1);
        }

        // Sample bus data for demonstration (you could retrieve real data from an API or database)
        String[][] busData = {
                {"Bus 101", "10:30 AM", "Downtown"},
                {"Bus 202", "11:00 AM", "Uptown"},
                {"Bus 303", "11:30 AM", "City Center"}
        };

        // Dynamically add rows for each bus
        for (String[] bus : busData) {
            TableRow row = new TableRow(this);

            for (String info : bus) {
                TextView cell = new TextView(this);
                cell.setText(info);
                cell.setPadding(8, 8, 8, 8);
                row.addView(cell);
            }
            busInfoTable.addView(row);
        }
    }

}
