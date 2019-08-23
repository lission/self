package com.thinkinjava.genericity;

/**
 * @author lisong@mimidai.com
 * @date 2019/7/7 20:26
 * 通过泛型，继承生成器Generator接口，实现费波纳茨数列
 */
public class Fibonacci implements Generator<Integer>{
    private int count = 0;
    @Override
    public Integer next() {
        return fib(count++);
    }

    /**
     * 递归实现费波纳茨数列
     * */
    private int fib(int n){
        if (n < 2){
            return 1;
        }else {
            return fib(n-2)+fib(n-1);
        }
    }

    public static void main(String[] args) {
        Fibonacci gen = new Fibonacci();
        for (int i=0;i<20;i++){
            System.out.println(gen.next());
        }
    }
}
