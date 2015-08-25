package minefantasy.mf2.item;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemMilkBucketMF extends Item
{
    public ItemMilkBucketMF(String name)
    {
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabMF.tabGadget);
        
        setTextureName("minefantasy2:tool/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
    }

    @Override
	public ItemStack onEaten(ItemStack bucket, World world, EntityPlayer user)
    {
    	ItemStack empty = new ItemStack(ToolListMF.bucketwood_empty);
    	if (!world.isRemote)
        {
            user.curePotionEffects(bucket);
        }
    	
    	if (user.capabilities.isCreativeMode)
        {
            return bucket;
        }
        else if (--bucket.stackSize <= 0)
        {
            return empty;
        }
        else
        {
            if (!user.inventory.addItemStackToInventory(empty))
            {
                user.dropPlayerItemWithRandomChoice(empty, false);
            }
        }
    	return bucket;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.drink;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer user)
    {
        user.setItemInUse(item, this.getMaxItemUseDuration(item));
        return item;
    }
    
    @Override
    public Item getContainerItem()
    {
    	return ToolListMF.bucketwood_empty;
    }
    
}