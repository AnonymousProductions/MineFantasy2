package minefantasy.mf2.block.basic;

import java.util.Random;

import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockMetalBarsMF extends BlockPane 
{
	
	private BaseMaterialMF baseMat;
	public String Name;
	
	public BlockMetalBarsMF(BaseMaterialMF material) 
	{
		//"minefantasy2:metal/"+material.name.toLowerCase()+"_bars"
		super(Material.iron, true);
		String name = material.name.toLowerCase() + "_bars";
		GameRegistry.registerBlock(this, name);
		setUnlocalizedName("minefantasy2:metal/"+name);
		Name = name;
		
		this.setHarvestLevel("pickaxe", material.harvestLevel);
		this.setStepSound(Block.soundTypeMetal);
		this.setHardness(material.hardness+1 / 2F);
		this.setResistance(material.hardness+1);
		this.baseMat = material;
	}

	
	private Random rand = new Random();
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity hitter)
    {
		if(baseMat == BaseMaterialMF.dragonforge && !hitter.isImmuneToFire() && hitter instanceof EntityLivingBase)
		{
			hitter.setFire(10);
			hitter.attackEntityFrom(DamageSource.inFire, 1);
			world.spawnParticle(EnumParticleTypes.FLAME, pos.getX()+rand.nextDouble(), pos.getY()+rand.nextDouble(), pos.getZ()+rand.nextDouble(), 0D, 0D, 0D);
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX()+rand.nextDouble(), pos.getY()+rand.nextDouble(), pos.getZ()+rand.nextDouble(), 0D, 0D, 0D);
		}
    }
}
