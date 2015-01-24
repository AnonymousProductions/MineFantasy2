package minefantasy.mf2.block.basic;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMetalMF extends Block 
{
	public BlockMetalMF(BaseMaterialMF material)
	{
		super(Material.iron);
		
		String name = material.name.toLowerCase() + "_block";
		GameRegistry.registerBlock(this, name);
		setBlockName(name);
		
		this.setBlockTextureName("minefantasy2:metal/"+name);
		this.setHarvestLevel("pickaxe", material.harvestLevel);
		this.setStepSound(Block.soundTypeMetal);
		this.setHardness(material.hardness+1 / 2F);
		this.setResistance(material.hardness+1);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
}