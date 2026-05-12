package com.ordana.would.reg;

import com.ordana.would.Would;
import com.ordana.would.items.CoconutItem;
import com.ordana.would.items.SyrupBottleItem;
import com.ordana.would.items.WalnutItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.block.Block;
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
            SyrupBottleItem::new, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(Foods.HONEY_BOTTLE).stacksTo(16));

    CoconutItem COCONUT = regItem("coconut",
            (p)-> new CoconutItem(ModBlocks.COCONUT, p), (new Item.Properties().useBlockDescriptionPrefix().food(CoconutItem.COCONUT, Consumable.builder().consumeSeconds(0.8f).build())));

    Item WALNUT = regItem("walnut",
            WalnutItem::new, new Item.Properties());

}
