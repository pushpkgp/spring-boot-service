package com.dell.service.core;

import com.dell.service.dao.AppDAO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AppCore {
    private String inputFile;
    private String jsonString = "";
    private List<AppDAO> list = new ArrayList<>();
    private AppDAO.Builder appDAOBuilder;

    public AppCore(String inputFile, AppDAO.Builder appDAOBuilder) {
        this.inputFile = inputFile;
        this.appDAOBuilder = appDAOBuilder;
    }

    public AppCore bufferedReader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream(inputFile), StandardCharsets.UTF_8));

        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                jsonString += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public List<AppDAO> serviceBuilder() {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("input");
            for (int i = 0; i < jsonArray.length(); i++) {
                String service = jsonArray.getJSONObject(i).getString("services");
                if (service.equalsIgnoreCase("Hospital")) {
                    String facilityName = jsonArray.getJSONObject(i).getString("facility_name");
                    String location = jsonArray.getJSONObject(i).getString("location");
                    JSONObject obj1 = new JSONObject(location);
                    JSONArray jsonArray1 = obj1.getJSONArray("coordinates");
                    String coordinates = "";
                    for (int j = 0; j < jsonArray1.length(); j++) {
                        coordinates += jsonArray1.getString(j) + " ";
                    }

                    list.add(appDAOBuilder
                            .service(service)
                            .facilityName(facilityName)
                            .location(coordinates)
                            .build());
                }
            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }

        return list;
    }
}