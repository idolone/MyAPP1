package com.example.lifecycle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    private long elapsedtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.ameter);
        chronometer.setBase(SystemClock.elapsedRealtime());
        Log.d("TAG", "onCreate: ");

    }

    @Override
    protected void onStart() {
        Log.d("TAG", "onStart: ");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d("TAG", "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d("TAG", "onResume: ");
        super.onResume();
        chronometer.setBase(SystemClock.elapsedRealtime() - elapsedtime);
        chronometer.start();
    }
    @Override
    protected void onPause() {
        Log.d("TAG", "onPause: ");
        super.onPause();
        elapsedtime = SystemClock.elapsedRealtime() - chronometer.getBase();
        chronometer.stop();
    }
    @Override
    protected void onStop() {
        Log.d("TAG", "onStop: ");
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("TAG", "onSaveInstanceState: ");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        Log.d("TAG", "onDestroy: ");
        super.onDestroy();
    }

}