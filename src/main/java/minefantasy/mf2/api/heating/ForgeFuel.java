package minefantasy.mf2.api.heating;

import net.minecraft.item.ItemStack;

public class ForgeFuel 
{
	protected ItemStack fuel;
	protected float duration;
	protected int baseHeat;
	/**
	 * Applied to lava, auto-lights the forge when placed
	 */
	protected boolean doesLight;

	public ForgeFuel(ItemStack item, float dura, int heat, boolean light)
	{
		fuel = item;
		duration = dura;
		baseHeat = heat;
		doesLight = light;
	}
}
