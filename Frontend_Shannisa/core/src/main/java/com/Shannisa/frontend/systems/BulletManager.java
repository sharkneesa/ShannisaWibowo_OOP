package com.Shannisa.frontend.systems;

import com.Shannisa.frontend.objects.bullets.Bullet;
import com.Shannisa.frontend.objects.bullets.BulletType;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;

public class BulletManager {
    // 1. Pool Queues for Object Recycling (Object Pool Pattern)
    private Queue<Bullet> playerBulletPool;
    private Queue<Bullet> enemyBulletPool;

    // 2. Lists of Active Bullets on Screen
    private List<Bullet> activePlayerBullets;
    private List<Bullet> activeEnemyBullets;

    public BulletManager() {
        // TODO:
        // 1. Initialize playerBulletPool and enemyBulletPool as empty LinkedLists
        // 2. Initialize activePlayerBullets and activeEnemyBullets as empty ArrayLists
        playerBulletPool=new LinkedList<>();
        activeEnemyBullets=new ArrayList<>();
    }

    // Borrowing Player Bullet from Pool (5 Points)
    public Bullet spawnPlayerBullet(float x, float y, float vx, float vy, int damage) {
        Bullet bullet;
        // TODO:
        // 1. Check whether playerBulletPool is not empty.
        //    If so, poll from the pool. If not, create a new one using the Factory.
        // 2. Re-initialize the bullet.
        // 3. Add the bullet to the activePlayerBullets list.
        // 4. Return the bullet instance.
        if(!playerBulletPool.isEmpty()){
            bullet = playerBulletPool.poll();
        }
        else{
           bullet = EntityFactory.createPlayerBullet(x,y,damage);
        }

        bullet.init(x,y,vx,vy,BulletType.AMULET,damage,true);
        activeEnemyBullets.add(bullet);

        return bullet;


    }

    // Borrowing Enemy Bullet from Pool (5 Points)
    public Bullet spawnEnemyBullet(float x, float y, float vx, float vy, int damage) {
        Bullet bullet;
        // TODO:
        // 1. Check whether enemyBulletPool is not empty:
        //    If so, poll from the pool. If not, create a new one using the Factory.
        // 2. Re-initialize the bullet.
        // 3. Add the bullet to the activeEnemyBullets list
        // 4. Return the bullet instance
        if(!enemyBulletPool.isEmpty()){
            bullet=playerBulletPool.poll();
        }
        else{
            bullet = EntityFactory.createEnemyBullet(x,y,damage);
        }

        bullet.init(x,y,vx,vy,BulletType.DANMAKU,damage,true);
        activeEnemyBullets.add(bullet);

        return bullet;
    }


    public void render(SpriteBatch batch) {
        for (Bullet b : activePlayerBullets) {
            if (!b.isDestroyed()) b.render(batch);
        }
        for (Bullet b : activeEnemyBullets) {
            if (!b.isDestroyed()) b.render(batch);
        }
    }

    // Helper methods to access pools and active lists
    public List<Bullet> getActivePlayerBullets() { return activePlayerBullets; }
    public List<Bullet> getActiveEnemyBullets() { return activeEnemyBullets; }
    public int getPlayerPoolSize() { return playerBulletPool.size(); }
    public int getEnemyPoolSize() { return enemyBulletPool.size(); }

    public void update(float delta, float screenWidth, float screenHeight) {
        // 1. Player bullet recycling & movement (5 Points)
        // TODO:
        // - Copy-paste the iterator loop from updateAndClean() in Main.java
        // - Change the class referenced by the iterator to `Bullet` instead of T
        //   and change the referenced list to activePlayerBullets
        // - Change the check to whether the bullet is off-screen
        //   OR the bullet is already marked as destroyed
        //   - If YES:
        //     * Mark the bullet as destroyed (destroy)
        //     * Safely remove the bullet from the active bullet list
        //     * Return the bullet to the pool queue
        Iterator<Bullet> iterator = activePlayerBullets.iterator();


        while (iterator.hasNext()){
            Bullet bullet = iterator.next();
            bullet.update(delta);
            if (bullet.isOffScreen(screenWidth,screenHeight)|| bullet.isDestroyed()){
                System.out.println("Removed via Generic Iterator: " + getClass().getSimpleName());
                bullet.destroy();
                iterator.remove();
                playerBulletPool.offer(bullet);
            }
        }


        // 2. Enemy bullet recycling & movement (5 Points)
        // TODO:
        // - Follow the exact same procedure as above for activeEnemyBullets
        // - Enemy bullets that go off-screen or are destroyed are returned to enemyBulletPool via offer()
        Iterator<Bullet> iterators = activeEnemyBullets.iterator();


        while (iterators.hasNext()){
            Bullet bullet = iterators.next();
            bullet.update(delta);
            if (bullet.isOffScreen(screenWidth,screenHeight)|| bullet.isDestroyed()){
                System.out.println("Removed via Generic Iterator: " + getClass().getSimpleName());
                iterators.remove();
                enemyBulletPool.offer(bullet);
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Bullet b : activePlayerBullets) {
            if (!b.isDestroyed()) b.render(batch);
        }
        for (Bullet b : activeEnemyBullets) {
            if (!b.isDestroyed()) b.render(batch);
        }
    }


}
