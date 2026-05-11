package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.items.CoconutItem;
import com.ordana.would.items.SyrupBottleItem;
import com.ordana.would.items.WalnutItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public interface ModItems {

    List<Item> ALL_SIGNS = Lists.newArrayList();
    List<Item> ALL_HANGING_SIGNS = Lists.newArrayList();
    List<BoatItem> ALL_BOATS = Lists.newArrayList();
    List<BoatItem> ALL_CHEST_BOATS = Lists.newArrayList();

    static void init() {
    }

    static <T extends Item> T regItem(String name, T itemSup) {
        return Registry.register(BuiltInRegistries.ITEM, Would.res(name), itemSup);
    }
    
    private static Item sign(String name, Block standingVariantSupplier, Block wallVariantSupplier) {
        Item item = new SignItem(standingVariantSupplier, wallVariantSupplier, new Item.Properties().stacksTo(16));
        ALL_SIGNS.add(item);

        return regItem(name, item);
    }

    private static Item hangingSign(String name, Block ceilingVariantSupplier, Block wallVariantSupplier) {
        Item item = new HangingSignItem(ceilingVariantSupplier, wallVariantSupplier, new Item.Properties().stacksTo(16));
        ALL_HANGING_SIGNS.add(item);

        return regItem(name, item);
    }

    private static Item boat(String name, EntityType<Boat> type, boolean chest) {
        BoatItem item = new BoatItem(chest, type, new Item.Properties().stacksTo(1));
        (chest ? ALL_CHEST_BOATS : ALL_BOATS).add(item);

        return regItem(name, item);
    }
    
    
    //signs
    Item ASPEN_SIGN = regItem("aspen_sign", 
            sign(ModBlocks.ASPEN_SIGN, ModBlocks.ASPEN_WALL_SIGN));
    Item AZALEA_SIGN = regItem("azalea_sign", 
            sign(ModBlocks.AZALEA_SIGN, ModBlocks.AZALEA_WALL_SIGN));
    Item BAOBAB_SIGN = regItem("baobab_sign", 
            sign(ModBlocks.BAOBAB_SIGN, ModBlocks.BAOBAB_WALL_SIGN));
    Item CEDAR_SIGN = regItem("cedar_sign", 
            sign(ModBlocks.CEDAR_SIGN, ModBlocks.CEDAR_WALL_SIGN));
    Item EBONY_SIGN = regItem("ebony_sign", 
            sign(ModBlocks.EBONY_SIGN, ModBlocks.EBONY_WALL_SIGN));
    Item FIR_SIGN = regItem("fir_sign", 
            sign(ModBlocks.FIR_SIGN, ModBlocks.FIR_WALL_SIGN));
    Item MAHOGANY_SIGN = regItem("mahogany_sign", 
            sign(ModBlocks.MAHOGANY_SIGN, ModBlocks.MAHOGANY_WALL_SIGN));
    Item MAPLE_SIGN = regItem("maple_sign", 
            sign(ModBlocks.MAPLE_SIGN, ModBlocks.MAPLE_WALL_SIGN));
    Item PALM_SIGN = regItem("palm_sign", 
            sign(ModBlocks.PALM_SIGN, ModBlocks.PALM_WALL_SIGN));
    Item PINE_SIGN = regItem("pine_sign", 
            sign(ModBlocks.PINE_SIGN, ModBlocks.PINE_WALL_SIGN));
    Item WALNUT_SIGN = regItem("walnut_sign", 
            sign(ModBlocks.WALNUT_SIGN, ModBlocks.WALNUT_WALL_SIGN));
    Item WILLOW_SIGN = regItem("willow_sign", 
            sign(ModBlocks.WILLOW_SIGN, ModBlocks.WILLOW_WALL_SIGN));
    Item BLUE_SPRUCE_SIGN = regItem("blue_spruce_sign", 
            sign(ModBlocks.BLUE_SPRUCE_SIGN, ModBlocks.BLUE_SPRUCE_WALL_SIGN));
    
    Item ASPEN_HANGING_SIGN = regItem("aspen_hanging_sign", 
            hangingSign(ModBlocks.ASPEN_HANGING_SIGN, ModBlocks.ASPEN_WALL_HANGING_SIGN));
    Item AZALEA_HANGING_SIGN = regItem("azalea_hanging_sign", 
            hangingSign(ModBlocks.AZALEA_HANGING_SIGN, ModBlocks.AZALEA_WALL_HANGING_SIGN));
    Item BAOBAB_HANGING_SIGN = regItem("baobab_hanging_sign", 
            hangingSign(ModBlocks.BAOBAB_HANGING_SIGN, ModBlocks.BAOBAB_WALL_HANGING_SIGN));
    Item CEDAR_HANGING_SIGN = regItem("cedar_hanging_sign", 
            hangingSign(ModBlocks.CEDAR_HANGING_SIGN, ModBlocks.CEDAR_WALL_HANGING_SIGN));
    Item EBONY_HANGING_SIGN = regItem("ebony_hanging_sign", 
            hangingSign(ModBlocks.EBONY_HANGING_SIGN, ModBlocks.EBONY_WALL_HANGING_SIGN));
    Item FIR_HANGING_SIGN = regItem("fir_hanging_sign", 
            hangingSign(ModBlocks.FIR_HANGING_SIGN, ModBlocks.FIR_WALL_HANGING_SIGN));
    Item MAHOGANY_HANGING_SIGN = regItem("mahogany_hanging_sign", 
            hangingSign(ModBlocks.MAHOGANY_HANGING_SIGN, ModBlocks.MAHOGANY_WALL_HANGING_SIGN));
    Item MAPLE_HANGING_SIGN = regItem("maple_hanging_sign", 
            hangingSign(ModBlocks.MAPLE_HANGING_SIGN, ModBlocks.MAPLE_WALL_HANGING_SIGN));
    Item PALM_HANGING_SIGN = regItem("palm_hanging_sign", 
            hangingSign(ModBlocks.PALM_HANGING_SIGN, ModBlocks.PALM_WALL_HANGING_SIGN));
    Item PINE_HANGING_SIGN = regItem("pine_hanging_sign", 
            hangingSign(ModBlocks.PINE_HANGING_SIGN, ModBlocks.PINE_WALL_HANGING_SIGN));
    Item WALNUT_HANGING_SIGN = regItem("walnut_hanging_sign", 
            hangingSign(ModBlocks.WALNUT_HANGING_SIGN, ModBlocks.WALNUT_WALL_HANGING_SIGN));
    Item WILLOW_HANGING_SIGN = regItem("willow_hanging_sign", 
            hangingSign(ModBlocks.WILLOW_HANGING_SIGN, ModBlocks.WILLOW_WALL_HANGING_SIGN));
    Item BLUE_SPRUCE_HANGING_SIGN = regItem("blue_spruce_hanging_sign", 
            hangingSign(ModBlocks.BLUE_SPRUCE_HANGING_SIGN, ModBlocks.BLUE_SPRUCE_WALL_HANGING_SIGN));


    //boats
    Item ASPEN_BOAT = regItem("aspen_boat", 
            boat(false, ModBoatEntity.Type.ASPEN));
    Item AZALEA_BOAT = regItem("azalea_boat", 
            boat(false, ModBoatEntity.Type.AZALEA));
    Item BAOBAB_BOAT = regItem("baobab_boat", 
            boat(false, ModBoatEntity.Type.BAOBAB));
    Item CEDAR_BOAT = regItem("cedar_boat", 
            boat(false, ModBoatEntity.Type.CEDAR));
    Item EBONY_BOAT = regItem("ebony_boat", 
            boat(false, ModBoatEntity.Type.EBONY));
    Item FIR_BOAT = regItem("fir_boat", 
            boat(false, ModBoatEntity.Type.FIR));
    Item MAHOGANY_BOAT = regItem("mahogany_boat", 
            boat(false, ModBoatEntity.Type.MAHOGANY));
    Item MAPLE_BOAT = regItem("maple_boat", 
            boat(false, ModBoatEntity.Type.MAPLE));
    Item PALM_BOAT = regItem("palm_boat", 
            boat(false, ModBoatEntity.Type.PALM));
    Item PINE_BOAT = regItem("pine_boat", 
            boat(false, ModBoatEntity.Type.PINE));
    Item WALNUT_BOAT = regItem("walnut_boat", 
            boat(false, ModBoatEntity.Type.WALNUT));
    Item WILLOW_BOAT = regItem("willow_boat", 
            boat(false, ModBoatEntity.Type.WILLOW));
    Item BLUE_SPRUCE_BOAT = regItem("blue_spruce_boat", 
            boat(false, ModBoatEntity.Type.BLUE_SPRUCE));
    
    Item ASPEN_CHEST_BOAT = regItem("aspen_chest_boat", 
            boat(true, ModBoatEntity.Type.ASPEN));
    Item AZALEA_CHEST_BOAT = regItem("azalea_chest_boat", 
            boat(true, ModBoatEntity.Type.AZALEA));
    Item BAOBAB_CHEST_BOAT = regItem("baobab_chest_boat", 
            boat(true, ModBoatEntity.Type.BAOBAB));
    Item CEDAR_CHEST_BOAT = regItem("cedar_chest_boat", 
            boat(true, ModBoatEntity.Type.CEDAR));
    Item EBONY_CHEST_BOAT = regItem("ebony_chest_boat", 
            boat(true, ModBoatEntity.Type.EBONY));
    Item FIR_CHEST_BOAT = regItem("fir_chest_boat", 
            boat(true, ModBoatEntity.Type.FIR));
    Item MAHOGANY_CHEST_BOAT = regItem("mahogany_chest_boat", 
            boat(true, ModBoatEntity.Type.MAHOGANY));
    Item MAPLE_CHEST_BOAT = regItem("maple_chest_boat", 
            boat(true, ModBoatEntity.Type.MAPLE));
    Item PALM_CHEST_BOAT = regItem("palm_chest_boat", 
            boat(true, ModBoatEntity.Type.PALM));
    Item PINE_CHEST_BOAT = regItem("pine_chest_boat", 
            boat(true, ModBoatEntity.Type.PINE));
    Item WALNUT_CHEST_BOAT = regItem("walnut_chest_boat", 
            boat(true, ModBoatEntity.Type.WALNUT));
    Item WILLOW_CHEST_BOAT = regItem("willow_chest_boat", 
            boat(true, ModBoatEntity.Type.WILLOW));
    Item BLUE_SPRUCE_CHEST_BOAT = regItem("blue_spruce_chest_boat", 
            boat(true, ModBoatEntity.Type.BLUE_SPRUCE));

    Item SYRUP_BOTTLE = regItem("syrup_bottle", 
            new SyrupBottleItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).food(Foods.HONEY_BOTTLE).stacksTo(16)));

    CoconutItem COCONUT = regItem("coconut",
            new CoconutItem(ModBlocks.COCONUT, (new Item.Properties()).food(CoconutItem.COCONUT)));

    Item WALNUT = regItem("walnut",
            new WalnutItem((new Item.Properties())));

}
