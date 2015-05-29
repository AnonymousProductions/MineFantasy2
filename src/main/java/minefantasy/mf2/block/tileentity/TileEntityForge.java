package minefantasy.mf2.block.tileentity;

import java.util.Random;

import minefantasy.mf2.api.heating.Heatable;
import minefantasy.mf2.api.refine.Alloy;
import minefantasy.mf2.api.refine.AlloyRecipes;
import minefantasy.mf2.api.refine.SmokeMechanics;
import minefantasy.mf2.block.refining.BlockForge;
import minefantasy.mf2.item.heatable.ItemHeated;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityForge extends TileEntity implements IInventory
{
	private ItemStack[] inv = new ItemStack[10];
	public float fuel, maxFuel;
	public float temperature;
	private Random rand = new Random();
	private int ticksExisted;
	public float dragonHeartPower = 0F;
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		boolean wasHot = temperature > 0;
		temperature = getTemperature();
		
		if(temperature > 0 && rand.nextInt(20) == 0)
		{
			SmokeMechanics.emitSmoke(worldObj, xCoord, yCoord, zCoord, 1);
		}
		if(++ticksExisted % 20 == 0)
		{
			for(int a = 0; a < 10; a++)
			{
				ItemStack item = getStackInSlot(a);
				if(item != null && !worldObj.isRemote)
				{
					modifyItem(item, a);
				}
			}
		}
		if(dragonHeartPower > 0)
		{
			dragonHeartPower -= 1F/100F;//5Seconds
			if(temperature < 300)//Must be 300+
			{
				dragonHeartPower = 0;
			}
		}
		
		if(wasHot != getTemperature() >0)
		{
			BlockForge.updateFurnaceBlockState(getTemperature() > 0, worldObj, xCoord, yCoord, zCoord);
		}
	}
	
	private void modifyItem(ItemStack item, int slot) 
	{
		if(item.getItem() == ComponentListMF.hotItem)
		{
			int temp = ItemHeated.getTemp(item);
			if(temp > temperature)
			{
				temp -= rand.nextInt(20);
			}
			else
			{
				int increase = (int) Math.min(temperature-temp, rand.nextFloat()*(temperature / 10F));
				temp += increase;
			}
			ItemHeated.setTemp(item, temp);
		}
		else
		{
			this.setInventorySlotContents(slot, ItemHeated.createHotItem(item));
		}
	}

	public int getTier()
	{
		if(this.blockType != null && blockType instanceof BlockForge)
		{
			return ((BlockForge)blockType).tier;
		}
		return 0;
	}
	public float getTemperature()
	{
		Block under = worldObj.getBlock(xCoord, yCoord-1, zCoord);
		
		if(under.getMaterial() == Material.fire)
		{
			return 100F;
		}
		if(under.getMaterial() == Material.lava)
		{
			return 500F;
		}
		return 0F;
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setFloat("temperature", temperature);
		nbt.setFloat("dragonHeartPower", dragonHeartPower);
		
		NBTTagList savedItems = new NBTTagList();

        for (int i = 0; i < this.inv.length; ++i)
        {
            if (this.inv[i] != null)
            {
                NBTTagCompound savedSlot = new NBTTagCompound();
                savedSlot.setByte("Slot", (byte)i);
                this.inv[i].writeToNBT(savedSlot);
                savedItems.appendTag(savedSlot);
            }
        }

        nbt.setTag("Items", savedItems);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		temperature = nbt.getFloat("temperature");
		dragonHeartPower = nbt.getFloat("dragonHeartPower");
		
		NBTTagList savedItems = nbt.getTagList("Items", 10);
        this.inv = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < savedItems.tagCount(); ++i)
        {
            NBTTagCompound savedSlot = savedItems.getCompoundTagAt(i);
            byte slotNum = savedSlot.getByte("Slot");

            if (slotNum >= 0 && slotNum < this.inv.length)
            {
                this.inv[slotNum] = ItemStack.loadItemStackFromNBT(savedSlot);
            }
        }
	}
	//INVENTORY

	@Override
	public int getSizeInventory()
	{
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int num)
    {
		onInventoryChanged();
        if (this.inv[slot] != null)
        {
            ItemStack itemstack;

            if (this.inv[slot].stackSize <= num)
            {
                itemstack = this.inv[slot];
                this.inv[slot] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.inv[slot].splitStack(num);

                if (this.inv[slot].stackSize == 0)
                {
                    this.inv[slot] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return inv[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item)
	{
		onInventoryChanged();
		inv[slot] = item;
	}

	public void onInventoryChanged() 
	{
	}

	@Override
	public String getInventoryName()
	{
		return "gui.crucible.name";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer user)
	{
		return user.getDistance(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D) < 8D;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item)
	{
		return true;
	}
}
