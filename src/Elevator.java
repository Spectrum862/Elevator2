import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Elevator implements Runnable{
    private LinkedList<Integer> queue;
    private int number;
    private String state;
    private int position;
    private LinkedList<Human> passengers;
    private int max_psg;
    private int flag; 

    public Elevator(int number){
        this.number = number;
        queue = new LinkedList<>();
        passengers = new LinkedList<>();
        state = "idle";
        position = 1;
        max_psg = 5;

    }
    
    public Elevator(int number,int maxpsg){
        this.number = number;
        queue = new LinkedList<>();
        passengers = new LinkedList<>();
        state = "idle";
        position = 1;
        max_psg = maxpsg;
    }
    
    @Override
    public void run() {
        
        while (true) {
            if(queue.isEmpty()==false){
                System.out.println(this.position);
                int index; 
                if ((this.getPosition() - this.getFisrt()) < 0) {
                    this.setState("up");
                    this.moveUp();
                }

                else  if ((this.getPosition() - this.getFisrt()) > 0) {
                            this.setState("down");
                            this.moveDown();
                            flag = 0;
                        }
                        else { //match destination
                            index =  this.getPosition()/Building.floorheight;
                            System.out.println("Arrive");        
                            sendHuman(this.position);
                            recieveHuman(this.state,this.position);
                            queue.removeFirst();
                            displayPSG();
                            if(queue.isEmpty())this.setState("idle");
                        }
            }    
            try {
                TimeUnit.SECONDS.sleep(Building.timeframe);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  
        }
    }
    
    public void sendHuman(int floor){
        int index = floor-1;
        LinkedList<Human> human = selectHuman(floor,passengers);
        for(int i = 0;i<human.size();i++){
            Building.floor[index].recievePSG(human.get(i));
        }
    }
    
    public void recieveHuman(String des,int floor){
        int index =  floor-1;
        LinkedList<Human> human = null;
        Floor target_floor = Building.floor[index];
        int freespace = max_psg-passengers.size();
        if(des.equals("up")){
            human = target_floor.selectHumanup(freespace);
        }
        if(des.equals("down")){
            human = target_floor.selectHumandown(freespace);
        }
        if(human!=null) 
            for(int i =0;i<human.size();i++)
                passengers.add(human.get(i));    
    }

    public  void  setState(String state){
        this.state = state;
    }
    
    public LinkedList<Human> getPassengers() {
        return passengers;
    } 

    public String getState() {
        return state;
    }

    public void addQueue(int floor){
        queue.add(floor);
        if(state.equals("up")) InsertionSort.sortQueue(queue);
        if(state.equals("down")) InsertionSort.sortQueueDES(queue);
    }

    public void addHuman(Human human){
        passengers.add(human);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position){
        this.position = position;
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
    
    public LinkedList<Human> selectHuman(int floor,LinkedList<Human> human){
        LinkedList<Human> buff = new LinkedList<>();
        LinkedList<Integer> tag = new LinkedList<>();
        for(int i = 0;i<human.size();i++){
            if (human.get(i).getFloor()==floor) {
                buff.add(human.get(i));
                tag.add(i);
            }
        }
        for(int i = tag.size()-1;i>=0;i--){
            int tagn = tag.get(i);
            passengers.remove(tagn);
        }
        return buff;
    }
  
    
    public void displayHuman(LinkedList<Human> human){
        System.out.printf("%7s","");
        for(int i = 0;i<human.size();i++) {
            System.out.printf("%6d",i);
        }
        System.out.printf("\n%7s","Floor");
        for(int i = 0;i<human.size();i++) System.out.printf("%6d", (human.get(i)).getFloor());
        System.out.printf("\n%7s","Direct");
        for(int i = 0;i<human.size();i++) System.out.printf("%6s", (human.get(i)).getDirection());
        System.out.println();
    }





}
