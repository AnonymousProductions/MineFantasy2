package minefantasy.mf2.item.tool.crafting;

import java.util.List;
import java.util.Random;

import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tool.IHuntingItem;
import minefantasy.mf2.api.tool.IToolMF;
import minefantasy.mf2.api.weapon.WeaponClass;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.weapon.ItemWeaponMF;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Anonymous Productions
 */
public class ItemKnifeMF extends ItemWeaponMF implements IToolMF, IHuntingItem
{
	private Random rand = new Random();
	private int tier;
	/**
	 * Knives are weapons used for hunting, and tools used for processing
	 */
    public ItemKnifeMF(String name, ToolMaterial material, int rarity, float weight, int tier)
    {
    	super(material, name, rarity, weight);
    	this.tier=tier;
    	baseDamage -= 3;
    	setCreativeTab(CreativeTabMF.tabCraftTool);
    	setUnlocalizedName("minefantasy2:Tool/Crafting/"+name);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
    	
    }
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		return item;
	}

	@Override
	public boolean canRetrieveDrops(ItemStack item) 
	{
		return true;
	}
	
	@Override
	public float getDigSpeed(ItemStack stack, IBlockState state)
	{
		return ToolHelper.modifyDigOnQuality(stack, super.getDigSpeed(stack, state));
	}
	@Override
	public float getEfficiency(ItemStack item) 
	{
		return material.getEfficiencyOnProperMaterial();
	}

	@Override
	public int getTier(ItemStack item) 
	{
		return tier;
	}

	@Override
	public String getToolType(ItemStack item)
	{
		return "knife";
	}
	
	@Override
	public boolean canBlock() 
	{
		return false;
	}
	/**
	 * Determines if the weapon can parry
	 */
	@Override
	public boolean canWeaponParry() 
	{
		return false;
	}
	
	@Override
	protected float getStaminaMod() 
	{
		return 0.5F;
	}
	@Override
	public WeaponClass getWeaponClass() 
	{
		return null;
	}
}
