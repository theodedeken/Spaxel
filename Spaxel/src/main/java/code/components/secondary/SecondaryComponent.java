package code.components.secondary;

import code.components.ComponentType;
import code.components.inventory.InventoryComponent;
import code.engine.NEntity;

import java.util.List;

/**
 * Created by theo on 19/06/17.
 */
public class SecondaryComponent extends InventoryComponent {

    public SecondaryComponent(List<NEntity> items) {
        super(ComponentType.SECONDARY, items);
    }
}