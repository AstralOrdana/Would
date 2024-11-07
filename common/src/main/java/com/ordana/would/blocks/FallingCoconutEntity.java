package com.ordana.would.blocks;

import com.ordana.would.reg.ModEntities;
import net.mehvahdjukaar.moonlight.api.entity.ImprovedFallingBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class FallingCoconutEntity extends ImprovedFallingBlockEntity {
    public FallingCoconutEntity(EntityType<? extends FallingBlockEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public void setBlockState(BlockState state) {
        if (state.hasProperty(BlockStateProperties.HANGING)) {
            state = state.setValue(BlockStateProperties.HANGING, true);
        }
        CompoundTag tag = new CompoundTag();
        tag.put("BlockState", NbtUtils.writeBlockState(state));
        tag.putInt("Time", this.time);
        this.readAdditionalSaveData(tag);
    }


    @Override
    public boolean causeFallDamage(float height, float amount, DamageSource source) {
        boolean r = super.causeFallDamage(height, amount, source);
        if (this.getDeltaMovement().lengthSqr() > 0.5 * 0.5) {
            this.discard();
            this.causeFallDamage(0.5f, 2.0f, this.damageSources().anvil(this));
        } else {
            if (!this.isSilent()) {
                level().levelEvent(LevelEvent.SOUND_POINTED_DRIPSTONE_LAND, this.blockPosition(), 0);
            }
        }
        return r;
    }

}
