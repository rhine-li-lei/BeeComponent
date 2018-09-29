package com.lilei.talking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lilei
 * Date : 2018/9/29
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talking_activity_main);
        getSupportFragmentManager()    //
                .beginTransaction()
                .add(R.id.fragment_container,new TalkingFragment())
                .commit();
    }
}
