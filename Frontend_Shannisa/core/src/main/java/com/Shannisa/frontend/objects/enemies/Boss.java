package com.Shannisa.frontend.objects.enemies;

import com.Shannisa.frontend.objects.Collideable;
import com.Shannisa.frontend.objects.Player;
import com.Shannisa.frontend.objects.items.Item;
import com.Shannisa.frontend.objects.patterns.ShootingPattern;
import com.Shannisa.frontend.objects.patterns.shootingStrategy.SpreadShot;
import com.Shannisa.frontend.systems.BulletManager;
import com.badlogic.gdx.graphics.Color;

public class Boss extends Enemy {
    // TODO: Declare a private field named `shootingPattern` of type ShootingPattern
    //       and immediately initialize it with the `SpreadShot` we just created.
    //       Make sure speed is 200f, spread angle is 30f, and 3 bullets (num) are fired.
    private ShootingPattern shootingPattern=new SpreadShot(200f, 3, 30f);

    // Existing Boss constructors you already made before. (Don't touch these!)
    public Boss(String name, int hp) { ... }
    public Boss(float x, float y, String name, int hp) { ... }

    // TODO: Create the shootBullet(BulletManager bulletManager) method:
    //       If shootingPattern is not null and the boss hasn't been destroyed,
    //       execute shootingPattern at the boss's center cone coordinates (x + width / 2 - 8, y).
    public void shootBullet(BulletManager bulletManager) {

        if (shootingPattern != null && !isDestroyed()) {
            shootingPattern.execute(x + width / 2f - 8f, y, bulletManager, false);
        }
    }


    // TODO: Provide a getter and setter for shootingPattern

    public ShootingPattern getShootingPattern() {

        return shootingPattern;
    }

    public void setShootingPattern(ShootingPattern shootingPattern) {
        this.shootingPattern = shootingPattern;
    }
}
