package com.Shannisa.frontend.objects.patterns;

import com.Shannisa.frontend.systems.BulletManager;

public interface ShootingPattern {
    void execute(float originX, float originY, BulletManager bulletManager, boolean isPlayer);
}
