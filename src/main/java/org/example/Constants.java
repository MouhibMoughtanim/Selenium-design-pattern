package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

public class Constants {

    public static String browserName = "chrome";
    public static JSONArray products = new JSONArray();
    public Constants(){
        JSONObject p1 = new JSONObject();
        JSONObject p2 = new JSONObject();
        JSONObject p3 = new JSONObject();
        p1.put("name","Brocolli");
        p1.put("number",5);
        p2.put("name","Beetroot");
        p2.put("number",9);
        p3.put("name","Cucumber");
        p3.put("number",6);
        this.products.put(p1);
        this.products.put(p2);
        this.products.put(p3);

    }
}
