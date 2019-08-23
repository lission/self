package com.thinkinjava.eunmTest;

import java.util.EnumSet;

/**
 * @author lisong@mimidai.com
 * @date 2019/7/3 16:12
 * enum 常量相关方法及EnumSet使用案例
 */
public class CarWash {
    public enum Cycle{
        UNDERBODY{
            @Override
            void action(){
                System.out.println("Spraying the underbody");
            }
        },
        WHEELWASH{
            @Override
            void action(){
                System.out.println("Washing the wheels");
            }
        },
        PREWASH{
            @Override
            void action(){
                System.out.println("Lossening the dirt");
            }
        };
        abstract void action();
    }

    public EnumSet<Cycle> cycles = EnumSet.of(Cycle.PREWASH, Cycle.UNDERBODY);

    public void add(Cycle cycle){
        cycles.add(cycle);
    }
    @Override
    public String toString(){
        return cycles.toString();
    }

    public void washCar(){
        for (Cycle cycle:cycles){
            cycle.action();
        }
    }
    public static void main(String[] args) {
        CarWash wash = new CarWash();
        System.out.println(wash);
        wash.washCar();
        wash.add(Cycle.UNDERBODY);
        wash.add(Cycle.WHEELWASH);
        System.out.println(wash);
        wash.washCar();
    }
}
