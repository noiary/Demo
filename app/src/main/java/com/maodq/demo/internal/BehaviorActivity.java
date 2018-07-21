package com.maodq.demo.internal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maodq.demo.R;

import java.util.ArrayList;
import java.util.List;

public class BehaviorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behivor);
        RecyclerView rvList = findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(new MyAdapter());
    }


    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

        List<String> mData = null;

        MyAdapter() {
            super();
            mData = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                mData.add("demo " + i);
            }
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = new TextView(parent.getContext());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            return new MyHolder(tv);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            TextView tv = (TextView) holder.itemView;
            tv.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }


        class MyHolder extends RecyclerView.ViewHolder {
            MyHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
