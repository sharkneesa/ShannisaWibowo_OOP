package com.Shannisa.frontend.objects.enemies;

import com.Shannisa.frontend.objects.Collideable;
import com.Shannisa.frontend.objects.Player;
import com.Shannisa.frontend.objects.items.Item;
import com.badlogic.gdx.graphics.Color;

public class Boss extends Enemy {

    public Boss(String name, int hp){
        super(380,400,24,24, Color.BLUE,name,hp,5000L);
    }

    public Boss(float x, float y, String name, int hp){
        super(x, y, 48, 48, Color.BLUE, name, hp, 5000L);
    }

    @Override
    public void onCollision(Collideable other) {
        // TODO: Check whether the other received by this method is a Player
        if(other instanceof Player){
            System.out.println("Player touches boss");
        }
        // TODO: Print "Player touches boss"
    }

}
