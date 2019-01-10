package code.system;

import code.engine.Engine;

/**
 * The UISystem is responsible for updating the current UI.
 */
public class UISystem extends GameSystem {

	/**
	 * Create a new UISystem
	 */
	public UISystem() {
		super(SystemType.UI);
	}

	public void update() {
		Engine.get().getCurrentUI().update();
	}
}
