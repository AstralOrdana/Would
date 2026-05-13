package com.ordana.would.reg;

import com.mojang.serialization.MapCodec;
import com.ordana.would.Would;
import com.ordana.would.consumables.ClearFireConsumeEffect;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.item.consume_effects.RemoveStatusEffectsConsumeEffect;

public interface ModFoods {

    FoodProperties COCONUT_FOOD = (new FoodProperties.Builder())
            .nutrition(2).saturationModifier(0.2F).alwaysEdible()
            .build();
    Consumable COCONUT_CONSUMABLE = Consumable.builder().consumeSeconds(0.8f).onConsume(ClearFireConsumeEffect.INSTANCE).build();
    Consumable SYRUP_CONSUMABLE = Consumables.defaultDrink().consumeSeconds(2.0F).onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.HUNGER)).sound(ModSoundEvents.SYRUP_DRINK).build();

    ConsumeEffect.Type<ClearFireConsumeEffect> CLEAR_FIRE_CONSUME_EFFECT = register("clear_fire", ClearFireConsumeEffect.CODEC, ClearFireConsumeEffect.STREAM_CODEC);

    private static <T extends ConsumeEffect> ConsumeEffect.Type<T> register(final String name, final MapCodec<T> codec, final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
        return Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE, Would.res(name), new ConsumeEffect.Type<>(codec, streamCodec));
    }

    static void init() {

    }
}
