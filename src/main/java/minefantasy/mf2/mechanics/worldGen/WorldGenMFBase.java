package minefantasy.mf2.mechanics.worldGen;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenMFBase implements IWorldGenerator 
{
	public static String generatorLayer = "MineFantasy2";
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		generate(random, chunkX, chunkZ, world);
	}
	public void generate(Random random, int chunkX, int chunkZ, World world) 
	{
		NBTTagCompound nbt = world.getWorldInfo().getNBTTagCompound();
		
		if(shouldGenerate(nbt, chunkX, chunkZ))
		{
			WorldGenOres.generate(random, chunkX, chunkZ, world);
			WorldGenPlants.generate(random, chunkX, chunkZ, world);
		}
	}
	
	private static boolean shouldGenerate(NBTTagCompound nbt, int x, int z)
	{
		String index = "WorldGenMF_x" + x + "z" + z + generatorLayer;
		
		if(nbt.hasKey(index))
		{
			return !nbt.getBoolean(index);
		}
		nbt.setBoolean(index, true);
		return true;
	}
}
