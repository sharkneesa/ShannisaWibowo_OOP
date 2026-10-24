package com.Shannisa.frontend.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public abstract class GameObject implements Collideable{
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected float speed;
    protected Color color;
    protected boolean active = true;
    protected TextureRegion sprite;
    protected Animation<TextureRegion> animation;
    protected float stateTime = 0f;



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

    public void update(float delta){
        // TODO: Increase the object's internal time so its animation advances
        stateTime += delta;
    }

    public void render(ShapeRenderer shapeRenderer) {
        if (shapeRenderer != null && color != null && active/* TODO: add a condition that the object is still active */) {
            shapeRenderer.setColor(color);
            shapeRenderer.rect(x, y, width, height);
        }
    }

    public void render(SpriteBatch batch) {
        if (batch != null && active) {
            if (animation != null) {
                TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
                batch.draw(currentFrame, x, y, width, height);
            } else if (sprite != null) {
                batch.draw(sprite, x, y, width, height);
            }
        }
    }


    public boolean isDestroyed() {
        // TODO: return true if the object is NOT active (active == false)
        return !active;
    }

    public void destroy() {
        // TODO: mark this object as inactive
        active=false;
    }

    public boolean isOffScreen(float screenWidth, float screenHeight) {
        // TODO: return true if the x or y position is outside the screen boundaries
        // Use a 50px tolerance margin on each side, so objects that have only
        // slightly passed the edge of the screen are not immediately considered gone.

        return x > screenWidth + 50 || y > screenHeight + 50 || x < -50 || y < -50;
    }



    @Override
    public Rectangle getCoreHitbox() {
        // TODO: return a new Rectangle matching this object's x, y, width, height
        return new Rectangle(x,y,width,height);
    }

    @Override
    public Rectangle getGrazeHitbox() {
        // TODO: return a Rectangle with +10px padding on every side
        return new Rectangle(x-10,y-10,width+20,height+20);
    }

    public TextureRegion getSprite() {
        return sprite;
    }

    public void setSprite(TextureRegion sprite) {
        this.sprite = sprite;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    @Override
    public void onCollision(Collideable other) {
        // Base collision handler (can be overridden by subclasses that need to react)
    }


}
