package com.ordana.would.neoforge;

import com.ordana.would.reg.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = WouldNeoForge.MOD_ID)
public class ForgeEvents {
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        var ret = ModEvents.onBlockCLicked(event.getItemStack(),
                event.getEntity(), event.getLevel(), event.getHand(), event.getHitVec());
        if (ret != InteractionResult.PASS) {
            event.setCanceled(true);
            event.setCancellationResult(ret);
        }
    }

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        ResourceKey<? extends Registry<?>> registryKey = event.getRegistryKey();
        if (registryKey.equals(Registries.BLOCK)) {
            ModBlocks.init();
        } else if (registryKey.equals(Registries.ITEM)) {
            ModItems.init();
        } else if (registryKey.equals(Registries.ENTITY_TYPE)) {
            ModEntities.init();
        } else if (registryKey.equals(Registries.TREE_DECORATOR_TYPE)) {
            ModTrees.init();
            ModWorldgenFeatures.init();
        }
    }

}
