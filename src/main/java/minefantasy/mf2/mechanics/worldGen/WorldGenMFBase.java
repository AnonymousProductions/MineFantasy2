package minefantasy.mf2.mechanics.worldGen;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenMFBase implements IWorldGenerator 
{
	public static String generatorLayer = "MineFantasy2";
	@Override
	public void generate(Random random, int chunkX,int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		generate(random,new Chunk(world, chunkX, chunkZ), world);
	}
	
	public void generate(Random random, Chunk chunk, World world) 
	{
		NBTTagCompound nbt = world.getWorldInfo().getNBTTagCompound();
		
		if(shouldGenerate(nbt, chunk))
		{
			WorldGenOres.generate(random, chunk, world);
			WorldGenPlants.generate(random, chunk, world);
		}
	}
	
	private static boolean shouldGenerate(NBTTagCompound nbt, Chunk chunk)
	{
		String index = "WorldGenMF_x" + chunk.xPosition + "z" + chunk.zPosition + generatorLayer;
		
		if(nbt.hasKey(index))
		{
			return !nbt.getBoolean(index);
		}
		nbt.setBoolean(index, true);
		return true;
	}
}
