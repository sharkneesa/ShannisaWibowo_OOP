package com.Shannisa.frontend.objects.enemies;

import com.Shannisa.frontend.objects.Collideable;
import com.Shannisa.frontend.objects.Player;
import com.Shannisa.frontend.objects.items.Item;
import com.badlogic.gdx.graphics.Color;

public class Fairy extends Enemy {

    public Fairy(String name, int hp){

        super(150,380,24,24, Color.PINK,name,hp,500L);
    }
    public Fairy(float x, float y, String name, int hp){
        super(x, y, 24, 24, Color.PINK, name,hp,500L);
        this.name = name;
        this.hp = hp;
        this.maxHP= hp; // this is a multilevel inheritance where there is a parent that has a child and has another child
    }

    @Override
    public void onCollision(Collideable other) {
        // TODO: Check whether the other received by this method is a Player
        if(other instanceof Player){
            System.out.println("Player touches fairy");
        }
        // TODO: Print "Player touches fairy"
    }


}
