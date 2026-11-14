package com.example.freepower;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;

/**
 * Nekonecny zdroj energie bez magnetu, civky a otaceni.
 * Konektor v rezimu PULL z nej tahá energii a nikdy se nevyprazdni.
 */
public class FreeGeneratorBlockEntity extends BlockEntity {
    /** Kolik energie se da vytahnout najednou. Uprav podle potreby. */
    public static final int MAX_EXTRACT = 100_000;
    public static final int CAPACITY = 1_000_000;

    private final IEnergyStorage energy = new IEnergyStorage() {
        @Override
        public int receiveEnergy(int toReceive, boolean simulate) {
            return 0;
        }

        @Override
        public int extractEnergy(int toExtract, boolean simulate) {
            return Math.min(toExtract, MAX_EXTRACT); // nic se neodecita
        }

        @Override
        public int getEnergyStored() {
            return CAPACITY;
        }

        @Override
        public int getMaxEnergyStored() {
            return CAPACITY;
        }

        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return false;
        }
    };

    public FreeGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(FreePower.FREE_GENERATOR_BE.get(), pos, state);
    }

    public IEnergyStorage getEnergy() {
        return energy;
    }
}
