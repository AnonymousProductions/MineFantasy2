/**
 * 
 */
package minefantasy.mf2.api.item;

import static minefantasy.mf2.mechanics.HotItemsHelper.*;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author tim4242
 * 
 */
public abstract class HotItem extends Item {

	/**
	 * @return The max temperature this Item can have.
	 */
	public abstract int getMaxTemp();
	
	/**
	 * @return The min temperature this Item can have.
	 */
	public abstract int getMinTemp();
	
	/**
	 * @return The temperature this item will be initialized to.
	 */
	public abstract int getDefaultTemp();
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int dam, boolean held)
	{
		if(getCurrTemp(stack) >= getBurnTemp())
		{
			if(!entity.isBurning())
				entity.setFire(10);
		}
		
		onHIUpdate(stack, world, entity, dam, held);
	}
	
	/**
	 * Method that gets called after the normal {@link net.minecraft.item.Item#onUpdate() onUpdate()} method
	 * 
	 * @param stack
	 * @param world
	 * @param entity
	 * @param dam
	 * @param held
	 * 
	 */
	public abstract void onHIUpdate(ItemStack stack, World world, Entity entity, int dam, boolean held);
	
	
}
