package minefantasy.mf2.item;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.knowledge.InformationList;
import minefantasy.mf2.mechanics.EventManagerMF;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemMagicAnswerBook extends Item
{
	public ItemMagicAnswerBook() 
	{
        super();
        setMaxStackSize(16);
        setCreativeTab(CreativeTabMF.tabGadget);
		GameRegistry.registerItem(this, "MF_InformationGuide", MineFantasyII.MODID);
		this.setUnlocalizedName("Information Book");
    }

    @Override
	public EnumRarity getRarity(ItemStack item)
	{
    	return EnumRarity.epic;
	}
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int i)
    {
    	return Items.enchanted_book.getIconFromDamage(i);
    }
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer user)
    {
    	user.openGui(MineFantasyII.instance, 1, world, 0, 0, 0);
        return item;
    }
}
