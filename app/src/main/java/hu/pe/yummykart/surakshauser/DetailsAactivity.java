package hu.pe.yummykart.surakshauser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailsAactivity extends AppCompatActivity {

    List<RecyclerItem> list;
    MyAdapter adapter;
    RecyclerView recyclerView;
    String statename,jsonData;
    TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_aactivity);


        list = new ArrayList<>();
        statename = getIntent().getStringExtra("statename");
        state = (TextView) findViewById(R.id.text);
        state.setText(statename+" CRIME RATE");

        jsonData = getIntent().getStringExtra("list");

        try {
            JSONObject jsonObject;
            JSONArray jsonArray;
            String sState, sCrime, s2010, s2011, s2012;
            int count = 0;
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("server_response");
            while(count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                sState = jo.getString("State");
                sCrime = jo.getString("Crime_Head");
                s2010 = jo.getString("Cases_2010");
                s2011 = jo.getString("Cases_2011");
                s2012 = jo.getString("Cases_2012");

                list.add(new RecyclerItem(sState,sCrime,s2010,s2011,s2012));
                count++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapter = new MyAdapter(list, this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

    }
}
