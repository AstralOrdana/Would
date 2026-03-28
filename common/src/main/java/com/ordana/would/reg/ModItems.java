package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.entities.ModBoatEntity;
import com.ordana.would.items.ModBoatItem;
import com.ordana.would.items.SyrupBottleItem;
import com.ordana.would.items.WalnutItem;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.Supplier;

public interface ModItems {

    List<Item> ALL_SIGNS = Lists.newArrayList();
    List<Item> ALL_HANGING_SIGNS = Lists.newArrayList();
    List<ModBoatItem> ALL_BOATS = Lists.newArrayList();
    List<ModBoatItem> ALL_CHEST_BOATS = Lists.newArrayList();

    static void init() {
    }

    static <T extends Item> Supplier<T> regItem(String name, Supplier<T> itemSup) {
        return RegHelper.registerItem(Would.res(name), itemSup);
    }
    
    private static Supplier<Item> sign(String name, Supplier<Block> standingVariantSupplier, Supplier<Block> wallVariantSupplier) {
        Item item = new SignItem(new Item.Properties().stacksTo(16), standingVariantSupplier.get(), wallVariantSupplier.get());
        ALL_SIGNS.add(item);

        return regItem(name, () -> item);
    }

    private static Supplier<Item> hangingSign(String name, Supplier<Block> ceilingVariantSupplier, Supplier<Block> wallVariantSupplier) {
        Item item = new HangingSignItem(ceilingVariantSupplier.get(), wallVariantSupplier.get(), new Item.Properties().stacksTo(16));
        ALL_HANGING_SIGNS.add(item);

        return regItem(name, () -> item);
    }

    private static Supplier<Item> boat(String name, boolean chest, ModBoatEntity.Type type) {
        ModBoatItem item = new ModBoatItem(chest, type, new Item.Properties().stacksTo(1));
        (chest ? ALL_CHEST_BOATS : ALL_BOATS).add(item);

        return regItem(name, () -> item);
    }
    
    
    //signs
    Supplier<Item> ASPEN_SIGN = sign("aspen_sign", ModBlocks.ASPEN_SIGN, ModBlocks.ASPEN_WALL_SIGN);
    Supplier<Item> AZALEA_SIGN = sign("azalea_sign", ModBlocks.AZALEA_SIGN, ModBlocks.AZALEA_WALL_SIGN);
    Supplier<Item> BAOBAB_SIGN = sign("baobab_sign", ModBlocks.BAOBAB_SIGN, ModBlocks.BAOBAB_WALL_SIGN);
    Supplier<Item> BLUE_SPRUCE_SIGN = sign("blue_spruce_sign", ModBlocks.BLUE_SPRUCE_SIGN, ModBlocks.BLUE_SPRUCE_WALL_SIGN);
    Supplier<Item> CEDAR_SIGN = sign("cedar_sign", ModBlocks.CEDAR_SIGN, ModBlocks.CEDAR_WALL_SIGN);
    Supplier<Item> EBONY_SIGN = sign("ebony_sign", ModBlocks.EBONY_SIGN, ModBlocks.EBONY_WALL_SIGN);
    Supplier<Item> FIR_SIGN = sign("fir_sign", ModBlocks.FIR_SIGN, ModBlocks.FIR_WALL_SIGN);
    Supplier<Item> MAHOGANY_SIGN = sign("mahogany_sign", ModBlocks.MAHOGANY_SIGN, ModBlocks.MAHOGANY_WALL_SIGN);
    Supplier<Item> MAPLE_SIGN = sign("maple_sign", ModBlocks.MAPLE_SIGN, ModBlocks.MAPLE_WALL_SIGN);
    Supplier<Item> PALM_SIGN = sign("palm_sign", ModBlocks.PALM_SIGN, ModBlocks.PALM_WALL_SIGN);
    Supplier<Item> PINE_SIGN = sign("pine_sign", ModBlocks.PINE_SIGN, ModBlocks.PINE_WALL_SIGN);
    Supplier<Item> WALNUT_SIGN = sign("walnut_sign", ModBlocks.WALNUT_SIGN, ModBlocks.WALNUT_WALL_SIGN);
    Supplier<Item> WILLOW_SIGN = sign("willow_sign", ModBlocks.WILLOW_SIGN, ModBlocks.WILLOW_WALL_SIGN);
    
