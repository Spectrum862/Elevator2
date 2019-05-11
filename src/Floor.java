import java.util.LinkedList;

public class Floor {
    private LinkedList<Human> queue_up;
    private LinkedList<Human> queue_down;
    private int floornum;

    public Floor(int floornum){
        this.queue_down = new LinkedList<>();
        this.queue_up = new LinkedList<>();
        this.floornum = floornum;
    }

    public int getFloornum() {
        return floornum;
    }

    public void addHumanUp(Human human){
        queue_up.add(human);
    }

    public void addHumanDown(Human human){
        queue_down.add(human);
    }

    public Human sendHumanup(int index){
        Human buff = queue_up.get(index);
        queue_up.remove(index);
        return buff;
    }

    public Human sendHumandown(int index){
        Human buff = queue_down.get(index);
        queue_down.remove(index);
        return buff;
    }

    public void displayQueueup(){
        System.out.printf("%7s","");
        for(int i = 0;i<queue_up.size();i++) {
            System.out.printf("%6d",i);
        }
        System.out.printf("\n%7s","Floor");
        for(int i = 0;i<queue_up.size();i++) System.out.printf("%6d", (queue_up.get(i)).getFloor());
        System.out.printf("\n%7s","Direct");
        for(int i = 0;i<queue_up.size();i++) System.out.printf("%6s", (queue_up.get(i)).getDirection());
    }

    public void displayQueuedown(){
        System.out.printf("%7s","");
        for(int i = 0;i<queue_down.size();i++) {
            System.out.printf("%6d",i);
        }
        System.out.printf("\n%7s","Floor");
        for(int i = 0;i<queue_down.size();i++) System.out.printf("%6d", (queue_down.get(i)).getFloor());
        System.out.printf("\n%7s","Direct");
        for(int i = 0;i<queue_down.size();i++) System.out.printf("%6s", (queue_down.get(i)).getDirection());
    }



}

