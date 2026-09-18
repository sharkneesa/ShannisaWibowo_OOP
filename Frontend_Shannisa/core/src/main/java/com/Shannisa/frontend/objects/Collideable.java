package com.Shannisa.frontend.objects;
import com.badlogic.gdx.math.Rectangle;

public interface Collideable{

    Rectangle getCoreHitbox();

    Rectangle getGrazeHitbox();

    void onCollision(Collideable other);

}
