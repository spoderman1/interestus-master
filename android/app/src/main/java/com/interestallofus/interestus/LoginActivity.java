package com.interestallofus.interestus;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;


import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


   /* private static int RC_SIGN_IN =0;
    private static String TAG = "MAIN_ACTIVITY";
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;*/


    private static final int RC_SIGN_IN = 0;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            //user signed in
            Log.d("AUTH", auth.getCurrentUser().getEmail());
        }else{


            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setProviders(
                            AuthUI.FACEBOOK_PROVIDER,
                            AuthUI.EMAIL_PROVIDER,
                            AuthUI.GOOGLE_PROVIDER)
                    .build(), 1);
        }

        findViewById(R.id.sign_out_button).setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            if(resultCode == RESULT_OK){
                Log.d("AUTH", auth.getCurrentUser().getEmail());
            }
            else{
                Log.d("AUTH", "NOT AUTHENTICATED");
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.sign_out_button){
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d("AUTH", "USER LOGGED OUT");
                        }
                    });
        }
    }
}


        /*mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null)
                    Log.d("AUTH", "User logged in: " + user.getEmail());
                else
                    Log.d("AUTH", "User logged out");
            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        findViewById(R.id.email_sign_in_button).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null)
            mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.email_sign_in_button:
                signIn();
                break;
            case R.id.sign_out_button:
                signOut();
                break;
        }
    }

    private void signIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);
            }
            else
            Log.d(TAG, "Login Failed");
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct)
    {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
       mAuth.signInWithCredential(credential)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       Log.d("AUTH", "signInWithCredential:oncomplete: " + task.isSuccessful());
                   }
               });
    }
*/
//}

