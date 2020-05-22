package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave,btnGetAllData,button2;
    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickSpeed,edtKickPower;
    private TextView txtView;
    private String allKickBoxer;

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
        txtView = findViewById(R.id.txtView);
        btnGetAllData = findViewById(R.id.btnGetAlldeta);
        button2 = findViewById(R.id.button2);

        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxing");
                parseQuery.getInBackground("2OF3q6i2cq", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if(object!= null && e==null){
                            txtView.setText(object.get("Name")+" ");

                        }



                    }
                });

            }
        });
        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allKickBoxer ="";

                ParseQuery<ParseObject> quarryAll = ParseQuery.getQuery("KickBoxing");

                quarryAll.setLimit(1);

                quarryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e==null ){
                            if(objects.size()>0){
                                for(ParseObject kickBoxer: objects) {
                                    allKickBoxer += kickBoxer.get("Name")+ "\n";
                                }
                                FancyToast.makeText(MainActivity.this,allKickBoxer,Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            }else {
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                        }


                    }
                });


            }
        });

           button2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

               }
           });


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
