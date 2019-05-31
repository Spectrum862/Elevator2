import java.util.LinkedList;
public class InsertionSort {

    public static int minIndex(LinkedList<Integer> list,
                               int sortIndex)
    {
        int min_index = -1;
        int min_value = Integer.MAX_VALUE;
        int s = list.size();
        for (int i = 0; i < s; i++)
        {
            int current = list.peek();

            // This is dequeue() in Java STL
            list.poll();

            // we add the condition i <= sortIndex
            // because we don't want to traverse
            // on the sorted part of the queue,
            // which is the right part.
            if (current <= min_value && i <= sortIndex)
            {
                min_index = i;
                min_value = current;
            }
            list.add(current);
        }
        return min_index;
    }

    // Moves given minimum element
    // to rear of queue
    public static void insertMinToRear(LinkedList<Integer> list,
                                       int min_index)
    {
        int min_value = 0;
        int s = list.size();
        for (int i = 0; i < s; i++)
        {   
            
            int current = list.peek();
            list.poll();
            if(list.contains(current)) continue;
            if (i != min_index)
                list.add(current);
            else
                min_value = current;
        }
        list.add(min_value);
    }

    public static void sortQueue(LinkedList<Integer> list)
    {   
        for(int i = 1; i <= list.size(); i++)
        {
            int min_index = minIndex(list,list.size() - i);
            insertMinToRear(list, min_index);
        }
    }
    
    public static void sortQueueDES(LinkedList<Integer> list)
    {
        sortQueue(list);
        LinkedList<Integer> buff = new LinkedList<>();
        for(int i = list.size()-1; i >= 0; i--)
        {
            buff.add(list.removeLast());
        }
        for(int i = buff.size()-1; i >= 0; i--)
        {
            list.add(buff.removeFirst());
        }
    }
}
