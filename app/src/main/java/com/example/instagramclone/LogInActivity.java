package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogin,btnSignUp;
    private EditText edtUsernamelogin,edtPasswordlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setTitle("LogIn");
                edtPasswordlogin = findViewById(R.id.edtPasswordlogin);
                edtUsernamelogin = findViewById(R.id.edtUsernamelogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(LogInActivity.this);
        btnLogin.setOnClickListener(LogInActivity.this);

        if(ParseUser.getCurrentUser() !=null) {
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                ParseUser.logInInBackground(edtUsernamelogin.getText().toString(), edtPasswordlogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(user!= null && e==null){
                            FancyToast.makeText(LogInActivity.this,user.get("username")+" is Logged in", Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                               transitionToSocialMediaActivity();
                        }else {
                            FancyToast.makeText(LogInActivity.this,e.getMessage(), Toast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                    }
                });
                break;
            case R.id.btnSignUp:
                Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                startActivity(intent);
                break;

        }
    }

    public void transitionToSocialMediaActivity(){
        Intent intent = new Intent(LogInActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }

}

