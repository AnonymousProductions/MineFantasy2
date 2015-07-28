package minefantasy.mf2.item;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemResearchBook extends Item
{
	public ItemResearchBook() 
	{
        super();
        setMaxStackSize(1);
        setCreativeTab(CreativeTabMF.tabGadget);
		GameRegistry.registerItem(this, "MF_ResearchBook", MineFantasyII.MODID);
		//this.setUnlocalizedName("infobook");
		this.setUnlocalizedName("minefantasy2:Other/research_book");
		setContainerItem(this);
    }

    @Override
	public EnumRarity getRarity(ItemStack item)
	{
    	return EnumRarity.UNCOMMON;
	}
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer user)
    {
		ResearchLogic.syncData(user);
    	user.openGui(MineFantasyII.instance, 1, world, 0, -1, 0);
        return item;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack item, EntityPlayer user, List list, boolean fullInfo)
    {
    	super.addInformation(item, user, list, fullInfo);
    	if(ClientItemsMF.showSpecials(item, user, list, fullInfo))
    	{
    		list.add("Gives information on MineFantasy Content");
    		list.add("");
    		list.add("This item can be re-crafted by placing");
    		list.add("a regular book on a carpenter bench");
    	}
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
