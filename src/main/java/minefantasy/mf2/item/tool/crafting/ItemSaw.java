package minefantasy.mf2.item.tool.crafting;

import java.util.List;
import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.api.tool.IToolMF;
import minefantasy.mf2.api.weapon.IDamageType;
import minefantasy.mf2.config.ConfigTools;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;

import com.google.common.collect.Multimap;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Anonymous Productions
 */
public class ItemSaw extends ItemAxe implements IToolMaterial, IDamageType, IToolMF
{
	private Random rand = new Random();
	/**
	 */
    public ItemSaw(String name, ToolMaterial material, int rarity)
    {
        super(material);
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabCraftTool);
        this.hitDamage = (2.0F + material.getDamageVsEntity())/2F;
        setTextureName("minefantasy2:Tool/Crafting/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
		
		this.setHarvestLevel("axe", material.getHarvestLevel());
    }
    
    private int itemRarity;
	private double hitDamage;
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
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
    	
    }
    
    @Override
	public ToolMaterial getMaterial()
	{
		return toolMaterial;
	}
    
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		return super.getDigSpeed(stack, block, meta) / 10F;
	}

	public boolean onBlockDestroyedOld(ItemStack item, World world, Block block, int x, int y, int z, EntityLivingBase user)
	{
		if(user instanceof EntityPlayer && canAcceptCost(user))
		{
			breakChain(world, x, y, z, item, block, user, 32, block, world.getBlockMetadata(x, y, z));
		}
		return super.onBlockDestroyed(item, world, block, x, y, z, user);
	}
	
	private void breakChain(World world, int x, int y, int z, ItemStack item, Block block, EntityLivingBase user, int maxLogs, Block orient, int orientM) 
	{
		if(maxLogs > 0 && isLog(world, x, y, z, orient, orientM))
		{
			Block newblock = world.getBlock(x, y, z);
			breakSurrounding(item, world, newblock, x, y, z, user);
			newblock.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), EnchantmentHelper.getFortuneModifier(user));
			world.setBlockToAir(x, y, z);
			item.damageItem(1, user);
			
			maxLogs --;
			for(int x1 = -1; x1 <= 1; x1 ++)
			{
				for(int y1 = -1; y1 <= 1; y1 ++)
				{
					for(int z1 = -1; z1 <= 1; z1 ++)
					{
						breakChain(world, x+x1, y+y1, z+z1, item, newblock, user, maxLogs, orient, orientM);
					}
				}
			}
			if(user instanceof EntityPlayer)
			{
				tirePlayer(user, 2.0F);
			}
		}
	}

	public static boolean canAcceptCost(EntityLivingBase user)
	{
		return canAcceptCost(user, 0.1F);
	}
	public static boolean canAcceptCost(EntityLivingBase user, float cost)
	{
		if(user instanceof EntityPlayer && StaminaBar.isSystemActive)
		{
			return StaminaBar.isPercentStamAvailable(user, cost, true);
		}
		return true;
	}
	
	public static void tirePlayer(EntityLivingBase user, float points)
	{
		if(user instanceof EntityPlayer && StaminaBar.isSystemActive)
		{
			StaminaBar.modifyStaminaValue(user, -StaminaBar.getBaseDecayModifier(user, true, true)*points);
			StaminaBar.ModifyIdleTime(user, 5F*points);
		}
	}

	private boolean isLog(World world, int x, int y, int z, Block orient, int orientM) 
	{
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		if(block != null)
		{
			return block == orient && block.getMaterial() == Material.wood;
		}
		return false;
	}

	public void breakSurrounding(ItemStack item, World world, Block block, int x, int y, int z, EntityLivingBase user)
	{
		if(!world.isRemote && ForgeHooks.isToolEffective(item, block, world.getBlockMetadata(x, y, z)))
		{
			for(int x1 = -2; x1 <= 2; x1 ++)
			{
				for(int y1 = -2; y1 <= 2; y1 ++)
				{
					for(int z1 = -2; z1 <= 2; z1 ++)
					{
						ForgeDirection FD = getFDFor(user);
						int blockX = x+x1 + FD.offsetX;
						int blockY = y+y1 + FD.offsetY;
						int blockZ = z+z1 + FD.offsetZ;
						
						if(!(x1+FD.offsetX == 0 && y1+FD.offsetY == 0 &&  z1+FD.offsetZ == 0))
						{
							Block newblock = world.getBlock(blockX, blockY, blockZ);
							int m = world.getBlockMetadata(blockX, blockY, blockZ);
							
							if(item.getItemDamage() < item.getMaxDamage() && newblock != null && user instanceof EntityPlayer && newblock.getMaterial() == Material.leaves)
							{
								if(rand.nextFloat()*100F < (100F - ConfigTools.hvyDropChance))
								{
									newblock.dropBlockAsItem(world, blockX, blockY, blockZ, m, EnchantmentHelper.getFortuneModifier(user));
								}
								world.setBlockToAir(blockX, blockY, blockZ);
								item.damageItem(1, user);
							}
						}
					}	
				}	
			}
		}
	}

	private ForgeDirection getFDFor(EntityLivingBase user) 
	{
		return ForgeDirection.UNKNOWN;//TODO: FD
	}
	
	@Override
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", this.hitDamage, 0));
        return multimap;
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
	public float[] getDamageRatio(Object implement) 
	{
		return new float[]{1,0};
	}

	@Override
	public float getEfficiency(ItemStack item)
	{
		return toolMaterial.getEfficiencyOnProperMaterial();
	}

	@Override
	public int getTier(ItemStack item)
	{
		return toolMaterial.getHarvestLevel();
	}

	@Override
	public String getToolType(ItemStack item)
	{
		return "saw";
	}
	@Override
	public float getPenetrationLevel(Object implement)
	{
		return 0.5F;
	}
}
