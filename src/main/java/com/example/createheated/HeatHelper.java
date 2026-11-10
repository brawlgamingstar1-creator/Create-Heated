package com.example.createheated;

/**
 * Finds Create's HeatLevel.KINDLED via reflection, so the mod
 * does not need Create on the compile classpath.
 */
public final class HeatHelper {
    private static Object kindled;
    private static boolean tried;

    private HeatHelper() {}

    public static Object kindled() {
        if (!tried) {
            tried = true;
            try {
                Class<?> cls = Class.forName(
                        "com.simibubi.create.content.processing.burner.BlazeBurnerBlock$HeatLevel");
                for (Object constant : cls.getEnumConstants()) {
                    if (((Enum<?>) constant).name().equals("KINDLED")) {
                        kindled = constant;
                    }
                }
            } catch (Throwable t) {
                CreateHeated.LOGGER.error("Create: Heated could not find Create's HeatLevel", t);
            }
        }
        return kindled;
    }
}
