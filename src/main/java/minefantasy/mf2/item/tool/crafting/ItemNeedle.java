package minefantasy.mf2.item.tool.crafting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.CustomToolHelper;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.material.CustomMaterial;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.api.tool.IToolMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.ForgeHooks;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Anonymous Productions
 */
public class ItemNeedle extends ItemTool implements IToolMaterial, IToolMF
{
	private ToolMaterial material;
	private int tier;
	private float baseDamage;
	private String name;
	
    public ItemNeedle(String name, ToolMaterial material, int rarity)
    {
        super(0F, material, Sets.newHashSet(new Block[] {}));
        this.material = material;
        this.name = name;
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabCraftTool);
        
        setTextureName("minefantasy2:Tool/Crafting/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
    }
    
    @Override
	public Multimap getItemAttributeModifiers()
	{
		Multimap map = HashMultimap.create();
		map.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 0.5D, 0));

        return map;
    }
 /*   private int itemRarity;
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
	}*/
    
	@Override
	public ToolMaterial getMaterial()
	{
		return toolMaterial;
	}
	
	@Override
	public float getEfficiency(ItemStack item) 
	{
		return ToolHelper.modifyDigOnQuality(item, material.getEfficiencyOnProperMaterial());
	}

	@Override
	public int getTier(ItemStack item) 
	{
		return tier;
	}

	@Override
	public String getToolType(ItemStack item)
	{
		return "needle";
	}
	
	//===================================================== CUSTOM START =============================================================\\
	private boolean isCustom = false;
	public ItemNeedle setCustom()
	{
		setCreativeTab(CreativeTabMF.tabCustom);
		setTextureName("minefantasy2:custom/tool/"+name);
		isCustom = true;
		return this;
	}
	public ItemNeedle setBaseDamage(float baseDamage)
    {
    	this.baseDamage = baseDamage;
    	return this;
    }
	
	
    private float efficiencyMod = 1.0F;
    public ItemNeedle setEfficiencyMod(float efficiencyMod)
    {
    	this.efficiencyMod = efficiencyMod;
    	return this;
    }
    
	@Override
	public Multimap getAttributeModifiers(ItemStack item)
	{
		Multimap map = HashMultimap.create();
		map.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", getMeleeDamage(item), 0));
	
	    return map;
	}
	/**
	 * Gets a stack-sensitive value for the melee dmg
	 */
    protected float getMeleeDamage(ItemStack item) 
    {
    	return baseDamage + CustomToolHelper.getMeleeDamage(item, toolMaterial.getDamageVsEntity());
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

	public ItemStack construct(String main)
	{
		return CustomToolHelper.construct(this, main);
	}
	protected int itemRarity;
    @Override
	public EnumRarity getRarity(ItemStack item)
	{
    	return CustomToolHelper.getRarity(item, itemRarity);
	}
    @Override
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
    	if (!ForgeHooks.isToolEffective(stack, block, meta))
        {
    		return this.func_150893_a(stack, block);
        }
		return CustomToolHelper.getEfficiency(stack, super.getDigSpeed(stack, block, meta), efficiencyMod);
	}
    public float func_150893_a(ItemStack stack, Block block)
    {
    	float base = super.func_150893_a(stack, block);
        return base <= 1.0F ? base : CustomToolHelper.getEfficiency(stack, this.efficiencyOnProperMaterial, efficiencyMod);
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
    			
    			list.add(this.construct(customMat.name));
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
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack item)
    {
    	String unlocalName = this.getUnlocalizedNameInefficiently(item) + ".name";
    	return CustomToolHelper.getLocalisedName(item, unlocalName);
    }
    //====================================================== CUSTOM END ==============================================================\\
}
