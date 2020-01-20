package com.yoekisoft.myapplication.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.OAuthProvider;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.yoekisoft.myapplication.R;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
   Button googleSignIn,googleSignOut;
   TwitterLoginButton twitterLoginButton;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");
        TwitterAuthConfig mTwitterAuthConfig = new TwitterAuthConfig("wzlb8ehkxY6IlhwzrCJgkq0OY", "rSSPGK0qtOycXk2o901dRkGVreroaQ9expvc5CvqvplZaJyYDj");
        TwitterConfig twitterConfig = new TwitterConfig.Builder(this)
                .twitterAuthConfig(mTwitterAuthConfig)
                .build();
        Twitter.initialize(twitterConfig);

        setContentView(R.layout.activity_main);

        twitterLoginButton=findViewById(R.id.twitter_login_button);
        googleSignIn=findViewById(R.id.googleButtonSignIn);
        googleSignOut=findViewById(R.id.googleButtonSignOut);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Buttons
        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        googleSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Toast.makeText(MainActivity.this, "Signed in to twitter successful", Toast.LENGTH_LONG).show();
                signInToFirebaseWithTwitterSession(result.data);
                twitterLoginButton.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_LONG).show();
       getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });
    }

    private void signInToFirebaseWithTwitterSession(TwitterSession session){
        AuthCredential credential = TwitterAuthProvider.getCredential(session.getAuthToken().token, session.getAuthToken().secret);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this, "Signed in firebase twitter successful", Toast.LENGTH_LONG).show();
                        if (!task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Auth firebase twitter failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser= mAuth.getCurrentUser();
        upadateUI(currentuser);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
        else
            {
                twitterLoginButton.onActivityResult(requestCode, resultCode, data);
            }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            googleSignIn.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity.this, "Name:--"+ user.getEmail(), Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }

                        // ...
                    }
                });
    }
    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       // updateUI(null);
                        Toast.makeText(MainActivity.this, " Signout", Toast.LENGTH_SHORT).show();
                        googleSignIn.setVisibility(View.VISIBLE);
                    }
                });
    }
    private void upadateUI(FirebaseUser user) {
        if (user != null) {/*
            mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));*/
            Toast.makeText(this, "Name:--"+user.getEmail(), Toast.LENGTH_SHORT).show();
           /* findViewById(R.id.signInButton).setVisibility(View.GONE);
            findViewById(R.id.signOutAndDisconnect).setVisibility(View.VISIBLE);*/
        } else {
         //   Toast.makeText(this, "Signout", Toast.LENGTH_SHORT).show();
            /*
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.signInButton).setVisibility(View.VISIBLE);
            findViewById(R.id.signOutAndDisconnect).setVisibility(View.GONE);*/
        }
    }

}
