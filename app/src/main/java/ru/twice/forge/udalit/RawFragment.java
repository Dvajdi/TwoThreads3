package ru.twice.forge.udalit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by twice on 30.03.16.
 */
public class RawFragment extends Fragment {
    double res;

    public double getRes() {
        return res;
    }

    public void setRes(double res) {

        this.res = res;
    }

    View v;

    public View getV() {
        return v;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.raw_layout, container, false);
        return v;
    }


}
