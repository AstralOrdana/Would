package com.ordana.would.neoforge;

import com.ordana.would.Would;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Would.MOD_ID)
public class WouldNeoForge {
    public static final String MOD_ID = Would.MOD_ID;

    public WouldNeoForge(IEventBus eventBus) {
        Would.commonInit();
    }
}

