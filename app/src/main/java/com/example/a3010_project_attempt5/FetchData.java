package com.example.a3010_project_attempt5;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FetchData extends AsyncTask <Void,Void,Void>{

    String data = "";
    String dataParsed = "";
    String singleParsed = "";

    String locations = "";

    String location_id ;
    String location_name = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {


            //******************************************************
            /*URL url = new URL("https://api.myjson.com/bins/ma89k");//values
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//connect to the server

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());



            URL url2 = new URL("https://api.myjson.com/bins/dv1gi");
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url2.openConnection();

            httpURLConnection2.setRequestMethod("GET");
            httpURLConnection2.connect();

            InputStream in2 = new BufferedInputStream(httpURLConnection2.getInputStream());


            data = readStream(in);
            locations = readStream(in2);


            JSONArray JA = new JSONArray(data);
            JSONArray JA2 = new JSONArray(locations);*/

            //*********************************************************

            URL url1 = new URL("http://172.0.0.1:5000");//values
            URLConnection URLConnection1 =  url1.openConnection();
            InputStream in1 = new BufferedInputStream(URLConnection1.getInputStream()) ;
            //BufferedReader reader1 = new BufferedReader(new InputStreamReader(in1));

            URL url2 = new URL("http://172.0.0.1:5000/locations");//values
            URLConnection URLConnection2 =  url2.openConnection();
            InputStream in2 = new BufferedInputStream(URLConnection2.getInputStream()) ;

            data = readStream(in1);
            locations = readStream(in2);

            JSONArray JA = new JSONArray(data);
            JSONArray JA2 = new JSONArray(locations);

            //**********************************************************


            for(int i = 0; i < JA.length(); i++ ){
                JSONObject JO = (JSONObject)JA.get(i);

                location_id = JO.getString("location_id");

                for(int z = 0; z < JA2.length(); z++) {
                    JSONObject JO2 = (JSONObject) JA2.get(z);
                    location_name = JO2.getString("location");

                   if (location_id.equals(JO2.getString("id"))) {
                       location_name = JO2.getString("location");
                       singleParsed =
                                        "Location         " + location_name + "\n" +
                                       "Date                " + JO.getString("tdate") + "\n" +
                                       "Time               " + JO.getString("ttime") + "\n" +
                                       "PH                   " + JO.getString("tph") + "\n" +
                                       "Temperature  " + JO.getString("ttemperature") + "\n" +
                                       "Turbidity         " + JO.getString("tturbidity") + "\n" +
                                       "Depth              " + JO.getString("tdepth") + "\n\n\n";

                       dataParsed = dataParsed + singleParsed;


                    }

                }



            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Viewallvalues.allvalues.setText(this.dataParsed); //first values is from MainAcitivity TextView variable, the second Values from this class string variable.
    }


    protected String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}
