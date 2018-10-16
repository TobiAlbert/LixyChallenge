package com.tobidaada.primetablechallenge;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tobidaada.primetablechallenge.utils.PrimeNumberGeneratorUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private TextView mTextView;
    private EditText mEditText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = findViewById(R.id.btn);
        mTextView = findViewById(R.id.tv);
        mEditText = findViewById(R.id.et);

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
                onRefreshLayout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
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
            mTextView.setText("");
            int length = Integer.parseInt(numberLength);
            generatePrimeNumbers(length);
        }
    }

    private void test() {


    }

    private List<TextView> generateTextViews(int length) {

        RecyclerView rv = new RecyclerView(this);
        RecyclerView.LayoutParams llm = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
        rv.setLayoutParams(llm);

        List<TextView> textViews = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            TextView textView = new TextView(this);
            textView.setId(i);
            textViews.add(textView);
        }

        return textViews;
    }

    private void generatePrimeNumbers(int numberLength) {
        List<Integer> primeNumberList = PrimeNumberGeneratorUtils.generatePrimeNumbers(numberLength);

        mTextView.append(" ");
        for (int primeNumber: primeNumberList) {
            mTextView.append(String.valueOf(primeNumber) + " ");
        }

        mTextView.append("\n");

        for (int i = 0; i < primeNumberList.size(); i++) {

            mTextView.append(primeNumberList.get(i) + " ");

            for (int j = 0; j < primeNumberList.size(); j++) {
                int mul = primeNumberList.get(i) * primeNumberList.get(j);
                mTextView.append(String.valueOf(mul) + " ");

            }

            mTextView.append("\n");
        }

    }

    private void onRefreshLayout() {
        mTextView.setText("");
        mEditText.setText("");
    }

}
