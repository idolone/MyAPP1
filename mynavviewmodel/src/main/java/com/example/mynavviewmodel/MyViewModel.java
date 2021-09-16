package com.example.mynavviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MyViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> num;
    public MyViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Integer> getNum() {
        if(num == null){
           num = new MutableLiveData<>();
           num.setValue(0);
        }
        return num;
    }

    public void add(int p){
        num.setValue(num.getValue()+p);
        if(num.getValue() < 0){
            num.setValue(0);
        }

    }
}
