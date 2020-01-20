package com.yoekisoft.myapplication.nepro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yoekisoft.myapplication.Api;
import com.yoekisoft.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNepro extends AppCompatActivity {
    Spinner siteSpinner;

    ListView siteList, reasonListid, wareHouseListid;
    ImageButton search;
    ScrollView scrollblock;
    String sticker;
    TextView stickerid, WareHouseName, itemId,textWare;
    EditText stickerText;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_nepro);
        initialize();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
    myRef = database.getReference("NEPRO");
        sticker = stickerText.getText().toString();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StickerData();
            }
        });
    }

    private Api RetroFitBlock() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);
        return api;
    }

    private void StickerData() {
        Api api = RetroFitBlock();

        StickerNo insertUser = new StickerNo(stickerText.getText().toString(), "10008");
        Call<ModelRetrofit> call = api.getStickerData(insertUser);
        call.enqueue(new Callback<ModelRetrofit>() {
            @Override
            public void onResponse(Call<ModelRetrofit> call, Response<ModelRetrofit> response) {
                scrollblock.setVisibility(View.VISIBLE);
                Toast.makeText(RetrofitNepro.this, "" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                Toast.makeText(RetrofitNepro.this, "" + response.body().getStickerID(), Toast.LENGTH_SHORT).show();
                String stickerid1 = response.body().getStickerID();
                Toast.makeText(RetrofitNepro.this, "" + response.body().getItemId(), Toast.LENGTH_SHORT).show();
                String itemId1 = response.body().getItemId();

//jhgv
                List<SiteListDatum> herolist = response.body().getSiteListData();

                String[] sitelist = new String[herolist.size()];
                for (int i = 0; i < herolist.size(); i++) {
                    sitelist[i] = herolist.get(i).getId() + "_" + herolist.get(i).getName();

                }
                stickerid.setText(stickerid1);
                itemId.setText(itemId1);
                WareHouseName.setText(response.body().getWareHouseName());
                siteList.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, sitelist));


                List nameList = new ArrayList<String>(Arrays.asList(sitelist));

                myRef.child("SiteList").setValue(nameList);



                siteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item = (String) parent.getItemAtPosition(position);
                        Log.d("Item", item);
                        String[] siteId = item.split("_");

                        wareHouseItem(siteId);

                        ////////////////
                    }


                });


                //Reason List
                List<ReasonList> reasonList = response.body().getReasonList();

                String[] reasonlist = new String[reasonList.size()];
                for (int i = 0; i < reasonList.size(); i++) {
                    reasonlist[i] = reasonList.get(i).getId() + "_" + reasonList.get(i).getName();

                }
                reasonListid.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, reasonlist));

            }


            @Override
            public void onFailure(Call<ModelRetrofit> call, Throwable t) {
                Toast.makeText(RetrofitNepro.this, "Failed", Toast.LENGTH_SHORT).show();
            }


        });


    }


    private void wareHouseItem(final String[] siteId) {
        StickerNo insertUser = new StickerNo(siteId[0]);
        Api api = RetroFitBlock();
        Call<WareSite> call = api.getWareHouse(insertUser);
        call.enqueue(new Callback<WareSite>() {
            @Override
            public void onResponse(Call<WareSite> call, Response<WareSite> response) {
                Log.d("response", response.body().getMessage());
                wareHouseListid.setVisibility(View.VISIBLE);
                textWare.setVisibility(View.VISIBLE);
                List<SiteListDatum> wareIdName=response.body().getWareHouseListData();
                String[] reasonlist = new String[wareIdName.size()];
                for (int i = 0; i < wareIdName.size(); i++) {
                    reasonlist[i] =wareIdName.get(i).getId() + "_" + wareIdName.get(i).getName();

                }
                wareHouseListid.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, reasonlist));

                List nameList = new ArrayList<String>(Arrays.asList(reasonlist));

                myRef.child("SiteList").child(siteId[0]).setValue(nameList);
            }

            @Override
            public void onFailure(Call<WareSite> call, Throwable t) {
                Log.d("failure", t.getMessage());
            }
        });

    }


    private void initialize() {
        stickerid = findViewById(R.id.stickerid);
        itemId = findViewById(R.id.itemId);
        textWare=findViewById(R.id.textWare);
        WareHouseName = findViewById(R.id.WareHouseName);
        search = findViewById(R.id.search);
        reasonListid = findViewById(R.id.reasonListid);
        siteList = findViewById(R.id.siteList);
        wareHouseListid = findViewById(R.id.wareHouseListid);
        scrollblock = findViewById(R.id.scrollblock);
        stickerText = findViewById(R.id.stickerText);


    }

}

