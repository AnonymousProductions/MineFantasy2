package minefantasy.mf2.item.armour;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.armour.ArmourDesign;
import minefantasy.mf2.api.armour.IElementalResistance;
import minefantasy.mf2.api.armour.ItemArmourMFBase;
import minefantasy.mf2.item.list.ArmourListMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.material.BaseMaterialMF;
import minefantasy.mf2.mechanics.CombatMechanics;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArmourMF extends ItemArmourMFBase implements IElementalResistance
{
	private int itemRarity;
	private BaseMaterialMF baseMaterial;
	
	public ItemArmourMF(String name, BaseMaterialMF material, ArmourDesign AD, int slot, String tex, int rarity)
	{
		super(name, material.getArmourConversion(), AD, slot, tex);
		baseMaterial = material;
		this.setTextureName("minefantasy2:Apparel/"+AD.getName()+"/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		setCreativeTab(CreativeTabMF.tabArmour);
		
		itemRarity = rarity;
	}

	public ItemArmourMF(String name, BaseMaterialMF material, ArmourDesign AD, int slot, String tex, int rarity, float customBulk)
	{
		this(name, material, AD, slot, tex, rarity);
		this.suitBulk = customBulk;
	}
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		String tex = "minefantasy2:textures/models/armour/"+design.getName()+"/"+texture;
		if(type == null && canColour())//bottom layer
		{
			return tex + "_cloth.png";
		}
		return tex+".png";
	}
	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
	{
		if(source.isMagicDamage() && this.getMagicResistance(stack, source) > 0.0F)
		{
			return;
		}
		if(source.isFireDamage() && this.getFireResistance(stack, source) > 0.0F)
		{
			return;
		}
		if(ArmourListMF.isUnbreakable(baseMaterial, entity))
		{
			return;
		}
		super.damageArmor(entity, stack, source, damage, slot);
	}

	@Override
	public float getMagicResistance(ItemStack item, DamageSource source)
	{
		return material.magicResistanceModifier;
	}

	@Override
	public float getFireResistance(ItemStack item, DamageSource source)
	{
		return material.fireResistanceModifier;
	}

	@Override
	public float getArrowDeflection(ItemStack item, DamageSource source)
	{
		return (design == ArmourDesign.MAIL || design == ArmourDesign.PLATE) ? 0.5F : 0.0F;
	}

	@Override
	public float getBaseResistance(ItemStack item, DamageSource source)
	{
		if(baseMaterial == BaseMaterialMF.enderforge && source.getSourceOfDamage() != null && source.getSourceOfDamage() instanceof EntityEnderPearl)
		{
			return 100F;
		}
		return 0;
	}
	
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		int lvl = itemRarity + 1;
		
		if(item.isItemEnchanted())
		{
			if(lvl == 0)
			{
				lvl++;
			}
			lvl ++;
		}
		if(design == ArmourDesign.PLATE)
		{
			lvl ++;
		}
		if(lvl >= ToolListMF.rarity.length)
		{
			lvl = ToolListMF.rarity.length-1;
		}
		return ToolListMF.rarity[lvl];
	}
	@Override
	protected boolean isUnbreakable()
	{
		return baseMaterial == BaseMaterialMF.enderforge;
	}
	
	@Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
		if(this != ArmourListMF.leather[0])
		{
			return;
		}
		addSet(list, ArmourListMF.leather);
		
		addSet(list, ArmourListMF.chainmail);
		addSet(list, ArmourListMF.fieldplate);
    }

	private void addSet(List list, Item[] items) 
	{
		for(Item item:items)
		{
			list.add(new ItemStack(item));
		}
	}
	
	@Override
	protected float getACModifier(EntityLivingBase player, ItemStack armour, DamageSource source, double damage) 
	{
		if(source.getSourceOfDamage() != null && source.getSourceOfDamage() instanceof EntityLivingBase)
		{
			if(isUndedEfficient())
			{
				if(((EntityLivingBase)source.getSourceOfDamage()).isEntityUndead())
				{
					return CombatMechanics.specialUndeadModifier;
				}
				if(source.getSourceOfDamage().getClass().getName().contains("Werewolf"))
				{
					return CombatMechanics.specialWerewolfModifier;
				}
			}
		}
		return 1.0F;
	}

	private boolean isUndedEfficient()
	{
		return baseMaterial == BaseMaterialMF.silver || baseMaterial == BaseMaterialMF.ornate;
	}
	@SideOnly(Side.CLIENT)
    private IIcon clothIcon;
	@SideOnly(Side.CLIENT)
    private IIcon armourIcon;
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg)
    {
		if(!canColour())
		{
			super.registerIcons(reg);
		}
		else
		{
			String baseLayer = isMetal() ? "" : "_overlay";
			this.armourIcon = reg.registerIcon(this.getIconString()+baseLayer);
			String dyeLayer = isMetal() ? "_cloth" : "";
	        this.clothIcon = reg.registerIcon(this.getIconString()+dyeLayer);
		}
    }
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int dam, int layer)
    {
		if(!canColour())
		{
			return super.getIconFromDamage(dam);
		}
        return layer == 1 ?  armourIcon : clothIcon;
    }
	
	public boolean canColour()
	{
		if(material.name.equalsIgnoreCase("hide") || material.name.equalsIgnoreCase("rough"))
		{
			return false;
		}
		return design == ArmourDesign.LEATHER || design == ArmourDesign.CLOTH || isMetal();
	}
	public boolean isMetal()
	{
		return design == ArmourDesign.MAIL || design == ArmourDesign.PLATE;
	}
	
	//COLOURING
	private int defaultColour = 10511680;
	
	@SideOnly(Side.CLIENT)
	@Override
    public boolean requiresMultipleRenderPasses()
    {
        return canColour();
    }
	
	/**
     * Return whether the specified armor ItemStack has a color.
     */
	@Override
	public boolean hasColor(ItemStack item)
    {
        return !canColour() ? false : (!item.hasTagCompound() ? false : (!item.getTagCompound().hasKey("display", 10) ? false : item.getTagCompound().getCompoundTag("display").hasKey("color", 3)));
    }

    /**
     * Return the color for the specified armor ItemStack.
     */
	@Override
    public int getColor(ItemStack item)
    {
        if (!canColour())
        {
            return -1;
        }
        else
        {
            NBTTagCompound nbttagcompound = item.getTagCompound();

            if (nbttagcompound == null)
            {
                return defaultColour;
            }
            else
            {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
                return nbttagcompound1 == null ? defaultColour : (nbttagcompound1.hasKey("color", 3) ? nbttagcompound1.getInteger("color") : defaultColour);
            }
        }
    }
	
	/**
     * Remove the color from the specified armor ItemStack.
     */
	@Override
    public void removeColor(ItemStack item)
    {
        if (canColour())
        {
            NBTTagCompound nbttagcompound = item.getTagCompound();

            if (nbttagcompound != null)
            {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

                if (nbttagcompound1.hasKey("color"))
                {
                    nbttagcompound1.removeTag("color");
                }
            }
        }
    }

    @Override
    public void func_82813_b(ItemStack item, int colour)
    {
        if (!canColour())
        {
            throw new UnsupportedOperationException("Can\'t dye non-leather!");
        }
        else
        {
            NBTTagCompound nbttagcompound = item.getTagCompound();

            if (nbttagcompound == null)
            {
                nbttagcompound = new NBTTagCompound();
                item.setTagCompound(nbttagcompound);
            }

            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

            if (!nbttagcompound.hasKey("display", 10))
            {
                nbttagcompound.setTag("display", nbttagcompound1);
            }

            nbttagcompound1.setInteger("color", colour);
        }
    }
    @Override
    public float getMagicAC(float AC, DamageSource source, double damage, EntityLivingBase player)
	{
    	if(damage > 1 && material.isMythic)
    	{
    		return AC;
    	}
		return 0F;
	}

	@Override
	public String getSuitWeigthType(ItemStack item)
	{
		if(design == ArmourDesign.MAIL)
		{
			return "medium";
		}
		if(design == ArmourDesign.PLATE)
		{
			return "heavy";
		}
		return super.getSuitWeigthType(item);
	}
}
