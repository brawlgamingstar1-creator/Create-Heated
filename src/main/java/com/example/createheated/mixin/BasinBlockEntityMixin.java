package com.example.createheated.mixin;

import com.example.createheated.HeatHelper;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Create 6.0.x decides how hot a basin is in BasinBlockEntity.getHeatLevelOf(BlockState).
 * Campfires are already "passive heaters" there, but only give SMOULDERING.
 * Recipes that need "heated" require KINDLED, so we upgrade lit campfires to KINDLED.
 */
@Mixin(targets = "com.simibubi.create.content.processing.basin.BasinBlockEntity", remap = false)
public abstract class BasinBlockEntityMixin {

    @Inject(
            method = "getHeatLevelOf(Lnet/minecraft/world/level/block/state/BlockState;)Lcom/simibubi/create/content/processing/burner/BlazeBurnerBlock$HeatLevel;",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void heated$campfire(BlockState state, CallbackInfoReturnable<Object> cir) {
        if (state.getBlock() instanceof CampfireBlock && state.getValue(CampfireBlock.LIT)) {
            Object kindled = HeatHelper.kindled();
            if (kindled != null) {
                cir.setReturnValue(kindled);
            }
        }
    }
}
