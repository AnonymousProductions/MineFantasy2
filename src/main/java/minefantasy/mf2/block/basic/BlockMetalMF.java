package minefantasy.mf2.block.basic;

import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockMetalMF extends Block 
{
	
	public String Name;
	
	public BlockMetalMF(BaseMaterialMF material)
	{
		super(Material.iron);
		
		String name = material.name.toLowerCase() + "_block";
		Name= name;
		GameRegistry.registerBlock(this, name);
		setUnlocalizedName("minefantasy2:metal/"+name);
		this.setDefaultState(this.blockState.getBaseState());
		//this.setBlockTextureName("minefantasy2:metal/"+name);
		this.setHarvestLevel("pickaxe", material.harvestLevel);
		this.setStepSound(Block.soundTypeMetal);
		this.setHardness(material.hardness+1 / 2F);
		this.setResistance(material.hardness+1);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
}