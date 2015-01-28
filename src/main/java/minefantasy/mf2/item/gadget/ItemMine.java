package minefantasy.mf2.item.gadget;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.entity.EntityMine;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemMine extends Item
{
    public ItemMine(String name)
    {
        this.maxStackSize = 16;
        this.setCreativeTab(CreativeTabMF.tabGadget);
        setTextureName("minefantasy2:Other/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer user)
    {
    	if(user.isSwingInProgress)return item;
    	user.swingItem();
        if (!user.capabilities.isCreativeMode)
        {
            --item.stackSize;
        }

        if (!world.isRemote)
        {
        	user.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 20, 5, true));
        	world.spawnEntityInWorld(new EntityMine(world, user));
        }

        return item;
    }
}