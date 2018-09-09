package com.davidgarrido.myweather.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.davidgarrido.myweather.API.API;
import com.davidgarrido.myweather.API.Services.WeatherServiceLoc;
import com.davidgarrido.myweather.API.Services.WeatherServiceName;
import com.davidgarrido.myweather.Models.City;
import com.davidgarrido.myweather.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    LocationManager locationManager;
    static final int REQUEST_CODE = 1;
    public static double lat;
    public static double lon;

    @BindView(R.id.textViewCity)
    TextView textViewCity;
    @BindView(R.id.textViewTemp)
    TextView textViewTemp;
    @BindView(R.id.textViewMax)
    TextView textViewMax;
    @BindView(R.id.textViewMin)
    TextView textViewMin;
    @BindView(R.id.textViewWeather)
    TextView textViewDescription;
    @BindView(R.id.textViewHumidity)
    TextView textViewHumidity;
    @BindView(R.id.textViewWind)
    TextView textViewWind;
    @BindView(R.id.textViewSunrise)
    TextView textViewSunrise;
    @BindView(R.id.textViewSunset)
    TextView textViewSunset;
    @BindView(R.id.imageViewWeather)
    ImageView imageViewIcon;
    @BindView(R.id.imageButtonLoc)
    ImageButton imageButtonLoc;

    public static int code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        if (code == 1) {
            cityLoc();
        } else if (code == 2) {
            cityName();
        }
        textViewCity.setOnClickListener(this);

        imageButtonLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityLoc();
            }
        });


    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                lat = location.getLatitude();
                lon = location.getLongitude();
            } else {
                Toast.makeText(this, "Unable to find correct location", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                getLocation();
                break;
        }
    }

    private void setData(City city) {
        if(city == null){
            Toast.makeText(MainActivity.this, "No se ha podido encontrar la ciudad", Toast.LENGTH_LONG).show();
            cityLoc();
        }else{
            textViewCity.setText(city.getName() + ", " + city.getCountry());
            DecimalFormat df = new DecimalFormat("#.0");
            textViewTemp.setText(df.format(city.getTemperature()) + "ºC");
            textViewMax.setText(city.getTempMax() + "ºC");
            textViewMin.setText(city.getTempMin() + "ºC");
            String description = city.getDescription();
            textViewDescription.setText(description.substring(0, 1).toUpperCase() + description.substring(1));
            textViewHumidity.setText(city.getHumidity() + "%");
            textViewWind.setText(city.getWindSpeed() + " m/s  " + API.convertDegToDir(city.getWindDir()));
            textViewSunrise.setText(API.convertTime(city.getSunrise()));
            textViewSunset.setText(API.convertTime(city.getSunset()));
            Picasso.get().load(API.BASE_ICONS + city.getIcon() + API.EXTENSION_ICONS).fit().into(imageViewIcon);
        }
    }
    private void cityLoc(){
        code = 1;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLocation();

        WeatherServiceLoc service = API.getApi().create(WeatherServiceLoc.class);
        Call<City> cityCall = service.getCity(lat, lon, API.APPKEY, "metric", "es");

        cityCall.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                City city = response.body();
                setData(city);
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void cityName(){
        Bundle bundle = getIntent().getExtras();
        WeatherServiceName serviceName = API.getApi().create(WeatherServiceName.class);
        Call<City> cityCall1 = serviceName.getCity(bundle.getString("cityName"), API.APPKEY, "metric", "es");

        cityCall1.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                City city2 = response.body();
                setData(city2);
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                cityLoc();
            }
        });
    }
}
