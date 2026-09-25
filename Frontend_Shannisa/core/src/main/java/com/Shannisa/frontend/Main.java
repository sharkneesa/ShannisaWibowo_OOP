package com.Shannisa.frontend;
import java.util.ArrayList;
import java.util.List;

import com.Shannisa.frontend.objects.bullets.Bullet;
import com.Shannisa.frontend.objects.items.ItemType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.Shannisa.frontend.objects.enemies.Boss;
import com.Shannisa.frontend.objects.enemies.Fairy;
import com.Shannisa.frontend.objects.items.Item;
import com.Shannisa.frontend.objects.GameObject;
import com.Shannisa.frontend.objects.Player;
import com.badlogic.gdx.Input;
import java.util.Iterator;
import java.util.Objects;

import static com.badlogic.gdx.Input.Keys.Z;


public class Main extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;

    // TODO 1: Declare fields for Player, Fairy, Boss, Items, and List<GameObject>

    private Player player;
    private Fairy fairy;
    private Boss boss;
    private Item item;
    private List<GameObject> entities;
    private Item powerItem;
    private Item pointItem;


    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        entities = new ArrayList<>();

        // TODO 2: Instantiate Player (Red square) at (280, 40)
        player = new Player(280,40,"Reimu Hakurei",100,15,3);

        // TODO 3: Instantiate Fairy (Pink square) at (150, 380)
        fairy = new Fairy(150,380,"Fairy1",20);

        // TODO 4: Instantiate Boss (Blue square) at (380, 400)
        boss = new Boss(380,400,"Boss1",150);

        // TODO 5: Instantiate Items (White squares) with downward speeds
        item = new Item(150,450,"Item1");

        powerItem = new Item(200, 450, 16, 16, 80f, ItemType.POWER, 500L);
        pointItem = new Item(320, 480, 12, 12, 120f,ItemType.POINT, 1000L);

        // TODO 6: Add all entities into the gameObjects list polymorphically
        entities.add(player);
        entities.add(fairy);
        entities.add(boss);
        entities.add(item);
        entities.add(powerItem);
        entities.add(pointItem);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        // TODO 1: If the Z key was just pressed, add a new bullet from player.shootBullet()
        // to the entities list.
        // Clue: Gdx.input.isKeyJustPressed()
        if(Gdx.input.isKeyJustPressed(Z)){
            player.shootBullet() = new Bullet(player.getX(), player.getY(), player.getSpeed());
        }

        // TODO 2: Call updateAndClean(entities, delta, Gdx.graphics.getWidth(), Gdx.graphics.getHeight())
        // to update and clean up destroyed/off-screen entities.

        updateAndClean(entities, delta,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // 3. Collision detection between entities (skip entities that are already destroyed)
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                GameObject a = entities.get(i);
                GameObject b = entities.get(j);

                if (!a.isDestroyed() && !b.isDestroyed()) {
                    if (a.getCoreHitbox().overlaps(b.getCoreHitbox())) {
                        a.onCollision(b);
                        b.onCollision(a);
                    }
                }
            }
        }

        ScreenUtils.clear(0.1f, 0.1f, 0.15f, 1f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (GameObject entity : entities) {
            // TODO 3: Use an if statement to check whether the entity has not been destroyed (!entity.isDestroyed()).
            // If so, call entity.render(shapeRenderer);
            if(!entity.isDestroyed()) {
                entity.render(shapeRenderer);
            }
        }
        shapeRenderer.end();
    }


    public <T extends GameObject> void updateAndClean(List<T> list, float delta, float screenWidth, float screenHeight) {
        // 1. Get an Iterator<T> from the given list.

        Iterator<T> iterator = list.iterator();

        // 2. While there are still elements available (hasNext()):
        //    a. Get the current element using next() and store it in a variable of type T.
        //    b. Call update(delta) on the element.
        //    c. If the element is off-screen (isOffScreen(screenWidth, screenHeight))
        //       OR isDestroyed():
        //       - Display the message: "Removed via Generic Iterator: " + [entity class name, using getClass().getSimpleName()]
        //       - Remove the element from the list using the Iterator's method
        //         (NOT list.remove()!).

        while (iterator.hasNext()){
            T object = iterator.next();
            object.update(delta);
            if (object.isOffScreen(screenWidth,screenHeight)|| object.isDestroyed()){
                System.out.println("Removed via Generic Iterator: " + getClass().getSimpleName());
                iterator.remove();
            }
        }
    }




    @Override
    public void dispose() {
        if (shapeRenderer != null) {
            shapeRenderer.dispose();
        }
    }


}
