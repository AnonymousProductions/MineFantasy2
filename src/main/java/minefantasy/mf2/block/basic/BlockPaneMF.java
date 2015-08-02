package minefantasy.mf2.block.basic;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockPaneMF extends BlockPane 
{
	
	public String Name;
	
	public BlockPaneMF(String name, String frontTex, String sideTex, Material material, boolean recoverable) 
	{
		super( material, recoverable);
		//"minefantasy2:basic/"+frontTex, "minefantasy2:basic/"+sideTex,
		GameRegistry.registerBlock(this, name);
		setUnlocalizedName("minefantasy2:basic/" +name);
		Name=name;
	}

}
