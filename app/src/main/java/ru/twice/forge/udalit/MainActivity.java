package ru.twice.forge.udalit;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable{

    static EditText etPrice1,etPrice2;
    static EditText etQuantity1,etQuantity2;
    static TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findMyViews();

        Handler h = new Handler(){

        };
    }

    void findMyViews(){
        etPrice1 =(EditText)findViewById(R.id.etPrice1);
        etPrice2 =(EditText)findViewById(R.id.etPrice2);
        etQuantity1 =(EditText)findViewById(R.id.etQuantity1);
        etQuantity2 =(EditText)findViewById(R.id.etQuantity2);
        tv=(TextView)findViewById(R.id.tv);
    }


    @Override
    public void run() {
        double p1 = Double.valueOf(etPrice1.getText().toString());
        double q1 = Double.valueOf(etQuantity1.getText().toString());
        tv.setText(String.valueOf(p1/q1));
    }
}
