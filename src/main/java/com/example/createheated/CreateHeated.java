package com.example.createheated;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(CreateHeated.MOD_ID)
public class CreateHeated {
    public static final String MOD_ID = "create_heated";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateHeated(IEventBus modEventBus) {
        LOGGER.info("Create: Heated loaded - lit campfires can now heat basins.");
    }
}
