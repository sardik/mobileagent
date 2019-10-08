package com.amtesting.mobileagentatex;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amtesting.mobileagentatex.Helper.DataParser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class beranda extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, GoogleMap.OnInfoWindowClickListener {

    private static final String TAG = beranda.class.getSimpleName();
    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    ArrayList<LatLng> MarkerPoints;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    View myView;
    TextView tv_dirextion;
    // progress dialog
    ProgressDialog pdialog;
    // get latitude and longitude current location
    public String getLatitude , getLongitude, origin, destination ;
    public String datadroppoint, distance, duration;
    PolylineOptions lineOptions;
    Polyline polyline;

    // String parse object from droppoint API
    public String latitude, longitude, nama, telpon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_beranda, container, false);
        /*((MainActivity) getActivity()).getSupportActionBar().setSubtitle("(Login Agent)");*/
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Beranda");
        ((MainActivity) getActivity()).getSupportActionBar().setLogo(R.mipmap.ic_atex);
        ((FragmentToActivity) getActivity()).SetNavState(R.id.nav_beranda);
        ((BottomNavToActivity) getActivity()).SetBtmNavState(R.id.bottom_pickup);
        tv_dirextion = (TextView) view.findViewById(R.id.tv_direction);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initializing
        MarkerPoints = new ArrayList<>();

        SupportMapFragment fragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

        ConnectivityManager conMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {
            // notify user you are online

        }
        else if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {
            // notify user you are not online
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Tidak Ada Koneksi");
            builder.setMessage("Hidupkan Koneksi Internet ?");

            String positiveText = getString(android.R.string.ok);
            builder.setPositiveButton(positiveText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // positive button logic
                            getActivity().finishAffinity();
                        }
                    });
//            String negativeText = getString(android.R.string.cancel);
//            builder.setNegativeButton(negativeText,
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // negative button logic
//                            getActivity().finishAffinity();
//                        }
//                    });

            AlertDialog dialog = builder.create();
            // display dialog
            dialog.show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        getContext(), R.raw.style_json));
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
        // Setting onclick event listener for the map
            mMap.setOnInfoWindowClickListener(this);

        // execute maps async
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // execute login async
                new getdroppointAsync().execute();
                Log.d("get Lat Lang", getLatitude + getLongitude);
            }
        }, 500);
    }


    public void onInfoWindowClick(Marker marker) {
        if(lineOptions != null) {
            polyline.remove();
        }
        String id = marker.getId();
        id = id.substring(1);
        Log.d("Debug", "ID Marker= " + id);

        LatLng position = marker.getPosition();

        origin = getLatitude + "," + getLongitude;
        destination = position.latitude + "," + position.longitude;

        // Getting URL to the Google Directions API
        String url = getUrl(origin, destination);
        Log.i("Info", "origin= " + origin);
        Log.i("Info", "destination= " + destination);
        Log.d("onMarkerClick", url.toString());
        FetchUrl FetchUrl = new FetchUrl();

        // Start downloading json data from Google Directions API
        FetchUrl.execute(url);
    }

    private String getUrl(String origin, String destination) {

        // Origin of route
        String str_origin = "origin=" + origin;

        // Destination of route
        String str_dest = "destination=" + destination;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class FetchUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("Background Task data", data.toString());
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("ParserTask",jsonData[0].toString());
                DataParser parser = new DataParser();
                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("ParserTask","Executing routes");
                Log.d("ParserTask",routes.toString());

            } catch (Exception e) {
                Log.d("ParserTask",e.toString());
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            lineOptions = null;
            distance = "";
            duration = "";
            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    if(j==0){	// Get distance from the list
                        distance = (String) point.get("distance");
                        continue;
                    }else if(j==1){ // Get duration from the list
                        duration = (String) point.get("duration");
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                    // Adding one the points in the route to LineOptions
                    lineOptions.add(position);
                    // Adding All the points in the route to LineOptions
//                    lineOptions.addAll(points);
                    lineOptions.width(5);
                    lineOptions.color(Color.YELLOW);
                    tv_dirextion.setText("Distance:"+distance + ", Duration:"+duration);
                    Log.d("onPostExecute","onPostExecute lineoptions decoded");
                }
            }
            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                polyline = mMap.addPolyline(lineOptions);
            }
            else {
                mMap.addPolyline(lineOptions);
                Log.d("onPostExecute","without Polylines drawn");
            }
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
//        Geocoder geocoder;
//        List<Address> addresses;
//        geocoder = new Geocoder(getActivity(), Locale.getDefault());
//
//        addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//
//        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//        String city = addresses.get(0).getLocality();
//        String state = addresses.get(0).getAdminArea();
//        String country = addresses.get(0).getCountryName();
//        String postalCode = addresses.get(0).getPostalCode();
//        String knownName = addresses.get(0).getFeatureName();
//        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        addresses = geocoder.getFromLocation(latLng, 1);
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Current Position");
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_atex_map_agent));
//        mCurrLocationMarker = mMap.addMarker(markerOptions);
        String title = "Lokasi Anda";
