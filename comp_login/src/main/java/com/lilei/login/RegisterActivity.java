package com.lilei.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

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
    }
}
