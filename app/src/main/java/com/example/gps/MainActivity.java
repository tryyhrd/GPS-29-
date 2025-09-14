package com.example.gps;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

public class MainActivity extends AppCompatActivity {

    LocationManager _locationManager;
    TextView textAddress;
    MapView mapView;
    LocationListener _locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            if (location != null) {
                mapView.getMap().move(
                        new CameraPosition(
                                new Point(location.getLatitude(), location.getLongitude()), 15, 0, 0
                        )
                );
                mapView.getMap().getMapObjects().clear();
                mapView.getMap().getMapObjects().addPlacemark(
                        new Point(location.getLatitude(), location.getLongitude()),
                        ImageProvider.fromResource(MainActivity.this, R.drawable.location)
                );

                GetAddressByGPS getAddressByGPS = new GetAddressByGPS(
                        String.valueOf(location.getLongitude() + ", " + location.getLatitude()),
                        textAddress
                );
                GetAddressByGPS.execute();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapKitFactory.setApiKey("6140d8d1-12ca-426b-b76e-65913c54854c");
        MapKitFactory.initialize(this);

        setContentView(R.layout.activity_main);

        mapView = findViewById(R.id.mapview);
        textAddress = findViewById(R.id.editext);
        _locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }
}