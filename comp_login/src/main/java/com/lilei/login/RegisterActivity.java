package com.lilei.login;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lilei.base.BaseActivity;
import com.lilei.router_anno.RouteNode;

/**
 * Created by lilei
 * Date : 2018/9/28
 */

@RouteNode(path = "/register", desc = "注册界面")
public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        long now = SystemClock.uptimeMillis();
        long cast = now - (long)getIntent().getExtras().get("jumptime2");
        long routercast = (long)getIntent().getExtras().get("routercast");

        Log.d("TAG-time_test", "router 所花时间："+routercast +"\t原生跳转时间："+cast);

    }

}
