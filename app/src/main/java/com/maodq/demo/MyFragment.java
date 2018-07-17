package com.maodq.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * MyFragment
 * Created by maodq on 17/5/16.
 */

public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        final TextView tvHello = (TextView) view.findViewById(R.id.tv_hello);
        tvHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHello.setText("哈哈哈哈哈哈哈哈哈");
            }
        });
        return view;
    }
}
