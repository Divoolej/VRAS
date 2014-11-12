package com.ant.very.objects;

/**
 * Created by Divoolej on 2014-10-27.
 */
public class Camera {
    private int x, y; //Coordinates of the bottom left corner of the Camera.
    private int draggedX, draggedY; // When dragging the Camera, these are the coordinates of the initial touch.
    private int cameraWidth, cameraHeight; // (the width is equal to the width of the device screen)
    private int maxX, maxY;

    // The following are very important, they are used to store the coordinates of Camera before
    // the dragging begins, so that the camera won't move by the calculated offset EVERY frame.
    // When the user isn't dragging the Camera, they are equal to x and y.
    private int originX, originY;

    public Camera(int x, int y, int cameraWidth, int cameraHeight, int maxX, int maxY) {
        this.x = x;
        this.y = y;
        this.originX = x;
        this.originY = y;
        this.cameraWidth = cameraWidth;
        this.cameraHeight = cameraHeight;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    // This is used after dragging to make the Origin variables ready for another drag
    public void equalizeOrigin() {
        setOriginX(getX());
        setOriginY(getY());
    }

    public void moveTo(int x, int y) {
        if (!(x < 0 || x > maxX))
            setX(x);
        if (!(y < 0 || y > maxY))
            setY(y);
    }

    public void moveBy(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    // Getters & Setters:

    public int getWidth() {
        return cameraWidth;
    }

    public int getHeight() {
        return cameraHeight;
    }

    public int getDraggedY() {
        return draggedY;
    }

    public void setDraggedY(int draggedY) {
        this.draggedY = draggedY;
    }

    public int getDraggedX() {
        return draggedX;
    }

    public void setDraggedX(int draggedX) {
        this.draggedX = draggedX;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOriginX() {
        return originX;
    }

    private void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    private void setOriginY(int originY) {
        this.originY = originY;
    }
}
