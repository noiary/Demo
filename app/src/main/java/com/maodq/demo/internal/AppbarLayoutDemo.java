package com.maodq.demo.internal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maodq.demo.R;

import java.util.Locale;

public class AppbarLayoutDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_appbar_layout_demo);
        init();
    }

    private void init() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.linear);
        for (int i = 0; i < 100; i++) {
            TextView tv = new TextView(this);
            tv.setText(String.format(Locale.CHINA, "demo -- %d", i));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            ll.addView(tv);
        }
    }
}
