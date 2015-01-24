package minefantasy.mf2.api.armour;

import java.text.DecimalFormat;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.armour.ArmourDesign;
import minefantasy.mf2.api.armour.ArmourMaterialMF;
import minefantasy.mf2.api.armour.IArmourMF;
import minefantasy.mf2.api.helpers.ArmourCalculator;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.weapon.DamageSourceAP;
import minefantasy.mf2.mechanics.MonsterUpgrader;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ItemArmourMFBase extends ItemArmor implements ISpecialArmor, IArmourMF, IArmourRating
{
	public static ArmorMaterial baseMaterial = EnumHelper.addArmorMaterial("MF Armour Base", 0, new int[]{2, 6, 5, 2}, 0);
	private int piece;
	private int baseRating;
	public float baseAR;
	public int enchantment;
	public String texture;
	public float armourWeight;
	public ArmourMaterialMF material;
	public ArmourDesign design;
	protected float suitBulk;
	
	public static final DecimalFormat decimal_format = new DecimalFormat("#.#");
	
	public ItemArmourMFBase(String name, ArmourMaterialMF material, ArmourDesign AD, int slot, String tex)
	{
		super(baseMaterial, 0, slot);
		this.material = material;
		baseAR = material.baseAR;
		armourWeight = AD.getWeight() * material.armourWeight;
		enchantment = material.enchantment;
		this.piece = slot;
		design = AD;
		suitBulk = design.getBulk();
		texture = tex;
		float baseDura = (float)material.durability * design.getDurability()/2F;
		float dura = baseDura /2F + (baseDura /2F * ArmourCalculator.sizes[slot] / ArmourCalculator.sizes[1]);
		this.setMaxDamage((int)dura);
		this.setUnlocalizedName(name);
		
		baseRating = ArmourCalculator.translateToVanillaAR(getProtectionRatio()+1F, baseMaterial.getDamageReductionAmount(slot), 15);
	}
	/*  Piece | Slot
	 *  0     | 3
	 *  1     | 2
	 *  2     | 1
	 *  3     | 0 */
	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
	{
		stack.damageItem((int)(damage/5F)+1, entity);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armour, int slot)
	{
		/*
		float max = (float)armour.getMaxDamage();
		float dam = max - (float)armour.getItemDamage();
		
		return (int)Math.round(5F / max * dam);
		*/
		return baseRating;
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armour, DamageSource source, double damage, int slot) 
	{
		float AC = getProtectionRatio();
		
		if(ArmourCalculator.advancedDamageTypes && !player.worldObj.isRemote)
		{
			AC = ArmourCalculator.modifyACForType(source, AC, design.getProtectiveTraits()[0], design.getProtectiveTraits()[1]);
		}
		AC *= getACModifier(player, armour, source, damage);
		
		if(source == DamageSource.fall && design == ArmourDesign.PLATE)
		{
			AC = 1F/material.armourWeight;
		}
		else if(source.isMagicDamage())
		{
			AC = getMagicAC(AC, source, damage, player);
		}
		else if(source.isUnblockable())
		{
			AC = 0.0F;
		}
		AC = ToolHelper.modifyArmourRating(armour, AC);
		if(player.getEntityData().hasKey("MF_ZombieArmour"))
		{
			AC -= 1.5F;
			if(AC < 0)AC = 0;
		}
		AC++;//because 1.0AC = no armour so it adds ontop of this
		
		double totalPercent = ArmourCalculator.convertToPercent(AC);
		double maxPercent = 0.99D;//max percentage is 99% damage to avoid immunity
		
		if(totalPercent > maxPercent)
		{
			totalPercent = maxPercent;
		}
		
		double percent = totalPercent*scalePiece();
		
		float max = (float) ((getMaxDamage() + 1 - armour.getItemDamage()) * (percent*25F));//I don't know how this variable works
		
		return new ArmorProperties(0, percent, (int)max);
	}
	
	public float getMagicAC(float AC, DamageSource source, double damage, EntityLivingBase player)
	{
		return 0F;
	}
	protected float getACModifier(EntityLivingBase player, ItemStack armour, DamageSource source, double damage) 
	{
		return 1.0F;
	}
	public float scalePiece()
	{
		return ArmourCalculator.sizes[piece];
	}

	private float getProtectionRatio() 
	{
		return material.baseAR*design.getRating();
	}

	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack armour, EntityPlayer user, List list, boolean extra) 
    {
        super.addInformation(armour, user, list, extra);
        
        if(ArmourCalculator.advancedDamageTypes)
        {
        	list.add("");
        	
        	float cut = design.getProtectiveTraits()[0]*100F;
        	float blunt = design.getProtectiveTraits()[1]*100F;
        	
    		list.add(StatCollector.translateToLocal("attribute.armour.cuttingresistance") + " = " +(int)cut + "%");
    		list.add(StatCollector.translateToLocal("attribute.armour.bluntresistance") + " = " +(int)blunt + "%");
        }
        list.add(StatCollector.translateToLocal("attribute.weight.name") + ": " + Math.round(getPieceWeight(armour, piece)));
        /*
        int rating = ArmourCalculator.getArmourRatingLevel(user, armour, slot);
        int equipped = ArmourCalculator.getArmourRatingLevel(user, user.getCurrentArmor(3-slot), slot);
        
        if(equipped > 0 && rating != equipped)
        {
        	int d = rating-equipped;
        	if(d > 0)
        	{
        		attatch += EnumChatFormatting.DARK_GREEN;
        	}
        	if(d < 0)
        	{
        		attatch += EnumChatFormatting.RED;
        	}
        	attatch += " (" + (d > 0 ? "+" : "") + d +")";
        }
        if(MineFantasyAPI.isInDebugMode)
        {
		    list.add(decimal_format.format(getPieceWeight(armour, slot)) + " Kg" + " (" + getACName() + " Armour" + ")");
		    list.add("Bulk: "+getBulkDisplay(design.bulk));
        }
        list.add(EnumChatFormatting.BLUE+StatCollector.translateToLocal("attribute.armour.protection") + " " + rating + attatch);
        */
    }

	private void addModifier(List list, float ratio, String name)
	{
		if(getProtectionRatio() != ratio)
        {
        	if(getProtectionRatio() > ratio)
        	{
        		float percent = (ratio / getProtectionRatio())-1F;
                list.add(EnumChatFormatting.RED+
                        StatCollector.translateToLocalFormatted("attribute.modifier.take."+ 1,
                                decimal_format.format(-(float)percent * 100),
                                        StatCollector.translateToLocal("attribute.armour."+name)));
            }else
            {
            	float percent = (ratio / getProtectionRatio())-1F;
                list.add(EnumChatFormatting.DARK_GREEN+
                        StatCollector.translateToLocalFormatted("attribute.modifier.plus."+ 1,
                                decimal_format.format((float)percent * 100),
                                        StatCollector.translateToLocal("attribute.armour."+name)));
            }
        }
	}
	/**
	 * Gets the bulk for display(Make sure to count the bulk for the piece)
	 */
	public static int getBulkDisplay(float bulk)
	{
		return (int) (bulk*100);
	}
	public ArmourMaterialMF getMaterial()
	{
		return this.material;
	}
	
	@Override
	public int getMaxDamage(ItemStack stack)
	{
    	if(isUnbreakable())
		{
    		setUnbreakable(stack);
		}
		return ToolHelper.setDuraOnQuality(stack, super.getMaxDamage());
	}

	protected boolean isUnbreakable()
	{
		return false;
	}

	@Override
	public float scalePiece(ItemStack item) 
	{
		return scalePiece();
	}
	
	@Override
	public int getItemEnchantability()
	{
		return material.enchantment;
	}

	@Override
	public float getPieceWeight(ItemStack item, int slot) 
	{
		return armourWeight * ArmourCalculator.sizes[slot];
	}
	
	public static void setUnbreakable(ItemStack tool)
	{
		if(!tool.hasTagCompound())
		{
			tool.setTagCompound(new NBTTagCompound());
		}
		tool.getTagCompound().setBoolean("Unbreakable", true);
	}
	
	private static final float[] AC = new float[]{40F, 60F};

	@Override
	public String getSuitWeigthType(ItemStack item)
	{
		if(design == ArmourDesign.CLOTH)return null;
		
		return armourWeight >= ArmourCalculator.ACArray[1] ? "heavy" : armourWeight >= ArmourCalculator.ACArray[0] ? "medium" : "light";
	}
}
