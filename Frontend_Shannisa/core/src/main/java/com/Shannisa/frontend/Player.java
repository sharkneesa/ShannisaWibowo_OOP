package com.Shannisa.frontend;

import java.awt.*;
import com.badlogic.gdx.graphics.Color;

public class Player extends GameObject {

    private String name;
    private int hp;
    private int power;
    private int spellCards;
    private long score;

    public Player(String name, int hp, int power, int spellCards){
        super(280,40,32,32,0, Color.RED);

        this.name=name;
        this.hp=hp;
        this.power=power;
        this.spellCards=spellCards;
    }

    public Player(float x, float y, String name, int hp, int power, int spellCards){
        super(x, y, 32, 32, 0, Color.RED);

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
        System.out.println(getName() + " collected " + item.getItemType() + "!");
        if (item.getScoreValue() > 0) {
            addScore(item.getScoreValue());
        }
    }



}
