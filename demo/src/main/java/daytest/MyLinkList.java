package daytest;

import lombok.Data;
import org.junit.Test;

import java.util.Queue;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/9 19:51
 */
public class MyLinkList {
    private Node head;
    private Node end;
    private int size =0;

    @Data
    class Node{
        private int data;
        private Node next;
        private Node prev;

        public Node(int data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                "data=" + data +
                '}';
        }
    }

    public void insert(int e,int index){
        if (index<0 ||index>size){
            System.out.println("范围异常");
            return;
        }
        if (index ==size){
            //尾部插
            final Node l = end;
            Node newNode = new Node(e,null,end);
            end = newNode;
            if (l==null){
                head = newNode;
            }else {
                l.next = newNode;
            }
        }else {
            //指定位置插入
            //checkNode
            Node node = checkNode(index);
            final Node pred = node.prev;
            Node newNode =new Node(e,node,pred);
            node.prev = newNode;
            if (pred == null){
                head = newNode;
            }else {
                pred.next = newNode;
            }
        }
        size++;

    }

    public void remove(int index){
        if (index<0||index>size){
            System.out.println("范围异常");
            return;
        }
        Node node = checkNode(index);
        final Node pred = node.prev;
        final Node nextd = node.next;
        if (pred == null){
            head = nextd;
        }else {
            pred.next = nextd;
        }
    }

    public int get(int index){
        if (index<0||index>size){
            System.out.println("范围异常");
            throw new IndexOutOfBoundsException("范围异常");
        }
        return checkNode(index).data;

    }
    private Node checkNode(int index){
        if (index < (size>>1)){
            Node temp =head;
            for (int x=0;x < index;x++){
                temp = temp.next;
            }
            return temp;
        }else {
            Node temp =end;
            for (int x=size-1;x >index;x--){
                temp = temp.prev;
            }
            return temp;
        }
    }

    public void outPut(){
        Node temp = head;
        while (temp!=null){
            System.out.println(temp.data);
            temp =temp.next;
        }
    }

    @Test
    public void test(){
        MyLinkList myLinkList = new MyLinkList();
        myLinkList.insert(0,0);
        myLinkList.insert(1,1);
        myLinkList.insert(2,2);
        myLinkList.insert(3,3);
        myLinkList.insert(4,3);
        myLinkList.outPut();
        myLinkList.remove(3);
        myLinkList.outPut();
        System.out.println(myLinkList.get(1));
        System.out.println(myLinkList.get(6));
    }
}
