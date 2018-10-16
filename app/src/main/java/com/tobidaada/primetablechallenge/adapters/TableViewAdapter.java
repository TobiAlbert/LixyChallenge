package com.tobidaada.primetablechallenge.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.tobidaada.primetablechallenge.R;
import com.tobidaada.primetablechallenge.model.Cell;
import com.tobidaada.primetablechallenge.model.ColumnHeader;
import com.tobidaada.primetablechallenge.model.RowHeader;

public class TableViewAdapter extends AbstractTableAdapter<ColumnHeader, RowHeader, Cell> {

    private Context mContext;

    public TableViewAdapter(Context context) {
        super(context);
        mContext = context;
    }

    class CellViewHolder extends AbstractViewHolder {

        public final TextView cellTextView;

        public CellViewHolder(View itemView) {
            super(itemView);
            cellTextView = itemView.findViewById(R.id.tv_cell_data);
        }
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_cell_data_layout, parent, false);

        return new CellViewHolder(rootView);
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int columnPosition, int rowPosition) {

        Cell cell = (Cell) cellItemModel;
        CellViewHolder viewHolder = (CellViewHolder) holder;
        viewHolder.cellTextView.setText( (String) cell.getmData());

        // For auto-sizing
        viewHolder.itemView.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        viewHolder.cellTextView.requestLayout();

    }

    @Override
    public int getCellItemViewType(int position) {
        return 0;
    }

    class ColumnHeaderViewHolder extends AbstractViewHolder {

        public final TextView cellTextView;

        public ColumnHeaderViewHolder(View itemView) {
            super(itemView);

            cellTextView = itemView.findViewById(R.id.tv_column_data);
        }
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_column_header_layout, parent, false);
        return new ColumnHeaderViewHolder(rootView);
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel, int columnPosition) {

        ColumnHeader columnHeader = (ColumnHeader) columnHeaderItemModel;
        ColumnHeaderViewHolder viewHolder = (ColumnHeaderViewHolder) holder;

        viewHolder.cellTextView.setText( (String) columnHeader.getmData());

    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        return 0;
    }

    class RowHeaderViewHolder extends AbstractViewHolder {

        public final TextView cellTextView;

        public RowHeaderViewHolder(View itemView) {
            super(itemView);

            cellTextView = itemView.findViewById(R.id.tv_row_data);
        }
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_row_header_layout, parent, false);

        return new RowHeaderViewHolder(rootView);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int rowPosition) {
        RowHeader rowHeader = (RowHeader) rowHeaderItemModel;
        RowHeaderViewHolder viewHolder = (RowHeaderViewHolder) holder;

        viewHolder.cellTextView.setText((String) rowHeader.getmData());
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }


    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.custom_corner_layout, null);
    }
}
