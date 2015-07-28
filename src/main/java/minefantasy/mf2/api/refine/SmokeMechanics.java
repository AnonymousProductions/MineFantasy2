package minefantasy.mf2.api.refine;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;

public class SmokeMechanics 
{
	/**
	 * Emits smoke from a carrying block
	 * @param max the maximum smoke emitted each tick (-1 for infinite)
	 * @return true if it's successful
	 */
	public static boolean emitSmokeFromCarrier(World world, BlockPos pos, ISmokeCarrier tile, int max)
	{
		int value = tile.getSmokeValue();
		
		if(max >= 0 && value > max)
		{
			value = max;
		}
		if(value <= 0)
		{
			return true;//yes it succeeded because it doesn't need to... it just really means nothing went wrong
		}
		boolean success = emitSmoke(world, pos, value) != -1;
		
		if(success)
		{
			modifySmoke(tile, -value);
		}
		return success;
	}
	/**
	 * Tries to emit smoke, sends it to chimneys if possible
	 * Returns -1 for fail, 0 for shared, 1 for success
	 */
	public static int emitSmoke(World world, BlockPos pos, int value)
	{
		TileEntity tile = world.getTileEntity(pos.add(new Vec3i(0,1,0)));//The above block
		if(tile == null) //No TileEntity above
		{
			if(world.isSideSolid(pos.add(new Vec3i(0,1,0)), EnumFacing.DOWN))//if solid above
			{
				return -1;
			}
			//if(world.canBlockSeeTheSky(x, y+1, z))
			{
				spawnSmoke(world, pos.getX(), pos.getY()+1, pos.getZ(), value);
				return 1;
			}
		}
		else
		{
			if(tile instanceof ISmokeCarrier)
			{
				if(((ISmokeCarrier)tile).getSmokeValue() < ((ISmokeCarrier)tile).getMaxSmokeStorage())
				{
					modifySmoke((ISmokeCarrier)tile, value);
					return 0;
				}
			}
			else
			{
				if(world.isSideSolid(pos.add(new Vec3i(0,1,0)), EnumFacing.DOWN))//if solid above
				{
					return -1;
				}
			}
		}
		return -1;
	}

	public static void modifySmoke(ISmokeCarrier tile, int value)
	{
		tile.setSmokeValue(tile.getSmokeValue() + value);
		if(tile.getSmokeValue() <= 0)
		{
			tile.setSmokeValue(0);
		}
	}

	/**
	 * Do not use this from blocks, use emitSmoke instead
	 * Spawns smoke
	 */
	private static Random rand = new Random();
	public static ISmokeHandler handler;
	public static void spawnSmoke(World world, int x, int y, int z, int value) 
	{
		if(!world.isRemote && handler != null)
		{
			handler.spawnSmoke(world, x+0.5D, y+0.5D, z+0.5D, value);
		}
		
		for(int a = 0; a < value; a++)
		{
			float sprayRange = 0.2F;
			float sprayX = (rand.nextFloat()*sprayRange) - (sprayRange/2);
			float sprayZ = (rand.nextFloat()*sprayRange) - (sprayRange/2);
			float height = 0.2F;
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,x+0.5D, y+0.5D, z+0.5D, sprayX, height, sprayZ);
		}
	}
	
	public static boolean tryUseChimney(World world, BlockPos pos, int value)
	{
		return tryUseChimney(world, pos, value, true);
	}
	public static boolean tryUseChimney(World world, BlockPos pos, int value, boolean indirect)
	{
		TileEntity tile = world.getTileEntity(pos);//The block
		if(tile != null)
		{
			if(tile instanceof ISmokeCarrier)
			{
				ISmokeCarrier chimney = (ISmokeCarrier) tile;
				if((!indirect || chimney.canAbsorbIndirect()) && chimney.getSmokeValue() < chimney.getMaxSmokeStorage())
				{
					modifySmoke(chimney, value);
					return true;
				}
			}
		}
		return false;
	}
	public static void emitSmokeIndirect(World world, BlockPos pos, int value) 
	{
		if(!tryUseChimney(world, pos.add(new Vec3i(0,1,0)), value, false) && !tryUseChimney(world, pos.add(new Vec3i(0,2,0)), value))
		{
			if(!tryUseChimney(world, pos.add(new Vec3i(-1,1,0)), value) && !tryUseChimney(world, pos.add(new Vec3i(1,1,0)), value) &&!tryUseChimney(world, pos.add(new Vec3i(0,1,-1)), value) &&!tryUseChimney(world, pos.add(new Vec3i(0,1,1)), value))
			{
				if(!tryUseChimney(world, pos.add(new Vec3i(-1,1,-1)), value) && !tryUseChimney(world, pos.add(new Vec3i(1,1,1)), value) &&!tryUseChimney(world, pos.add(new Vec3i(-1,1,-1)), value) &&!tryUseChimney(world, pos.add(new Vec3i(1,1,1)), value))
				{
					spawnSmoke(world, pos.getX(), pos.getY()+1, pos.getZ(), value);
				}	
			}
		}
	}
}
