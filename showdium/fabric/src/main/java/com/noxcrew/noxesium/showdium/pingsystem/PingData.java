package com.noxcrew.noxesium.showdium.pingsystem;

import java.util.UUID;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

/**
 * Base class containing core ping data.
 * Extended by PingEntry for full ping functionality.
 */
public abstract class PingData {

    protected Vec3 position;
    protected final @Nullable UUID targetEntityId;
    protected final @Nullable UUID creatorId;
    protected final int sequenceNumber;
    protected final int dimensionId;

    protected PingData(
            Vec3 position,
            @Nullable UUID targetEntityId,
            @Nullable UUID creatorId,
            int sequenceNumber,
            int dimensionId) {
        this.position = position;
        this.targetEntityId = targetEntityId;
        this.creatorId = creatorId;
        this.sequenceNumber = sequenceNumber;
        this.dimensionId = dimensionId;
    }

    public Vec3 getPosition() {
        return position;
    }

    public void setPosition(Vec3 position) {
        this.position = position;
    }

    public @Nullable UUID getTargetEntityId() {
        return targetEntityId;
    }

    public @Nullable UUID getCreatorId() {
        return creatorId;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public int getDimensionId() {
        return dimensionId;
    }
}
