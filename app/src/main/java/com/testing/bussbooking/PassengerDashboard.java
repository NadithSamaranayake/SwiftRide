package com.testing.bussbooking;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.testing.bussbooking.R;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class PassengerDashboard extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the OSMDroid configuration
        Configuration.getInstance().setUserAgentValue("com.testing.bussbooking");

        // Set the content view to the layout file with the MapView
        setContentView(R.layout.passenger_dashboard);

        // Initialize the MapView
        mapView = findViewById(R.id.map);
        mapView.setTileSource(TileSourceFactory.MAPNIK);  // Set tile source to MAPNIK
        mapView.setMultiTouchControls(true);  // Enable multi-touch controls (zoom, move)

        // Fixed location (Example: New York City)
        double latitude = 6.9094292188763555; // Latitude for New York City
        double longitude = 79.8632807247173; // Longitude for New York City

        // Create a GeoPoint for the fixed location
        GeoPoint fixedLocation = new GeoPoint(latitude, longitude);

        // Set the map center to the fixed location
        mapView.getController().setCenter(fixedLocation);

        // Set the zoom level (15.0 is a good zoom level to focus on the location)
        mapView.getController().setZoom(15.0);

        // Create a Marker for the fixed location
        Marker locationMarker = new Marker(mapView);
        locationMarker.setPosition(fixedLocation);  // Set the position of the marker
        locationMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);  // Set marker anchor
        locationMarker.setIcon(getResources().getDrawable(R.drawable.ic_location));  // Set custom location icon (make sure you have this icon in your drawable)

        // Optionally: Set a title or snippet for the marker
        locationMarker.setTitle("Fixed Location");
        locationMarker.setSnippet("This is a fixed location marker.");

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
}
