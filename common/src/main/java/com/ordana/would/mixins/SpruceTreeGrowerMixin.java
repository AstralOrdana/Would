package com.ordana.would.mixins;


import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.SpruceTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpruceTreeGrower.class)
public class SpruceTreeGrowerMixin {

    @Inject(method = "getConfiguredMegaFeature", at = @At("HEAD"), cancellable = true)
    private void overrideBonemeal(RandomSource random, CallbackInfoReturnable<ResourceKey<ConfiguredFeature<?, ?>>> cir) {
        cir.setReturnValue(TreeFeatures.MEGA_SPRUCE);
    }
}