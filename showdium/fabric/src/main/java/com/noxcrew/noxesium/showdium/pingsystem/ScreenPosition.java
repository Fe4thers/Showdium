package com.noxcrew.noxesium.showdium.pingsystem;

import net.minecraft.world.phys.Vec2;

/**
 * Represents a position on the screen with depth information.
 * Used for projecting 3D world coordinates to 2D screen space.
 */
public class ScreenPosition {

    private final float xPos;
    private final float yPos;
    private final float depthValue;

    public ScreenPosition(float x, float y, float depth) {
        this.xPos = x;
        this.yPos = y;
        this.depthValue = depth;
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }

    public float getDepth() {
        return depthValue;
    }

    /**
     * Checks if this position is behind the camera (not visible).
     */
    public boolean isBehindCamera() {
        return depthValue <= 0f;
    }

    /**
     * Calculates the distance from this screen position to another point.
     * Returns MAX_VALUE if behind camera.
     */
    public float distanceTo(Vec2 target) {
        if (isBehindCamera()) {
            return Float.MAX_VALUE;
        }
        float deltaX = target.x - xPos;
        float deltaY = target.y - yPos;
        return new Vec2(deltaX, deltaY).length();
    }

    /**
     * Checks if this position is within the specified screen bounds.
     */
    public boolean isWithinBounds(Vec2 topLeft, Vec2 bottomRight) {
        return xPos >= topLeft.x && xPos <= bottomRight.x && yPos >= topLeft.y && yPos <= bottomRight.y;
    }

    /**
     * Checks if this position is visible on screen (not behind camera and within bounds).
     */
    public boolean isVisibleOnScreen(float screenWidth, float screenHeight) {
        if (isBehindCamera()) {
            return false;
        }
        return xPos >= 0 && xPos <= screenWidth && yPos >= 0 && yPos <= screenHeight;
    }
}
