package minefantasy.mf2.item.tool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.CustomToolHelper;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.material.CustomMaterial;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.api.tool.IToolMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.CustomToolListMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.ForgeHooks;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Anonymous Productions
 */
public class ItemShearsMF extends ItemShears implements IToolMaterial, IToolMF
{
	private ToolMaterial toolMaterial;
	private int tier;
	private String name;
	
    public ItemShearsMF(String name, ToolMaterial material, int rarity, int tier)
    {
        super();
        this.name=name;
        this.tier = tier;
        itemRarity = rarity;
        toolMaterial = material;
        setCreativeTab(CreativeTabMF.tabOldTools);
        this.setMaxDamage(material.getMaxUses());
        setTextureName("minefantasy2:Tool/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
    }
    
	//===================================================== CUSTOM START =============================================================\\
	private boolean isCustom = false;
	public ItemShearsMF setCustom(String s)
	{
		canRepair = false;
		setTextureName("minefantasy2:custom/tool/"+s+"/"+name);
		isCustom = true;
		return this;
	}
    private float efficiencyMod = 1.0F;
    public ItemShearsMF setEfficiencyMod(float efficiencyMod)
    {
    	this.efficiencyMod = efficiencyMod;
    	return this;
    }
    
    protected float getWeightModifier(ItemStack stack)
	{
    	return CustomToolHelper.getWeightModifier(stack, 1.0F);
	}
	private IIcon detailTex = null;
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg)
    {
    	if(isCustom)
    	{
    		detailTex = reg.registerIcon(this.getIconString()+"_detail");
    	}
    	super.registerIcons(reg);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return isCustom;
    }
    @Override
    public IIcon getIcon(ItemStack stack, int pass)
    {
    	if(isCustom && pass > 0 && detailTex != null)
    	{
    		return detailTex;
    	}
        return super.getIcon(stack, pass);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack item, int layer)
    {
    	return CustomToolHelper.getColourFromItemStack(item, layer, super.getColorFromItemStack(item, layer));
    }
    @Override
	public int getMaxDamage(ItemStack stack)
	{
		return CustomToolHelper.getMaxDamage(stack, super.getMaxDamage(stack));
	}
	public ItemStack construct(String main, String haft)
	{
		return CustomToolHelper.construct(this, main, haft);
	}
	protected int itemRarity;
    @Override
	public EnumRarity getRarity(ItemStack item)
	{
    	return CustomToolHelper.getRarity(item, itemRarity);
	}
    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass)
    {
    	return CustomToolHelper.getHarvestLevel(stack, super.getHarvestLevel(stack, toolClass));
    }
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
    	if(isCustom)
    	{
    		ArrayList<CustomMaterial> metal = CustomMaterial.getList("metal");
    		Iterator iteratorMetal = metal.iterator();
    		while(iteratorMetal.hasNext())
        	{
    			CustomMaterial customMat = (CustomMaterial) iteratorMetal.next();
    			if(MineFantasyII.isDebug() || customMat.getItem() != null)
    			{
    				list.add(this.construct(customMat.name, "OakWood"));
    			}
        	}
    	}
    	else
    	{
    		super.getSubItems(item, tab, list);
    	}
    }
    
    @Override
    public void addInformation(ItemStack item, EntityPlayer user, List list, boolean extra) 
    {
    	if(isCustom)
        {
        	CustomToolHelper.addInformation(item, list);
        }
        super.addInformation(item, user, list, extra);
    }

	@Override
	public float getEfficiency(ItemStack item) 
	{
		return CustomToolHelper.getEfficiency(item, toolMaterial.getEfficiencyOnProperMaterial(), efficiencyMod);
	}

	@Override
	public int getTier(ItemStack item) 
	{
		return CustomToolHelper.getCrafterTier(item, tier);
	}
	@Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack item)
    {
    	String unlocalName = this.getUnlocalizedNameInefficiently(item) + ".name";
    	return CustomToolHelper.getLocalisedName(item, unlocalName);
    }
	//====================================================== CUSTOM END ==============================================================\\
	@Override
	public String getToolType(ItemStack item) 
	{
		return "shears";
	}

	@Override
	public ToolMaterial getMaterial() 
	{
		return toolMaterial;
	}
}
