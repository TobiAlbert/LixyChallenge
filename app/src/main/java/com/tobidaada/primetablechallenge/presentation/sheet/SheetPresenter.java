package com.tobidaada.primetablechallenge.presentation.sheet;

import com.tobidaada.primetablechallenge.model.Cell;
import com.tobidaada.primetablechallenge.model.ColumnHeader;
import com.tobidaada.primetablechallenge.model.RowHeader;
import com.tobidaada.primetablechallenge.utils.PrimeNumberGeneratorUtils;

import java.util.ArrayList;
import java.util.List;

public class SheetPresenter implements SheetContract.SheetPresenter {

    private SheetContract.SheetView mView;
    private List<RowHeader> mRowHeaderList = new ArrayList<>();
    private List<ColumnHeader> mColumnHeaderList = new ArrayList<>();
    private List<List<Cell>> mCellList = new ArrayList<>();

    public SheetPresenter(SheetContract.SheetView view) {
        mView = view;
    }

    @Override
    public void generatePrimeNumbers(int numberLength) {
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

        mView.displayResults(mColumnHeaderList, mRowHeaderList, mCellList);

    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
