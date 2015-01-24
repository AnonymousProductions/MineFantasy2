package minefantasy.mf2.item;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.mechanics.EventManagerMF;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemBandage extends Item
{
    private String name;
    private float healPwr;
	private float secondsToUse = 5F;

	public ItemBandage(String id, float healAmount) 
	{
        super();
        name = id;
        healPwr = healAmount;
        setMaxStackSize(16);
        setCreativeTab(CreativeTabMF.tabGadget);
        setTextureName("minefantasy2:Other/"+name);
		GameRegistry.registerItem(this, "MF_Aid_"+name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
    }

    @Override
	public EnumRarity getRarity(ItemStack item)
	{
    	if(healPwr <= 5)
    	{
    		return ToolListMF.poor;
    	}
    	return super.getRarity(item);
	}
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer user)
    {
    	if(canHeal(user) && user.hurtResistantTime <= 0)
    	{
    		user.setItemInUse(item, this.getMaxItemUseDuration(item));
    	}
        return item;
    }
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack item)
    {
        return EnumAction.block;
    }
    public int getMaxItemUseDuration(ItemStack item)
    {
        return (int)(20F*secondsToUse);
    }
    @Override
    public ItemStack onEaten(ItemStack item, World world, EntityPlayer player)
    {
    	return heal(player, player, item);
    }
    
    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
    {
    	super.onUsingTick(stack, player, count);
    	
    	if(!canHeal(player) &&  !player.worldObj.isRemote)
    	{
    		player.stopUsingItem();
    	}
    	if(count % 5 == 0)
    	{
    		player.playSound("dig.cloth", 1F, 0.005F);
    	}
    	player.swingItem();
    }
    
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity)
    {
        if(player.isSneaking() && isReadyToHeal(player, entity))
        {
        	heal(player, entity, itemstack);
        	return true;
        }
        return super.itemInteractionForEntity(itemstack, player, entity);
    }
	private ItemStack heal(EntityPlayer player, EntityLivingBase toHeal, ItemStack item) 
	{
		if(player.worldObj.isRemote)
		{
			return item;
		}
		
		if(toHeal != null)
        {
        	if(canHeal(toHeal))
        	{
        		toHeal.worldObj.playSoundEffect(toHeal.posX, toHeal.posY, toHeal.posZ, "dig.cloth", 1.0F, 3.0F);
        		player.swingItem();
        		
        		if(!player.worldObj.isRemote)
        		{
		        	toHeal.heal(healPwr);
		        	
		        	if(!player.capabilities.isCreativeMode)
		        	{
		        		item.stackSize --;
		        		if(item.stackSize <= 0)
		        		{
		        			
		        		}
		        	}
        		}
        		toHeal.getEntityData().setInteger(EventManagerMF.injuredNBT, 0);
        	}
        }
		 return item;
	}

	private boolean canHeal(EntityLivingBase toHeal)
	{
		return toHeal.hurtResistantTime <= 0 && (toHeal.getHealth() <= (toHeal.getMaxHealth()-1F) || EventManagerMF.getInjuredTime(toHeal) > 0) && !toHeal.isBurning();
	}
	
	public boolean isReadyToHeal(EntityPlayer player, EntityLivingBase patient)
	{
		int time = getUserHealTime(patient) + 1;
		patient.playSound("dig.cloth", 1F, 0.005F);
		player.swingItem();
		
		if(time >= (int)(4*secondsToUse))
		{
			setUserHealTime(patient, 0);
			return true;
		}
		
		setUserHealTime(patient, time);
		return false;
	}
	public void setUserHealTime(EntityLivingBase user, int value)
	{
		if(user.getEntityData() != null)
		{
			user.getEntityData().setInteger(healingID, value);
		}
	}
	public int getUserHealTime(EntityLivingBase user)
	{
		if(user.getEntityData() != null)
		{
			if(user.getEntityData().hasKey(healingID))
			{
				return user.getEntityData().getInteger(healingID);
			}
		}
		return 0;
	}
	private static final String healingID = "MF_Bandage_progress";
}
