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
        return (int)Math.random()*rows;
    }

    public int getRandomCol(){
        return (int)Math.random()*cols;
    }
}
