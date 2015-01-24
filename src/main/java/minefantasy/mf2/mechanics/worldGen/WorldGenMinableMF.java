package minefantasy.mf2.mechanics.worldGen;

import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMinableMF extends WorldGenerator
{
    private Block oreBlock;
    /** The number of blocks to generate. */
    private int numberOfBlocks;
    private Block beddingBlock;
    private static final String __OBFID = "CL_00000426";
    private int mineableBlockMeta;

    public WorldGenMinableMF(Block ore, int size)
    {
        this(ore, size, Blocks.stone);
    }

    public WorldGenMinableMF(Block ore, int size, Block bed)
    {
        this.oreBlock = ore;
        this.numberOfBlocks = size;
        this.beddingBlock = bed;
    }

    public WorldGenMinableMF(Block block, int meta, int number, Block target)
    {
        this(block, number, target);
        this.mineableBlockMeta = meta;
    }

    public boolean generate(World world, Random seed, int x, int y, int z)
    {
    	boolean success = false;
    	
        float f = seed.nextFloat() * (float)Math.PI;
        double d0 = (double)((float)(x + 8) + MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
        double d1 = (double)((float)(x + 8) - MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
        double d2 = (double)((float)(z + 8) + MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
        double d3 = (double)((float)(z + 8) - MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
        double d4 = (double)(y + seed.nextInt(3) - 2);
        double d5 = (double)(y + seed.nextInt(3) - 2);

        for (int l = 0; l <= this.numberOfBlocks; ++l)
        {
            double d6 = d0 + (d1 - d0) * (double)l / (double)this.numberOfBlocks;
            double d7 = d4 + (d5 - d4) * (double)l / (double)this.numberOfBlocks;
            double d8 = d2 + (d3 - d2) * (double)l / (double)this.numberOfBlocks;
            double d9 = seed.nextDouble() * (double)this.numberOfBlocks / 16.0D;
            double d10 = (double)(MathHelper.sin((float)l * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
            double d11 = (double)(MathHelper.sin((float)l * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
            int i1 = MathHelper.floor_double(d6 - d10 / 2.0D);
            int j1 = MathHelper.floor_double(d7 - d11 / 2.0D);
            int k1 = MathHelper.floor_double(d8 - d10 / 2.0D);
            int l1 = MathHelper.floor_double(d6 + d10 / 2.0D);
            int i2 = MathHelper.floor_double(d7 + d11 / 2.0D);
            int j2 = MathHelper.floor_double(d8 + d10 / 2.0D);

            for (int oreX = i1; oreX <= l1; ++oreX)
            {
                double d12 = ((double)oreX + 0.5D - d6) / (d10 / 2.0D);

                if (d12 * d12 < 1.0D)
                {
                    for (int oreY = j1; oreY <= i2; ++oreY)
                    {
                        double d13 = ((double)oreY + 0.5D - d7) / (d11 / 2.0D);

                        if (d12 * d12 + d13 * d13 < 1.0D)
                        {
                            for (int oreZ = k1; oreZ <= j2; ++oreZ)
                            {
                                double d14 = ((double)oreZ + 0.5D - d8) / (d10 / 2.0D);

                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && world.getBlock(oreX, oreY, oreZ).isReplaceableOreGen(world, oreX, oreY, oreZ, beddingBlock))
                                {
                                	success = true;
                                    world.setBlock(oreX, oreY, oreZ, this.oreBlock, mineableBlockMeta, 2);
                                }
                            }
                        }
                    }
                }
            }
        }

        return success;
    }
}