package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.items.WalnutItem;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.UseRemainder;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.Function;

public interface ModItems {

    List<Item> ALL_SIGNS = Lists.newArrayList();
    List<Item> ALL_HANGING_SIGNS = Lists.newArrayList();
    List<BoatItem> ALL_BOATS = Lists.newArrayList();
    List<BoatItem> ALL_CHEST_BOATS = Lists.newArrayList();

    static void init() {
    }

    static <T extends Item> T regItem(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Would.MOD_ID, name));

        // Create the item instance.
        T item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    Item SYRUP_BOTTLE = regItem("syrup_bottle",
            Item::new, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).component(DataComponents.USE_REMAINDER, new UseRemainder(new ItemStackTemplate(Items.GLASS_BOTTLE))).food(Foods.HONEY_BOTTLE, ModFoods.SYRUP_CONSUMABLE).stacksTo(16));

    BlockItem COCONUT = regItem("coconut",
            (p)-> new BlockItem(ModBlocks.COCONUT, p), (new Item.Properties().component(DataComponents.USE_REMAINDER, new UseRemainder(new ItemStackTemplate(Items.BOWL, 2))).useBlockDescriptionPrefix().food(ModFoods.COCONUT_FOOD, ModFoods.COCONUT_CONSUMABLE)));

    Item WALNUT = regItem("walnut",
            WalnutItem::new, new Item.Properties());

}
