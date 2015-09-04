package minefantasy.mf2.item.tool.advanced;

import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.config.ConfigTools;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import minefantasy.mf2.item.tool.crafting.ItemSaw;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Anonymous Productions
 */
public class ItemHvyPick extends ItemPickaxe implements IToolMaterial
{
	private Random rand = new Random();
	/**
	 * A Heavy pick is designed more for quarry use, it is significantly slower than a normal pick, has 2 less harvest levels
	 * but has 2x the durability.
	 * 
	 * These picks mine a 3x3 area of blocks, though with their lessened harvest, most ores will be avoided
	 * They however have a reduced drop chance
	 */
    public ItemHvyPick(String name, ToolMaterial material, int rarity)
    {
        super(material);
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabToolAdvanced);
        
        setTextureName("minefantasy2:Tool/Advanced/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
		
		this.setHarvestLevel("pickaxe", material.getHarvestLevel());
		this.setMaxDamage(material.getMaxUses()*2);
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
		return ToolHelper.modifyDigOnQuality(stack, super.getDigSpeed(stack, block, meta)) / 10F;
	}
	@Override
	public boolean onBlockDestroyed(ItemStack item, World world, Block block, int x, int y, int z, EntityLivingBase user)
	{
		if(!world.isRemote && ForgeHooks.isToolEffective(item, block, world.getBlockMetadata(x, y, z)) && ItemSaw.canAcceptCost(user))
		{
			for(int x1 = -1; x1 <= 1; x1 ++)
			{
				for(int y1 = -1; y1 <= 1; y1 ++)
				{
					for(int z1 = -1; z1 <= 1; z1 ++)
					{
						ForgeDirection FD = getFDFor(user);
						int blockX = x+x1 + FD.offsetX;
						int blockY = y+y1 + FD.offsetY;
						int blockZ = z+z1 + FD.offsetZ;
						
						if(!(x1+FD.offsetX == 0 && y1+FD.offsetY == 0 &&  z1+FD.offsetZ == 0))
						{
							Block newblock = world.getBlock(blockX, blockY, blockZ);
							int m = world.getBlockMetadata(blockX, blockY, blockZ);
							
							if(newblock != null && user instanceof EntityPlayer && ForgeHooks.canHarvestBlock(newblock, (EntityPlayer) user, m) && ForgeHooks.isToolEffective(item, newblock, m))
							{
								if(rand.nextFloat()*100F < (100F - ConfigTools.hvyDropChance))
								{
									newblock.dropBlockAsItem(world, blockX, blockY, blockZ, m, EnchantmentHelper.getFortuneModifier(user));
								}
								world.setBlockToAir(blockX, blockY, blockZ);
								item.damageItem(1, user);
								ItemSaw.tirePlayer(user, 1F);
							}
						}
					}	
				}	
			}
		}
		return super.onBlockDestroyed(item, world, block, x, y, z, user);
	}
	
	private ForgeDirection getFDFor(EntityLivingBase user) 
	{
		return ForgeDirection.UNKNOWN;//TODO: FD
	}
	@Override
	public int getMaxDamage(ItemStack stack)
	{
		return ToolHelper.setDuraOnQuality(stack, super.getMaxDamage());
	}
}
