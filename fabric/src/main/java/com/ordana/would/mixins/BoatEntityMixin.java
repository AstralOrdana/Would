package com.ordana.would.mixins;

import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Boat.class)
public abstract class BoatEntityMixin {

    /*
    @Shadow
    public abstract Boat.Type getVariant();

    @Inject(method = "asItem", at = @At(value = "FIELD", target = "Lnet/minecraft/item/Items;OAK_BOAT:Lnet/minecraft/item/Item;", opcode = Opcodes.GETSTATIC), cancellable = true)
    private void checkCustomBoats(CallbackInfoReturnable<Item> cir) {
        Boat.Type type = this.getVariant();
        if (type != Boat.Type.OAK) {
            for (var entry : ModItems.BOAT_SETS) {
                if (type == entry.type()) {
                    cir.setReturnValue(entry.boat());
                }
            }
        }
    }

     */
}