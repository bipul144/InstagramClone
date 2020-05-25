package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button LogIn,SignUp;
    EditText edtEmail,edtUsername,edtPassword;
    private String allKickBoxer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Instagram");

        SignUp = findViewById(R.id.SignUp);

        edtEmail = findViewById(R.id.edtEmail);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(SignUp);
                }
                return false;
            }
        });
        LogIn = findViewById(R.id.LogIn);

        SignUp.setOnClickListener(this);
        LogIn.setOnClickListener(this);

        if(ParseUser.getCurrentUser() !=null) {
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.SignUp :

                if(edtEmail.getText().toString().equals("")||edtUsername.getText().toString().equals("")||edtPassword.getText().toString().equals("")){

                    FancyToast.makeText(MainActivity.this," Please Fill All The Fields ", Toast.LENGTH_LONG,FancyToast.ERROR,true).show();

                }else {


                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEmail.getText().toString());
                appUser.setUsername(edtUsername.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing Up !!"+ edtUsername.getText().toString());
                progressDialog.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(MainActivity.this,appUser.get("username")+" Is Signed Up Successfully ", Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            transitionToSocialMediaActivity();
                        }else {
                            FancyToast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }

                        progressDialog.dismiss();
                    }
                });}

                break;
            case R.id.LogIn:
                Intent intent = new Intent(MainActivity.this,LogInActivity.class);
                startActivity(intent);
                break;

        }

    }
    public void rootLayoutTapped(View view){
       try{
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }catch (Exception e){
           e.printStackTrace();
       }
    }
public void transitionToSocialMediaActivity(){
        Intent intent = new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
}



}
