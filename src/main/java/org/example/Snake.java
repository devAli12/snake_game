package org.example;

import java.util.LinkedList;

public class Snake {

    private LinkedList<Cell> snakeList = new LinkedList<>();
    private Cell head ;

    public Snake(Cell firstCell){
        this.head=firstCell;
        snakeList.add(firstCell);
        firstCell.setCellType(CellType.SNAKE_NODE);
    }

    public void move(Cell nextCell){
        Cell tail = snakeList.removeLast();
        tail.setCellType(CellType.EMPTY);
        head=nextCell;
        nextCell.setCellType(CellType.SNAKE_NODE);
        snakeList.addFirst(nextCell);
    }

    public void grow(){
        snakeList.addFirst(head);
    }
    public boolean checkCrash(Cell nextCell){
        for(Cell cell:snakeList){
            if(cell==nextCell) return true;
        }
        return false;
    }

    public LinkedList<Cell> getSnakeList() {
        return snakeList;
    }

    public void setSnakeList(LinkedList<Cell> snakeList) {
        this.snakeList = snakeList;
    }

    public Cell getHead() {
        return head;
    }

    public void setHead(Cell head) {
        this.head = head;
    }
}
