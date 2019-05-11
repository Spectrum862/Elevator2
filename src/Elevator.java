
import java.util.LinkedList;

public class Elevator {
    private LinkedList<Integer> queue;
    private int number;
    private String state;
    private double position;
    private LinkedList<Human> passengers;
    private int max_psg;

    public Elevator(int number){
        this.number = number;
        queue = new LinkedList<>();
        passengers = new LinkedList<>();
        state = "idle";
        position = 1;
        max_psg = 5;
    }

    public  void  setState(String state){
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void addQueue(int floor){
        queue.addFirst(floor);
    }

    public void addHuman(Human human){
        passengers.add(human);
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public Human sendHuman(int index){
        Human buff = passengers.get(index);
        passengers.remove(index);
        return buff;
    }

    public void displayPSG(){
        System.out.printf("%7s","");
        for(int i = 0;i<passengers.size();i++) {
            System.out.printf("%6d",i);
        }
        System.out.printf("\n%7s","Floor");
        for(int i = 0;i<passengers.size();i++) System.out.printf("%6d", (passengers.get(i)).getFloor());
        System.out.printf("\n%7s","Direct");
        for(int i = 0;i<passengers.size();i++) System.out.printf("%6s", (passengers.get(i)).getDirection());
        System.out.println();
    }

    public void recieveHuman(){}

    public boolean isFull(){
        if(passengers.size()==max_psg) return true;
        else return false;
    }

    public void moveUp(){
        position += Building.scale;
    }

    public void moveDown(){
        position -= Building.scale;
    }

    public int getFisrt(){
        return queue.getFirst();
    }





}
