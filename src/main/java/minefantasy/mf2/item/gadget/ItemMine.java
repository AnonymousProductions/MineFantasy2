package minefantasy.mf2.item.gadget;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.entity.EntityMine;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemMine extends Item
{
    public ItemMine(String name)
    {
    	this.maxStackSize = 16;
        this.setCreativeTab(CreativeTabMF.tabGadget);
        setTextureName("minefantasy2:Other/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack item)
    {
        return EnumAction.block;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer user)
    {
    	if(!user.isSwingInProgress)
    	{
    		world.playSoundEffect((double)user.posX, (double)user.posY + 1.5D, (double)user.posZ, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
    		user.setItemInUse(item, getMaxItemUseDuration(item));
    	}
    	return item;
    }
    @Override
    public int getMaxItemUseDuration(ItemStack item)
    {
        return 35;
    }
    public ItemStack onEaten(ItemStack item, World world, EntityPlayer user)
    {
    	world.playSoundEffect(user.posX, user.posY+1.5, user.posZ, "random.click", 1.0F, 1.0F);
    	user.swingItem();
        if (!user.capabilities.isCreativeMode)
        {
            --item.stackSize;
        }

        if (!world.isRemote)
        {
        	world.spawnEntityInWorld(new EntityMine(world, user).setType(getFilling(item), getCasing(item)));
        }

        return item;
    }
    
    @Override
    public void addInformation(ItemStack item, EntityPlayer user, List list, boolean fullInfo)
    {
    	super.addInformation(item, user, list, fullInfo);
    	
    	EnumExplosiveType fill = EnumExplosiveType.getType(getFilling(item));
    	EnumCasingType casing = EnumCasingType.getType(getCasing(item));
    	int damage = (int) (fill.damage*casing.damageModifier);
    	float range = fill.range*casing.rangeModifier;
    	
    	list.add(StatCollector.translateToLocal("bomb.case."+casing.name+".name"));
    	list.add(StatCollector.translateToLocal("bomb.damage.name")+": "+damage);
    	list.add(StatCollector.translateToLocalFormatted("bomb.range.metric.name", range));
    }
    @Override
    public String getUnlocalizedName(ItemStack item)
    {
    	EnumExplosiveType type = EnumExplosiveType.getType(getFilling(item));
    	return "item.mine_"+type.name;
    }
    private final String fillingNBT = "MineFantasy_ExplosiveType";
    private final String casingNBT = "MineFantasy_CaseType";
    
    /**
     * 0 = Basic
     * 1 = Shrapnel
     * 2 = Fire
     */
    public void setFilling(ItemStack item, byte filling)
    {
    	NBTTagCompound nbt = getNBT(item);
    	nbt.setByte(fillingNBT, filling);
    }
    public byte getFilling(ItemStack item)
    {
    	NBTTagCompound nbt = getNBT(item);
    	if(nbt.hasKey(fillingNBT))
    	{
    		return nbt.getByte(fillingNBT);
    	}
    	return (byte)0;
    }
    
    /**
     * 0 = Ceramic
     * 1 = Iron
     */
    public void setCasing(ItemStack item, byte casing)
    {
    	NBTTagCompound nbt = getNBT(item);
    	nbt.setByte(casingNBT, casing);
    }
    public byte getCasing(ItemStack item)
    {
    	NBTTagCompound nbt = getNBT(item);
    	if(nbt.hasKey(casingNBT))
    	{
    		return nbt.getByte(casingNBT);
    	}
    	return (byte)0;
    }
    
    public NBTTagCompound getNBT(ItemStack item)
    {
    	if(!item.hasTagCompound())item.setTagCompound(new NBTTagCompound());
    	return item.getTagCompound();
    }
    
    public ItemStack createMine(byte casing, byte filling, int stackSize)
    {
    	ItemStack mine = new ItemStack(this, stackSize);
    	
    	setFilling(mine, filling);
    	setCasing(mine, casing);
    	return mine;
    }
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
		list.add(createMine((byte)0, (byte)0, 1));
		list.add(createMine((byte)0, (byte)1, 1));
		list.add(createMine((byte)0, (byte)2, 1));
		
		list.add(createMine((byte)1, (byte)0, 1));
		list.add(createMine((byte)1, (byte)1, 1));
		list.add(createMine((byte)1, (byte)2, 1));
		
		list.add(createMine((byte)2, (byte)0, 1));
		list.add(createMine((byte)2, (byte)1, 1));
		list.add(createMine((byte)2, (byte)2, 1));
    }

    //TODO Icons
    @SideOnly(Side.CLIENT)
	public IIcon getIcon(byte type)
	{
		return mines[type];
	}
    private IIcon[] mines = new IIcon[3];
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg)
    {
        this.itemIcon = mines[0] = reg.registerIcon("minefantasy2:Other/mine_ceramic");
        mines[1] = reg.registerIcon("minefantasy2:Other/mine_iron");
        mines[2] = reg.registerIcon("minefantasy2:Other/mine_obsidian");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack item)
    {
    	int type = (int)getCasing(item);
    	return mines[type];
    }
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack item, int layer)
    {
    	if(layer > 0)
    	{
	    	int type = (int)getFilling(item);
	    	if(type != 0)
	    	{
	    		return ToolListMF.bomb_custom.icons[type-1];
	    	}
    	}
    	return getIconIndex(item);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
    	return true;
    }
    
    @Override
    public EnumRarity getRarity(ItemStack item)
    {
    	if(getFilling(item) >= 2 || getCasing(item) >= 2)
    	{
    		return EnumRarity.uncommon;
    	}
    	return EnumRarity.common;
    }
}