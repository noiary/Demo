package com.maodq.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maodq.demo.internal.AppbarLayoutDemo;
import com.maodq.demo.internal.BehaviorActivity;
import com.maodq.demo.internal.EventDispatchActivity;
import com.maodq.demo.internal.HandlerDemoActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_behavior).setOnClickListener(this);
        findViewById(R.id.btn_appbar_layout).setOnClickListener(this);
        findViewById(R.id.btn_bottom_sheet_dialog).setOnClickListener(this);
        findViewById(R.id.btn_handler_demo).setOnClickListener(this);
        findViewById(R.id.btn_event).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_behavior:
                intent = new Intent(this, BehaviorActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_appbar_layout:
                intent = new Intent(this, AppbarLayoutDemo.class);
                startActivity(intent);
                break;
            case R.id.btn_bottom_sheet_dialog:
                showBottomSheetDialog(this);
                break;
            case R.id.btn_handler_demo:
                intent = new Intent(this, HandlerDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_event:
                intent = new Intent(this, EventDispatchActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void showBottomSheetDialog(Activity activity) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_bottom_sheet_dialog, null);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from((View) view.getParent());
        int testHeight = (int) (getResources().getDisplayMetrics().density * 256);
        CoordinatorLayout.LayoutParams layoutParams =
                new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, testHeight);
        ((View) view.getParent().getParent()).getLayoutParams().height = testHeight;
        behavior.setPeekHeight(testHeight);

        RecyclerView recyclerView = (RecyclerView) view;
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        BottomSheetAdapter adapter = new BottomSheetAdapter(this, mockData());
        recyclerView.setAdapter(adapter);
        bottomSheetDialog.show();
    }

    private List<String> mockData() {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("demo " + i);
        }
        return data;
    }

    private static class BottomSheetAdapter extends RecyclerView.Adapter {

        private List<String> mData;
        final Activity mActivity;

        public BottomSheetAdapter(Activity activity, List<String> data) {
            mData = data;
            mActivity = activity;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(mActivity);
            return new MyHolder(textView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView itemView = (TextView) holder.itemView;
            itemView.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }


        private class MyHolder extends RecyclerView.ViewHolder {

            public MyHolder(TextView itemView) {
                super(itemView);
                itemView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            }
        }
    }
}
