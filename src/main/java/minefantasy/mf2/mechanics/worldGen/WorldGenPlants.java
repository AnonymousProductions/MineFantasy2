package minefantasy.mf2.mechanics.worldGen;

import java.util.Random;

import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.config.ConfigWorldGen;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class WorldGenPlants 
{
	public static void generate(Random seed, Chunk chunk, World world) 
	{
		
		BiomeGenBase biome = world.getBiomeGenForCoords(new BlockPos(chunk.xPosition*16, chunk.zPosition*16,chunk.getHeight(chunk.xPosition*16, chunk.zPosition*16)));
		if(isBiomeInConstraint(biome, ConfigWorldGen.berryMinTemp, ConfigWorldGen.berryMaxTemp, ConfigWorldGen.berryMinRain, ConfigWorldGen.berryMaxRain))
		{
			generatePlant(seed, chunk, world, BlockListMF.berryBush, 0, ConfigWorldGen.berryRarity);
		}
	}
	public static boolean isBiomeInConstraint(BiomeGenBase biome, float tempMin, float tempMax, float rainMin, float rainMax)
	{
		if(biome != null)
		{
			return biome.temperature >= tempMin && biome.temperature < tempMax && biome.rainfall >= rainMin && biome.rainfall < rainMax;
		}
		return false;
	}
	
	private static void generatePlant(Random seed, Chunk chunk, World world, Block plant, int meta, float chance) 
	{
		boolean doGen = world.getWorldInfo().getTerrainType() != WorldType.FLAT;
		if (doGen && seed.nextFloat() < chance)
        {
            int j = chunk.xPosition*16 + seed.nextInt(16);
            int k = chunk.zPosition*16 + seed.nextInt(16);
            int l = world.getTopSolidOrLiquidBlock(world.getHeight(new BlockPos(j, k,0))).getY();
            
            
            (new WorldGenBush(BlockListMF.berryBush, 0)).generate(world, seed, new BlockPos(j, l, k));
		}
	}
	
	private static void generateOreWithNeighbour(Random seed, Chunk chunk, World world, Block ore, Block bed, Material neighbour, int size, int frequencyMin, int frequencyMax, float rarity, int layerMin, int layerMax) 
	{
		int frequency = MathHelper.getRandomIntegerInRange(seed, frequencyMin, frequencyMax);
		if(seed.nextFloat() < rarity)
		{
			for(int count = 0; count < frequency; count ++)
			{
				int x = chunk.xPosition*16 + seed.nextInt(16);
				int y = MathHelper.getRandomIntegerInRange(seed, layerMin, layerMax);
				int z = chunk.zPosition*16 + seed.nextInt(16);
				
				if(isNeibourNear(world, x, y, z, neighbour))
				{
					if((new WorldGenMinableMF(ore, size, bed)).generate(world, seed, new BlockPos(x, y, z)))
					{
					}
				}
			}
		}
	}
	private static void generateOreWithNeighbour(Random seed, Chunk chunk, World world, Block ore, Block bed, Block neighbour, int size, int frequencyMin, int frequencyMax, float rarity, int layerMin, int layerMax) 
	{
		int frequency = MathHelper.getRandomIntegerInRange(seed, frequencyMin, frequencyMax);
		if(seed.nextFloat() < rarity)
		{
			for(int count = 0; count < frequency; count ++)
			{
				int x = chunk.xPosition*16 + seed.nextInt(16);
				int y = MathHelper.getRandomIntegerInRange(seed, layerMin, layerMax);
				int z = chunk.zPosition*16 + seed.nextInt(16);
				
				if(isNeibourNear(world, x, y, z, neighbour))
				{
					if((new WorldGenMinableMF(ore, size, bed)).generate(world, seed, new BlockPos(x, y, z)))
					{
					}
				}
			}
		}
	}
	private static boolean isNeibourNear(World world, int x, int y, int z, Block neighbour) 
	{
		return world.getBlockState(new BlockPos(x-1, y, z)).getBlock() == neighbour
			|| world.getBlockState(new BlockPos(+1, y,  z)).getBlock() == neighbour
			|| world.getBlockState(new BlockPos(x, y-1, z)).getBlock() == neighbour
			|| world.getBlockState(new BlockPos(x, y+1, z)).getBlock() == neighbour
			|| world.getBlockState(new BlockPos(x, y, z-1)).getBlock() == neighbour
			|| world.getBlockState(new BlockPos(x, y, z+1)).getBlock() == neighbour;
	}
	private static boolean isNeibourNear(World world, int x, int y, int z, Material neighbour) 
	{
		return world.getBlockState(new BlockPos(x-1, y, z)).getBlock().getMaterial() == neighbour
			|| world.getBlockState(new BlockPos(+1, y,  z)).getBlock().getMaterial() == neighbour
			|| world.getBlockState(new BlockPos(x, y-1, z)).getBlock().getMaterial() == neighbour
			|| world.getBlockState(new BlockPos(x, y+1, z)).getBlock().getMaterial() == neighbour
			|| world.getBlockState(new BlockPos(x, y, z-1)).getBlock().getMaterial() == neighbour
			|| world.getBlockState(new BlockPos(x, y, z+1)).getBlock().getMaterial() == neighbour;
	}
}
