package com.tobidaada.primetablechallenge.presentation.sheet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.evrencoskun.tableview.TableView;
import com.tobidaada.primetablechallenge.R;
import com.tobidaada.primetablechallenge.adapters.TableViewAdapter;
import com.tobidaada.primetablechallenge.model.Cell;
import com.tobidaada.primetablechallenge.model.ColumnHeader;
import com.tobidaada.primetablechallenge.model.RowHeader;
import com.tobidaada.primetablechallenge.utils.PrimeNumberGeneratorUtils;

import java.util.ArrayList;
import java.util.List;

public class SheetActivity extends AppCompatActivity {

    private TableViewAdapter mAdapter;
    private List<RowHeader> mRowHeaderList = new ArrayList<>();
    private List<ColumnHeader> mColumnHeaderList = new ArrayList<>();
    private List<List<Cell>> mCellList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);

        Toolbar toolbar = findViewById(R.id.toolbar_sheet);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TableView mTableView = findViewById(R.id.tableview_sheet);
        mAdapter = new TableViewAdapter(getApplicationContext());
        mTableView.setAdapter(mAdapter);

        Intent intent = getIntent();

        if (intent != null) {

            if (intent.hasExtra("numberLength")) {
                int numberLength = intent.getIntExtra("numberLength", 0);

                generatePrimeNumbers(numberLength);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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

        mAdapter.setAllItems(mColumnHeaderList, mRowHeaderList, mCellList);

    }
}
