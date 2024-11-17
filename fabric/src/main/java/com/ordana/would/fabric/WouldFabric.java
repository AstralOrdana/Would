package com.ordana.would.fabric;

import com.ordana.would.Would;
import com.ordana.would.reg.ModEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;


public class WouldFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        Would.commonInit();

        UseBlockCallback.EVENT.register(WouldFabric::onRightClickBlock);
    }

    public static InteractionResult onRightClickBlock(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
        return ModEvents.onBlockCLicked(player.getItemInHand(hand), player, level, hand, hitResult);
    }
}
