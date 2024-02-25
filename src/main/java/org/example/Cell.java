package org.example;

public class Cell {
    private final int row,col;
    private CellType cellType;

    public Cell(int x ,int y){
        this.row=x;
        this.col=y;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }
}
