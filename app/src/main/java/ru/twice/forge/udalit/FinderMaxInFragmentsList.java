package ru.twice.forge.udalit;

import java.util.ArrayList;

/**
 * Created by twice on 31.03.16.
 */
public class FinderMaxInFragmentsList {
   public ArrayList<RawFragment> fragments;

    public FinderMaxInFragmentsList(ArrayList<RawFragment> fragments) {
        this.fragments = fragments;
    }

    double getMinRes(){
        double min = 0;
        for (int i = 0; i <fragments.size() ; i++) {
            if(min>fragments.get(i).getRes()){min=fragments.get(i).getRes();}
        }
        return min;
    }

    ArrayList getNumberMinRes(){
        double min = getMinRes();

        ArrayList numbers = new ArrayList();

        for (int i = 0; i < fragments.size(); i++) {
            if(fragments.get(i).getRes()==min){numbers.add(i);}
        }

        return numbers;
    }
}
