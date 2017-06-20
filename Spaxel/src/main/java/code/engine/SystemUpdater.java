package code.engine;

import code.system.GameSystem;
import code.system.RenderSystem;
import org.lwjgl.system.CallbackI;

import java.awt.*;
import java.util.EnumMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by theo on 5-6-2016.
 */
public class SystemUpdater {
    private EnumMap<SystemType, Thread> threads;
    private EnumMap<SystemType, GameSystem> systems;
    private ExecutorService e = Executors.newCachedThreadPool();

    public SystemUpdater(){
        threads = new EnumMap<>(SystemType.class);
    }

    public void setSystems(EnumMap<SystemType, GameSystem> systems){
        this.systems = systems;
    }

    public class SystemWrapper implements Runnable{
        private GameSystem system;
        private CountDownLatch latch;

        public SystemWrapper(GameSystem system, CountDownLatch latch){
            this.system = system;
            this.latch = latch;
        }
        @Override
        public void run() {
            system.update();
            latch.countDown();
        }
    }

    /**
     * general update 50 times a second
     *
     * LifeSystem in entities.cleanup
     * UISystem
     * InventorySystem
     * SoundSystem
     * TrailSystem
     *
     */
    public void generalUpdate(){
        if (!e.isShutdown()){
            CountDownLatch latch;
            Engine.getEngine().getMouseWrapper().update();
            Engine.getEngine().getKeyboard().update();

            if (Engine.getEngine().getGameState() != Engine.GameState.PLAY){
                latch = new CountDownLatch(2);
                e.execute(new SystemWrapper(systems.get(SystemType.SOUND), latch));
                e.execute(new SystemWrapper(systems.get(SystemType.UI), latch));
            }
            else {
                //TODO increment for each new system + temporary clean this up with config or smth
                latch = new CountDownLatch(12);
                e.execute(new SystemWrapper(systems.get(SystemType.AI), latch));
                e.execute(new SystemWrapper(systems.get(SystemType.SOUND), latch));
                e.execute(new SystemWrapper(systems.get(SystemType.INVENTORY), latch));
                e.execute(new SystemWrapper(systems.get(SystemType.UI), latch));
                e.execute(new SystemWrapper(systems.get(SystemType.SPAWNER), latch));
                e.execute(new SystemWrapper(systems.get(SystemType.TRAIL), latch));
                //TODO new systems
                e.execute(new SystemWrapper(systems.get(SystemType.AGE), latch));
                e.execute(new SystemWrapper(systems.get(SystemType.VELOCITY), latch));
                e.execute(new SystemWrapper(systems.get(SystemType.DAMAGE), latch));
                e.execute(new SystemWrapper(systems.get(SystemType.HEALTH), latch));
                e.execute(new SystemWrapper(systems.get(SystemType.COOLDOWN), latch));
                e.execute(new SystemWrapper(systems.get(SystemType.HIT), latch));
                //Engine.getEngine().temp.update();
            }
            try{
                latch.await();
                if (Engine.getEngine().getGameState() == Engine.GameState.PLAY){
                    Engine.getEngine().getEntityStream().cleanup();
                    Engine.getEngine().getNEntityStream().cleanup();
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * updates all the entities that change more than 50 times a second
     */
    public void renderUpdate(){
        CountDownLatch latch = new CountDownLatch(0);

        if (Engine.getEngine().getGameState() == Engine.GameState.PLAY){
            latch = new CountDownLatch(3);
            e.execute(new SystemWrapper(systems.get(SystemType.ACTOR), latch));
            e.execute(new SystemWrapper(systems.get(SystemType.PROJECTILE), latch));
            e.execute(new SystemWrapper(systems.get(SystemType.PARTICLE), latch));
        }
        try{
            latch.await();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void render(){
        ((RenderSystem) Engine.getEngine().getSystem(SystemType.RENDER)).render();
    }

    public void renderloading(LoadingScreen loading) {
        ((RenderSystem) Engine.getEngine().getSystem(SystemType.RENDER)).renderloading(loading);
    }

    public void shutdown(){
        e.shutdown();
    }
}
