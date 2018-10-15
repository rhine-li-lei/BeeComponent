package com.lilei.talking;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lilei.router_anno.RouteNode;
import com.lilei.router_api.UIRouter.UIRouter;

/**
 * Created by lilei
 * Date : 2018/9/28
 */
@RouteNode(path = "/talking",desc = "听听堂")
public class TalkingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.talking_fragment_talking, container, false);
        return view;
    }
}
