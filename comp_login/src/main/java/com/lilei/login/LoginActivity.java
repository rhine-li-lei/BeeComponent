package com.lilei.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

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
public class LoginActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
