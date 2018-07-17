package com.maodq.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.maodq.demo.MyFragment;
import com.maodq.demo.R;

public class FragmentDemoActivity extends AppCompatActivity {

    private FrameLayout flContainer;
    private TextView tv1;
    private TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);

        flContainer = findViewById(R.id.fl_container);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container, new MyFragment())
                .commit();
    }
}
