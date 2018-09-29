package com.lilei.beecomponent;

import com.lilei.base.BaseApplication;
import com.lilei.router_api.UIRouter.UIRouter;

/**
 * Created by lilei
 * Date : 2018/9/28
 */

public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        UIRouter.getInstance().registerUI("app");
        UIRouter.getInstance().registerUI("login");
//        UIRouter.getInstance().registerUI("talking");
    }
}
