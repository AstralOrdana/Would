package com.ordana.would.reg;

import com.ordana.would.Would;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ModCreativeTab {

    public static final RegSupplier<CreativeModeTab> MOD_TAB =
            RegHelper.registerCreativeModeTab(Would.res("would"),
                    (c) -> c.title(Component.translatable("itemGroup.would.would"))
                            .icon(() -> ModBlocks.EBONY_LOG.get().asItem().getDefaultInstance()));

    public static void init() {
        RegHelper.addItemsToTabsRegistration(ModCreativeTab::registerItemsToTabs);
    }


    public static void registerItemsToTabs(RegHelper.ItemToTabEvent e) {
        after(e, Items.OAK_LOG,
                ModBlocks.WILLOW_LOG, ModBlocks.WILLOW_PLANKS, ModBlocks.WILLOW_LEAVES,
                ModBlocks.BAOBAB_LOG, ModBlocks.BAOBAB_PLANKS, ModBlocks.BAOBAB_LEAVES,
                ModBlocks.EBONY_LOG, ModBlocks.EBONY_PLANKS, ModBlocks.EBONY_LEAVES,
                ModBlocks.FIR_LOG, ModBlocks.FIR_PLANKS, ModBlocks.FIR_LEAVES,
                ModBlocks.PINE_LOG, ModBlocks.PINE_PLANKS, ModBlocks.PINE_LEAVES,
                ModBlocks.CEDAR_LOG, ModBlocks.CEDAR_PLANKS, ModBlocks.CEDAR_LEAVES,
                ModBlocks.MAHOGANY_LOG, ModBlocks.MAHOGANY_PLANKS, ModBlocks.MAHOGANY_LEAVES,
                ModBlocks.AZALEA_LOG, ModBlocks.AZALEA_PLANKS, ModBlocks.AZALEA_LEAVES,
                ModBlocks.PALM_LOG, ModBlocks.PALM_PLANKS, ModBlocks.PALM_LEAVES,
                ModBlocks.MAPLE_LOG, ModBlocks.MAPLE_PLANKS, ModBlocks.MAPLE_LEAVES,
                ModBlocks.ASPEN_LOG ,ModBlocks.ASPEN_LOG_GAZING, ModBlocks.ASPEN_PLANKS, ModBlocks.ASPEN_LEAVES,
                ModBlocks.WALNUT_LOG, ModBlocks.WALNUT_PLANKS, ModBlocks.WALNUT_LEAVES);
    }

    private static void after(RegHelper.ItemToTabEvent event, Item target, Supplier<?>... items) {

        ItemLike[] entries = Arrays.stream(items).map((s -> (ItemLike) (s.get()))).toArray(ItemLike[]::new);
        var tab = MOD_TAB.getHolder().unwrapKey().get();
        event.addAfter(tab, i -> i.is(target), entries);
    }

    private static void before(RegHelper.ItemToTabEvent event, Item target, Supplier<?>... items) {

        ItemLike[] entries = Arrays.stream(items).map(s -> (ItemLike) s.get()).toArray(ItemLike[]::new);
        var tab = MOD_TAB.getHolder().unwrapKey().get();
        event.addBefore(tab, i -> i.is(target), entries);
    }
}
