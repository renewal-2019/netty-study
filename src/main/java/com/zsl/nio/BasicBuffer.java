package com.zsl.nio;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        //创建一个buffer,大小为5,可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //向buffer中存放数据
        for(int i = 0; i<intBuffer.capacity();i++){
            intBuffer.put(i*2);
        }

        //从buffer中读取数据
        //将buffer转换,读写切换
        //public Buffer flip() {
        //        limit = position;
        //        position = 0;
        //        mark = -1;
        //        return this;
        //    }
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
