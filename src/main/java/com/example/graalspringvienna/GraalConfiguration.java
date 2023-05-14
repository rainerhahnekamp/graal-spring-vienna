package com.example.graalspringvienna;

import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.context.annotation.Profile;

@Configuration(proxyBeanMethods = false)
@ImportRuntimeHints(GraalConfiguration.BlenderRegistrar.class)
public class GraalConfiguration {
    @Bean
    public Blender getDefaultBlender() {
        if ("prod".equals(System.getenv("GRAAL_BLENDER"))) {
            return new BlenderImpl();
        }
        return new DummyBlender();
    }

    public static class BlenderRegistrar implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            hints.reflection().registerConstructor(BlenderImpl.class.getDeclaredConstructors()[0], ExecutableMode.INVOKE);
        }
    }
}
