package minefantasy.mf2.mechanics;

import minefantasy.mf2.entity.EntityArrowMF;
import minefantasy.mf2.item.archery.ItemArrowMF;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class MFArrowDispenser extends BehaviorDefaultDispenseItem
{
    /**
     * Dispense the specified stack, play the dispense sound and spawn particles.
     */
    public ItemStack dispenseStack(IBlockSource dispenser, ItemStack item)
    {
        World world = dispenser.getWorld();
        IPosition iposition = BlockDispenser.getDispensePosition(dispenser);
        EnumFacing direction = BlockDispenser.getFacing(dispenser.getBlockMetadata());
        double posX = dispenser.getX() + ((float)direction.getFrontOffsetX());
		double posY = dispenser.getY() + ((float)direction.getFrontOffsetY());
		double posZ = dispenser.getZ() + ((float)direction.getFrontOffsetZ());
		
        if(item.getItem() instanceof ItemArrowMF)
        {
        	ItemArrowMF arrowitem = (ItemArrowMF)item.getItem();
	        EntityArrowMF arrow = arrowitem.getFiredArrow(new EntityArrowMF(world, posX, posY, posZ), item);
	        arrow.setThrowableHeading((double)direction.getFrontOffsetX(), (double)((float)direction.getFrontOffsetY() + 0.1F), (double)direction.getFrontOffsetZ(), this.func_82500_b(), this.func_82498_a());
	        world.spawnEntityInWorld((Entity)arrow);
	        arrow.canBePickedUp = 1;
	        item.splitStack(1);
        }
        return item;
    }

    /**
     * Play the dispense sound from the specified block.
     */
    protected void playDispenseSound(IBlockSource p_82485_1_)
    {
        p_82485_1_.getWorld().playAuxSFX(1002, new BlockPos(p_82485_1_.getX(), p_82485_1_.getY(), p_82485_1_.getZ()), 0);
    }

    protected float func_82498_a()
    {
        return 6.0F;
    }

    protected float func_82500_b()
    {
        return 1.1F;
    }
}