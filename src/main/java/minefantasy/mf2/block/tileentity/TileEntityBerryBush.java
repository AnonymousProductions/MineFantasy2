package minefantasy.mf2.block.tileentity;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class TileEntityBerryBush extends TileEntity implements IUpdatePlayerListBox
{
	private long nextGrowbackTime;
	private Random rand = new Random();
	
	@Override
	public void update()
	{
		//update();
		
		if(!worldObj.isRemote && worldObj.getTotalWorldTime() >= nextGrowbackTime)
		{//Regrow
			
			worldObj.setBlockState(this.getPos(), this.getBlockType().getStateFromMeta(0), 2);
		}
	}
	
	public void harvestBerries()
	{
		double[] days = new double[]{2D, 7D};
		double modifier = MathHelper.getRandomDoubleInRange(rand, days[0], days[1]);
		nextGrowbackTime = worldObj.getTotalWorldTime() + (int)(24000*modifier);
		worldObj.setBlockState(this.getPos(), this.getBlockType().getStateFromMeta(1), 2);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setLong("nextGrowbackTime", nextGrowbackTime);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		nextGrowbackTime = nbt.getLong("nextGrowbackTime");
	}
}
