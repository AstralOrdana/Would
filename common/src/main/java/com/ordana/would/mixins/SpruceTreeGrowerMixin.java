package com.ordana.would.mixins;

import net.minecraft.world.level.block.grower.TreeGrower;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TreeGrower.class)
public class SpruceTreeGrowerMixin {

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/grower/TreeGrower;<init>(Ljava/lang/String;FLjava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;)V", ordinal = 1), index = 1)
    private static float overrideBonemeal(float secondaryChance) {
        return 1;
    }
}