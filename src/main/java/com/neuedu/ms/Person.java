package com.neuedu.ms;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 单例-懒汉式
 * */
public class Person {
   private int  age=20;
    private  volatile static  Person mPerson;
     private Person(){}

     public    static  Person getInstance(){
         if(mPerson==null){
             synchronized (Person.class){
                 if(mPerson==null){
                     mPerson=new Person();
                 }
             }
         }
          return mPerson;
     }

    public static void main(String[] args) {
        //  java怎么创建对象  new +构造方法； 反射; 序列化/反序列化
        try {
            Class c=Class.forName("com.neuedu.ms.Person");
            Person person=(Person) c.newInstance();
            //System.out.println(person.age);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


        Date date= new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date));


    }

}
