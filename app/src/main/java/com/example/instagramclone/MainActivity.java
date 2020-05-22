package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickSpeed,edtKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.button);
        btnSave.setOnClickListener(MainActivity.this);
        edtName = findViewById(R.id.edtName);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);




    }


    @Override
    public void onClick(View v) {
        try {
            final ParseObject KickBoxing = new ParseObject("KickBoxing");
            KickBoxing.put("Name", edtName.getText().toString());
            KickBoxing.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            KickBoxing.put("PunchPower", Integer.parseInt(edtPunchPower.getText().toString()));
            KickBoxing.put("KickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
            KickBoxing.put("KickPower", Integer.parseInt(edtKickPower.getText().toString()));
            KickBoxing.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null)
                        FancyToast.makeText(MainActivity.this, KickBoxing.get("Name") + "  Is Successfully Updated", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    else
                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                }

            });
        }catch (Exception e){
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
}
