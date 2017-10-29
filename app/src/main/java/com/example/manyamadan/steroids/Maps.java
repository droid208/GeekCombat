package com.example.manyamadan.steroids;

/**
 * Created by manyamadan on 16/07/16.
 */

        import android.app.Activity;
        import android.location.Location;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.MapFragment;
        import com.google.android.gms.maps.MapView;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.Circle;
        import com.google.android.gms.maps.model.CircleOptions;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;

/*public class Maps extends Activity {
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map); mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

            }
        });

        Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
                .title("Hamburg"));
        Marker kiel = map.addMarker(new MarkerOptions()
                .position(KIEL)
                .title("Kiel")
                .snippet("Kiel is cool")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_launcher)));

        // Move the camera instantly to hamburg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }


}
*/

public class Maps extends Activity {
    static final LatLng TutorialsPoint = new LatLng(21 , 57);
    private GoogleMap googleMap;
    private Circle mCircle;
    private Marker mMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

     /*   try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//            Marker TP = googleMap.addMarker(new MarkerOptions().
//                    position(TutorialsPoint).title("TutorialsPoint"));

            double radiusInMeters = 100.0;
            //red outline
            int strokeColor = 0xffff0000;
            //opaque red fill
            int shadeColor = 0x44ff0000;


            CircleOptions circleOptions = new CircleOptions().center(TutorialsPoint).radius(radiusInMeters).fillColor(shadeColor).strokeColor(strokeColor).strokeWidth(2);
            mCircle = googleMap.addCircle(circleOptions);

            MarkerOptions markerOptions = new MarkerOptions().position(TutorialsPoint);
            mMarker = googleMap.addMarker(markerOptions);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        */

        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                if (mCircle == null || mMarker == null) {
                    drawMarkerWithCircle(latLng);
                } else {
                    updateMarkerWithCircle(latLng);
                }
            }
        });

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions()
                        .position(point)
                        .snippet(""));
                double radiusInMeters = 100000.0;
                int strokeColor = 0xffff0000; //red outline
                int shadeColor = 0x44ff0000; //opaque red fill

                CircleOptions circleOptions = new CircleOptions().center(point).radius(radiusInMeters).fillColor(shadeColor).strokeColor(strokeColor).strokeWidth(8);
                mCircle = googleMap.addCircle(circleOptions);

                MarkerOptions markerOptions = new MarkerOptions().position(point);
                mMarker = googleMap.addMarker(markerOptions);


            }


        });



    }


    private void updateMarkerWithCircle(LatLng position) {
        mCircle.setCenter(position);
        mMarker.setPosition(position);
    }

    private void drawMarkerWithCircle(LatLng position){
        double radiusInMeters = 100000.0;
        int strokeColor = 0xffff0000; //red outline
        int shadeColor = 0x44ff0000; //opaque red fill

        CircleOptions circleOptions = new CircleOptions().center(position).radius(radiusInMeters).fillColor(shadeColor).strokeColor(strokeColor).strokeWidth(8);
        mCircle = googleMap.addCircle(circleOptions);

        MarkerOptions markerOptions = new MarkerOptions().position(position);
        mMarker = googleMap.addMarker(markerOptions);
    }
}