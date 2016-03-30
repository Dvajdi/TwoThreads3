package ru.twice.forge.udalit;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Runnable,View.OnClickListener {

    static LinearLayout conteiner;
    static TextView tv;
    static Button btnSS,btn_add;
    int rez=0;
    static boolean isStop;

    double p1,q1;

    static Handler h;
    static Thread t;
    android.support.v4.app.FragmentTransaction ft;

    ArrayList <RawFragment> fragments;

    String strPrice;
    String strQuantity;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findMyViews();

        fragments = new ArrayList<>();
        isStop=true;

        h = new MyVeryOwnHandler();

        t=(Thread)getLastCustomNonConfigurationInstance();
        if(t==null){
        t = new Thread(this);
        t.start();}

        }

    private static class MyVeryOwnHandler extends Handler {
        public void handleMessage(Message msg) {
            tv.setText(String.valueOf(msg.what));
            //tv.setText("Статус = "+t.isAlive());
        }
    };

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return t;
    }

    @Override
    public void run() {

        while (isStop){
            for (int i = 0; i <fragments.size() ; i++) {
                strPrice =((EditText)fragments.get(i).getV().findViewById(R.id.et_price)).getText().toString();
                strQuantity=((EditText)fragments.get(i).getV().findViewById(R.id.et_quantity)).getText().toString();


            }

            rez++;
            Log.d("qwe","rez = "+rez);
            Log.d("qwe","isStop = "+isStop);
            Sleeper.sleep(50);
        h.sendEmptyMessage(rez);
        }
    }




    void findMyViews() {

        tv = (TextView) findViewById(R.id.tv);
        btnSS=(Button)findViewById(R.id.btnSS);
        btn_add=(Button)findViewById(R.id.btn_add);
        conteiner=(LinearLayout)findViewById(R.id.conteiner);
        btnSS.setOnClickListener(this);
        btn_add.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSS:onStopOnRun();break;
            case R.id.btn_add:addRaw();break;
        }


    }

    private void addRaw() {
Log.d("zxc","кликер раблотает");
        RawFragment fragment = new RawFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.conteiner,fragment);
        ft.commit();
        fragments.add(fragment);
    }

    private void onStopOnRun() {
        if(isStop==false){
            t=(Thread)getLastCustomNonConfigurationInstance();
            if(t==null)
            {Log.d("asd","создался новый поток");t=new Thread(this);t.start();}
        }
        isStop=!isStop;
        Log.d("qwe", "isStop = " + isStop);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //isStop=false;
    }
}
