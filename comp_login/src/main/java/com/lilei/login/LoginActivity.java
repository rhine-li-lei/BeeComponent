package com.lilei.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.lilei.base.BaseActivity;
import com.lilei.router_anno.RouteNode;

/**
 * Created by lilei
 * Date : 2018/9/28
 *
 * 记：compile：可传递依赖
 *     implementation：不可以传递依赖，隐藏自己使用的依赖，避免一条依赖链路上，变动时候少编译模块，提高编译速度
 *     api: 可传递依赖
 */
@RouteNode(path = "/login",desc = "登陆界面")
public class LoginActivity extends BaseActivity {

    long cast;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        long now = SystemClock.uptimeMillis();
        cast = now - (long)getIntent().getExtras().get("jumptime");
        Log.d("TAG", "onCreate: "+cast);

        findViewById(R.id.button_jump_to_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToRegister();
            }
        });
    }


    private void ToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("jumptime2", SystemClock.uptimeMillis());
        bundle.putLong("routercast",cast);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
