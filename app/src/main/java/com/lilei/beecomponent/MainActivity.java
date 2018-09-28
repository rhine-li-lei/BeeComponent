package com.lilei.beecomponent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lilei.router_anno.RouteNode;
import com.lilei.router_api.UIRouter.UIRouter;

@RouteNode(path = "/main",desc = "主界面")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpToLogin(View view) {
        if(UIRouter.getInstance().openUri(MainActivity.this, "BeeComp://login/login")){  // 可查看路由表
            finish();
        }
    }
}
