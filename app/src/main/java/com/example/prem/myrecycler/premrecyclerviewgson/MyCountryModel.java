package com.example.prem.myrecycler.premrecyclerviewgson;

import java.io.Serializable;

/**
 * Created by prem on 9/2/18.
 */

class MyCountryModel implements Serializable {
    private String country_id;
    private String country_name;

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

}
