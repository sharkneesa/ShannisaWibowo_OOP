package com.Shannisa.frontend.objects.items;

import com.Shannisa.frontend.objects.Collideable;
import com.Shannisa.frontend.objects.Player;
import com.badlogic.gdx.graphics.Color;
import com.Shannisa.frontend.objects.GameObject;

public class Item extends GameObject {
    private String itemType;
    private long scoreValue;
    private ItemType itemTypeEnum;

    public Item(float x, float y, String itemType){
        super(x, y, 16, 16, 100f, Color.WHITE);
        this.scoreValue =  1000L;
        this.itemType=itemType;
    }

    public Item(float x, float y, float width, float height, float speed, String itemType){
        super(x, y, width, height, speed, Color.WHITE);
        this.scoreValue = 1000L;
        this.itemType=itemType;

    }

    public Item(float x, float y, float width, float height, float speed, String itemType, long scoreValue){
        super(x, y, width, height, speed, Color.WHITE);
        this.itemType=itemType;
        this.scoreValue=itemTypeEnum.getScoreValue();
    }

    public long getScoreValue() {
        return scoreValue;
    }

    public String getItemType(){
        return itemType;
    }

    public ItemType getItemTypeEnum(){return itemTypeEnum;}

    @Override
    public void update(float delta) {
        this.y -= speed * delta;
    }

    //since only items move frequently in games like touhou (at least in non open world games), for the characterrs itself the user controls their movement/the enemy moves depending on the player
    public Item(float x, float y, ItemType itemTypeEnum){
        super(x,y,16,16,100f,Color.WHITE);
        this.scoreValue=itemTypeEnum.getScoreValue();
        this.itemTypeEnum=itemTypeEnum;
        this.itemType=itemTypeEnum.name();
    }

    public Item(float x, float y, float width, float height, float speed, ItemType itemTypeEnum, long scoreValue){
        super(x,y,width,height, speed, Color.WHITE);
        this.scoreValue=itemTypeEnum.getScoreValue();
        this.itemTypeEnum=itemTypeEnum;
        this.itemType=itemTypeEnum.name();
    }

    @Override
    public void onCollision(Collideable other) {
        if (other instanceof Player) {
            // Item pickup is handled on the Player side via collectItem()

        }
    }



}
