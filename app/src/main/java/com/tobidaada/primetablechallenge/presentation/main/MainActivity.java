package com.tobidaada.primetablechallenge.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tobidaada.primetablechallenge.R;
import com.tobidaada.primetablechallenge.presentation.sheet.SheetActivity;

public class MainActivity extends AppCompatActivity implements MainContract.MainView{

    private EditText mEditText;
    private MainContract.MainPresenter mainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        mainPresenter = new MainPresenter(this); // get a reference to the presenter

        if (savedInstanceState != null) {
            mEditText.setText(savedInstanceState.getString("userInput"));
        }

    }

    private void initializeViews() {
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
                refreshLayout();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }

    private void onPrimeNumberLengthEntered() {
        // send user input to the presenter for verification
        mainPresenter.verifyUserInput(mEditText.getText().toString());
    }

    @Override
    public void sendDataToSheetActivity(int numberLength) {
        Intent intent = new Intent(MainActivity.this, SheetActivity.class);
        intent.putExtra("numberLength", numberLength);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage() {
        mEditText.setError(getString(R.string.edit_text_error_message));
    }

    @Override
    public void refreshLayout() {
        mEditText.setText("");
    }

}
