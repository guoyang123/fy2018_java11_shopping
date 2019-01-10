package com.neuedu.ms;

import com.neuedu.pojo.Product;

/**
 * 商品秒杀类
 * */
public class ProductS {

    private  int  stock=10000;
    private  int   stockskill=0; // 9900+stockskill=10000


    /**
     * 秒杀方法
     * 10000
     *
     * */
    public   void  skill(){

        synchronized (this){
            if(this.stockskill<100){

                try {
                    Thread.sleep(10);
                    this.stock--; // 9999 9998
                    this.stockskill++; // 1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }


    }

    public static void main(String[] args) {


        ProductS productS=new ProductS();
        for(int i=0;i<10000;i++){
            Thread thread=new Thread(new Customer(productS));
            thread.start();
        }


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("剩余库存"+productS.stock+" 秒杀的商品数:"+productS.stockskill);

    }

}

/**
 * 用户
 * */
class  Customer implements  Runnable{

    ProductS productS;

    public Customer(ProductS productS){
        this.productS=productS;
    }

    @Override
    public void run() {
        productS.skill();
    }
}


