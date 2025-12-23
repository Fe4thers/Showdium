package com.noxcrew.noxesium.showdium.config;

/**
 * Runtime configuration for the ping system.
 */
public final class PingSystemConfig {

    private static boolean enabled = true;
    private static float volume = 1.0f;

    private PingSystemConfig() {}

    /**
     * Returns whether the ping system is enabled.
     */
    public static boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets whether the ping system is enabled.
     */
    public static void setEnabled(boolean value) {
        enabled = value;
        ShowdiumConfig.getInstance().pingEnabled = value;
    }

    /**
     * Returns the ping sound volume (0.0 to 1.0).
     */
    public static float getVolume() {
        return volume;
    }

    /**
     * Sets the ping sound volume.
     */
    public static void setVolume(float value) {
        volume = Math.clamp(value, 0.0f, 1.0f);
        ShowdiumConfig.getInstance().pingVolume = volume;
    }

    /**
     * Loads settings from the config file.
     */
    public static void loadFromConfig() {
        ShowdiumConfig config = ShowdiumConfig.getInstance();
        enabled = config.pingEnabled;
        volume = config.pingVolume;
    }
}
