package com.yoekisoft.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yoekisoft.myapplication.R.*;

public class Main2Activity extends AppCompatActivity {
    ListView listViewHero;
    Button button;
    EditText editText;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main2);
        editText=findViewById(id.word);
        spinner=findViewById(id.spinner);
        listViewHero = findViewById(id.listHero);
        button=findViewById(id.buttonhero);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHeroes();
            }
        });


    }

    private void getHeroes() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Hero>> call = api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                List<Hero> heroList = response.body();
                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getName();
                }

                listViewHero.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

                Toast.makeText(Main2Activity.this, "Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}