package daytest;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/10 14:28
 * 使用数组实现队列
 * FIFO的线性结构，只能从队头出，从队尾入
 */
public class MyQueue {
    private int[] array;
    private int head;
    private int last;
    private int count;

    public MyQueue(int capital) {
        this.array = new int[capital];
        this.head = 0;
        this.last = 0;
        this.count = 0;
    }

    /*
    * 队尾占用一个数组位，队列实际大小等于capital-1
    * */
    public void enqueue(int e){
        /*if (count == array.length){
            System.out.println("队列已满");
            return;
        }*/
        if ((last+1)%array.length == head){
            System.out.println("队列已满");
            return;
        }


        array[last] = e;
        last = (last+1)%array.length;
        count++;
    }

    public int dequeue(){
        /*if (count ==0){
            throw new IndexOutOfBoundsException("队列已空");
        }*/
        if (last ==head){
            throw new IndexOutOfBoundsException("队列已空");
        }
        int temp = array[head];
        head = (head+1)%array.length;
        count--;
        return temp;
    }

    public void outPut(){
        /*for (int i=0;i<count;i++){
            System.out.println(array[i]);
        }*/
        for (int i=head;i!=last;i=(i+1)%array.length){
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        //
        MyQueue myQueue = new MyQueue(3);
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.enqueue(3);
        myQueue.outPut();
        System.out.println("------------------------");
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
    }
}
