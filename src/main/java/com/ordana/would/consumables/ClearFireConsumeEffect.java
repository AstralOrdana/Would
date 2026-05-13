package com.ordana.would.consumables;

import com.mojang.serialization.MapCodec;
import com.ordana.would.reg.ModFoods;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

public class ClearFireConsumeEffect implements ConsumeEffect {
    public static final ClearFireConsumeEffect INSTANCE = new ClearFireConsumeEffect();
    public static final MapCodec<ClearFireConsumeEffect> CODEC = MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, ClearFireConsumeEffect> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return ModFoods.CLEAR_FIRE_CONSUME_EFFECT;
    }

    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity user) {
        if (!level.isClientSide()) {
            user.clearFire();
        }
        return true;
    }
}
