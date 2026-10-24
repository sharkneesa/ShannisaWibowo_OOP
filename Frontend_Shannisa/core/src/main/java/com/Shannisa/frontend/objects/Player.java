package com.Shannisa.frontend.objects;

import com.Shannisa.frontend.objects.bullets.Bullet;
import com.Shannisa.frontend.objects.bullets.BulletType;
import com.Shannisa.frontend.objects.enemies.Enemy;
import com.Shannisa.frontend.objects.items.Item;
import com.Shannisa.frontend.objects.items.ItemType;
import com.Shannisa.frontend.systems.AssetManager;
import com.Shannisa.frontend.systems.AudioManager;
import com.Shannisa.frontend.systems.EntityFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends GameObject {

    private String name;
    private int hp;
    private int power;
    private int spellCards;
    private long score;
    private int currentDir = 0;

    public Player(String name, int hp, int power, int spellCards){
        super(280,40,32,48,200f, Color.RED);

        this.name=name;
        this.hp=hp;
        this.power=power;
        this.spellCards=spellCards;
    }

    public Player(float x, float y, String name, int hp, int power, int spellCards){
        super(x, y, 32, 48, 200f, Color.RED);

        this.name=name;
        this.hp=hp;
        this.power=power;
        this.spellCards=spellCards;

    }

    public int getHp(){
        return this.hp;
    }

    public String getName(){
        return this.name;
    }

    public int getPower() {
        return this.power;
    }

    public int getSpellCards(){
        return this.spellCards;
    }

    public long getScore(){return this.score;}

    public void setName(String name){
        this.name=name;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
    }

    public void setSpellCards(int spellCards){
        this.spellCards=spellCards;
    }



    public void takeDamage(int damage) {
        // 1. Reduce hp by the damage value.

        this.hp=this.hp-damage;

        // 2. HP must not become negative.

        if (this.hp<0){
            this.hp=0;
        }

        // 3. If HP is still greater than 0, display the remaining HP in the format: [PlayerName] took [damage] damage! Remaining HP: [hp]

        // 4. If HP reaches 0, display a message that the Player has been defeated.

        if (hp>0){
            System.out.println(name + "took" + damage + "damage!" + "Remaining HP:" + this.hp);
        }
        if (this.hp == 0) {
            System.out.println(name + " was defeated! ");
        }
    }

    public void shoot(Enemy target) {
        // 1. Create an int named damage, calculated by adding 10 to power.

        int damage = 10 + getPower();

        // 2. Display information that the Player is shooting the Enemy, in the format: [name] shoots [TargetName] dealing [damage] DMG!

        System.out.println(getName() + " shoots " + target.getName() + " dealing " + damage + " DMG!");

        // 3. Call the Enemy object's takeDamage() method.

        target.takeDamage(damage);
    }

    public Bullet shootBullet() {
        int damage = 10 + power;
        System.out.println(name + " shoots bullet dealing " + damage + " DMG!");

        AudioManager.getInstance().playSound("shoot");

        // TODO: Return a Bullet using EntityFactory
        // with the same x and y formulas as in the previous implementation.
        return EntityFactory.createPlayerBullet(x + width / 2 - 4, y + height, damage);
    }


    public boolean isAlive() {
        // 1. Return true if hp > 0, and false otherwise
        return this.hp > 0;
    }

    public void addScore(long points) {
        if (points > 0) {
            this.score += points;
            System.out.println(getName() + " gained " + points + " pts! Total Score: " + this.score);
        }
    }

    public void collectItem(Item item) {

        // Prevent collecting the same item twice
        if (item.isDestroyed()) {
            return;
        }

        ItemType type = item.getItemTypeEnum();

        if (type != null) {
            switch (type) {

                case POWER -> {
                    this.power += type.getPowerBonus();
                    addScore(item.getScoreValue());
                    System.out.println(
                        getName() + " collected POWER item! Power increased to " + getPower()
                    );
                }

                case POINT -> {
                    addScore(item.getScoreValue());
                    System.out.println(
                        getName() + " collected POINT item!"
                    );
                }

                case BOMB -> {
                    this.spellCards++;
                    addScore(item.getScoreValue());
                    System.out.println(
                        getName() + " collected BOMB item! SpellCards: " + getSpellCards()
                    );
                }

                case LIFE -> {
                    this.hp += 20;
                    addScore(item.getScoreValue());
                    System.out.println(
                        getName() + " collected LIFE item! HP: " + getHp()
                    );
                }
            }
        } else {
            addScore(item.getScoreValue());

            System.out.println(
                name + " collected " + item.getItemType() + "!"
            );
        }

        // Mark the ITEM as destroyed after collecting it
        AudioManager.getInstance().playSound("chime");
        item.destroy();
    }



    @Override
    public void update(float delta) {

        // TODO 1: Call GameObject's update(delta) through super.
        super.update(delta);

        // TODO 2: Declare a local float variable dx with an initial value of 0
        float dx = 0;

        if (Gdx.input != null) {

            if (Gdx.input.isKeyPressed(Input.Keys.W) ||
                Gdx.input.isKeyPressed(Input.Keys.UP)) {

                y += speed * delta;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S) ||
                Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

                y -= speed * delta;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.A) ||
                Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

                x -= speed * delta;

                // TODO 3: Adjust dx to match the direction.
                dx = -1;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D) ||
                Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

                x += speed * delta;

                // TODO 4: Adjust dx to match the direction.
                dx = 1;
            }
        }

        // TODO 5: Call updateAnimationState(dx)
        updateAnimationState(dx);
    }

    public void updateAnimationState(float dx) {
        AssetManager assets = AssetManager.getInstance();
        if (dx < 0) {
            // TODO:
            // 1. Only make the following changes if currentDir is not -1.
            // 2. Set currentDir to -1.
            // 3. Retrieve the "player_left" animation using assets.getAnimation(...).
            //    Store it in a local variable of type Animation<TextureRegion> named anim.
            // 4. If anim is not null, assign it using setAnimation(...).
            if (currentDir != -1) {
                currentDir = -1;

                Animation<TextureRegion> anim =
                    assets.getAnimation("player_left");

                if (anim != null) {
                    setAnimation(anim);
                }
            }
        }
        else if (dx > 0) {

            // TODO:
            // 1. Only make the following changes if currentDir is not 1.
            // 2. Set currentDir to 1.
            // 3. Retrieve the "player_right" animation using assets.getAnimation(...).
            //    Store it in a local variable of type Animation<TextureRegion> named anim.
            // 4. If anim is not null, assign it using setAnimation(...).

            if (currentDir != 1) {
                currentDir = 1;

                Animation<TextureRegion> anim =
                    assets.getAnimation("player_right");

                if (anim != null) {
                    setAnimation(anim);
                }
            }


        } else {
            // TODO:
            // 1. Only make the following changes if currentDir is not 0.
            // 2. Set currentDir to 0.
            // 3. Retrieve the "player_idle" animation using assets.getAnimation(...).
            //    Store it in a local variable of type Animation<TextureRegion> named anim.
            // 4. If anim is not null, assign it using setAnimation(...).
            if (currentDir != 0) {
                currentDir = 0;

                Animation<TextureRegion> anim =
                    assets.getAnimation("player_idle");

                if (anim != null) {
                    setAnimation(anim);
                }
            }

        }
    }


    @Override
    public void onCollision(Collideable other) {
        // TODO: Check whether the other received by this method is an Item
        if(other instanceof Item){
            System.out.println("Player touches items");
            collectItem((Item)other);
        }
        // TODO: Print "Player touches items" then call collectItem((Item) other)
    }





}
