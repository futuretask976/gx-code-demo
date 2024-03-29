package com.gx.code.utils.spi;

public class SpiInstA implements SpiInterface {
    @Override
    public void sayHello() {
        System.out.println("SpiInstA sayHello");
    }
}
