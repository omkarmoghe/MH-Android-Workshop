package com.omkar.weatherapp;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Omkar Moghe on 1/30/2016.
 */
public class WeatherFragmentPageAdapter extends FragmentPagerAdapter {

    private ArrayList<String> cities;

    public WeatherFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.newInstance(cities.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return cities.get(position);
    }

    @Override
    public int getCount() {
        return cities.size();
    }
}
