package code.entity;

import code.level.Level;
import code.graphics.Render;
import code.graphics.Sprite;
import code.input.Keyboard;
import code.inventory.Inventory;

public class Player extends Entity {
	private Sprite sprite;
	private double rot;
	private int mouseX;
	private int mouseY;
	private Level level;
	private Inventory inventory;
	private double maxspeed;
	private double acc;
	private double xdir;
	private double ydir;

	public Player(int x, int y, Sprite sprite) {
		super(x, y);
		this.sprite = sprite;
		inventory = new Inventory();
		maxspeed = 20;
		acc = 0.5;
		xdir = 0;
		ydir = 0;
	}

	public void update(Keyboard keys, int mouseX, int mouseY) {
		double dx = 0;
		double dy = 0;
		if (keys.down) {
			dx = Math.sin(rot) * acc;
			dy = Math.cos(rot) * acc;
		}
		if (keys.up) {
			dx = -Math.sin(rot) * acc;
			dy = -Math.cos(rot) * acc;
		}
		if (keys.left) {
			dx = Math.sin(rot + Math.PI / 2) * acc;
			dy = Math.cos(rot + Math.PI / 2) * acc;
		}

		if (keys.right) {
			dx = Math.sin(rot - Math.PI / 2) * acc;
			dy = Math.cos(rot - Math.PI / 2) * acc;
		}
		if (controlSpeed(xdir + dx, ydir + dy)) {
			xdir += dx;
			ydir += dy;
		} else {
			xdir = 0.975 * xdir + dx;
			ydir = 0.975 * ydir + dy;
		}

		setX(getX() + xdir);
		setY(getY() + ydir);
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		inventory.update();
	}

	public boolean controlSpeed(double dx, double dy) {
		double speed = Math.sqrt(dx * dx + dy * dy);
		return speed < maxspeed;
	}

	public void render(int xPos, int yPos, Render render) {
		rot = Math.PI + Math.atan2(((double) (mouseX - xPos)), (double) (mouseY - yPos));
		sprite.render(xPos, yPos, rot, render);
	}

	public void addLevel(Level level) {
		this.level = level;
	}

	public void primaryWeapon() {

		level.addProjectiles(inventory.primaryWeapon((int) getX(), (int) getY(), rot));
	}

	public void secondaryWeapon() {

	}
	
	public Sprite getSprite() {
		return sprite;
	}

}
