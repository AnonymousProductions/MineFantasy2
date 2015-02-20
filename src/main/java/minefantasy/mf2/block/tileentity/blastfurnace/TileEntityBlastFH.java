package minefantasy.mf2.block.tileentity.blastfurnace;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraftforge.common.util.ForgeDirection;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.entity.EntityFireBlast;
import minefantasy.mf2.item.list.ComponentListMF;

public class TileEntityBlastFH extends TileEntityBlastFC
{
	public int fuel;
	public int maxFuel;
	public float heat;
	public float progress;
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if(isBuilt)
		{
			if(fuel > 0)
			{
				--fuel;
				++progress;
				float maxProgress = getMaxProgress();
				if(progress >= maxProgress)
				{
					progress -= maxProgress;
					smeltItem();
				}
			}
			else if(!worldObj.isRemote)
			{
				if(isFuel(items[0]))
				{
					fuel = maxFuel = TileEntityFurnace.getItemBurnTime(items[0]);
					decrStackSize(0, 1);
				}
			}
		}
		if(fireTime > 0)
		{
			if(fuel > 0)--fuel;
			
			fireTime--;
			if(ticksExisted % 2 == 0)
			shootFire();
			
		}
	}
	private float getMaxProgress() 
	{
		return 600;
	}
	public static int maxFurnaceHeight = 8;
	private void smeltItem() 
	{
		ItemStack result = null;
		
		for(int y = 0; y < maxFurnaceHeight; y++)
		{
			TileEntity tileEntity = worldObj.getTileEntity(xCoord, yCoord+y+1, zCoord);
			if(tileEntity != null && tileEntity instanceof TileEntityBlastFC && !(tileEntity instanceof TileEntityBlastFH))
			{
				result = stackResult((TileEntityBlastFC) tileEntity, y+1, result);
			}
			else
			{
				break;
			}
		}
		if(result != null)
		{
			dropItem(result);
			fireTime = 20;
			worldObj.playSoundEffect(xCoord+0.5, yCoord+0.5, zCoord+0.5, "random.fizz", 2.0F, 0.5F);
			worldObj.playSoundEffect(xCoord+0.5, yCoord+0.25, zCoord+0.5, "fire.fire", 1.0F, 0.75F);
			startFire(1, 0, 0);
			startFire(-1, 0, 0);
			startFire(0, 0, 1);
			startFire(0, 0, -1);
		}
	}
	
	private void dropItem(ItemStack result)
	{
		TileEntity under = worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
		if(under != null && under instanceof TileEntityHopper)
		{
			TileEntityHopper hopper = (TileEntityHopper)under;
			for(int slot = 0; slot < hopper.getSizeInventory(); slot++)
			{
				if(hopper.getStackInSlot(slot) == null)
				{
					hopper.setInventorySlotContents(slot, result);
					return;
				}
				else if(hopper.getStackInSlot(slot).isItemEqual(result))
				{
					int freeSpace = hopper.getStackInSlot(slot).getMaxStackSize() - hopper.getStackInSlot(slot).stackSize;
					if(freeSpace >= result.stackSize)
					{
						hopper.getStackInSlot(slot).stackSize += result.stackSize;
						return;
					}
					else
					{
						hopper.getStackInSlot(slot).stackSize += freeSpace;
						result.stackSize -= freeSpace;
					}
				}
			}
		}
		EntityItem entity = new EntityItem(worldObj, xCoord+0.5, yCoord+0.5, zCoord+0.5, result);
		worldObj.spawnEntityInWorld(entity);
	}
	private void startFire(int x, int y, int z) 
	{
		if(isAir(x, y, z))
		{
			worldObj.setBlock(xCoord+x, yCoord+y, zCoord+z, Blocks.fire);
		}
	}
	private void shootFire()
	{
		if(!worldObj.isRemote)
		{
			shootFire(-1, 0, 0);
			shootFire(1, 0, 0);
			shootFire(0, 0, -1);
			shootFire(0, 0, 1);
		}
	}
	private void shootFire(int x, int y, int z)
	{
		double v = 0.125D;
		EntityFireBlast fireball = new EntityFireBlast(worldObj, xCoord+0.5+x, yCoord+0.5+y, zCoord+0.5+z, (double)x*v, (double)y*v, (double)z*v);
		fireball.ticksExisted = 15;
		worldObj.spawnEntityInWorld(fireball);
	}
	
	private ItemStack stackResult(TileEntityBlastFC shaft, int y, ItemStack result) 
	{
		if(shaft.getIsBuilt())
		{
			ItemStack input = shaft.getStackInSlot(1);
			if(shaft.getStackInSlot(0) == null || !isCoal(shaft.getStackInSlot(0)))
			{
				return null;
			}
			if(input != null)
			{
				ItemStack newResult = getResult(input);
				if(result == null)
				{
					for(int a = 0; a < shaft.getSizeInventory(); a++)
					{
						shaft.decrStackSize(a, 1);
					}
					return newResult;
				}
				else
				{
					if(result.isItemEqual(newResult))
					{
						for(int a = 0; a < shaft.getSizeInventory(); a++)
						{
							shaft.decrStackSize(a, 1);
						}
						result.stackSize += newResult.stackSize;
					}
				}
			}
		}
		return result;
	}
	@Override
	protected void interact(TileEntityBlastFC tile)
	{
		
	}
	@Override
	public void updateBuild()
	{
		isBuilt = getIsBuilt();
	}
	@Override
	protected boolean getIsBuilt() 
	{
		return (isFirebrick(-1, 0, -1) && isFirebrick(1, 0, -1) && isFirebrick(-1, 0, 1) && isFirebrick(1, 0, -1)) && (isAir(-1, 0, 0) && isAir(1, 0, 0) && isAir(0, 0, -1) && isAir(0, 0, 1));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setInteger("fuel", fuel);
		nbt.setInteger("maxFuel", maxFuel);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		fuel = nbt.getInteger("fuel");
		maxFuel = nbt.getInteger("maxFuel");
	}
	public static boolean isFuel(ItemStack item)
	{
		return TileEntityFurnace.getItemBurnTime(item) > 0;
	}
	@Override
	public int[] getAccessibleSlotsFromSide(int side) 
	{
		return side > 0 ? new int[]{0} : new int[]{};
	}
	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side)
	{
		return side != 0 && isItemValidForSlot(slot, item);
	}
	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side)
	{
		return false;
	}
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item)
	{
		if(slot == 0)
		{
			return isFuel(item);
		}
		return false;
	}
}
