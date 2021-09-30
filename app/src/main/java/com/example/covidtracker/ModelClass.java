package com.example.covidtracker;

import java.lang.reflect.Constructor;

class ModelClass {
    private String cases, deaths, todayDeaths, todayRecovered, active, country, total, todayTotal, todayCases, recovered;

    public ModelClass(String cases, String deaths, String todayDeaths, String todayRecovered, String active, String country, String total, String todayTotal, String todayCases, String recovered) {
        this.cases = cases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.todayRecovered = todayRecovered;
        this.active = active;
        this.country = country;
        this.total = total;
        this.todayTotal = todayTotal;
        this.todayCases = todayCases;
        this.recovered = recovered;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setTodayTotal(String todayTotal) {
        this.todayTotal = todayTotal;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getCases() {
        return cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public String getActive() {
        return active;
    }

    public String getCountry() {
        return country;
    }

    public String getTotal() {
        return total;
    }

    public String getTodayTotal() {
        return todayTotal;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getRecovered() {
        return recovered;
    }
}
