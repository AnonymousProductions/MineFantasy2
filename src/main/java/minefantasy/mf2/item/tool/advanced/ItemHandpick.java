package minefantasy.mf2.item.tool.advanced;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	private String Name;
    public ItemHandpick(String name, ToolMaterial material, int rarity)
    {
        super(material);
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabToolAdvanced);
        
        setUnlocalizedName("minefantasy2:Tool/Advanced/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		setMaxDamage(material.getMaxUses()/2);
		Name=name;
    }
    
    public String getName(){
    	return Name;
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
	public float getDigSpeed(ItemStack stack, IBlockState state)
	{
		return Math.max(1.0F, ToolHelper.modifyDigOnQuality(stack, super.getDigSpeed(stack, state))/2F);
	}
	@Override
	public boolean onBlockDestroyed(ItemStack item, World world, Block block, BlockPos pos, EntityLivingBase user)
	{
		int m = block.getMetaFromState(world.getBlockState(pos));
		List<ItemStack>drops = block.getDrops(world, pos, block.getStateFromMeta(0), m);
		
		if(drops != null && !drops.isEmpty())
		{
			Iterator<ItemStack> list = drops.iterator();
			while(list.hasNext())
			{
				ItemStack drop = list.next();
				if(!drop.isItemEqual(new ItemStack(block, 1, m)) && !(drop.getItem() instanceof ItemBlock) && rand.nextFloat() < getDoubleDropChance())
				{
					dropItem(world, pos, drop.copy());
				}
			}
		}
		
		if(!world.isRemote)
		{
        	int meta = block.getMetaFromState(world.getBlockState(pos));
        	int harvestlvl = this.getMaterial().getHarvestLevel();
        	int fortune = EnchantmentHelper.getFortuneModifier(user);
        	boolean silk = EnchantmentHelper.getSilkTouchModifier(user);
        	
        	ArrayList<ItemStack>specialdrops = RandomOre.getDroppedItems(block, meta, harvestlvl, fortune, silk, pos.getY());
	    	
	    	if(specialdrops != null && !specialdrops.isEmpty())
	    	{
	    		Iterator list = specialdrops.iterator();
	    		
	    		while(list.hasNext())
	    		{
	    			ItemStack newdrop = (ItemStack)list.next();
	    			
			    	if(newdrop != null)
			    	{
			    		if(newdrop.stackSize < 1)newdrop.stackSize = 1;
			    		
			    		dropItem(world, pos, newdrop);
			    	}
	    		}
	    	}
		}
		return super.onBlockDestroyed(item, world, block, pos, user);
	}
	private void dropItem(World world, BlockPos pos, ItemStack drop) 
	{
		if(world.isRemote)return;
		
		EntityItem dropItem = new EntityItem(world, pos.getX()+0.5D, pos.getY()+0.5D, pos.getZ()+0.5D, drop);
		dropItem.setDefaultPickupDelay();
		world.spawnEntityInWorld(dropItem);
	}

	private float getDoubleDropChance()
	{
		return ConfigTools.handpickBonus/100F;
	}
	
	@Override
	public int getMaxDamage(ItemStack stack)
	{
    	if(ToolMaterialMF.isUnbreakable(toolMaterial))
		{
    		ToolMaterialMF.setUnbreakable(stack);
		}
		return ToolHelper.setDuraOnQuality(stack, super.getMaxDamage());
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
