package com.ordana.would.mixins.earlyrisers;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

public class CustomBoatEarlyRiser implements Runnable {

    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        String boatType = remapper.mapClassName("intermediary", "net.minecraft.class_1690$class_1692");
        String block = 'L' + remapper.mapClassName("intermediary", "net.minecraft.class_2248") + ';';
        ClassTinkerers.enumBuilder(boatType, block, "Ljava/lang/String;")
                .addEnum("WOULD_ASTER", () -> new Object[] {null, "would_aster"})
                .addEnum("WOULD_AZALEA", () -> new Object[] {null, "would_azalea"})
                .addEnum("WOULD_BAOBAB", () -> new Object[] {null, "would_baobab"})
                .addEnum("WOULD_CEDAR", () -> new Object[] {null, "would_cedar"})
                .build();
    }

}