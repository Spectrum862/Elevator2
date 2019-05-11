import java.lang.reflect.Array;

public class Human {
    private String direction;
    private int floor;
    //private int position;

    public Human(String direction, int floor){
        this.direction = direction;
        this.floor = floor;
    }
    /*public Human(String direction,int floor,int position){
        this.direction = direction;
        this.floor = floor;
        this.position = position;
    }*/

    /*public int getPosition(){
        return position;
    }*/

    public int getFloor() {
        return floor;
    }

    public String getDirection() {
        return direction;
    }

    public void displayHuman(){
        System.out.printf("\n%7s","Floor");
        System.out.printf("%6d", floor);
        System.out.printf("\n%7s","Direct");
        System.out.printf("%6s", direction);
        //System.out.printf("\n%7s","Position");
        //System.out.printf("%6d", position);
        System.out.println();
    }
}