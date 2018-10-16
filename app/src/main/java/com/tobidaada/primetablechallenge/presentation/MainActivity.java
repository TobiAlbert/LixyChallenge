package com.tobidaada.primetablechallenge.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.evrencoskun.tableview.TableView;
import com.tobidaada.primetablechallenge.R;
import com.tobidaada.primetablechallenge.adapters.TableViewAdapter;
import com.tobidaada.primetablechallenge.model.Cell;
import com.tobidaada.primetablechallenge.model.ColumnHeader;
import com.tobidaada.primetablechallenge.model.RowHeader;
import com.tobidaada.primetablechallenge.utils.PrimeNumberGeneratorUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private TableView mTableView;
    private FrameLayout mFrameLayout;
    private TableViewAdapter mAdapter;
    private List<RowHeader> mRowHeaderList = new ArrayList<>();
    private List<ColumnHeader> mColumnHeaderList = new ArrayList<>();
    private List<List<Cell>> mCellList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = findViewById(R.id.btn);
        mEditText = findViewById(R.id.et);

        mTableView = new TableView(this);
        mFrameLayout = findViewById(R.id.frameView);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);

        mTableView.setLayoutParams(layoutParams);
        mFrameLayout.addView(mTableView);

        mAdapter = new TableViewAdapter(getApplicationContext());

        mTableView.setAdapter(mAdapter);
        mAdapter.setAllItems(mColumnHeaderList, mRowHeaderList, mCellList);

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

            if (!TextUtils.isEmpty(length)) {
                generatePrimeNumbers(Integer.parseInt(length));
            }
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
            generatePrimeNumbers(length);
        }
    }

    private void generatePrimeNumbers(int numberLength) {
        List<Integer> primeNumberList = PrimeNumberGeneratorUtils.generatePrimeNumbers(numberLength);

        for (int number: primeNumberList) {
            mRowHeaderList.add(new RowHeader(String.valueOf(number), String.valueOf(number)));
            mColumnHeaderList.add(new ColumnHeader(String.valueOf(number), String.valueOf(number)));
        }

        for (int i = 0; i < primeNumberList.size(); i++) {

            List<Cell> cellList = new ArrayList<>();

            for (int j = 0; j < primeNumberList.size(); j++) {
                int product = primeNumberList.get(i) * primeNumberList.get(j);
                cellList.add(new Cell(i + "-" + j, String.valueOf(product)));
            }

            mCellList.add(cellList);
        }

        mTableView = new TableView(this);

        mFrameLayout = findViewById(R.id.frameView);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);

        mTableView.setLayoutParams(layoutParams);
        mFrameLayout.addView(mTableView);


        mAdapter.setAllItems(mColumnHeaderList, mRowHeaderList, mCellList);

    }

    private void onRefreshLayout() {

        mEditText.setText("");
        mFrameLayout.removeAllViews();

        /*mTableView = new TableView(this);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);

        mTableView.setLayoutParams(layoutParams);
        mFrameLayout.addView(mTableView);
*/

    }

}
