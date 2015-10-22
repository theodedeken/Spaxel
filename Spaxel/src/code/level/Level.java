package code.level;

import java.util.List;
import java.util.ArrayList;

import code.Game;
import code.collision.HitPoint;
import code.collision.HitShape;
import code.entity.Enemy;
import code.entity.Entity;
import code.entity.Player;
import code.entity.Projectile;
import code.graphics.Render;
import code.input.Keyboard;
import code.input.Mouse;
import code.math.Vector;

public class Level {

	private Player player;
	private int xOffset;
	private int yOffset;
	private int screenXOffset;
	private int screenYOffset;
	private int mouseX;
	private int mouseY;
	private List<Projectile> projectiles;
	private List<Enemy> enemies;
	private Entity collisionTest = new Entity(0,0,0);
	
	

	public Level() {
		xOffset = 0;
		yOffset = 0;
		projectiles = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		HitShape temp = new HitShape();
		temp.addHitPoint(new HitPoint(new Vector(new double[]{0,0,1})));
		collisionTest.setHitShape(temp);
	}

	public void update(Keyboard keyboard, Mouse mouse) {
		mouseX = mouse.getX();
		mouseY = mouse.getY();
		screenXOffset = mouseX / 2 - Game.GAME_WIDTH / 4;
		screenYOffset = mouseY / 2 - Game.GAME_HEIGHT / 4;
		player.update(keyboard, mouseX, mouseY);
		xOffset = (int) player.getX();
		yOffset = (int) player.getY();
		if (mouse.mouse1) {
			player.primaryWeapon();
		}
		if (mouse.mouse2) {
			player.secondaryWeapon();
		}

		for (Projectile p : projectiles) {
			p.update();
		}
		cleanProjectiles(projectiles);
		collisionTest.update();
	}

	public void render(Render render) {
		int playerXPos = Game.GAME_WIDTH / 2 - 8 * 4 - screenXOffset;
		int playerYPos = Game.GAME_HEIGHT / 2 - 8 * 4 - screenYOffset;
		render.dots(playerXPos - xOffset, playerYPos - yOffset);
		player.render(playerXPos, playerYPos, render);

		for (Projectile p : projectiles) {
			p.render(playerXPos - xOffset, playerYPos - yOffset, render);
		}

		for (Enemy e : enemies) {
			e.render(playerXPos - xOffset, playerYPos - yOffset, render);
		}
		player.getUpdHitShape().render(playerXPos - xOffset, playerYPos - yOffset, render);
		collisionTest.getUpdHitShape().render(playerXPos - xOffset, playerYPos - yOffset, render);
	}

	public void addPlayer(Player player) {
		this.player = player;
		player.addLevel(this);
		enemies.add(new Enemy(0, 0, 0, player.getSprite()));
	}

	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	public void addProjectiles(List<Projectile> projs) {
		if (projs != null)
			projectiles.addAll(projs);
	}

	public void cleanProjectiles(List<Projectile> projectiles) {
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if (!p.isAlive()) {
				projectiles.remove(p);
			}
		}
	}

}
