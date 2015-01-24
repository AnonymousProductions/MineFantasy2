package minefantasy.mf2.item.archery;

import java.text.DecimalFormat;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.archery.IDisplayMFArrows;
import minefantasy.mf2.api.archery.ISpecialBow;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemBowMF extends ItemBow implements ISpecialBow, IDisplayMFArrows
{
	public static final DecimalFormat decimal_format = new DecimalFormat("#.##");
	public IIcon[] iconArray = new IIcon[3];
	private final EnumBowType model;
	private ToolMaterial material = ToolMaterial.WOOD;
	private int itemRarity;
	private float damage = 1.0F;
	
	public ItemBowMF(String name, ToolMaterial mat, EnumBowType type, int rarity)
    {
		this(name, (int)((float)mat.getMaxUses()*type.durability), type, mat.getDamageVsEntity(), rarity);
		material = mat;
    }
	
    private ItemBowMF(String name, int dura, EnumBowType type, float damage, int rarity)
    {
    	this.damage = (damage/2F) + 2.0F;
        model = type;
        this.maxStackSize = 1;
        this.setMaxDamage(dura);
        itemRarity = rarity;
        setTextureName("minefantasy2:Bow/"+name);
		this.setUnlocalizedName(name);
        GameRegistry.registerItem(this, name, MineFantasyII.MODID);
        setCreativeTab(CreativeTabMF.tabArcher);
    }
    
    @SideOnly(Side.CLIENT)
    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    @Override
    public void onPlayerStoppedUsing(ItemStack item, World world, EntityPlayer player, int time)
    {
        int power = (this.getMaxItemUseDuration(item) - time);
        power *= model.speed; // Speeds up the power in relation to ticks used
        
        power = (int)((float)power / 20F * getMaxPower());//scales the power down from full
        
        if(power > getMaxPower())power = (int)getMaxPower();//limits the power to max
        
        ArrowLooseEvent event = new ArrowLooseEvent(player, item, power);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        power = event.charge;
        
        boolean var5 = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, item) > 0;

        if (var5 || player.inventory.hasItem(Items.arrow))
        {
            float var7 = (float)power / 20.0F;
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

            if ((double)var7 < 0.1D)
            {
                return;
            }

            if (var7 > 1.0F)
            {
                var7 = 1.0F;
            }

            EntityArrow var8 = new EntityArrow(world, player, var7 * 2.0F);

            if (var7 == 1.0F)
            {
                var8.setIsCritical(true);
            }

            int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, item);

            if (var9 > 0)
            {
                var8.setDamage(var8.getDamage() + (double)var9 * 0.5D + 0.5D);
            }

            int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, item);

            if (var10 > 0)
            {
                var8.setKnockbackStrength(var10);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, item) > 0)
            {
                var8.setFire(100);
            }

        	item.damageItem(1, player);
            world.playSoundAtEntity(player, "minefantasy2:weapon.bowFire", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

            if (var5)
            {
                var8.canBePickedUp = 2;
            }
            else
            {
                player.inventory.consumeInventoryItem(Items.arrow);
            }
            var8 = (EntityArrow) modifyArrow(var8);

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(var8);
            }
        }
    }

    /**
     * Gets the power of the bow
     * 20 is the power of V bows(max)
     */
    private float getMaxPower() 
    {
    	return 20F * model.power;
	}
	public ItemStack onFoodEaten(ItemStack item, World world, EntityPlayer player)
    {
        return item;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack item)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack item)
    {
        return EnumAction.bow;
    }
    
    @Override
    public void addInformation(ItemStack item, EntityPlayer player, List desc, boolean flag) 
    {
		super.addInformation(item, player, desc, flag);
		
		desc.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("attribute.bowPower.name") + ": " + decimal_format.format(damage));
        
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        ArrowNockEvent event = new ArrowNockEvent(player, item);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }
        
        if (player.capabilities.isCreativeMode || player.inventory.hasItem(Items.arrow))
        {
            player.setItemInUse(item, this.getMaxItemUseDuration(item));
        }

        return item;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }
    public IIcon getIconIndex(ItemStack stack, int useRemaining)
    {
    	float multiplier = 1.0F / model.speed; //Reverses the decimal (eg. 0.5 becomes 2.0)
        if (stack != null)
        {
            if (useRemaining >= 18*multiplier) return iconArray[2];//The return values are
            if (useRemaining >  13*multiplier) return iconArray[1];//the icon indexes (in the /Tutorial/Items.png file)
            if (useRemaining >   0) return iconArray[0];
        }
        return this.getIconFromDamage(0);
    }
    
    @Override
    public void onUpdate(ItemStack item, World world, Entity entity, int i, boolean b)
    {
    	super.onUpdate(item, world, entity, i, b);
    	if(!item.hasTagCompound())
    		item.setTagCompound(new NBTTagCompound());
    	item.stackTagCompound.setInteger("Use", i);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister reg)
    {
    	this.itemIcon = reg.registerIcon(this.getIconString()+"_standby");
    	
        for (int i = 0; i < 3; ++i)
        {
            this.iconArray[i] = reg.registerIcon(this.getIconString() + "_pulling_" + (i));
        }
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * used to cycle through icons based on their used duration, i.e. for the bow
     */
    public IIcon getItemIconForUseDuration(int use)
    {
        return this.iconArray[use];
    }
    
	public int getDrawAmount(int timer) 
	{
		float multiplier = 1.0F / model.speed; //Reverses the decimal (eg. 0.5 becomes 2.0)
		if (timer >= 18 * multiplier)
            return 2;
        else if (timer > 13 * multiplier)
        	return 1;
        else if (timer > 0)
        	return 0;
		
		return -2;
	}
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		int lvl = itemRarity;
		
		EnumRarity[] rarity = new EnumRarity[]{EnumRarity.common, EnumRarity.uncommon, EnumRarity.rare, EnumRarity.epic};
		if(item.isItemEnchanted())
		{
			if(lvl == 0)
			{
				lvl++;
			}
			lvl ++;
		}
		if(lvl >= rarity.length)
		{
			lvl = rarity.length-1;
		}
		return rarity[lvl];
	}
	
	private ToolMaterial getMaterial() 
	{
		return material;
	}
	
	private EnumRarity rarity(ItemStack item, int lvl)
	{
		EnumRarity[] rarity = new EnumRarity[]{EnumRarity.common, EnumRarity.uncommon, EnumRarity.rare, EnumRarity.epic};
		if(item.isItemEnchanted())
		{
			if(lvl == 0)
			{
				lvl++;
			}
			lvl ++;
		}
		if(lvl >= rarity.length)
		{
			lvl = rarity.length-1;
		}
		return rarity[lvl];
	}
	
	public Entity modifyArrow(Entity arrow) 
	{
		if (getMaterial() == BaseMaterialMF.dragonforge.getToolConversion())
		{
			arrow.setFire(60);
		}
		if (getMaterial() == BaseMaterialMF.silver.getToolConversion() || getMaterial() == BaseMaterialMF.ornate.getToolConversion())
		{
			arrow.getEntityData().setBoolean("MF_Silverbow", true);
		}
		arrow.getEntityData().setFloat("MF_Bow_Damage", this.damage);
		return arrow;
	}
	
	public boolean canUseRenderer(ItemStack item) 
	{
		return true;
	}
	
	@Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
		if(this != ToolListMF.bows[0])
		{
			return;
		}
		
		addSet(list, ToolListMF.bows);
		addSet(list, ToolListMF.arrows);
		addSet(list, ToolListMF.bodkinArrows);
		addSet(list, ToolListMF.broadArrows);
    }

	private void addSet(List list, Item[] items) 
	{
		for(Item item:items)
		{
			list.add(new ItemStack(item));
		}
	}
	
	@Override
	public int getMaxDamage(ItemStack stack)
	{
    	if(ToolMaterialMF.isUnbreakable(material))
		{
    		ToolMaterialMF.setUnbreakable(stack);
		}
		return ToolHelper.setDuraOnQuality(stack, super.getMaxDamage());
	}
}
