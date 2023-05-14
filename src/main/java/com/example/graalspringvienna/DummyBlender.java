package com.example.graalspringvienna;

public class DummyBlender implements Blender {
    @Override
    public void run() {
        System.out.println("done");
    }
}
