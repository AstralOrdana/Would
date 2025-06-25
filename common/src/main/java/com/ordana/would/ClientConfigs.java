package com.ordana.would;

import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ModConfigHolder;

import java.util.function.Supplier;

public class ClientConfigs {

    public static void init() {

    }

    public static ModConfigHolder CONFIG_SPEC;

    public static Supplier<Boolean> BOOLEAN_CONFIG;
    public static Supplier<Integer> INTEGER_CONFIG;

    static {
        ConfigBuilder builder = ConfigBuilder.create(Would.res("client"), ConfigType.CLIENT);

        builder.push("general");
        BOOLEAN_CONFIG = builder.comment("Boolean Config Name").define("boolean_config", false);

        INTEGER_CONFIG = builder.comment("Integer Config Name").define("integer_config", 16, 8, 512);
        builder.pop();

        CONFIG_SPEC = builder.build();
        CONFIG_SPEC.forceLoad();
    }

}