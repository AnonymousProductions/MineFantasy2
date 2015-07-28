package minefantasy.mf2.mechanics.worldGen;

import java.util.Random;

import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.config.ConfigWorldGen;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldGenOres 
{
	public static void generate(Random seed, Chunk chunk, World world) 
	{
		generateOre(seed, chunk, world, BlockListMF.oreCopper, Blocks.stone, ConfigWorldGen.copperSize, ConfigWorldGen.copperFrequencyMin, ConfigWorldGen.copperFrequencyMax, ConfigWorldGen.copperRarity, ConfigWorldGen.copperLayerMin, ConfigWorldGen.copperLayerMax);
		generateOre(seed, chunk, world, BlockListMF.oreTin,    Blocks.stone, ConfigWorldGen.tinSize, ConfigWorldGen.tinFrequencyMin, ConfigWorldGen.tinFrequencyMax, ConfigWorldGen.tinRarity, ConfigWorldGen.tinLayerMin, ConfigWorldGen.tinLayerMax);
		generateOre(seed, chunk, world, BlockListMF.oreSilver, Blocks.stone, ConfigWorldGen.silverSize, ConfigWorldGen.silverFrequencyMin, ConfigWorldGen.silverFrequencyMax, ConfigWorldGen.silverRarity, ConfigWorldGen.silverLayerMin, ConfigWorldGen.silverLayerMax);
		generateOreWithNeighbour(seed, chunk, world, BlockListMF.oreMythic, Blocks.stone, Blocks.bedrock, ConfigWorldGen.mythicSize, ConfigWorldGen.mythicFrequencyMin, ConfigWorldGen.mythicFrequencyMax, ConfigWorldGen.mythicRarity, ConfigWorldGen.mythicLayerMin, ConfigWorldGen.mythicLayerMax);
		
		generateOre(seed, chunk, world, BlockListMF.oreKaolinite, Blocks.stone, ConfigWorldGen.kaoliniteSize, ConfigWorldGen.kaoliniteFrequencyMin, ConfigWorldGen.kaoliniteFrequencyMax, ConfigWorldGen.kaoliniteRarity, ConfigWorldGen.kaoliniteLayerMin, ConfigWorldGen.kaoliniteLayerMax);
		generateOre(seed, chunk, world, BlockListMF.oreClay,    Blocks.dirt, ConfigWorldGen.claySize, ConfigWorldGen.clayFrequencyMin, ConfigWorldGen.clayFrequencyMax, ConfigWorldGen.clayRarity, ConfigWorldGen.clayLayerMin, ConfigWorldGen.clayLayerMax);
		generateOreWithNeighbour(seed, chunk, world, BlockListMF.oreNitre, Blocks.stone, Blocks.air, ConfigWorldGen.nitreSize, ConfigWorldGen.nitreFrequencyMin, ConfigWorldGen.nitreFrequencyMax, ConfigWorldGen.nitreRarity, ConfigWorldGen.nitreLayerMin, ConfigWorldGen.nitreLayerMax);
		generateOre(seed, chunk, world, BlockListMF.oreSulfur, Blocks.stone, ConfigWorldGen.sulfurSize, ConfigWorldGen.sulfurFrequencyMin, ConfigWorldGen.sulfurFrequencyMax, ConfigWorldGen.sulfurRarity, ConfigWorldGen.sulfurLayerMin, ConfigWorldGen.sulfurLayerMax);
		generateOre(seed, chunk, world, BlockListMF.oreBorax, Blocks.stone, ConfigWorldGen.boraxSize, ConfigWorldGen.boraxFrequencyMin, ConfigWorldGen.boraxFrequencyMax, ConfigWorldGen.boraxRarity, ConfigWorldGen.boraxLayerMin, ConfigWorldGen.boraxLayerMax);
	
		generateOre(seed, chunk, world, BlockListMF.limestone, Blocks.stone, ConfigWorldGen.limestoneSize, ConfigWorldGen.limestoneFrequencyMin, ConfigWorldGen.limestoneFrequencyMax, ConfigWorldGen.limestoneRarity, ConfigWorldGen.limestoneLayerMin, ConfigWorldGen.limestoneLayerMax);
	}
	
	
	private static void generateOre(Random seed, Chunk chunk, World world, Block ore, Block bed, int size, int frequencyMin, int frequencyMax, float rarity, int layerMin, int layerMax) 
	{
		int frequency = MathHelper.getRandomIntegerInRange(seed, frequencyMin, frequencyMax);
		if(seed.nextFloat() < rarity)
		{
			for(int count = 0; count < frequency; count ++)
			{
				int x = chunk.xPosition*16 + seed.nextInt(16);
				int y = MathHelper.getRandomIntegerInRange(seed, layerMin, layerMax);
				int z = chunk.zPosition*16 + seed.nextInt(16);
				(new WorldGenMinable(ore.getDefaultState(), size, BlockHelper.forBlock(Blocks.bed))).generate(world, seed, new BlockPos(x, y, z));
			}
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
				
				BlockPos newpos = new BlockPos(x,y,z);
				
				if(isNeibourNear(world, newpos, neighbour))
				{
					if((new WorldGenMinableMF(ore, size, bed)).generate(world, seed, newpos))
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
				
				BlockPos newpos = new BlockPos(x,y,z);
				
				if(isNeibourNear(world, newpos, neighbour))
				{
					if((new WorldGenMinableMF(ore, size, bed)).generate(world, seed, newpos))
					{
					}
				}
			}
		}
	}
	private static boolean isNeibourNear(World world, BlockPos pos, Block neighbour) 
	{
		return world.getBlockState(pos.subtract(new Vec3i(1,0,0))).getBlock() == neighbour
			|| world.getBlockState(pos.add(1,0,0)).getBlock() == neighbour
			|| world.getBlockState(pos.subtract(new Vec3i(0,1,0))).getBlock() == neighbour
			|| world.getBlockState(pos.add(0,1,0)).getBlock() == neighbour
			|| world.getBlockState(pos.subtract(new Vec3i(0,0,1))).getBlock() == neighbour
			|| world.getBlockState(pos.add(0,0,1)).getBlock() == neighbour;
	}
	private static boolean isNeibourNear(World world, BlockPos pos, Material neighbour) 
	{
		return world.getBlockState(pos.subtract(new Vec3i(1,0,0))).getBlock().getMaterial() == neighbour
				|| world.getBlockState(pos.add(1,0,0)).getBlock().getMaterial() == neighbour
				|| world.getBlockState(pos.subtract(new Vec3i(0,1,0))).getBlock().getMaterial() == neighbour
				|| world.getBlockState(pos.add(0,1,0)).getBlock().getMaterial() == neighbour
				|| world.getBlockState(pos.subtract(new Vec3i(0,0,1))).getBlock().getMaterial() == neighbour
				|| world.getBlockState(pos.add(0,0,1)).getBlock().getMaterial() == neighbour;
	}
}
