package ru.twice.forge.udalit;

import android.app.FragmentTransaction;
import android.graphics.Color;
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

    static ArrayList <RawFragment> fragments;
    static ArrayList bestNumbers;

    String strPrice;
    String strQuantity;


    static FinderMaxInFragmentsList finder;
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
        finder = new FinderMaxInFragmentsList(fragments);
        }

    private static class MyVeryOwnHandler extends Handler {
        public void handleMessage(Message msg) {

            View view=fragments.get(msg.what).getV();
            ((TextView)view.findViewById(R.id.tv_res)).setText(msg.obj.toString());
            if(msg.arg1==1){view.setBackgroundColor(Color.RED);}
            if(msg.arg1==0){view.setBackgroundColor(Color.GREEN);}
        }
    };

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return t;
    }

    @Override
    public void run() {
            double price,quantity;
            double result,min;

            RawFragment fragment;
            View v;
            ArrayList numbers;
            int arg1;
        while (isStop){
            for (int i = 0; i <fragments.size() ; i++) {
                fragment=fragments.get(i);
                v=fragment.getView();

                if(v!=null){
                strPrice =((EditText)v.findViewById(R.id.et_price)).getText().toString();
                strQuantity=((EditText)v.findViewById(R.id.et_quantity)).getText().toString();

                try {price=Double.valueOf(strPrice);}catch(Exception e){price=0;}
                try{quantity=Double.valueOf(strQuantity);}catch(Exception e){quantity=1;}
                result=price/quantity;

                fragment.setRes(result);
                    min = getBestNumbers(fragments);
                    Log.d("asd","min = "+min);
                    //Sleeper.sleep(100);
                if(result==min){arg1=1;}else{arg1=0;}

                Message msg=h.obtainMessage(i,arg1,0,String.valueOf(result));
                //Message msg=h.obtainMessage(i,String.valueOf(result));
                h.sendMessage(msg);}
            }


        }
    }

double getBestNumbers(ArrayList<RawFragment>fragments){
    double min=Double.MAX_VALUE;
    double res=0;
    for (int i = 0; i <fragments.size() ; i++) {
        res=fragments.get(i).getRes();
        if(res<min){min=res;}
    }
   return min;
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
            {t=new Thread(this);t.start();}
        }
        isStop=!isStop;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //isStop=false;
    }
}
