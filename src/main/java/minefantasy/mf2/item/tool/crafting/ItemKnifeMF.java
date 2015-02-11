package minefantasy.mf2.item.tool.crafting;

import java.util.List;
import java.util.Random;

import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tool.IHuntingItem;
import minefantasy.mf2.api.tool.IToolMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.weapon.ItemWeaponMF;
import net.minecraft.block.Block;
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
	/**
	 * Knives are weapons used for hunting, and tools used for processing
	 */
    public ItemKnifeMF(String name, ToolMaterial material, int rarity, float weight)
    {
    	super(material, name, rarity, weight);
    	baseDamage -= 3;
    	setCreativeTab(CreativeTabMF.tabCraftTool);
        setTextureName("minefantasy2:Tool/Crafting/"+name);
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
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		return ToolHelper.modifyDigOnQuality(stack, super.getDigSpeed(stack, block, meta));
	}
	@Override
	public float getEfficiency(ItemStack item) 
	{
		return material.getEfficiencyOnProperMaterial();
	}

	@Override
	public int getTier(ItemStack item) 
	{
		return material.getHarvestLevel();
	}

	@Override
	public String getToolType(ItemStack item)
	{
		return "knife";
	}
	
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
}
