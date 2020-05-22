package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLogInActivity extends AppCompatActivity {
    private EditText edtUserNameSignIn,edtPasswordSignIn,edtUserNameLogIn,edtPasswordLogIn;
    private Button SignUp,LogIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        edtUserNameSignIn = findViewById(R.id.edtUserNameSignIn);
        edtUserNameLogIn = findViewById(R.id.edtUserNameLogIn);
        edtPasswordSignIn = findViewById(R.id.edtPasswordSignIn);
        edtPasswordLogIn = findViewById(R.id.edtPasswordLogIn);
        SignUp = findViewById(R.id.SignUp);
        LogIn = findViewById(R.id.LogIn);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignIn.getText().toString());
                appUser.setPassword(edtPasswordSignIn.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(SignUpLogInActivity.this,appUser.get("username")+" Is Signed Up Successfully ", Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }else {
                            FancyToast.makeText(SignUpLogInActivity.this,e.getMessage(), Toast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                    }
                });


            }
        });

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtPasswordLogIn.getText().toString(), edtPasswordLogIn.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(user!= null && e==null){
                            FancyToast.makeText(SignUpLogInActivity.this,user.get("username")+" is Logged in", Toast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }else {
                            FancyToast.makeText(SignUpLogInActivity.this,e.getMessage(), Toast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                    }
                });



            }
        });

    }
}
