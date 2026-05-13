package com.ordana.would.entities;

import com.ordana.would.reg.ModBlocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;

public class FallingCoconutEntity extends FallingBlockEntity {
    public FallingCoconutEntity(EntityType<? extends FallingBlockEntity> type, Level level) {
        super(type, level);
        setBlockState(ModBlocks.COCONUT.defaultBlockState());
    }

    public void setBlockState(BlockState state) {
        if (state.hasProperty(BlockStateProperties.HANGING)) {
            state = state.setValue(BlockStateProperties.HANGING, true);
        }
        CompoundTag tag = new CompoundTag();
        tag.put("BlockState", NbtUtils.writeBlockState(state));
        tag.putInt("Time", this.time);
        this.readAdditionalSaveData(TagValueInput.create(ProblemReporter.DISCARDING, level().registryAccess(), tag));
    }
}
