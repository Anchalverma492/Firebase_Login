package com.yoekisoft.myapplication.DAtabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging;
import com.yoekisoft.myapplication.FirebaseMsg.InAppMsg;
import com.yoekisoft.myapplication.R;

public class FireBaseData extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mDatabase;
    EditText Fsttext, scndtext, emailtext, phonetext,searchText;
    Button upload, delete,search;
    TextView show;
    String name, sName, phone, emailString,searchString;
    DatabaseReference myRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_data);
        initialize();
        InAppMsg listener = new InAppMsg();
        FirebaseInAppMessaging.getInstance().addClickListener(listener);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("USER");
        Log.d("data", name + sName);

      /*  mDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                Log.d(TAG, "User name: " + user.getName() + ", emailString " + user.getEmail());
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
        emailtext = findViewById(R.id.email);
        phonetext = findViewById(R.id.phone);
        upload = findViewById(R.id.upload);
        delete = findViewById(R.id.delete);
        searchText=findViewById(R.id.searchText);
        show=findViewById(R.id.showsearch);
        search=findViewById(R.id.search);
        search.setOnClickListener(this);
        delete.setOnClickListener(this);
        upload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload:

                name = Fsttext.getText().toString();
                sName = scndtext.getText().toString();
                phone = phonetext.getText().toString();
                emailString = emailtext.getText().toString();

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

                myRef.child(name + sName).setValue(new UserModel(name, sName, emailString, phone));


//read Values



                break;
            case R.id.search:

                searchString=searchText.getText().toString();
                Query query = myRef.orderByChild("email").equalTo("Veham@gmail.com");
                Log.d("Query",query.toString());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UserModel user = new UserModel();
                        DataSnapshot dsp=dataSnapshot.getChildren().iterator().next();
                        user.Fname = dsp.child("Fname").getValue().toString();
                        user.Sname = dsp.child("Sname").getValue().toString();
                        user.email=dsp.child("email").getValue().toString();
                        user.phone=dsp.child("phone").getValue().toString();
                        Log.d("response",user.Fname);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                break;
            case R.id.delete:
               DatabaseReference mynepro = database.getReference("NEPRO");
               mynepro.child("SiteList").child("AL-KHOMRA").child("0").removeValue();
                name = Fsttext.getText().toString();
                sName = scndtext.getText().toString();
                myRef.child(name + sName).removeValue();





                break;
        }
    }

}
