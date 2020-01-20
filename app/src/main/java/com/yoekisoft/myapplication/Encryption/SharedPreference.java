package com.yoekisoft.myapplication.Encryption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yoekisoft.myapplication.Encryption.EncryptedSharedPreferences;
import com.yoekisoft.myapplication.R;

public class SharedPreference extends AppCompatActivity implements View.OnClickListener {
EditText sharedtext, AfterShow ;
Button decryption,encryption,save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
initialize();

    }

    private void initialize() {
        sharedtext=findViewById(R.id.sharedtext);
        AfterShow=findViewById(R.id.AfterShow);
        save=findViewById(R.id.save);
        encryption=findViewById(R.id.encryption);
        decryption=findViewById(R.id.decryption);
        save.setOnClickListener(this);
        encryption.setOnClickListener(this);
        decryption.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                String data=sharedtext.getText().toString();
                Boolean prefernce= EncryptedSharedPreferences.getInstance(getApplicationContext()).saveValue(data);
                Log.d("SAVE",""+prefernce);
                break;
            case R.id.encryption:
                break;
            case R.id.decryption:
                break;

        }
    }
}
