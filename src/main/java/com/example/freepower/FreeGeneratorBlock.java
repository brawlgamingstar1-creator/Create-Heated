package com.example.freepower;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FreeGeneratorBlock extends BaseEntityBlock {
    public static final MapCodec<FreeGeneratorBlock> CODEC = simpleCodec(FreeGeneratorBlock::new);

    public FreeGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FreeGeneratorBlockEntity(pos, state);
    }

    // BaseEntityBlock je ve vychozim stavu neviditelny, proto ho prepisujeme.
    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
