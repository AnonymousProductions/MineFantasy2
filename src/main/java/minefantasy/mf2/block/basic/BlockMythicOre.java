package minefantasy.mf2.block.basic;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMythicOre extends BlockOreMF
{
	private String NAME;
	
	public BlockMythicOre(String name)
	{
		super(name, 4, 2);
		NAME = name;
	}
	
	public String getName()
	{
		return "ores/"+NAME;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, BlockPos pos,IBlockState state, Random rand)
    {
		if (rand.nextInt(20) == 0 && world.isRemote)
        {
			//"minefantasy2:block.mythicore"
			world.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "random.levelup", 1.0F, rand.nextFloat() * 0.4F + 1.1F, true);
        }
    }
}
