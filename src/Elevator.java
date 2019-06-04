import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        position = Building.firstfloorPosi;;
        max_psg = 12;

    }
    
    public Elevator(int number,int maxpsg){
        this.number = number;
        queue = new LinkedList<>();
        passengers = new LinkedList<>();
        state = "idle";
        position = Building.firstfloorPosi;
        max_psg = maxpsg;
    }
    
    @Override
    public void run() {
        
        while (true) {
                int index; 
                if(queue.isEmpty()==false){
                    if ((this.getPosition() - this.getFirst()) > 0) {
                        this.setState("up");
                        this.moveUp();
                    }

                    else    if ((this.getPosition() - this.getFirst()) < 0) {
                                this.setState("down");
                                this.moveDown();
                            }
                            else if ((this.getPosition() == this.getFirst())){ //match destination                              
                        try {
                            TimeUnit.MICROSECONDS.sleep(Building.timeframe*200); //Elevator open time
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Elevator.class.getName()).log(Level.SEVERE, null, ex);
                        }                                
                                sendHuman(this.position);
                                recieveHuman(this.state,this.position);
                                queue.removeFirst(); 
                                if(queue.isEmpty()) this.setState("idle");



                            }
                }
                else if(queue.isEmpty()){
                    try {
                        TimeUnit.MICROSECONDS.sleep(Building.timeframe*200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Elevator.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                    int floorindex = (Building.firstfloorPosi-this.getPosition())/Building.floorheight;
                    if(!Building.floor[floorindex].isQDownEmpty())this.setState("down");
                    else if(!Building.floor[floorindex].isQUpEmpty()) this.setState("up");
                    recieveHuman(this.state,this.position);
                }                
                           
            try {
                TimeUnit.MICROSECONDS.sleep(Building.timeframe);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  
        }
    }
    
    public int getPsize(){
        return passengers.size();
    }
    
    public void sendHuman(int position){
        int index = (Building.firstfloorPosi-position)/Building.floorheight;
        LinkedList<Human> human = selectHuman(index+1,passengers);
        for(int i = 0;i<human.size();i++){
            Building.floor[index].recievePSG(human.get(i));
        }
    }
    
    public void recieveHuman(String state,int position){
        int index =  (Building.firstfloorPosi-position)/Building.floorheight;
        LinkedList<Human> human = null;
        Floor target_floor = Building.floor[index];
        int freespace = max_psg-passengers.size();
        
        if(state.equals("up")){
            human = target_floor.selectHumanup(freespace);
        }
        else if(state.equals("down")){
                    human = target_floor.selectHumandown(freespace);
                }
        
        if(human!=null) 
            for(int i =0;i<human.size();i++){
                this.addHuman(human.get(i));
                this.addQueue(human.get(i).getFloor());
            }
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

    public synchronized void addQueue(int floor){
        floor = Building.firstfloorPosi-(Building.floorheight*(floor-1));
        queue.addLast(floor);
        if(state.equals("up")) InsertionSort.sortQueueDES(queue);
        if(state.equals("down")) InsertionSort.sortQueue(queue);
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

    public boolean isQueueEmpty(){
        return queue.isEmpty();
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
        position -= Building.scale;
    }

    public void moveDown(){
        position += Building.scale;
        
    }

    public synchronized int getFirst(){
        return queue.getFirst();
    }
    
    public synchronized int getFirstFloor(){
        return ((Building.firstfloorPosi-queue.getFirst())/Building.floorheight)+1;
    }
    
    public int FloornumPosi(){
        return ((Building.firstfloorPosi-this.position)/Building.floorheight)+1;
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
    

    
    public synchronized String displayQueue(){
        String buff;
        buff = " ";
        for(int i = 0;i<queue.size();i++) buff = buff+" "+(((Building.firstfloorPosi-queue.get(i))/Building.floorheight)+1);
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
