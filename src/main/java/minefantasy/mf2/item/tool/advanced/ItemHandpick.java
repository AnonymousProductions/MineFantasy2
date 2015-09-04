package minefantasy.mf2.item.tool.advanced;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.mining.RandomOre;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.config.ConfigTools;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Anonymous Productions
 */
public class ItemHandpick extends ItemPickaxe implements IToolMaterial
{
	private Random rand = new Random();
	
	/**
	 * Handpicks are lighter and weaker picks, they are far less effective but can yield double drops.
	 * Breaking some blocks can also yield special drops
	 * 
	 * They however have half the durability and dig speed, making them less efficient tools.
	 */
    public ItemHandpick(String name, ToolMaterial material, int rarity)
    {
        super(material);
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabToolAdvanced);
        
        setTextureName("minefantasy2:Tool/Advanced/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
		setMaxDamage(material.getMaxUses()/2);
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
    
    @Override
	public ToolMaterial getMaterial()
	{
		return toolMaterial;
	}
    
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		return Math.max(1.0F, ToolHelper.modifyDigOnQuality(stack, super.getDigSpeed(stack, block, meta))/2F);
	}
	@Override
	public boolean onBlockDestroyed(ItemStack item, World world, Block block, int x, int y, int z, EntityLivingBase user)
	{
		int m = world.getBlockMetadata(x, y, z);
		ArrayList<ItemStack>drops = block.getDrops(world, x, y, z, 0, m);
		
		if(drops != null && !drops.isEmpty())
		{
			Iterator<ItemStack> list = drops.iterator();
			while(list.hasNext())
			{
				ItemStack drop = list.next();
				if(!drop.isItemEqual(new ItemStack(block, 1, m)) && !(drop.getItem() instanceof ItemBlock) && rand.nextFloat() < getDoubleDropChance())
				{
					dropItem(world, x, y, z, drop.copy());
				}
			}
		}
		
		if(!world.isRemote)
		{
        	int meta = world.getBlockMetadata(x, y, z);
        	int harvestlvl = this.getMaterial().getHarvestLevel();
        	int fortune = EnchantmentHelper.getFortuneModifier(user);
        	boolean silk = EnchantmentHelper.getSilkTouchModifier(user);
        	
        	ArrayList<ItemStack>specialdrops = RandomOre.getDroppedItems(block, meta, harvestlvl, fortune, silk, y);
	    	
	    	if(specialdrops != null && !specialdrops.isEmpty())
	    	{
	    		Iterator list = specialdrops.iterator();
	    		
	    		while(list.hasNext())
	    		{
	    			ItemStack newdrop = (ItemStack)list.next();
	    			
			    	if(newdrop != null)
			    	{
			    		if(newdrop.stackSize < 1)newdrop.stackSize = 1;
			    		
			    		dropItem(world, x, y, z, newdrop);
			    	}
	    		}
	    	}
		}
		return super.onBlockDestroyed(item, world, block, x, y, z, user);
	}
	private void dropItem(World world, int x, int y, int z, ItemStack drop) 
	{
		if(world.isRemote)return;
		
		EntityItem dropItem = new EntityItem(world, x+0.5D, y+0.5D, z+0.5D, drop);
		dropItem.delayBeforeCanPickup = 10;
		world.spawnEntityInWorld(dropItem);
	}

	private float getDoubleDropChance()
	{
		return ConfigTools.handpickBonus/100F;
	}
	@Override
	public int getMaxDamage(ItemStack stack)
	{
		return ToolHelper.setDuraOnQuality(stack, super.getMaxDamage());
	}
}
