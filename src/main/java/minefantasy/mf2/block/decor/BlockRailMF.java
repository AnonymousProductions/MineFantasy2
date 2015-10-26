package minefantasy.mf2.block.decor;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRail;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockRailMF extends BlockRail
{
	private BaseMaterialMF baseMat;
	private boolean isPowered = true;
	public BlockRailMF(BaseMaterialMF material)
    {
		this(material, material.name.toLowerCase());
    }
	public BlockRailMF(BaseMaterialMF material, String type)
    {
        super();
        String name = type + "_rail";
		GameRegistry.registerBlock(this, name);
		setBlockName(name);
		this.setBlockTextureName("minefantasy2:mechanical/"+name);
		
		this.setHarvestLevel("pickaxe", material.harvestLevel);
		this.setStepSound(Block.soundTypeMetal);
		this.setHardness(material.hardness+1 / 2F);
		this.setResistance(material.hardness+1);
		this.baseMat = material;
    }
    
    @Override
    public float getRailMaxSpeed(World world, EntityMinecart cart, int y, int x, int z)
    {
        return 0.8F;
    }
}