package com.example.graalspringvienna;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("graal")
public class GraalController {
    private final Blender blender;

    public GraalController(Blender blender) {
        this.blender = blender;
    }

    @GetMapping
    public void index() throws Exception {
        var blender = (Blender) Class.forName(System.getenv("GRAAL_BLENDER")).getDeclaredConstructor().newInstance();
        for (int i = 0; i < 10; i++) {

            long start = System.nanoTime();
            for (int j = 0; j < 100; j++) {
                blender.run();
            }
            long end = System.nanoTime();
            System.out.println(String.format("%d ms", (end - start) / 1_000_000));
        }
    }
}