//        String data = "kordinat : " + latLng;
//        data += ",Longitude : " + location.getLongitude();
        mCurrLocationMarker = mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(title)
                            .snippet("kordinat : " + latLng)
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_atex_map_agent)));


//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(new LatLng(location.getLatitude(), location.getLongitude()))
//                .zoom(17)
//                .bearing(90)
//                .tilt(45)
//                .build();
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //move map camera
        mMap.animateCamera(CameraUpdateFactory
                .newLatLngZoom(latLng, 15.0f));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17.0f));
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(getActivity())
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getContext(),
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getActivity(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private class getdroppointAsync extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String Urldroppoint = "http://192.168.6.9/CRUDAPISLIM/dropstore2/";
             getLatitude = String.valueOf(mLastLocation.getLatitude()).toString();
             getLongitude = String.valueOf(mLastLocation.getLongitude()).toString();
//            if (getLatitude == null) {
//                getLatitude = String.valueOf(mLastLocation.getLatitude()).toString();
//            }
            try {
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(Urldroppoint+getLatitude+","+getLongitude);
                HttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                datadroppoint = EntityUtils.toString(entity);
                Log.d("Urldroppoint", Urldroppoint);
                Log.d("Debug", datadroppoint);
                Log.d("get Lat", getLatitude);
                Log.d("get Long", getLongitude);

            }
            catch (IOException e) {
                Log.d("HTTPCLIENT", e.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            try {
                JSONObject object = new JSONObject(datadroppoint);
                JSONArray Jarray  = object.getJSONArray("data");

                for (int i = 0; i < Jarray.length(); i++) {
                    JSONObject obj = Jarray.getJSONObject(i);
                    latitude = obj.getString("latitude");
                    longitude = obj.getString("longitude");
                    nama = obj.getString("nama");
                    // telpon = obj.getString("phone_pengirim");
                    Log.d("Debug", "LatLang= " + latitude + " - " + longitude + " Title= " + nama + " No.Tlp= " );
                    String title = "Alamat : " + nama;
                    String data = "Latitude : " + latitude;
                    data += ",Longitude : " + longitude;
                    mMap.addMarker(new MarkerOptions()
                            .position( new LatLng(Double.valueOf(latitude), Double.valueOf(longitude)))
                            .title(title)
                            .snippet(data)
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_atex_map_droppoint)));
                    Log.i("locations", Double.valueOf(latitude).toString());

                    mMap.setInfoWindowAdapter(infoWindowAdapter);
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    GoogleMap.InfoWindowAdapter infoWindowAdapter = new GoogleMap.InfoWindowAdapter() {
        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.info_windows_droppoint, null);
            final String[] hasil = marker.getSnippet().split(",");
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title_marker);
            TextView tv_latitude = (TextView) view.findViewById(R.id.tv_data_latitude);
            TextView tv_longitude = (TextView) view.findViewById(R.id.tv_data_longitude);
            Button btn_goto = (Button) view.findViewById(R.id.btn_goto);

                tv_title.setText(marker.getTitle());
                tv_latitude.setText(hasil[0]);
                tv_longitude.setText(hasil[1]);

            return view;
        }
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    public interface FragmentToActivity {
        public void SetNavState(int id);
    }
    public interface BottomNavToActivity {
        public void SetBtmNavState(int id);
    }
}
