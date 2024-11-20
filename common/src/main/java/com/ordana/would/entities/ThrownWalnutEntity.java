package com.ordana.would.entities;

import com.ordana.would.reg.ModEntities;
import com.ordana.would.reg.ModItems;
import net.mehvahdjukaar.moonlight.api.entity.ImprovedProjectileEntity;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ThrownWalnutEntity extends ImprovedProjectileEntity {


    public ThrownWalnutEntity(EntityType<? extends ThrownWalnutEntity> type, Level world) {
        super(type, world);
    }

    public ThrownWalnutEntity(Level level, LivingEntity thrower, ItemStack item) {
        super(ModEntities.THROWN_WALNUT.get(), thrower, level);
        this.setItem(item);
    }

    public ThrownWalnutEntity(Level worldIn, double x, double y, double z) {
        super(ModEntities.THROWN_WALNUT.get(), x, y, z, worldIn);
    }


    @Override
    protected Item getDefaultItem() {
        return ModItems.WALNUT.get();
    }

    public void handleEntityEvent(byte id) {
        if (id == 3) {

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @Override
    protected void updateRotation() {
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        double d = 0;
        if (entity instanceof LivingEntity livingEntity) {
            d = Math.max(0.0D, 1.0D - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
        }
        Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale(0.6D * d);
        if (vec3.lengthSqr() > 0.0D) {
            entity.push(vec3.x, 0.1D, vec3.z);
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            level.playSound(null, getX(), getY(), getZ(), SoundEvents.BAMBOO_WOOD_BREAK, SoundSource.NEUTRAL, 0.75F, 2.5F + (random.nextFloat() / 2));
            level.addFreshEntity(new ItemEntity(level, result.getLocation().x, result.getLocation().y + 0.5, result.getLocation().z, new ItemStack(getDefaultItem())));
            this.discard();
        }
    }

}