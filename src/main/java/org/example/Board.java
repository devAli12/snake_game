package org.example;

public class Board {
    private final int ROW_COUNT,COL_COUNT;
    private Cell[][] cells ;

    public Board(int rowCount , int colCount){
        this.ROW_COUNT=rowCount;
        this.COL_COUNT=colCount;
        cells = new Cell[ROW_COUNT][COL_COUNT];
        for(int row=0;row<cells.length;row++){
            for(int col=0;col< cells[0].length;col++){
                cells[row][col]= new Cell(row,col);
                cells[row][col].setCellType(CellType.EMPTY);
            }
        }
       generateFood();
    }

    public void generateFood() {
        int row = 0, column = 0;
        while (true) {
            row = (int)(Math.random() * ROW_COUNT);
            column = (int)(Math.random() * COL_COUNT);
            if (cells[row][column].getCellType() != CellType.SNAKE_NODE)
                break;
        }
        cells[row][column].setCellType(CellType.FOOD);
    }

    public int getROW_COUNT() {
        return ROW_COUNT;
    }

    public int getCOL_COUNT() {
        return COL_COUNT;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
}
