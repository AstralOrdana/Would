package com.ordana.would;

import com.ordana.would.reg.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Would {

    public static final String MOD_ID = "would";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier res(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }

}