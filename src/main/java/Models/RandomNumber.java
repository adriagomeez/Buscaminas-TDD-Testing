package Models;

public class RandomNumber {

    private int rows;
    private int cols;

    public RandomNumber(){
        rows = 0;
        cols = 0;
    }

    public void setSize(int row, int col) {
        this.rows = row;
        this.cols = col;
    }

    public int getRandomRow(){
        int a = (int)(Math.random()*rows);
        return a;
    }

    public int getRandomCol(){
        int a = (int)(Math.random()*cols);
        return a;
    }
}
