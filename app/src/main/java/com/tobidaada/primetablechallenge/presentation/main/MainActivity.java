package com.tobidaada.primetablechallenge.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tobidaada.primetablechallenge.R;
import com.tobidaada.primetablechallenge.presentation.sheet.SheetActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = findViewById(R.id.btn);
        mEditText = findViewById(R.id.et);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPrimeNumberLengthEntered();
            }
        });

        if (savedInstanceState != null) {
            mEditText.setText(savedInstanceState.getString("userInput"));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_clear:
                onRefreshLayout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mEditText.getText().toString().length() >= 1) {
            outState.putString("userInput", mEditText.getText().toString());
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            String length = savedInstanceState.getString("userInput");
            mEditText.setText(length);
        }
    }

    private void onPrimeNumberLengthEntered() {
        boolean isOk = true;

        String numberLength = mEditText.getText().toString();

        if (TextUtils.isEmpty(numberLength)) {
            mEditText.setError("Required Field");
            isOk = false;
        }

        if (isOk) {
            int length = Integer.parseInt(numberLength);
            sendDataToSheetActivity(length);
        }
    }

    private void sendDataToSheetActivity(int numberLength) {
        Intent intent = new Intent(MainActivity.this, SheetActivity.class);
        intent.putExtra("numberLength", numberLength);
        startActivity(intent);
    }

    private void onRefreshLayout() {
        mEditText.setText("");
    }
}
