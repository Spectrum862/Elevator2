import java.util.LinkedList;

public class Floor {
    private LinkedList<Human> queue_up;
    private LinkedList<Human> queue_down;
    private LinkedList<Human> arrived_psg;
    private int floornum;
    

    public Floor(int floornum){
        this.queue_down = new LinkedList<>();
        this.queue_up = new LinkedList<>();
        this.arrived_psg = new LinkedList<>();
        this.floornum = floornum;
    }
    

    public int getFloornum() {
        return floornum;
    }

    public void addHumanUp(Human human){
        queue_up.addLast(human);
    }

    public void addHumanDown(Human human){
        queue_down.addLast(human);
    }
    
    public boolean isQUpEmpty(){
       return queue_up.isEmpty();
    }
    
    public boolean isQDownEmpty(){
       return queue_down.isEmpty();
    }
    

    public synchronized LinkedList<Human> selectHumanup(int freespace){    
        if(queue_up.isEmpty() == false){ 
            LinkedList<Human> buff = new LinkedList<>();
            int queuesize = queue_up.size();
            for(int i = 0;i<freespace && i<queuesize;i++) {
                buff.add(queue_up.removeFirst());
            }
            return buff;
        }
        return null;
    }
    
    public synchronized LinkedList<Human> selectHumandown(int freespace){
        if(queue_down.isEmpty()== false){
            LinkedList<Human> buff = new LinkedList<>();
            int queuesize = queue_down.size();
            for(int i = 0;i<freespace && i < queuesize ;i++)buff.add(queue_down.removeFirst());
            return buff;
        }
        return null;
    }

    public LinkedList<Human> getQueue_down() {
        return queue_down;
    }

    public LinkedList<Human> getQueue_up() {
        return queue_up;
    }
    
    
    
    public void recievePSG(Human human){
        this.arrived_psg.add(human);
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
        System.out.println("");
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
        System.out.println("");
    }
    
    public void displayRecievePSG(){
        System.out.printf("%7s","");
        for(int i = 0;i<arrived_psg.size();i++) {
            System.out.printf("%6d",i);
        }
        System.out.printf("\n%7s","Floor");
        for(int i = 0;i<arrived_psg.size();i++) System.out.printf("%6d", (arrived_psg.get(i)).getFloor());
        System.out.printf("\n%7s","Direct");
        for(int i = 0;i<arrived_psg.size();i++) System.out.printf("%6s", (arrived_psg.get(i)).getDirection());
        
    }



}

