package Models;

public class Square {

    private int row;
    private int col;
    private boolean mine;
    private boolean open;
    private int nearMines;

    public Square(int Row, int Col){
        row = Row;
        col = Col;
        mine = false;
        open = false;
        nearMines = 0;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public boolean isMine(){
        return mine;
    }

    public boolean isOpen(){
        return open;
    }

    public void setMine(){
        mine = true;
    }

    public void setOpen(){
        open = true;
    }

    public int getNearMines() {
        return nearMines;
    }

    public void increaseNearMines(){
        nearMines++;
    }

}
