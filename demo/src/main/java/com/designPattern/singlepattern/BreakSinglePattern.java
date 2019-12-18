package com.designPattern.singlepattern;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/17 17:57
 * @description 破解单例模式
 */
public class BreakSinglePattern {

    @Test
    public void reflectTest() throws Exception{
        //正常单例
        StaticInnerClassSinglePattern singlePattern1 = StaticInnerClassSinglePattern.getInstance();
        StaticInnerClassSinglePattern singlePattern2 = StaticInnerClassSinglePattern.getInstance();
        System.out.println(singlePattern1);
        System.out.println(singlePattern2);
        //反射破解
        Class cls = StaticInnerClassSinglePattern.class;
        Constructor con = cls.getDeclaredConstructor(null);
        con.setAccessible(true);//首先关闭安全检查，就可以调用私有的构造器
        StaticInnerClassSinglePattern singlePattern3 = (StaticInnerClassSinglePattern) con.newInstance();
        StaticInnerClassSinglePattern singlePattern4 = (StaticInnerClassSinglePattern) con.newInstance();
        System.out.println(singlePattern3);
        System.out.println(singlePattern4);
    }
    /**
     * 如何防止反射破解，只需要在无参构造函数中加上，实例判断逻辑，如果已经声明，则抛出异常
     * */



    @Test
    public void SerializableTest() throws Exception{
        //序列化、反序列化破解单例（前提是单例类实现了Serializable接口）
        DubleCheckSinglePattern instance1 = DubleCheckSinglePattern.getInstance();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(instance1);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        DubleCheckSinglePattern instance2 = (DubleCheckSinglePattern) in.readObject();
        System.out.println(instance1);
        System.out.println(instance2);
    }
    /**
     * 单例类中加入readResolve()方法，然后在方法体中返回我们的单例实例即可，
     * 因为readResolve()方法是在readObject()方法之后才被调用，因而它每次都会用我们自己生成的单实例替换从流中读取的对象。这样自然就保证了单例
     * 源码在ObjectInputStream类的readOrdinaryObject方法中
     * */



    /**
     * 枚举类天然防止反射和反序列化破解
     * 报错：NoSuchMethodException
     * */
    @Test
    public void reflectTestFault() throws Exception{
        //正常单例
        EnumSinglePattern singlePattern1 = EnumSinglePattern.getInstance();
        EnumSinglePattern singlePattern2 = EnumSinglePattern.getInstance();
        System.out.println(singlePattern1);
        System.out.println(singlePattern2);
        //反射破解
        Class cls = EnumSinglePattern.class;
        Constructor con = cls.getDeclaredConstructor(null);
        con.setAccessible(true);//首先关闭安全检查，就可以调用私有的构造器
        EnumSinglePattern singlePattern3 = (EnumSinglePattern) con.newInstance();
        EnumSinglePattern singlePattern4 = (EnumSinglePattern) con.newInstance();
        System.out.println(singlePattern3);
        System.out.println(singlePattern4);
    }

    @Test
    public void SerializableTestFault() throws Exception{
        //序列化、反序列化破解单例（前提是单例类实现了Serializable接口）
        EnumSinglePattern instance1 = EnumSinglePattern.getInstance();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(instance1);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        EnumSinglePattern instance2 = (EnumSinglePattern) in.readObject();
        System.out.println(instance1);
        System.out.println(instance2);
    }

}
