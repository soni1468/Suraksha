package hu.pe.yummykart.surakshauser;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import fr.ganfra.materialspinner.MaterialSpinner;

public class DataAnalysisActivity extends AppCompatActivity {

    MaterialSpinner spinner;
    String stateName;
    String JSON_STRING = null;
    String state[] = {"ANDHRA PRADESH", "ARUNACHAL PRADESH", "ASSAM", "BIHAR", "CHHATTISGARH", "GOA", "GUJARAT", "HARYANA", "HIMACHAL PRADESH", "JAMMU & KASHMIR", "JHARKHAND", "KARNATAKA"
            , "KERALA", "MADHYA PRADESH", "MAHARASHTRA", "MANIPUR", "MEGHALAYA", "MIZORAM", "NAGALAND", "ODISHA", "PUNJAB", "RAJASTHAN"
            , "SIKKIM", "TAMIL NADU", "TRIPURA", "UTTAR PRADESH", "UTTARAKHAND", "WEST BENGAL", "A & N ISLANDS", "CHANDIGARH", "D & N HAVELI"
            , "DAMAN & DIU", "DELHI", "LAKSHADWEEP", "PUDUCHERRY"};
    ArrayAdapter<String> adapter;
    private List<RecyclerItem> itemlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);

        itemlist = new ArrayList<>();
        spinner = (MaterialSpinner) findViewById(R.id.spinnerState);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, state);

        spinner.setAdapter(adapter);
        spinner.setPaddingSafe(0, 0, 0, 0);
    }

    public void submit(View v) {
        stateName = spinner.getSelectedItem().toString();
        if (stateName.equals("State/UT")) {
            Toast.makeText(getApplicationContext(), "Select a State/UT", Toast.LENGTH_SHORT).show();
        } else {
            new DoInBackgroundState().execute(stateName);
        }
    }

    public void perVariation(View v){
        new DoInBackgroundVariation().execute();
    }

    public class DoInBackgroundState extends AsyncTask<String, Void, String> {

        SpotsDialog alertDialog;
        Context context;

        @Override
        protected void onPreExecute() {
            alertDialog = new SpotsDialog(DataAnalysisActivity.this, "Fetching Data...");
            alertDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String stateName = params[0];
            InputStream inputStream = null;
            try {
                String json_url = "http://yummykart.pe.hu/getcrimedetails.php";
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("State", "UTF-8") + "=" + URLEncoder.encode(stateName, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                StringBuilder stringBuilder = new StringBuilder();
                if (bufferedReader != null) {
                    while ((JSON_STRING = bufferedReader.readLine()) != null) {
                        stringBuilder.append(JSON_STRING + "\n");
                    }
                } else
                    return null;

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            alertDialog.dismiss();
            if (result != null) {
                if (result.equals("unsuccessful")) {
                    Toast.makeText(getApplicationContext(), "error fetching data! please try again.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent j = new Intent(getApplicationContext(), DetailsAactivity.class);
                    j.putExtra("statename", stateName);
                    j.putExtra("list", result);
                    startActivity(j);
                }

            } else {
                Toast.makeText(getApplicationContext(), "check your internet connection !", Toast.LENGTH_LONG).show();
            }

        }
    }

    public class DoInBackgroundVariation extends AsyncTask<String, Void, String> {

        SpotsDialog alertDialog;
        //  Context context;

        @Override
        protected void onPreExecute() {
            alertDialog = new SpotsDialog(DataAnalysisActivity.this, "Fetching Data...");
            alertDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            // String stateName = params[0];
            InputStream inputStream = null;
            try {
                String json_url = "http://yummykart.pe.hu/variationincrime.php";
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");

                inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                StringBuilder stringBuilder = new StringBuilder();
                if (bufferedReader != null) {
                    while ((JSON_STRING = bufferedReader.readLine()) != null) {
                        stringBuilder.append(JSON_STRING + "\n");
                    }
                } else
                    return null;

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            alertDialog.dismiss();
            if (result != null) {
                if (result.equals("unsuccessful")) {
                    Toast.makeText(getApplicationContext(), "error fetching data! please try again.", Toast.LENGTH_SHORT).show();
                } else {
                    //  Toast.makeText(getApplicationContext(), "check", Toast.LENGTH_LONG).show();
                    Intent l = new Intent(DataAnalysisActivity.this, VariationActivity.class);
                    l.putExtra("list", result);
                    startActivity(l);
                }

            } else {
                Toast.makeText(getApplicationContext(), "check your internet connection !", Toast.LENGTH_LONG).show();
            }

        }
    }
}
