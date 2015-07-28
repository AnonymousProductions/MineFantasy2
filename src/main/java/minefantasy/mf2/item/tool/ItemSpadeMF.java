package minefantasy.mf2.item.tool;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Anonymous Productions
 */
public class ItemSpadeMF extends ItemSpade implements IToolMaterial
{
    public ItemSpadeMF(String name, ToolMaterial material, int rarity)
    {
        super(material);
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabTool);
        
        setUnlocalizedName("minefantasy2:Tool/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		
    }
    private int itemRarity;
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
    
	@Override
	public float getDigSpeed(ItemStack stack, IBlockState state)
	{
		return ToolHelper.modifyDigOnQuality(stack, super.getDigSpeed(stack, state));
	}
	
	@Override
    public void addInformation(ItemStack item, EntityPlayer user, List list, boolean extra) 
    {
        super.addInformation(item, user, list, extra);
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
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
