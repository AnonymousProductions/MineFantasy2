package minefantasy.mf2.mechanics.worldGen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBush extends WorldGenerator
{
	private Block block;
	private int meta;
	
	public WorldGenBush(Block block, int meta)
	{
		super();
		this.block = block;
		this.meta = meta;
	}
	@Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int l = 0; l < 8; ++l)
        {
            int i1 = pos.getX() + rand.nextInt(8) - rand.nextInt(8);
            int j1 = pos.getY() + rand.nextInt(4) - rand.nextInt(4);
            int k1 = pos.getZ() + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(new BlockPos(i1, j1, k1)) && world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock() == Blocks.grass && block.canPlaceBlockAt(world, new BlockPos(i1, j1, k1)))
            {
            	world.setBlockState(new BlockPos(i1, j1, k1), block.getStateFromMeta(meta), 2);
            }
        }

        return true;
    }

}