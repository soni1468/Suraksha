package hu.pe.yummykart.surakshauser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VariationActivity extends AppCompatActivity {
    String jsonData;
    List<RecyclerItemVariation> list;
    MyAdapterVar adapterVar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variation);
        jsonData = getIntent().getStringExtra("list");
        list = new ArrayList<>();
        //  Toast.makeText(getApplicationContext(),jsonData,Toast.LENGTH_LONG).show();

        try {
            JSONObject jsonObject;
            JSONArray jsonArray;
            String sCrime, s2014, s2015, s2015var, sArrest;
            int count = 0;
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("server_response");
            while(count < jsonArray.length())
            {
                JSONObject jo = jsonArray.getJSONObject(count);
                sCrime = jo.getString("Crime_Head");
                s2014 = jo.getString("Cases_2014");
                s2015 = jo.getString("Cases_2015");
                s2015var = jo.getString("Var_2015");
                sArrest = jo.getString("Arrest");

                //  Toast.makeText(getApplicationContext(),sArrest,Toast.LENGTH_LONG).show();
                list.add(new RecyclerItemVariation(sCrime,s2014,s2015,s2015var,sArrest));
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapterVar = new MyAdapterVar(list,this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewvariation);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapterVar);
    }
}
