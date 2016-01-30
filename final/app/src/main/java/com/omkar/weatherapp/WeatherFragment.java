package com.omkar.weatherapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Omkar Moghe on 1/30/2016.
 */
public class WeatherFragment extends Fragment {

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

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, city.getText(), Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
