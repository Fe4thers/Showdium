package com.noxcrew.noxesium.showdium.pingsystem;

import net.minecraft.client.Camera;
import org.joml.Matrix4f;

/**
 * Stores render context information captured during world rendering.
 * This data is needed for projecting 3D positions to screen space during HUD rendering.
 */
public final class RenderContext {

    private static Matrix4f storedModelView;
    private static Matrix4f storedProjection;
    private static Camera storedCamera;
    private static float storedTickDelta;

    private RenderContext() {
        // Prevent instantiation
    }

    /**
     * Captures the current render matrices from the world renderer.
     * Called by the LevelRenderer mixin.
     */
    public static void captureMatrices(Matrix4f modelView, Matrix4f projection, float tickDelta, Camera camera) {
        storedModelView = new Matrix4f(modelView);
        storedProjection = new Matrix4f(projection);
        storedCamera = camera;
        storedTickDelta = tickDelta;
    }

    /**
     * Clears the stored render context.
     */
    public static void clear() {
        storedModelView = null;
        storedProjection = null;
        storedCamera = null;
        storedTickDelta = 0f;
    }

    public static Matrix4f getModelViewMatrix() {
        return storedModelView;
    }

    public static Matrix4f getProjectionMatrix() {
        return storedProjection;
    }

    public static Camera getCamera() {
        return storedCamera;
    }

    public static float getTickDelta() {
        return storedTickDelta;
    }

    /**
     * Checks if the render context has valid data.
     */
    public static boolean hasValidData() {
        return storedModelView != null && storedProjection != null && storedCamera != null;
    }
}
