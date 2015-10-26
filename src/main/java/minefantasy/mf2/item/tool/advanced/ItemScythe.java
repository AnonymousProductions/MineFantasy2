package minefantasy.mf2.item.tool.advanced;

import java.util.ArrayList;
import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.api.weapon.IDamageType;
import minefantasy.mf2.farming.FarmingHelper;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import minefantasy.mf2.item.tool.crafting.ItemSaw;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Anonymous Productions
 */
public class ItemScythe extends Item implements IToolMaterial, IDamageType
{
	private Random rand = new Random();
	private ToolMaterial toolMaterial;
	/**
	 */
    public ItemScythe(String name, ToolMaterial material, int rarity)
    {
    	this.toolMaterial = material;
    	this.setFull3D();
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabToolAdvanced);
        this.hitDamage = 2.0F + material.getDamageVsEntity();
        setTextureName("minefantasy2:Tool/Advanced/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
		this.maxStackSize = 1;
        this.setMaxDamage(material.getMaxUses());
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
	public ToolMaterial getMaterial()
	{
		return toolMaterial;
	}
	
	private boolean cutGrass(World world, int x, int y, int z, int r, EntityPlayer entity, boolean leaf) 
	{
    	boolean flag = false;
    	ItemStack item = entity.getHeldItem();
    	if(item == null)return false;
    	
		for(int x2 = -r; x2 <= r; x2 ++)
		{
			for(int y2 = -r; y2 <= r; y2 ++)
			{
				for(int z2 = -r; z2 <= r; z2 ++)
				{
					Block block = world.getBlock(x+x2, y+y2, z+z2);
					int meta = world.getBlockMetadata(x+x2, y+y2, z+z2);
					if(block != null)
					{
						Material m = block.getMaterial();
						if(canCutMaterial(m, block.getBlockHardness(world, x+x2, y+y2, z+z2), leaf))
						{
							if(getDistance(x+x2, y+y2, z+z2, x, y, z) < r*1)
							{
								flag = true;
								
								ArrayList<ItemStack> items = block.getDrops(world, x+x2, y+y2, z+z2, meta, 0);
								world.setBlockToAir(x+x2, y+y2, z+z2);
								world.playAuxSFXAtEntity(entity, 2001, x+x2, y+y2, z+z2, Block.getIdFromBlock(block) + (world.getBlockMetadata(x+x2, y+y2, z+z2) << 16));
								tryBreakFarmland(world, x+x2, y+y2-1, z+z2);
								if(!entity.capabilities.isCreativeMode)
								{
									ItemSaw.tirePlayer(entity, 1F);
									for (ItemStack drop : items)
						            {
						                if (world.rand.nextFloat() <= 1.0F)
						                {
						                    dropBlockAsItem_do(world, x+x2, y+y2, z+z2, drop);
						                }
						            }
								}
								item.damageItem(1, entity);
							}
						}
					}
				}
			}
		}
		return flag;
	}
	
	private void tryBreakFarmland(World world, int x, int y, int z)
	{
		Block base = world.getBlock(x, y, z);
		
		if(base != null && base == Blocks.farmland && FarmingHelper.didHarvestRuinBlock(world, true))
		{
			world.setBlock(x, y, z, Blocks.dirt);
		}
	}

	protected void dropBlockAsItem_do(World world, int x, int y, int z, ItemStack drop)
    {
        if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops"))
        {
            float var6 = 0.7F;
            double var7 = world.rand.nextFloat() * var6 + (1.0F - var6) * 0.5D;
            double var9 = world.rand.nextFloat() * var6 + (1.0F - var6) * 0.5D;
            double var11 = world.rand.nextFloat() * var6 + (1.0F - var6) * 0.5D;
            EntityItem var13 = new EntityItem(world, x + var7, y + var9, z + var11, drop);
            var13.delayBeforeCanPickup = 10;
            world.spawnEntityInWorld(var13);
        }
    }
	
	private boolean canCutMaterial(Material m, float str, boolean leaf)
	{
    	if(!leaf)
    	{
	    	if(str <= 0.0F)
	    	{
				return
				   m == Material.vine
				|| m == Material.plants
				|| m == Material.grass
				;}
	    	else return false;
    	}
    	return m == Material.leaves || m == Material.vine;
	}

	public double getDistance(double x, double y, double z, int posX, int posY, int posZ)
    {
        double var7 = posX - x;
        double var9 = posY - y;
        double var11 = posZ - z;
        return MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
    }
	
	@Override
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", this.hitDamage, 0));
        return multimap;
    }
	
	@Override
    public boolean onItemUse(ItemStack hoe, EntityPlayer player, World world, int x, int y, int z, int facing, float pitch, float yaw, float pan)
    {
        if (!player.canPlayerEdit(x, y, z, facing, hoe) || !ItemSaw.canAcceptCost(player))
        {
            return false;
        }
        else
        {
            Block block = world.getBlock(x, y, z);

            if(block != null)
            {
            	Material m = block.getMaterial();
            	float hard = block.getBlockHardness(world, x, y, z);
            	if(this.canCutMaterial(m, hard, false))
            	{
            		if(cutGrass(world, x, y, z, 5, player, false))
            		{
            			player.swingItem();
            		}
            	}
            	else
            	if(this.canCutMaterial(m, hard, true))
            	{
            		if(cutGrass(world, x, y, z, 3, player, true))
            		{
            			player.swingItem();
            		}
            	}
            }
        }
        return false;
    }
	
	
	@Override
	public int getMaxDamage(ItemStack stack)
	{
		return ToolHelper.setDuraOnQuality(stack, super.getMaxDamage());
	}

	@Override
	public float[] getDamageRatio(Object... implement) 
	{
		return new float[]{1,0, 2};
	}
	@Override
	public float getPenetrationLevel(Object implement)
	{
		return 0F;
	}
}
