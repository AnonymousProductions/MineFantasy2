package minefantasy.mf2.block.basic;

import java.util.Random;

import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMythicOre extends BlockOreMF
{
	public BlockMythicOre(String name)
	{
		super(name, 4, 2);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
		if (rand.nextInt(20) == 0 && world.isRemote)
        {
			//"minefantasy2:block.mythicore"
			world.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "random.levelup", 1.0F, rand.nextFloat() * 0.4F + 1.1F, true);
        }
    }
}
