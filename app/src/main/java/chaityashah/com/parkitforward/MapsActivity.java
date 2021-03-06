package chaityashah.com.parkitforward;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);
        mMap.addMarker(new MarkerOptions().position(new LatLng(33.778155, -84.396385)).title("2009 Red Cadillac J8R3HS").snippet("30 Minutes Remaining"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(33.777406, -84.399426)).title("2010 Blue Chevy ABC123").snippet("10 Minutes Remaining"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(33.776464, -84.395052)).title("2015 Red Buick 2AAF1S").snippet("6 Minutes Remaining"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(33.776102, -84.396710)).title("2009 Grey GMC 412A2D").snippet("12 Minutes Remaining"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(33.776078, -84.397807)).title("2002 Blue Cadillac 291JJ2").snippet("27 Minutes Remaining"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(33.775744, -84.398838)).title("1996 Red Chevy 52BSS1").snippet("5 Minutes Remaining"));



        //set map type
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            mMap.setMyLocationEnabled(true);
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            if(mMap != null){
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
            }
            Circle circle = mMap.addCircle(new CircleOptions()
                    .center(new LatLng(location.getLatitude(), location.getLongitude()))
                    .radius(500)
                    .strokeColor(Color.LTGRAY)
                    .fillColor(Color.TRANSPARENT));
        }
    };
}
