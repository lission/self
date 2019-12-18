package daytest;

import java.util.concurrent.ForkJoinPool;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/10 16:30
 * 通过数组实现栈
 * 栈是一种FILO先进后出的线性结构，只能从栈顶输出
 */
public class MyStack {

    private int[] array;
    private int top;
    private int bottom;

    public MyStack(int capital) {
        array = new int[capital];
    }

    public void push(int e){
        if (top == array.length){
            throw new IndexOutOfBoundsException("栈存储已满"+array.length);
        }
        array[top] = e;
        top++;
    }

    public int pop(){
        if (top < bottom){
            throw new IndexOutOfBoundsException("栈已空");
        }
        int temp =array[top-1];
        top--;
        return temp;
    }

    public void outPut(){
        for (int i=top-1;i>=bottom;i--){
            System.out.println(array[i]);
        }
    }


    public static void main(String[] args) {
        //
        /*MyStack myStack = new MyStack(3);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.outPut();
        System.out.println("------------------------");
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());*/
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ForkJoinPool forkJoinPool1 = ForkJoinPool.commonPool();
        ForkJoinPool forkJoinPool2 = new ForkJoinPool();
        System.out.println(forkJoinPool.equals(forkJoinPool1));
    }
}
