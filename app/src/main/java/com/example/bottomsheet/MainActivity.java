package com.example.bottomsheet;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity implements BottomSheetFragment.BottomSheetListener {

    private TextView textViewData;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        textViewData = findViewById(R.id.textViewData);
        Button openBottomSheetButton = findViewById(R.id.openBottomSheetButton);

        openBottomSheetButton.setOnClickListener(v -> {
            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
            bottomSheetFragment.show(getSupportFragmentManager(), "BottomSheet");
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {

            textViewData.setText("Data will appear here");
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void onDataReceived(String data) {
        textViewData.setText(data);
    }
}
