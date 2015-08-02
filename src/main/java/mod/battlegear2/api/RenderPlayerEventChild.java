package mod.battlegear2.api;

import mod.battlegear2.api.core.BattlegearUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

/**
 * Those events are posted to {@link BattlegearUtils.RENDER_BUS} from {@link BattlegearRenderHelper}
 */
public abstract class RenderPlayerEventChild extends RenderPlayerEvent{
	public static enum PlayerElementType{
		Offhand,
		ItemOffhand,
		ItemOffhandSheathed,
		ItemMainhandSheathed,
	}

    /**
     * Describe what element is rendered, either the player arm or the item hold/sheathed
     */
	public final PlayerElementType type;
    /**
     * True in first person rendering, false in third person rendering
     */
	public final boolean isFirstPerson;
    /**
     * The element to be rendered, or null if a player arm
     */
	public final ItemStack element;
	public RenderPlayerEventChild(RenderPlayerEvent parent, PlayerElementType type, boolean firstPerson, ItemStack item,double x, double y, double z) {
		super(parent.entityPlayer, parent.renderer, parent.partialRenderTick,x,y,z);
		this.type = type;
		this.isFirstPerson = firstPerson;
		this.element = item;
	}

	
}
