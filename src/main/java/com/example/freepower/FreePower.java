package com.example.freepower;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(FreePower.MODID)
public class FreePower {
    public static final String MODID = "freepower";

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);

    public static final DeferredBlock<FreeGeneratorBlock> FREE_GENERATOR = BLOCKS.register("free_generator",
            () -> new FreeGeneratorBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.METAL)));

    public static final DeferredItem<BlockItem> FREE_GENERATOR_ITEM =
            ITEMS.registerSimpleBlockItem("free_generator", FREE_GENERATOR);

    public static final Supplier<BlockEntityType<FreeGeneratorBlockEntity>> FREE_GENERATOR_BE =
            BLOCK_ENTITIES.register("free_generator",
                    () -> BlockEntityType.Builder.of(FreeGeneratorBlockEntity::new, FREE_GENERATOR.get()).build(null));

    public FreePower(IEventBus modBus) {
        BLOCKS.register(modBus);
        ITEMS.register(modBus);
        BLOCK_ENTITIES.register(modBus);

        modBus.addListener(this::registerCapabilities);
        modBus.addListener(this::addToCreativeTab);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        // Stejna energy capability, kterou cte Electrical Connector z Create: New Age.
        event.registerBlockEntity(
                Capabilities.EnergyStorage.BLOCK,
                FREE_GENERATOR_BE.get(),
                (be, side) -> be.getEnergy());
    }

    private void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(FREE_GENERATOR_ITEM.get());
        }
    }
}
