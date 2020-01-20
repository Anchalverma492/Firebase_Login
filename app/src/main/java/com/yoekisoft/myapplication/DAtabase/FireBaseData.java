package com.yoekisoft.myapplication.DAtabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yoekisoft.myapplication.R;

import java.util.HashMap;
import java.util.Map;

public class FireBaseData extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mDatabase;
    EditText Fsttext, scndtext,emailtext,phonetext;
    Button upload,delete;
     String name,sName,phone,email;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_data);
        initialize();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
       myRef = database.getReference("USER");
Log.d("data",name+sName);

      /*  mDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                Log.d(TAG, "User name: " + user.getName() + ", email " + user.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
*/

    }

    private void initialize() {
        Fsttext = findViewById(R.id.Fsttext);
        scndtext = findViewById(R.id.scndtext);
        emailtext=findViewById(R.id.email);
        phonetext=findViewById(R.id.phone);
        upload = findViewById(R.id.upload);
        delete=findViewById(R.id.delete);
        delete.setOnClickListener(this);
        upload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload:
                name = Fsttext.getText().toString();
                sName = scndtext.getText().toString();
                phone=phonetext.getText().toString();
                email=emailtext.getText().toString();

// Write a message to the database

                /*
                  //Setting firebase unique key for Hashmap list
                  String userId = myRef.push().getKey();
                // creating user object
                  UserModel user = new UserModel(name,sName,"1234");
               myRef.child(userId).setValue(user);
//                String userId = myRef.push().getKey();
//                Map<String, UserModel> users = new HashMap<>();
//                users.put(, );*/

                myRef.child(name+sName).setValue(new UserModel(name, sName, email,phone));


//read Values
                myRef.child(name+sName).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UserModel user = dataSnapshot.getValue(UserModel.class);
                        Toast.makeText(FireBaseData.this, "" + user, Toast.LENGTH_SHORT).show();
                        Log.d("DATA", user.Fname);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                break;
            case R.id.delete:

                name = Fsttext.getText().toString();
                sName = scndtext.getText().toString();
                myRef.child(name+sName).removeValue();

                break;
        }
    }
}
