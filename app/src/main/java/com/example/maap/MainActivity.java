package com.example.maap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.maap.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    EditText txtlatitud, txtlongitud;
    GoogleMap mMap;
    VideoView videoView;
    MediaController mediaController;
    private HashMap<String, Integer> videoMap;
    private VideoView currentVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        txtlongitud = findViewById(R.id.txt_longitud);
        txtlatitud = findViewById(R.id.txt_latitud);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        videoMap = new HashMap<>();
        videoMap.put("Santo Tomas Talca", R.raw.sedetalca);
        videoMap.put("Santo Tomas Temuco", R.raw.sedetemuco);
        videoMap.put("Santo Tomas Arica", R.raw.sedearica);
        videoMap.put("Santo Tomas Iquique", R.raw.sedeiquique);
        videoMap.put("Santo Tomas Antofagasta", R.raw.sedeantofagasta);
        videoMap.put("Santo Tomas La Serena", R.raw.sedeserena);
        videoMap.put("Santo Tomas Viña del mar", R.raw.sedevina);
        videoMap.put("Santo Tomas Santiago", R.raw.sedesantiago);
        videoMap.put("Santo Tomas Concepcion", R.raw.sedeconce);
        videoMap.put("Santo Tomas Los angeles", R.raw.sedeangeles);
        videoMap.put("Santo Tomas Valdvia", R.raw.sedevaldivia);
        videoMap.put("Santo Tomas Osorno", R.raw.sedeosorno);
        videoMap.put("Santo Tomas Puerto Montt", R.raw.sedepuerto);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);

        // Agrega tus marcadores
        addMarker(-35.42798673115764, -71.67313790230351, "Santo Tomas Talca");
        addMarker(-38.734672742952945, -72.60196170920258, "Santo Tomas Temuco");
        addMarker(-18.48301115898071, -70.31000646709215, "Santo Tomas Arica");
        addMarker(-20.218951716992773, -70.14073144439894, "Santo Tomas Iquique");
        addMarker(-23.63420056860634, -70.39409234615572, "Santo Tomas Antofagasta");
        addMarker(-29.905446023164764, -71.2579090828718, "Santo Tomas La Serena");
        addMarker(-33.037119044857036, -71.52219067802844, "Santo Tomas Viña del mar");
        addMarker(-33.49664180362305, -70.6165723116854, "Santo Tomas Santiago  ");
        addMarker(-36.82501576017694, -73.0617319505458, "Santo Tomas Concepcion");
        addMarker(-37.471789004493374, -72.35420246958519, "Santo Tomas Los angeles");
        addMarker(-39.81680437317448, -73.23315818042083, "Santo Tomas Valdivia");
        addMarker(-40.57099919161073, -73.1373428397276, "Santo Tomas Osorno");
        addMarker(-41.47202433629501, -72.92876244096891, "Santo Tomas Puerto Montt");

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-41.47202433629501, -72.92876244096891)));
    }




    private void addMarker(double lat, double lng, String title) {
        LatLng location = new LatLng(lat, lng);
        Marker marker = mMap.addMarker(new MarkerOptions().position(location).title(title));
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {

        String title = marker.getTitle();
        if (videoMap.containsKey(title)) {
            int videoResId = videoMap.get(title);
            playVideo(videoResId);
        }
        return false;

    }

    private void playVideo(int videoResId) {
        if (currentVideoView != null) {
            currentVideoView.stopPlayback();
        }

        currentVideoView = videoView;
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + videoResId));
        videoView.start();
    }


}