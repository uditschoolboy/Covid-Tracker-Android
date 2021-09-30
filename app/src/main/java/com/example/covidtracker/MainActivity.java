package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CountryCodePicker countryCodePicker;
    TextView mTodayTotal, mTotal, mActive, mTodayActive, mRecovered, mTodayRecovered, mDeaths, mTodayDeaths;
    String country;
    TextView mfilter;
    Spinner spinner;
    String[] types = {"cases", "deaths", "recovered", "active"};
    private List<ModelClass> modelClassList;
    private List<ModelClass> modelClassList2;
    PieChart mPieChart;
    private RecyclerView recyclerView;
    com.example.covidtracker.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
        countryCodePicker = findViewById(R.id.ccp);
        mTodayActive = findViewById(R.id.todayactive);
        mActive = findViewById(R.id.active);
        mDeaths = findViewById(R.id.deaths);
        mTodayDeaths = findViewById(R.id.todaydeaths);
        mRecovered = findViewById(R.id.recovered);
        mTodayRecovered = findViewById(R.id.todayrecovered);
        mTotal = findViewById(R.id.totalcase);
        mTodayTotal = findViewById(R.id.todaytotal);
        mPieChart = findViewById(R.id.piechart);
        spinner = findViewById(R.id.spinner);
        mfilter = findViewById(R.id.filter);
        recyclerView = findViewById(R.id.recyclerView);
        modelClassList = new ArrayList<>();
        modelClassList2 = new ArrayList<>();

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        ApiUtilities.getAPIInterface().getCountryData().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                modelClassList2.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {

            }
        });
        adapter = new Adapter(getApplicationContext(), modelClassList2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        countryCodePicker.setAutoDetectedCountry(true);
        country=countryCodePicker.getSelectedCountryName();
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country = countryCodePicker.getSelectedCountryName();
                fetchdata();
            }
        });
        fetchdata();
    }
    private void fetchdata() {
        ApiUtilities.getAPIInterface().getCountryData().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                modelClassList.addAll(response.body());
                for(int i = 0; i < modelClassList.size(); i++) {
                    if(modelClassList.get(i).getCountry().equals(country)) {
                        mActive.setText((modelClassList.get(i).getActive()));
                        mTodayDeaths.setText((modelClassList.get(i).getTodayDeaths()));
                        mTodayRecovered.setText((modelClassList.get(i).getTodayRecovered()));
                        mTodayTotal.setText((modelClassList.get(i).getTodayTotal()));
                        mDeaths.setText((modelClassList.get(i).getDeaths()));
                        mRecovered.setText((modelClassList.get(i).getRecovered()));
                        mTotal.setText((modelClassList.get(i).getTotal()));

                        updateGraph(Integer.parseInt(modelClassList.get(i).getCases()),
                                Integer.parseInt(modelClassList.get(i).getActive()),
                                Integer.parseInt(modelClassList.get(i).getRecovered()),
                                Integer.parseInt(modelClassList.get(i).getDeaths()));
                    }
                }
            }

            private void updateGraph(int cases, int active, int recovered, int deaths) {
                mPieChart.clearChart();
                mPieChart.addPieSlice(new PieModel("Confirm", cases, Color.parseColor("#FFB701")));
                mPieChart.addPieSlice(new PieModel("Active", active, Color.parseColor("#FF4caf50")));
                mPieChart.addPieSlice(new PieModel("Recovered", recovered, Color.parseColor("#38accd")));
                mPieChart.addPieSlice(new PieModel("Deaths", deaths, Color.parseColor("#F55c47")));
                mPieChart.startAnimation();
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = types[i];
        mfilter.setText(item);
        adapter.filter(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}