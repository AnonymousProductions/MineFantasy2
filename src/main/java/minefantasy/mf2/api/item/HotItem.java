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

	public abstract int getMaxTemp();
	
	public abstract int getMinTemp();
	
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
	
	public abstract void onHIUpdate(ItemStack stack, World world, Entity entity, int dam, boolean held);
	
	
}
