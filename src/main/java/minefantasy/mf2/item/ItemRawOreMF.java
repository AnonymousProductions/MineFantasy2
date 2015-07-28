package minefantasy.mf2.item;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRawOreMF extends ItemComponentMF 
{
	
	private static String NAME;
	public ItemRawOreMF(String name, int rarity)
	{
		super(rarity);
		setUnlocalizedName("minefantasy2:oreItem/"+name);
		this.setCreativeTab(CreativeTabMF.tabMaterialsMF);
		GameRegistry.registerItem(this, "MF_Ore_"+name, MineFantasyII.MODID);
		NAME = name;
	}
	
	public String getName()
	{
		return NAME;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
