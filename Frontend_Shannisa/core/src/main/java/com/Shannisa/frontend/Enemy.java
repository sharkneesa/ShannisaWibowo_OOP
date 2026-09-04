package com.Shannisa.frontend;

public static class Enemy {
    String name;
    int hp;
    int maxHp;

    public Enemy(String name, int hp, int maxHp){
        this.name=name;
        this.hp=hp;
        this.maxHp=hp;
    }
}

public static void main(String[] args){
    Enemy name = new Enemy("Reimu Hakurei", 100, 50);
}
