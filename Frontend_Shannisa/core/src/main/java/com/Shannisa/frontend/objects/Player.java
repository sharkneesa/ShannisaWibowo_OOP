package com.Shannisa.frontend.objects;

import com.Shannisa.frontend.objects.enemies.Enemy;
import com.Shannisa.frontend.objects.items.Item;
import com.Shannisa.frontend.objects.items.ItemType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

public class Player extends GameObject {

    private String name;
    private int hp;
    private int power;
    private int spellCards;
    private long score;

    public Player(String name, int hp, int power, int spellCards){
        super(280,40,32,32,200f, Color.RED);

        this.name=name;
        this.hp=hp;
        this.power=power;
        this.spellCards=spellCards;
    }

    public Player(float x, float y, String name, int hp, int power, int spellCards){
        super(x, y, 32, 32, 200f, Color.RED);

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
        ItemType type = item.getItemTypeEnum();
        if (type != null) {
            switch (type) {
                case POWER -> {
                    // 1. Increase power by type.getPowerBonus() via this.power
                    // 2. Add score by item.getScoreValue() via addScore() (addScore() already automatically prints "gained X pts!")
                    // 3. Print: [name] collected POWER item! Power increased to [power]
                    this.power+= type.getPowerBonus();
                    addScore(item.getScoreValue());
                    System.out.println(getName()  + " collected POWER item!"+ "Power is Increased to " + getPower()
                    );
                }
                case POINT -> {
                    // 1. Add score by item.getScoreValue() via addScore()
                    // 2. Print: [name] collected POINT item!
                    addScore(item.getScoreValue());
                    System.out.println(getName()+ " collected POINT item!");
                }
                case BOMB -> {
                    // 1. Increase spellCards by 1
                    // 2. Add score by item.getScoreValue() via addScore()
                    // 3. Print: [name] collected BOMB item! SpellCards: [spellCards]
                    this.spellCards++;
                    addScore(item.getScoreValue());
                    System.out.println(getName()+ " collected BOMB item! SpellCards: "+ getSpellCards());
                }
                case LIFE -> {
                    // 1. Increase hp by 20
                    // 2. Add score by item.getScoreValue() via addScore()
                    // 3. Print: [name] collected LIFE item! HP: [hp]
                    this.hp+=20;
                    addScore(item.getScoreValue());
                    System.out.println(getName()+ " collected LIFE item! HP: "+getHp());
                }
            }
        } else {
            addScore(item.getScoreValue());
            System.out.println(name + " collected " + item.getItemType() + "!");
        }
    }


    @Override
    public void update(float delta) {
        if (Gdx.input != null) {
            // TODO: Check W / UP input   → y += speed * delta
            if (Gdx.input.isKeyPressed(Input.Keys.W)||Gdx.input.isKeyPressed(Input.Keys.UP)){
                y+=speed*delta;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.S)||Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                y -= speed * delta;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.A) ||Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                x -= speed * delta;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                x += speed * delta;
            }
            // TODO: Check S / DOWN input → y -= speed * delta
            // TODO: Check A / LEFT input → x -= speed * delta
            // TODO: Check D / RIGHT input → x += speed * delta
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
