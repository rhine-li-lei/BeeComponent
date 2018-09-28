package com.lilei.router_api.UIRouter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lilei.router_api.utils.UriUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilei
 * Date : 2018/9/26
 */

public class UIRouter implements IUIRouter {

    private List<IComponentRouter> uiRouters = new ArrayList<>();  // generated router

    private static volatile UIRouter instance;

    private UIRouter() {
    }

    public static UIRouter getInstance() {
        if (instance == null) {
            synchronized (UIRouter.class) {
                if (instance == null) {
                    instance = new UIRouter();
                }
            }
        }
        return instance;
    }


    @Override
    public boolean openUri(Context context, String url) {
        return openUri(context, UriParse(url));
    }

    @Override
    public boolean openUri(Context context, Uri url) {
        return openUri(context, url,null);
    }

    @Override
    public boolean openUri(Context context, String url, Bundle bundle) {
        return openUri(context, UriParse(url), bundle);
    }

    @Override
    public boolean openUri(Context context, Uri uri, Bundle bundle) {
        return openUri(context,uri,bundle,-1);
    }

    @Override
    public boolean openUri(Context context, String url, Bundle bundle, Integer requestCode) {
        return openUri(context, UriParse(url), bundle, -1);
    }

    @Override
    public boolean openUri(Context context, Uri uri, Bundle bundle, Integer requestCode) {
        if (uri == null){
            return false;
        }
        for (IComponentRouter router :uiRouters) {
            try {
                if (router.verifyUri(uri) && router.openUri(context, uri, bundle, requestCode)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean verifyUri(Uri uri) {
        for (IComponentRouter temp : uiRouters) {
            if (temp.verifyUri(uri)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void registerUI(IComponentRouter router, int priority) {
        uiRouters.add(router);
    }

    @Override
    public void registerUI(IComponentRouter router) {
        uiRouters.add(router);
    }

    @Override
    public void registerUI(String host) {
        uiRouters.add(fetch(host));
    }

    @Override
    public void registerUI(String host, int priority) {
        registerUI(host);
    }

    @Override
    public void unregisterUI(IComponentRouter router) {
        for (int i = 0; i < uiRouters.size(); i++) {
            if (router == uiRouters.get(i)) {
                uiRouters.remove(i);
                break;
            }
        }
    }

    @Override
    public void unregisterUI(String host) {
        IComponentRouter router = fetch(host);
        if (router != null) {
            unregisterUI(router);
        }
    }

    private IComponentRouter fetch(@NonNull String host) {

        String path = "com.lilei.router_gen"+"."+ UriUtils.firstCharUpperCase(host)+"$Router";

        try {
            Class cla = Class.forName(path);
            IComponentRouter instance = (IComponentRouter) cla.newInstance();
//            routerInstanceCache.put(path, instance);
            return instance;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Uri UriParse(String uri){
        uri = uri.trim();
        Uri result;
        if (!TextUtils.isEmpty(uri)) {
            if (!uri.contains("://") &&
                    (!uri.startsWith("tel:") ||
                            !uri.startsWith("smsto:") ||
                            !uri.startsWith("file:"))) {
                uri = "http://" + uri;
            }
            result = Uri.parse(uri);
        } else {
            result = null;
        }
        return result;
    }
}
