package com.omkar.weatherapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Omkar Moghe on 1/30/2016.
 */
public class WeatherFragment extends Fragment {
    public static final String TAG = "WeatherFragment";

    private TextView temperature;
    private TextView city;

    private String cityToSearch = "San Francisco";

    public WeatherFragment() {
        super();
    }

    public static WeatherFragment newInstance(String city) {
        Bundle args = new Bundle();
        args.putString("city", city);

        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check if a city to search for was provided to this fragment
        Bundle args = getArguments();
        if (args.containsKey("city")) {
            cityToSearch = args.getString("city");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        temperature = (TextView) view.findViewById(R.id.temperature);
        city = (TextView) view.findViewById(R.id.city);
        city.setText(cityToSearch);

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, city.getText(), Snackbar.LENGTH_SHORT).show();
            }
        });

        // get the weather data
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + cityToSearch + "&appid=44db6a862fba0b067b1930da0d769e98";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject weather = new JSONObject(response.body().string());
                    double kelvin = weather.getJSONObject("main").getDouble("temp");
                    final double fahrenheit = kelvin * 9 / 5 - 459.67;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            temperature.setText(new DecimalFormat("#").format(fahrenheit).toString());
                        }
                    });
                } catch (JSONException j) {
                    Log.e(TAG, "damn you json", j);
                }
            }
        });

        return view;
    }
}
