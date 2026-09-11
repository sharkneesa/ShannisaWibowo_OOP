package com.Shannisa.frontend;
import com.badlogic.gdx.graphics.Color;

public class Enemy extends GameObject{
    protected String name;
    protected int hp;
    protected int maxHp;
    protected long scoreValue;
    protected int maxHP;

    public Enemy(String name, int hp){
        super(200, 380, 24,24,0,Color.PINK);

        this.name=name;
        this.hp=hp;
        this.maxHP=hp;
    }

    public Enemy(float x, float y, float width, float height, Color color, String name, int hp, long scoreValue) {
        super(x,y,width,height, 0, color);

        this.name=name;
        this.hp=hp;
        this.maxHP=hp;


    }

    public String getName() {
        return this.name;
    }

    public int getMaxHp(){
        return this.maxHP;
    }

    public long getScoreValue(){
        return this.scoreValue;
    }

    public int getHp(){
        return this.hp;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
    }

    public void setScoreValue(long scoreValue){
        this.scoreValue=scoreValue;
    }




    public boolean takeDamage(int damage) {
        // 1. Reduce hp by the damage value.
        if(getHp()==0){
            return false;
        }
        setHp(getHp() - damage);
        if (getHp()==0){
            System.out.println(getName() + "was defeated!");
            return true;
        }
        else{
            System.out.println(getName() + "took" + damage + "damage!" + "Remaining HP:" + getHp() + "/" + getMaxHp());
            return false;
        }


    }

    public void attack(Player player, int damage) {
        // 1. Display information that the Enemy is attacking the Player, in the format: [EnemyName] unleashes bullet barrage on [PlayerName]!

        System.out.println(name + "unleashes bullet barrage on" + player.name);

        // 2. Call the Player's takeDamage() method using the given damage.

        player.takeDamage(damage);
    }

    public boolean isAlive() {
        // 1. Return true if hp > 0, and false otherwise
        return this.hp > 0;
    }

}
