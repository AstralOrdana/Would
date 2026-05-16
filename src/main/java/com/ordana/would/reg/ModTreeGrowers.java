package com.ordana.would.reg;

import com.ordana.would.Would;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

public interface ModTreeGrowers {

    TreeGrower COCONUT = createSimple("palm");
    TreeGrower WILLOW = createSimple("willow");
    TreeGrower BAOBAB = new TreeGrower("baobab", createKey("baobab"), Optional.empty(), Optional.empty());
    TreeGrower EBONY = createSimple("ebony");
    TreeGrower FIR = new TreeGrower("fir", createKey("mega_fir"), createKey("fir"), Optional.empty());
    TreeGrower PINE = new TreeGrower("pine", createKey("mega_pine"), createKey("pine"), Optional.empty());
    TreeGrower CEDAR = createSimple("cedar");
    TreeGrower MAHOGANY = createSimple("mahogany");
    TreeGrower MAPLE = createSimple("maple");
    TreeGrower ASPEN = createSimple("aspen");
    TreeGrower WALNUT = createSimple("walnut");
    TreeGrower BLUE_SPRUCE = new TreeGrower("blue_spruce", createKey("mega_blue_spruce"), createKey("blue_spruce"), Optional.empty());
    TreeGrower BALD_CYPRESS = createSimple("bald_cypress");

    private static TreeGrower createSimple(String name) {
        return new TreeGrower(name, Optional.empty(), createKey(name), Optional.empty());
    }

    private static Optional<ResourceKey<ConfiguredFeature<?, ?>>> createKey(String name) {
        return Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, Would.res(name)));
    }

    static void init() {}

}
