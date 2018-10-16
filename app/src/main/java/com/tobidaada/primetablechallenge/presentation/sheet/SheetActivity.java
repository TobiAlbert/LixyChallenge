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

import java.util.List;

public class SheetActivity extends AppCompatActivity implements SheetContract.SheetView {

    private TableViewAdapter mAdapter;
    private SheetContract.SheetPresenter mSheetPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);

        initializeViews();

        mSheetPresenter = new SheetPresenter(this);

        Intent intent = getIntent();

        if (intent != null) {

            if (intent.hasExtra("numberLength")) {
                int numberLength = intent.getIntExtra("numberLength", 0);

                mSheetPresenter.generatePrimeNumbers(numberLength);
            }
        }
    }

    private void initializeViews() {
        Toolbar toolbar = findViewById(R.id.toolbar_sheet);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TableView mTableView = findViewById(R.id.tableview_sheet);
        mAdapter = new TableViewAdapter(getApplicationContext());
        mTableView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSheetPresenter.onDestroy();
    }

    @Override
    public void displayResults(List<ColumnHeader> columnHeaders, List<RowHeader> rowHeaders, List<List<Cell>> cellList) {
        mAdapter.setAllItems(columnHeaders, rowHeaders, cellList);
    }
}
