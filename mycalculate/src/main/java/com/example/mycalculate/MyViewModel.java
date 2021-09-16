package com.example.mycalculate;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.Random;

public class MyViewModel extends AndroidViewModel {
    SavedStateHandle handle;
    private static String KEY_HIGH_SCORE = "key_high_score";
    private static String KEY_CUR_SCORE = "key_cur_score";
    private static String KEY_LEFT_NUM = "key_left_num";
    private static String KEY_RIGHT_NUM = "key_right_num";
    private static String KEY_OPERATE = "key_operate";
    private static String KEY_ANSWER = "key_answer";

    boolean win_flag = false;

    public MyViewModel(@NonNull Application application,SavedStateHandle handle) {
        super(application);
        if(!handle.contains(KEY_HIGH_SCORE)){
          handle.set(KEY_HIGH_SCORE,SPUtil.getIntWithDefault(getApplication(),KEY_HIGH_SCORE,0));
          handle.set(KEY_LEFT_NUM,0);
          handle.set(KEY_RIGHT_NUM,0);
          handle.set(KEY_OPERATE,"+");
          handle.set(KEY_ANSWER,0);
          handle.set(KEY_CUR_SCORE,0);
        }

        this.handle = handle;
    }

    public MutableLiveData<Integer> getLeftNum(){
        return handle.getLiveData(KEY_LEFT_NUM);
    }
    public MutableLiveData<Integer> getRightNum(){
        return handle.getLiveData(KEY_RIGHT_NUM);
    }
    public MutableLiveData<String> getOperater(){
        return handle.getLiveData(KEY_OPERATE);
    }
    public MutableLiveData<Integer> getHighScore(){
        return handle.getLiveData(KEY_HIGH_SCORE);
    }
    public MutableLiveData<Integer> getCurScore(){
        return handle.getLiveData(KEY_CUR_SCORE);
    }

    public MutableLiveData<Integer> getAnswer(){
        return handle.getLiveData(KEY_ANSWER);
    }

    void generator(){
        int LEVEL = 20;
        Random random = new Random();
        int x,y;
        x = random.nextInt(LEVEL) + 1;
        y = random.nextInt(LEVEL) + 1;
        if(x % 2 == 0){
            getOperater().setValue("+");
            if(x > y){
                getAnswer().setValue(x);
                getLeftNum().setValue(y);
                getRightNum().setValue(x - y);
            }else{
                getAnswer().setValue(y);
                getLeftNum().setValue(x);
                getRightNum().setValue(y - x);
            }

        }else {
            getOperater().setValue("-");
            if(x > y){
                getAnswer().setValue(x - y);
                getLeftNum().setValue(x);
                getRightNum().setValue(y);
            }else{
                getAnswer().setValue(y - x);
                getLeftNum().setValue(y);
                getRightNum().setValue(x);
            }
        }

    }

    void save(){
        SPUtil.saveInt(getApplication(),KEY_HIGH_SCORE,getHighScore().getValue());
    }

    void answerCorrect(){
        getCurScore().setValue(getCurScore().getValue() + 1);
        if(getCurScore().getValue() > getHighScore().getValue()){
            getHighScore().setValue(getCurScore().getValue());
            win_flag = true;
        }
        generator();
    }

   public void reset(){
        handle.set(KEY_HIGH_SCORE,0);
        save();
    }


}
