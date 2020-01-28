package com.yoekisoft.myapplication.SharedPreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yoekisoft.myapplication.R;

public class SharedDEmo extends AppCompatActivity implements View.OnClickListener {
    EditText nameid, emailid, phoneid, deletetext;
    String name, email, phone;
    Button save, shared, update, deletebutton;
    TextView show, afterUpdate;
    SharedPreferences.Editor editor;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_demo);
        intialize();
        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();
    }

    private void intialize() {
        nameid = findViewById(R.id.sharedname);
        // emailid = findViewById(R.id.emailid);
        phoneid = findViewById(R.id.sharedphone);
        show = findViewById(R.id.sharedshowtext);
        deletetext = findViewById(R.id.deletetext);
        deletebutton = findViewById(R.id.deletebutton);
        deletebutton.setOnClickListener(this);
        shared = findViewById(R.id.Sharedshowbutton);
        shared.setOnClickListener(this);
        save = findViewById(R.id.uploadShared);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uploadShared:
                name = nameid.getText().toString();
                phone = phoneid.getText().toString();
                if (name.isEmpty() || phone.isEmpty())
                {
                    Toast.makeText(this, "Enter data", Toast.LENGTH_SHORT).show();
                }
                editor.putBoolean("status", true); // Storing boolean - true/false
                editor.putString("key_name", name); // Storing string
                editor.putString("key_phone", phone); // Storing integer
                editor.commit();
                nameid.setText("");
                phoneid.setText("");

                break;
            case R.id.Sharedshowbutton:
                boolean shownstatus = pref.getBoolean("status", false);
                String showname = pref.getString("key_name", null); // getting String
                String showphone = pref.getString("key_phone", null); // getting String
                if (!pref.contains("key_name")  &&!pref.contains("key_phone"))  {
                    if(showname!=null &&showphone !=null)
                    show.setText("No data");
                }
                else
                    show.setText("_" + shownstatus + "_" + showname + "" + showphone);
                break;
            case R.id.deletebutton:
              /*  String textdelete = deletetext.getText().toString();
                String showname1 = pref.getString("key_name", null);
                if (showname1.equalsIgnoreCase(textdelete)) {
                    editor.clear(); // will delete key name
                    // will delete key email
                    editor.commit();
                }*/
                break;
        }
    }
}