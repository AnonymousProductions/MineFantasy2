package minefantasy.mf2.item.food;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.item.archery.ItemArrowMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.mechanics.PlayerTagData;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
/**
 * @author Anonymous Productions
 */
public class ItemUnfinishedFood extends Item 
{
	public ItemUnfinishedFood(String name)
	{
		this(name, 0);
	}
	public ItemUnfinishedFood(String name, int rarity)
	{
		super();
		setMaxStackSize(1);
		itemRarity = rarity;
		setTextureName("minefantasy2:food/unfinished/"+name);
		this.setCreativeTab(CreativeTabMF.tabFood);
		GameRegistry.registerItem(this, "MF_UFood"+name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
	}
	
	private int itemRarity;
    @Override
	public EnumRarity getRarity(ItemStack item)
	{
		int lvl = itemRarity+1;
		
		if(item.isItemEnchanted())
		{
			if(lvl == 0)
			{
				lvl++;
			}
			lvl ++;
		}
		if(lvl >= ToolListMF.rarity.length)
		{
			lvl = ToolListMF.rarity.length-1;
		}
		return ToolListMF.rarity[lvl];
	}
    public void onCrafted(EntityPlayer user, ItemStack item)
	{
	}
}
