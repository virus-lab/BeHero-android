package network.virus.behero_android;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by PJunhyukMainDT on 2018-03-04.
 */

public class Task extends AsyncTask<String, Void, String> {
    private String str, receiveMsg;
    String esntlId = "";
    String authKey = "";

    //    public Integer sex_value = 0;
//
//    public void putSex(Integer i) {
//        sex_value = i;
//    }
//
    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            String url_string = null;

//            if (sex_value != 0) {
//                url_string = "http://www.safe182.go.kr/api/lcm/findChildList.do?esntlId=" + esntlId + "&authKey=" + authKey + "&rowSize=10&sexdstnDscd=" + sex_value;
//            } else if (sex_value == 0) {
            url_string = "http://www.safe182.go.kr/api/lcm/findChildList.do?esntlId=" + esntlId + "&authKey=" + authKey + "&rowSize=10";
//            }

            url = new URL(url_string);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.i("receiveMsg : ", receiveMsg);

                reader.close();
            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }

    public String[][] listJSONParser(String jsonString, Integer sex_value) {
        String occrde = null;
        String alldressingDscd = null;
        String ageNow = null;
        String age = null;
        String writngTrgetDscd = null;
        String sexdstnDscd = null;
        String occrAdres = null;
        String nm = null;
        String tknphotolength = null;

        String[][] list = new String[100][9];

        try {
            JSONArray jarray = new JSONObject(jsonString).getJSONArray("list");

            for (int i = 0; i < jarray.length(); i++) {
                HashMap<String,String> map = new HashMap<>();
                JSONObject jObject = jarray.getJSONObject(i);

                occrde = jObject.optString("occrde");
                alldressingDscd = jObject.optString("alldressingDscd");
                ageNow = jObject.optString("ageNow");
                age = jObject.optString("age");
                writngTrgetDscd = jObject.optString("writngTrgetDscd");
                sexdstnDscd = jObject.optString("sexdstnDscd");
                occrAdres = jObject.optString("occrAdres");
                nm = jObject.optString("nm");
                tknphotolength = jObject.optString("tknphotolength");

//                if (sexdstnDscd == sex_value) {
//
//                }

                list[i][0] = occrde;
                list[i][1] = alldressingDscd;
                list[i][2] = ageNow;
                list[i][3] = age;
                list[i][4] = writngTrgetDscd;
                list[i][5] = sexdstnDscd;
                list[i][6] = occrAdres;
                list[i][7] = nm;
                list[i][8] = tknphotolength;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}