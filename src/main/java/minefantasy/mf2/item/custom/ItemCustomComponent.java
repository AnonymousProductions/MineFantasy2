package minefantasy.mf2.item.custom;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.GuiHelper;
import minefantasy.mf2.api.material.CustomMaterial;
import minefantasy.mf2.item.gadget.EnumCasingType;
import minefantasy.mf2.item.gadget.EnumExplosiveType;
import minefantasy.mf2.item.gadget.EnumFuseType;
import minefantasy.mf2.item.gadget.EnumPowderType;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCustomComponent extends Item 
{
	@SideOnly(Side.CLIENT)
	public IIcon baseTex;
	private String name;
	private float mass;
	
	public ItemCustomComponent(String name, float mass)
	{
		this.name = name;
		this.setCreativeTab(CreativeTabMF.tabMaterials);
		GameRegistry.registerItem(this, "custom_"+name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
		this.mass=mass;
	}
	
	 @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
	 
    }
	 
	public float getWeightInKg(ItemStack tool)
    {
    	CustomMaterial base = getBase(tool);
    	if(base != null)
    	{
    		return base.density * mass;
    	}
    	return mass;
    }
 
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack tool, EntityPlayer user, List list, boolean fullInfo)
    {
    	super.addInformation(tool, user, list, fullInfo);
    	
    	float mass = getWeightInKg(tool);
    	
    	CustomMaterial base = getBase(tool);
    	if(base != null)
    	{
    		list.add(EnumChatFormatting.GOLD + base.getMaterialString());
    	}
    	list.add(CustomMaterial.getWeightString(mass));
    		
    }
	 
	@Override
    public String getItemStackDisplayName(ItemStack tool)
    {
    	CustomMaterial head = getBase(tool);
    	return StatCollector.translateToLocalFormatted("item.commodity_"+ name +".name", StatCollector.translateToLocal("material."+head.name.toLowerCase() + ".name"));
    }
	
	public CustomMaterial getBase(ItemStack haft)
	{
		return CustomMaterial.getMaterialFor(haft, "base");
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getRenderPasses(int metadata)
    {
        return 1;
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack item, int layer)
    {
		CustomMaterial base = getBase(item);
    	if(base != null)
    	{
    		return base.getColourInt();
    	}
    	return super.getColorFromItemStack(item, layer);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack item, int layer)
    {
		return baseTex;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
    	return true;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg)
    {
        baseTex = reg.registerIcon("minefantasy2:custom/commodity/"+name);
    }
    
	public ItemStack createComm(String base) 
	{
		ItemStack item = new ItemStack(this);
		CustomMaterial.addMaterial(item, "base", base);
		return item;
	}
}
