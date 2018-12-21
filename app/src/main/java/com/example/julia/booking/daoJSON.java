package com.example.julia.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class daoJSON {
    public static final String requestURL = "http://685debfc.ngrok.io/query";

    public static String reciveJSONforQuery(String query) throws IOException {
       String url = "http://localhost:3000/query";
        //String url = "http://192.168.1.159:3000/query";
        URL obj = new URL(requestURL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("POST");


        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        con.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());

        //SEND TO SERVER!!!
        wr.writeBytes("query=" + URLEncoder.encode(query, "UTF-8"));

        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public static List<Object> JSONtoList(String JSONString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<Object> someClassList = objectMapper.readValue(JSONString, typeFactory.constructCollectionType(List.class, Object.class));
        return someClassList;
    }

}

//    public static void main(String[] args) {
//        try {
//            String toParse = reciveJSONforQuery("SELECT * FROM Games");
//            List<Object> res = JSONtoList(toParse);
//            System.out.println(res);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
