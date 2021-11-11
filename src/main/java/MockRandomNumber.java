import java.util.LinkedList;
import java.util.List;

public class MockRandomNumber extends RandomNumber{

    private List<Integer> num;

    public MockRandomNumber(){
        num = new LinkedList<Integer>();
    }

    public void setNum(int n){
        num.add(n);
    }
    public int getRandomRow(){
        int n = num.get(0);
        num.remove(0);
        return n;
    }

    public int getRandomCol(){
        int n = num.get(0);
        num.remove(0);
        return n;
    }
}
