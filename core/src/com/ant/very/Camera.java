package com.ant.very;

/**
 * Created by Divoolej on 2014-10-27.
 */
public class Camera {
    private int x, y; //Coordinates of the bottom left corner of the Camera
    private int draggedX; //When dragging the Camera, these are the coordinates of the initial touch
    private int draggedY; //They are used to calculate the offset by which we should move the Camera
    private int originX, originY; //These are very important, they are used to store the coordinates of Camera before the dragging begins, so that the camera won't move by the calculated offset EVERY frame.
                                  //When the user isn't dragging the Camera, they are equal to x and y
    private int w, h; // the width and height of the camera (the width is equal to the width of the device screen)

    private int maxX, maxY;

    public Camera(int x, int y, int w, int h, int mX, int mY)
    {
        setX(x);
        setY(y);
        setOriginX(x);
        setOriginY(y);
        this.w = w;
        this.h = h;
        maxX = mX;
        maxY = mY;
    }

    public void equalizeOrigin() {  //This is used after dragging to make the Origin variables ready for another drag
        setOriginX(getX());
        setOriginY(getY());
    }

    public void move(int x, int y) { //move & moveTo are used to move the Camera (wow, rly?), the difference is clear I guess
        setX(getX() + x);
        setY(getY() + y);
    }

    public void moveTo(int x, int y) {
        if (x < 0)
            setX(0);
        else if (x > maxX)
            setX(maxX);
        else
            setX(x);

        if (y < 0)
            setY(0);
        else if (y > maxY)
            setY(maxY);
        else
            setY(y);
    }

    //Getters & Setters

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
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

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }
}
