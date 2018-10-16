package com.tobidaada.primetablechallenge.presentation.sheet;

import com.tobidaada.primetablechallenge.model.Cell;
import com.tobidaada.primetablechallenge.model.ColumnHeader;
import com.tobidaada.primetablechallenge.model.RowHeader;

import java.util.List;

public interface SheetContract {

    interface SheetView {
        void displayResults(List<ColumnHeader> columnHeaders,
                            List<RowHeader> rowHeaders,
                            List<List<Cell>> cellList);
    }

    interface SheetPresenter {
        void generatePrimeNumbers(int numberLength);
        void onDestroy();
    }
}
