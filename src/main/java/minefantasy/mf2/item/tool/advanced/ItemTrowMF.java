package minefantasy.mf2.item.tool.advanced;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * @author Anonymous Productions
 */
public class ItemTrowMF extends ItemSpade implements IToolMaterial
{
	private Random rand = new Random();
	/**
	 * The trow is a light, less efficient varient of shovel. It is far slower and half the durability of their larger counterparts
	 * but are able to dig up special drops like flint and seeds
	 */
    public ItemTrowMF(String name, ToolMaterial material, int rarity)
    {
        super(material);
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabToolAdvanced);
        
        setTextureName("minefantasy2:Tool/Advanced/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		setMaxDamage(getMaxDamage()/2);
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
    @Override
	public ToolMaterial getMaterial()
	{
		return toolMaterial;
	}
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		return Math.max(1.0F, ToolHelper.modifyDigOnQuality(stack, super.getDigSpeed(stack, block, meta))*0.5F);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack item, World world, Block block, int x, int y, int z, EntityLivingBase user)
	{
		int m = world.getBlockMetadata(x, y, z);
		alwaysDropFlint(block, item, world, user, x, y, z);
		return super.onBlockDestroyed(item, world, block, x, y, z, user);
	}
	
	private void alwaysDropFlint(Block block, ItemStack item, World world, EntityLivingBase user, int x, int y, int z) 
	{
		if(block == Blocks.gravel)
		{
			world.setBlockToAir(x, y, z);
			int loot = 0;
			int enc = EnchantmentHelper.getFortuneModifier(user);
			if(enc > 0)
			{
				loot = rand.nextInt(enc);
			}
			dropItem(world, x, y, z, new ItemStack(Items.flint, 1 + loot));
		}
	}
	private void dropItem(World world, int x, int y, int z, ItemStack drop) 
	{
		if(world.isRemote)return;
		
		EntityItem dropItem = new EntityItem(world, x+0.5D, y+0.5D, z+0.5D, drop);
		dropItem.delayBeforeCanPickup = 10;
		world.spawnEntityInWorld(dropItem);
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
}
