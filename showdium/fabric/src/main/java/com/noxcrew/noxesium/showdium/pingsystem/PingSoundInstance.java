package com.noxcrew.noxesium.showdium.pingsystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

/**
 * A directional sound instance that adjusts its position based on the ping location.
 * Makes the ping sound appear to come from the correct direction.
 */
public class PingSoundInstance extends AbstractTickableSoundInstance {

    private static final double MAX_SOUND_DISTANCE = 64.0;
    private static final double SOUND_RANGE = 14.0;

    private final Vec3 targetPosition;

    public PingSoundInstance(SoundEvent sound, SoundSource category, float volume, float pitch, Vec3 position) {
        super(sound, category, RandomSource.create());
        this.volume = volume;
        this.pitch = pitch;
        this.targetPosition = position;
        updatePosition();
    }

    @Override
    public void tick() {
        var player = Minecraft.getInstance().player;

        if (player == null) {
            stop();
            return;
        }

        updatePosition();
    }

    /**
     * Updates the sound position based on player location.
     * Maps the actual distance to a closer position for better directional audio.
     */
    private void updatePosition() {
        var player = Minecraft.getInstance().player;

        if (player == null) {
            return;
        }

        Vec3 playerPos = player.position();
        Vec3 directionToPing = playerPos.vectorTo(targetPosition);

        // Map distance to a manageable range for audio
        double actualDistance = directionToPing.length();
        double mappedDistance = Math.min(actualDistance, MAX_SOUND_DISTANCE) / MAX_SOUND_DISTANCE * SOUND_RANGE;

        // Calculate virtual sound position
        Vec3 soundDirection = directionToPing.normalize().scale(mappedDistance);
        Vec3 soundPosition = playerPos.add(soundDirection);

        this.x = soundPosition.x;
        this.y = soundPosition.y;
        this.z = soundPosition.z;
    }
}
