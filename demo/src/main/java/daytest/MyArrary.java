package daytest;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/2 19:58
 */
public class MyArrary {
    private int[] array;
    private int size;
    
    public MyArrary(int capcity) {
        this.array = new int[capcity];
        this.size = 0;
    }
    
    public void insert(int element,int index){
        if (index <0||index>size){
            System.out.println("参数异常");
            return;
        }
        if (index > array.length){
            System.out.println("超过数组最大容量");
            return;
        }
        for (int i=size-1;i>=index;i--){
            array[i+1] = array[i];
        }
        array[index]=element;
        size++;
    }
    
    public void outPut(){
        for (int i=0;i<size;i++){
            System.out.println(array[i]);
        }
    }
    public int getSize(){
        return size;
    }

    public static void main(String[] args) {
        MyArrary myArrary = new MyArrary(10);
        myArrary.insert(1,0);
        myArrary.insert(2,1);
        myArrary.insert(3,2);
        myArrary.insert(4,2);
        myArrary.insert(5,2);
        myArrary.insert(6,5);
        System.out.println("---------------"+myArrary.getSize());
        myArrary.outPut();
    }
}
