package com.lilei.router_api.UIRouter;

/**
 * router behaviors for component type, sub of {@link IComponentRouter}
 */

public interface IUIRouter extends IComponentRouter {

    int PRIORITY_NORMAL = 0;
    int PRIORITY_LOW = -1000;
    int PRIORITY_HEIGHT = 1000;

    void registerUI(IComponentRouter router, int priority);

    void registerUI(IComponentRouter router);

    void registerUI(String host);

    void registerUI(String host, int priority);

    void unregisterUI(IComponentRouter router);

    void unregisterUI(String host);
}
