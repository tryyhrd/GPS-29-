package com.example.gps;

import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.GsonBuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class GetAddressByGPS extends AsyncTask<Void, Void, Void> {
    TextView textAddress;
    String coordinates;
    String token = "8023e6e-0390-45c-6e27-feed15c0e10";
    AddressResponse response = null;

    public GetAddressByGPS(String coordinates, TextView textAddress) {
        this.coordinates = coordinates;
        this.textAddress = textAddress;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Document document = Jsoup.connect("https://geocode-maps.yandex.ru/1.x/?apikey=" + token +
                            "&format=json&geocode=" + coordinates + "&results=1")
                    .ignoreContentType(true)
                    .get();

            GsonBuilder builder = new GsonBuilder();
            response = builder.create().fromJson(document.text(), AddressResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (response != null &&
                response.response != null &&
                response.response.GeoObjectCollection != null &&
                response.response.GeoObjectCollection.featureMember != null &&
                !response.response.GeoObjectCollection.featureMember.isEmpty()) {

            textAddress.setText(response.response.GeoObjectCollection.featureMember.get(0)
                    .GeoObject.metaDataProperty.GeocoderMetaData.text);
        } else {
            textAddress.setText("Адрес не найден");
        }
    }
}
