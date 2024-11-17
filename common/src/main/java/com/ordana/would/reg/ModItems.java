package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.entities.ModBoatEntity;
import com.ordana.would.items.ModBoatItem;
import com.ordana.would.items.SyrupBottleItem;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class ModItems {

    public static void init() {
    }

    public static <T extends Item> Supplier<T> regItem(String name, Supplier<T> itemSup) {
        return RegHelper.registerItem(Would.res(name), itemSup);
    }

    private static Item sign(Block sign, Block wallSign) {
        return new SignItem((new Item.Properties()).stacksTo(16), sign, wallSign);
    }

    private static Item hangingSign(Block sign, Block wallSign) {
        return new HangingSignItem(sign, wallSign, (new Item.Properties()).stacksTo(16));
    }

    private static Item boat(boolean chest, ModBoatEntity.Type type) {
        return new ModBoatItem(chest, type, (new Item.Properties()).stacksTo(1));
    }
    
    
    //signs
    public static final Supplier<Item> ASPEN_SIGN = regItem("aspen_sign", () ->
            sign(ModBlocks.ASPEN_SIGN.get(), ModBlocks.ASPEN_WALL_SIGN.get()));
    public static final Supplier<Item> AZALEA_SIGN = regItem("azalea_sign", () ->
            sign(ModBlocks.AZALEA_SIGN.get(), ModBlocks.AZALEA_WALL_SIGN.get()));
    public static final Supplier<Item> BAOBAB_SIGN = regItem("baobab_sign", () ->
            sign(ModBlocks.BAOBAB_SIGN.get(), ModBlocks.BAOBAB_WALL_SIGN.get()));
    public static final Supplier<Item> CEDAR_SIGN = regItem("cedar_sign", () ->
            sign(ModBlocks.CEDAR_SIGN.get(), ModBlocks.CEDAR_WALL_SIGN.get()));
    public static final Supplier<Item> EBONY_SIGN = regItem("ebony_sign", () ->
            sign(ModBlocks.EBONY_SIGN.get(), ModBlocks.EBONY_WALL_SIGN.get()));
    public static final Supplier<Item> FIR_SIGN = regItem("fir_sign", () ->
            sign(ModBlocks.FIR_SIGN.get(), ModBlocks.FIR_WALL_SIGN.get()));
    public static final Supplier<Item> MAHOGANY_SIGN = regItem("mahogany_sign", () ->
            sign(ModBlocks.MAHOGANY_SIGN.get(), ModBlocks.MAHOGANY_WALL_SIGN.get()));
    public static final Supplier<Item> MAPLE_SIGN = regItem("maple_sign", () ->
            sign(ModBlocks.MAPLE_SIGN.get(), ModBlocks.MAPLE_WALL_SIGN.get()));
    public static final Supplier<Item> PALM_SIGN = regItem("palm_sign", () ->
            sign(ModBlocks.PALM_SIGN.get(), ModBlocks.PALM_WALL_SIGN.get()));
    public static final Supplier<Item> PINE_SIGN = regItem("pine_sign", () ->
            sign(ModBlocks.PINE_SIGN.get(), ModBlocks.PINE_WALL_SIGN.get()));
    public static final Supplier<Item> WALNUT_SIGN = regItem("walnut_sign", () ->
            sign(ModBlocks.WALNUT_SIGN.get(), ModBlocks.WALNUT_WALL_SIGN.get()));
    public static final Supplier<Item> WILLOW_SIGN = regItem("willow_sign", () ->
            sign(ModBlocks.WILLOW_SIGN.get(), ModBlocks.WILLOW_WALL_SIGN.get()));
    
    public static final Supplier<Item> ASPEN_HANGING_SIGN = regItem("aspen_hanging_sign", () ->
            hangingSign(ModBlocks.ASPEN_HANGING_SIGN.get(), ModBlocks.ASPEN_WALL_HANGING_SIGN.get()));
    public static final Supplier<Item> AZALEA_HANGING_SIGN = regItem("azalea_hanging_sign", () ->
            hangingSign(ModBlocks.AZALEA_HANGING_SIGN.get(), ModBlocks.AZALEA_WALL_HANGING_SIGN.get()));
    public static final Supplier<Item> BAOBAB_HANGING_SIGN = regItem("baobab_hanging_sign", () ->
            hangingSign(ModBlocks.BAOBAB_HANGING_SIGN.get(), ModBlocks.BAOBAB_WALL_HANGING_SIGN.get()));
    public static final Supplier<Item> CEDAR_HANGING_SIGN = regItem("cedar_hanging_sign", () ->
            hangingSign(ModBlocks.CEDAR_HANGING_SIGN.get(), ModBlocks.CEDAR_WALL_HANGING_SIGN.get()));
    public static final Supplier<Item> EBONY_HANGING_SIGN = regItem("ebony_hanging_sign", () ->
            hangingSign(ModBlocks.EBONY_HANGING_SIGN.get(), ModBlocks.EBONY_WALL_HANGING_SIGN.get()));
    public static final Supplier<Item> FIR_HANGING_SIGN = regItem("fir_hanging_sign", () ->
            hangingSign(ModBlocks.FIR_HANGING_SIGN.get(), ModBlocks.FIR_WALL_HANGING_SIGN.get()));
    public static final Supplier<Item> MAHOGANY_HANGING_SIGN = regItem("mahogany_hanging_sign", () ->
            hangingSign(ModBlocks.MAHOGANY_HANGING_SIGN.get(), ModBlocks.MAHOGANY_WALL_HANGING_SIGN.get()));
    public static final Supplier<Item> MAPLE_HANGING_SIGN = regItem("maple_hanging_sign", () ->
            hangingSign(ModBlocks.MAPLE_HANGING_SIGN.get(), ModBlocks.MAPLE_WALL_HANGING_SIGN.get()));
    public static final Supplier<Item> PALM_HANGING_SIGN = regItem("palm_hanging_sign", () ->
            hangingSign(ModBlocks.PALM_HANGING_SIGN.get(), ModBlocks.PALM_WALL_HANGING_SIGN.get()));
    public static final Supplier<Item> PINE_HANGING_SIGN = regItem("pine_hanging_sign", () ->
            hangingSign(ModBlocks.PINE_HANGING_SIGN.get(), ModBlocks.PINE_WALL_HANGING_SIGN.get()));
    public static final Supplier<Item> WALNUT_HANGING_SIGN = regItem("walnut_hanging_sign", () ->
            hangingSign(ModBlocks.WALNUT_HANGING_SIGN.get(), ModBlocks.WALNUT_WALL_HANGING_SIGN.get()));
    public static final Supplier<Item> WILLOW_HANGING_SIGN = regItem("willow_hanging_sign", () ->
            hangingSign(ModBlocks.WILLOW_HANGING_SIGN.get(), ModBlocks.WILLOW_WALL_HANGING_SIGN.get()));


    //boats
    public static final Supplier<Item> ASPEN_BOAT = regItem("aspen_boat", () ->
            boat(false, ModBoatEntity.Type.ASPEN));
    public static final Supplier<Item> AZALEA_BOAT = regItem("azalea_boat", () ->
            boat(false, ModBoatEntity.Type.AZALEA));
    public static final Supplier<Item> BAOBAB_BOAT = regItem("baobab_boat", () ->
            boat(false, ModBoatEntity.Type.BAOBAB));
    public static final Supplier<Item> CEDAR_BOAT = regItem("cedar_boat", () ->
            boat(false, ModBoatEntity.Type.CEDAR));
    public static final Supplier<Item> EBONY_BOAT = regItem("ebony_boat", () ->
            boat(false, ModBoatEntity.Type.EBONY));
    public static final Supplier<Item> FIR_BOAT = regItem("fir_boat", () ->
            boat(false, ModBoatEntity.Type.FIR));
    public static final Supplier<Item> MAHOGANY_BOAT = regItem("mahogany_boat", () ->
            boat(false, ModBoatEntity.Type.MAHOGANY));
    public static final Supplier<Item> MAPLE_BOAT = regItem("maple_boat", () ->
            boat(false, ModBoatEntity.Type.MAPLE));
    public static final Supplier<Item> PALM_BOAT = regItem("palm_boat", () ->
            boat(false, ModBoatEntity.Type.PALM));
    public static final Supplier<Item> PINE_BOAT = regItem("pine_boat", () ->
            boat(false, ModBoatEntity.Type.PINE));
    public static final Supplier<Item> WALNUT_BOAT = regItem("walnut_boat", () ->
            boat(false, ModBoatEntity.Type.WALNUT));
    public static final Supplier<Item> WILLOW_BOAT = regItem("willow_boat", () ->
            boat(false, ModBoatEntity.Type.WILLOW));
    
    public static final Supplier<Item> ASPEN_CHEST_BOAT = regItem("aspen_chest_boat", () ->
            boat(true, ModBoatEntity.Type.ASPEN));
    public static final Supplier<Item> AZALEA_CHEST_BOAT = regItem("azalea_chest_boat", () ->
            boat(true, ModBoatEntity.Type.AZALEA));
    public static final Supplier<Item> BAOBAB_CHEST_BOAT = regItem("baobab_chest_boat", () ->
            boat(true, ModBoatEntity.Type.BAOBAB));
    public static final Supplier<Item> CEDAR_CHEST_BOAT = regItem("cedar_chest_boat", () ->
            boat(true, ModBoatEntity.Type.CEDAR));
    public static final Supplier<Item> EBONY_CHEST_BOAT = regItem("ebony_chest_boat", () ->
            boat(true, ModBoatEntity.Type.EBONY));
    public static final Supplier<Item> FIR_CHEST_BOAT = regItem("fir_chest_boat", () ->
            boat(true, ModBoatEntity.Type.FIR));
    public static final Supplier<Item> MAHOGANY_CHEST_BOAT = regItem("mahogany_chest_boat", () ->
            boat(true, ModBoatEntity.Type.MAHOGANY));
    public static final Supplier<Item> MAPLE_CHEST_BOAT = regItem("maple_chest_boat", () ->
            boat(true, ModBoatEntity.Type.MAPLE));
    public static final Supplier<Item> PALM_CHEST_BOAT = regItem("palm_chest_boat", () ->
            boat(true, ModBoatEntity.Type.PALM));
    public static final Supplier<Item> PINE_CHEST_BOAT = regItem("pine_chest_boat", () ->
            boat(true, ModBoatEntity.Type.PINE));
    public static final Supplier<Item> WALNUT_CHEST_BOAT = regItem("walnut_chest_boat", () ->
            boat(true, ModBoatEntity.Type.WALNUT));
    public static final Supplier<Item> WILLOW_CHEST_BOAT = regItem("willow_chest_boat", () ->
            boat(true, ModBoatEntity.Type.WILLOW));

    public static final Supplier<Item> SYRUP_BOTTLE = regItem("syrup_bottle", () ->
            new SyrupBottleItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).food(Foods.HONEY_BOTTLE).stacksTo(16)));

}
