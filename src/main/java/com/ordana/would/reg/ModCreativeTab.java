package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.WouldPlatform;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

public class ModCreativeTab {

    public static final CreativeModeTab MOD_TAB =
            WouldPlatform.INSTANCE.registerCreativeModeTab(Would.res("would"), Component.translatable("itemGroup.would.would"), ()-> ModBlocks.EBONY_LOG.asItem().getDefaultInstance(), (itemDisplayParameters, output)->{
                for (WouldType wouldType : ModWoodTypes.ALL) {
                    acceptWoodType(wouldType, output);
                }
                output.accept(ModBlocks.EBONY_LEAVES_FRUITING);
                output.accept(ModBlocks.EBONY_HEARTWOOD);
                output.accept(ModBlocks.MAPLE_LOG_SAPPY);
                output.accept(ModBlocks.MAPLE_WOOD_SAPPY);
                output.accept(ModItems.WALNUT);
                output.accept(ModItems.SYRUP_BOTTLE);
            });

    private static void acceptWoodType(WouldType wouldType, CreativeModeTab.Output output) {
        output.accept(wouldType.log());
        output.accept(wouldType.wood());
        output.accept(wouldType.strippedLog());
        output.accept(wouldType.strippedWood());
        output.accept(wouldType.planks());
        output.accept(wouldType.stairs());
        output.accept(wouldType.slabBlock());
        output.accept(wouldType.fenceBlock());
        output.accept(wouldType.fenceGateBlock());
        output.accept(wouldType.door());
        output.accept(wouldType.trapDoorBlock());
        output.accept(wouldType.pressurePlate());
        output.accept(wouldType.button());
        output.accept(wouldType.shelfBlock());
        output.accept(wouldType.boatItem());
        output.accept(wouldType.chestBoatItem());
        output.accept(wouldType.signItem());
        output.accept(wouldType.hangingSignItem());
        if (wouldType.sapling() != null)
            output.accept(wouldType.sapling());
        if (wouldType.leaves() != null)
            output.accept(wouldType.leaves());
    }


    public static void init() {

    }


}
