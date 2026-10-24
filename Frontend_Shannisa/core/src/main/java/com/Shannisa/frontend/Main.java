package com.Shannisa.frontend;
import java.util.ArrayList;
import java.util.List;

import com.Shannisa.frontend.objects.bullets.Bullet;
import com.Shannisa.frontend.objects.items.ItemType;
import com.Shannisa.frontend.systems.AssetManager;
import com.Shannisa.frontend.systems.AudioManager;
import com.Shannisa.frontend.systems.EntityFactory;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private Boss boss;
    private List<GameObject> entities;
    private Item powerItem;
    private Item pointItem;
    private SpriteBatch batch;
    private List<Fairy> fairy;


    @Override

    public void create() {

        // TODO 1:
        // When initializing the renderer, create a SpriteBatch and store it in batch.
        batch = new SpriteBatch();

        // TODO 2:
        // Initialize the fairy and entities lists as empty ArrayLists.
        fairy = new ArrayList<>();
        entities = new ArrayList<>();

        // TODO 3:
        // Before creating entities, get the AssetManager instance and call init().
        AssetManager.getInstance().init();

        AudioManager.getInstance().init();
        AudioManager.getInstance().playMusic("bgm");

        // TODO 4:
        // Update how all entities are created!
        player = EntityFactory.createPlayer(280, 40, "Reimu Hakurei", 100, 15, 3);

        fairy.add(EntityFactory.createFairy(150, 380, "Red Fairy", 20));
        fairy.add(EntityFactory.createFairy(250, 380, "Blue Fairy", 20, "fairy_idle_blue"));

        boss = EntityFactory.createBoss(380, 400, "Rumia", 150);

        powerItem = EntityFactory.createItem(200, 450, ItemType.POWER);
        pointItem = EntityFactory.createItem(320, 480, ItemType.POINT);

        // TODO 5:
        // Add all the objects you have just created to entities.
        entities.add(player);
        entities.addAll(fairy);
        entities.add(boss);
        entities.add(powerItem);
        entities.add(pointItem);
    }


    @Override
    public void render() {

        float delta = Gdx.graphics.getDeltaTime();

        // TODO 1: If the Z key was just pressed, add a new bullet from player.shootBullet()
        // to the entities list.
        if (Gdx.input.isKeyJustPressed(Z)) {
            entities.add(player.shootBullet());
        }

        // TODO 2: Update and clean entities.
        updateAndClean(
            entities,
            delta,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight()
        );

        // Collision detection between entities
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

        batch.begin();

        for (GameObject entity : entities) {
            if (!entity.isDestroyed()) {

                // TODO: Call each entity's .render() method with the SpriteBatch as its argument.
                entity.render(batch);
            }
        }

        batch.end();
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
        if (batch != null) {
            batch.dispose();
        }

        // TODO: Call dispose on AssetManager to release the loaded Textures as well.
        AssetManager.getInstance().dispose();
        AudioManager.getInstance().dispose();

    }


}
