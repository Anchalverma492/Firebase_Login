package com.yoekisoft.myapplication.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yoekisoft.myapplication.R;

import java.util.List;

public class login_SQLite extends AppCompatActivity implements View.OnClickListener {
    EditText nameid, emailid, phoneid;
    String name, email, phone;
    Button save, shared, update,delete;
    TextView show,afterUpdate;
    SharedPreferences.Editor editor;
    SharedPreferences pref;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__sqlite);
        intialize();
        db = new DatabaseHelper(this);
       /* save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
                // Get the layout inflater

                LayoutInflater inflater = getLayoutInflater();
                builder.setTitle("");
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflater.inflate(R.layout.customdialog, null))
                        // Add action buttons
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create();
                builder.show();

            }
        });*/
       /* pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();*/

    }

    private void intialize() {
        nameid = findViewById(R.id.nameid);
        // emailid = findViewById(R.id.emailid);
        phoneid = findViewById(R.id.phoneid);
        show = findViewById(R.id.show);
        delete=findViewById(R.id.delete);
        delete.setOnClickListener((View.OnClickListener) this);
        shared = findViewById(R.id.Shared);
        shared.setOnClickListener((View.OnClickListener) this);
        update = findViewById(R.id.update);
        update.setOnClickListener((View.OnClickListener) this);
        save = findViewById(R.id.uploadSQlite);
        save.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uploadSQlite:
                name = nameid.getText().toString();
                // email = emailid.getText().toString();
                phone = phoneid.getText().toString();

                db.addContact(new ModelData(name, name + "@gmail.com", phone));

                Toast.makeText(this, "Insertion Successful", Toast.LENGTH_SHORT).show();
                nameid.setText("");
               // emailid.setText("");
                phoneid.setText("");

             /*   editor.putBoolean("Status", true); // Storing boolean - true/false
                editor.putString("Name", name); // Storing string
                editor.putString("Email", email); // Storing integer
                editor.putString("Phone",phone); // Storing float
                editor.commit();*/

                break;
            case R.id.Shared:
                StringBuilder builder = new StringBuilder();
                List<String> list = db.selectContact();
                for (int i = 0; i < list.size(); i++) {

                    builder.append(list.get(i)+"\n");
                }
                show.setText(builder);


/*

                if (pref.contains(Name)) {
                    name.setText(sharedpreferences.getString(Name, ""));
                }
                if (sharedpreferences.contains(Email)) {
                    email.setText(sharedpreferences.getString(Email, ""));

                }
*/
                break;
            case R.id.update:

                db.updateContact();
                StringBuilder builder1 = new StringBuilder();
                List<String> list1= db.selectContact();
                for (int i = 0; i < list1.size(); i++) {

                    builder1.append(list1.get(i)+"\n");
                }
                show.setText(builder1);
//                Toast.makeText(this, modelDAta1.getName() +"Email"+ modelDAta1.getEmail(), Toast.LENGTH_SHORT).show();

                break;
            case R.id.delete:

                db.deleteContact();
                break;
        }
    }
}