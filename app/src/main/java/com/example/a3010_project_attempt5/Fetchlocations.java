package com.example.a3010_project_attempt5;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

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

public class Fetchlocations extends AsyncTask<Void, Void, Void> {

    String locations = "";
    String values = "";

    String Singlelocation = "";
    int locationLength;

    String singleCorrvalues = "";
    String finalCorrvalues = "";


    SpannableStringBuilder builder = new SpannableStringBuilder();

    @SuppressLint("WrongThread")
    @Override
    protected Void doInBackground(Void... voids) {

        try {

            URL url1 = new URL("https://api.myjson.com/bins/dv1gi");//locations
            HttpURLConnection httpURLConnection1 = (HttpURLConnection) url1.openConnection();
            httpURLConnection1.setRequestMethod("GET");
            httpURLConnection1.connect();
            InputStream in1 = new BufferedInputStream(httpURLConnection1.getInputStream());

            URL url2 = new URL("https://api.myjson.com/bins/ma89k");//values
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url2.openConnection();
            httpURLConnection2.setRequestMethod("GET");
            httpURLConnection2.connect();
            InputStream in2 = new BufferedInputStream(httpURLConnection2.getInputStream());





            locations = readStream(in1);
            JSONArray JA1 = new JSONArray(locations);

            values = readStream(in2);
            final JSONArray JA2 = new JSONArray(values);


            for (int i = 0; i < JA1.length(); i++){
                String linktext = "";
                String location_id = "";
                JSONObject JO1 = (JSONObject)JA1.get(i);
                linktext = JO1.getString("location");
                location_id = JO1.getString("id");

                Singlelocation = "Location    " + linktext + "\n\n";



                //Makes the location name string clickable.
                SpannableString hyper = new SpannableString(Singlelocation);


                final String finalLinktext = linktext;
                final String finalLocation_id = location_id;

                //Define a ClickableSpan, so that the "click" does something whenever we click it.
                ClickableSpan click = new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {

                           for(int z = 0; z < JA2.length(); z++) {

                               try {
                                   JSONObject JO2 = (JSONObject) JA2.get(z);
                                   if(finalLocation_id.equals(JO2.getString("location_id"))){
                                       singleCorrvalues =
                                                        "Location         " + finalLinktext + "\n" +
                                                       "Date                " + JO2.getString("tdate") + "\n" +
                                                       "Time               " + JO2.getString("ttime") + "\n" +
                                                       "PH                   " + JO2.getString("tph") + "\n" +
                                                       "Temperature  " + JO2.getString("ttemperature") + "\n" +
                                                       "Turbidity         " + JO2.getString("tturbidity") + "\n" +
                                                       "Depth              " + JO2.getString("tdepth") + "\n\n\n";

                                       finalCorrvalues = finalCorrvalues + singleCorrvalues;


                                   }
                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }
                           }




                        MainActivity.data.setText(finalCorrvalues);

                    }
                };

                locationLength = linktext.length();


                //Sets a connection between the clickable string and the ClickableSpan,
                //so that when the string is clicked, the does whatever typed in the onClick above.

                hyper.setSpan(click,12,12 + locationLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                builder.append(hyper);

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

        //This line sets the locations TextView in the Selectlocation page
        //to show the location table.
        MainActivity.data.setMovementMethod(LinkMovementMethod.getInstance());
        MainActivity.data.setClickable(true);
        MainActivity.data.setText(builder);


    }




    //Define the readStream function that reads the InputStream.
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