    Supplier<Item> ASPEN_HANGING_SIGN = hangingSign("aspen_hanging_sign", ModBlocks.ASPEN_HANGING_SIGN, ModBlocks.ASPEN_WALL_HANGING_SIGN);
    Supplier<Item> AZALEA_HANGING_SIGN = hangingSign("azalea_hanging_sign", ModBlocks.AZALEA_HANGING_SIGN, ModBlocks.AZALEA_WALL_HANGING_SIGN);
    Supplier<Item> BAOBAB_HANGING_SIGN = hangingSign("baobab_hanging_sign", ModBlocks.BAOBAB_HANGING_SIGN, ModBlocks.BAOBAB_WALL_HANGING_SIGN);
    Supplier<Item> BLUE_SPRUCE_HANGING_SIGN = hangingSign("blue_spruce_hanging_sign", ModBlocks.BLUE_SPRUCE_HANGING_SIGN, ModBlocks.BLUE_SPRUCE_WALL_HANGING_SIGN);
    Supplier<Item> CEDAR_HANGING_SIGN = hangingSign("cedar_hanging_sign", ModBlocks.CEDAR_HANGING_SIGN, ModBlocks.CEDAR_WALL_HANGING_SIGN);
    Supplier<Item> EBONY_HANGING_SIGN = hangingSign("ebony_hanging_sign", ModBlocks.EBONY_HANGING_SIGN, ModBlocks.EBONY_WALL_HANGING_SIGN);
    Supplier<Item> FIR_HANGING_SIGN = hangingSign("fir_hanging_sign", ModBlocks.FIR_HANGING_SIGN, ModBlocks.FIR_WALL_HANGING_SIGN);
    Supplier<Item> MAHOGANY_HANGING_SIGN = hangingSign("mahogany_hanging_sign", ModBlocks.MAHOGANY_HANGING_SIGN, ModBlocks.MAHOGANY_WALL_HANGING_SIGN);
    Supplier<Item> MAPLE_HANGING_SIGN = hangingSign("maple_hanging_sign", ModBlocks.MAPLE_HANGING_SIGN, ModBlocks.MAPLE_WALL_HANGING_SIGN);
    Supplier<Item> PALM_HANGING_SIGN = hangingSign("palm_hanging_sign", ModBlocks.PALM_HANGING_SIGN, ModBlocks.PALM_WALL_HANGING_SIGN);
    Supplier<Item> PINE_HANGING_SIGN = hangingSign("pine_hanging_sign", ModBlocks.PINE_HANGING_SIGN, ModBlocks.PINE_WALL_HANGING_SIGN);
    Supplier<Item> WALNUT_HANGING_SIGN = hangingSign("walnut_hanging_sign", ModBlocks.WALNUT_HANGING_SIGN, ModBlocks.WALNUT_WALL_HANGING_SIGN);
    Supplier<Item> WILLOW_HANGING_SIGN = hangingSign("willow_hanging_sign", ModBlocks.WILLOW_HANGING_SIGN, ModBlocks.WILLOW_WALL_HANGING_SIGN);

    //boats
    Supplier<Item> ASPEN_BOAT = boat("aspen_boat", false, ModBoatEntity.Type.ASPEN);
    Supplier<Item> AZALEA_BOAT = boat("azalea_boat", false, ModBoatEntity.Type.AZALEA);
    Supplier<Item> BAOBAB_BOAT = boat("baobab_boat", false, ModBoatEntity.Type.BAOBAB);
    Supplier<Item> BLUE_SPRUCE_BOAT = boat("blue_spruce_boat", false, ModBoatEntity.Type.BLUE_SPRUCE);
    Supplier<Item> CEDAR_BOAT = boat("cedar_boat", false, ModBoatEntity.Type.CEDAR);
    Supplier<Item> EBONY_BOAT = boat("ebony_boat", false, ModBoatEntity.Type.EBONY);
    Supplier<Item> FIR_BOAT = boat("fir_boat", false, ModBoatEntity.Type.FIR);
    Supplier<Item> MAHOGANY_BOAT = boat("mahogany_boat", false, ModBoatEntity.Type.MAHOGANY);
    Supplier<Item> MAPLE_BOAT = boat("maple_boat", false, ModBoatEntity.Type.MAPLE);
    Supplier<Item> PALM_BOAT = boat("palm_boat", false, ModBoatEntity.Type.PALM);
    Supplier<Item> PINE_BOAT = boat("pine_boat", false, ModBoatEntity.Type.PINE);
    Supplier<Item> WALNUT_BOAT = boat("walnut_boat", false, ModBoatEntity.Type.WALNUT);
    Supplier<Item> WILLOW_BOAT = boat("willow_boat", false, ModBoatEntity.Type.WILLOW);
    
    Supplier<Item> ASPEN_CHEST_BOAT = boat("aspen_chest_boat", true, ModBoatEntity.Type.ASPEN);
    Supplier<Item> AZALEA_CHEST_BOAT = boat("azalea_chest_boat", true, ModBoatEntity.Type.AZALEA);
    Supplier<Item> BAOBAB_CHEST_BOAT = boat("baobab_chest_boat", true, ModBoatEntity.Type.BAOBAB);
    Supplier<Item> BLUE_SPRUCE_CHEST_BOAT = boat("blue_spruce_chest_boat", true, ModBoatEntity.Type.BLUE_SPRUCE);
    Supplier<Item> CEDAR_CHEST_BOAT = boat("cedar_chest_boat", true, ModBoatEntity.Type.CEDAR);
    Supplier<Item> EBONY_CHEST_BOAT = boat("ebony_chest_boat", true, ModBoatEntity.Type.EBONY);
    Supplier<Item> FIR_CHEST_BOAT = boat("fir_chest_boat", true, ModBoatEntity.Type.FIR);
    Supplier<Item> MAHOGANY_CHEST_BOAT = boat("mahogany_chest_boat", true, ModBoatEntity.Type.MAHOGANY);
    Supplier<Item> MAPLE_CHEST_BOAT = boat("maple_chest_boat", true, ModBoatEntity.Type.MAPLE);
    Supplier<Item> PALM_CHEST_BOAT = boat("palm_chest_boat", true, ModBoatEntity.Type.PALM);
    Supplier<Item> PINE_CHEST_BOAT = boat("pine_chest_boat", true, ModBoatEntity.Type.PINE);
    Supplier<Item> WALNUT_CHEST_BOAT = boat("walnut_chest_boat", true, ModBoatEntity.Type.WALNUT);
    Supplier<Item> WILLOW_CHEST_BOAT = boat("willow_chest_boat", true, ModBoatEntity.Type.WILLOW);

    Supplier<Item> SYRUP_BOTTLE = regItem("syrup_bottle", () -> new SyrupBottleItem(new Item.Properties()
        .craftRemainder(Items.GLASS_BOTTLE)
        .food(Foods.HONEY_BOTTLE)
        .stacksTo(16))
    );

    Supplier<Item> WALNUT = regItem("walnut", () -> new WalnutItem(new Item.Properties()));

}
