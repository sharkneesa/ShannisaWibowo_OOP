package com.Shannisa.frontend;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class GameObject {
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected float speed;
    protected Color color;


    public GameObject(float x, float y, float width, float height, float speed, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth(){
        return this.width;
    }

    public float getHeight(){
        return this.height;
    }

    public float getSpeed(){
        return this.speed;
    }

    public Color getColor(){
        return this.color;
    }

    public void setX(float x){
        this.x=x;
    }

    public void setY(float y){
        this.y=y;
    }

    public void setWidth(float width) {
        if (width > 0) this.width = width;
    }

    public void setHeight(float height) {
        if (height > 0) this.height = height;
    }

    public void setSpeed(float speed) {
        if (speed >= 0) this.speed = speed;
    }

    public void setColor(Color color){
        this.color=color;
    }



    public void update(float data){
    //left empty since not every subclass is required to use this method, if it's an abstract then the other
        //subclasses are forced to use it
    }

    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x,y,width,height);
    }

}
