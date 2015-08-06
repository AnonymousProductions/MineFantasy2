package minefantasy.mf2.block.tileentity;

import java.util.List;
import java.util.Random;

import minefantasy.mf2.api.crafting.IBasicMetre;
import minefantasy.mf2.api.heating.ForgeFuel;
import minefantasy.mf2.api.refine.IBellowsUseable;
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
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.WorldServer;

public class TileEntityForge extends TileEntity implements IInventory, IBasicMetre, IBellowsUseable, IUpdatePlayerListBox
{
	public static final float dragonHeat = 200F; //200*
	private ItemStack[] inv = new ItemStack[10];
	public float fuel;
	public float maxFuel = 6000;//5m
	public float temperature, fuelTemperature;
	public float maxTemperature = 1000;
	private Random rand = new Random();
	private int ticksExisted;
	public float dragonHeartPower = 0F;
	public String texTypeForRender = "stone";
	private boolean isBurning = false;
	
	public TileEntityForge(Block block, String type) 
	{
		texTypeForRender = type;
		blockType = block;
		worldObj.setBlockState(pos, worldObj.getBlockState(pos).getBlock().getStateFromMeta(0));
	}

	public TileEntityForge() {
	}

	@Override
	public void update()
	{
		int xCoord = this.getPos().getX();
		int yCoord = this.getPos().getY();
		int zCoord = this.getPos().getZ();
		
		if(justShared > 0)--justShared;
		//update();
		
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
			syncData();
		}
		if (ticksExisted % 10 == 0) {
			shareTemp();
		}
		
		if(!isLit())
		{
			if(temperature > 0)
			{
				temperature --;
			}
			return;
		}
		tickFuel();
		if(fuel <= 0)
		{
			this.extinguish();
			return;
		}
		isBurning = isBurning();//Check if it's burning
		float maxTemp = fuelTemperature + getUnderTemperature();
		
		if(temperature < maxTemp)
		{
			float amount = 0.2F;
			temperature += amount;
			if(temperature > maxTemp)
			{
				temperature = maxTemp;
			}
		}
		else if(temperature > maxTemp && rand.nextInt(20) == 0)
		{
			--temperature;
		}
		
		if(isBurning && temperature > 100 && rand.nextInt(20) == 0 && !isOutside())
		{
			SmokeMechanics.emitSmokeIndirect(worldObj, this.getPos(), 1);
		}
		if(dragonHeartPower > 0)
		{
			dragonHeartPower -= 1F/100F;//5Seconds
			if(temperature < dragonHeat)//Must be 300+
			{
				dragonHeartPower = 0;
			}
		}
	}
	private boolean isOutside()
	{
		
		for(int x = -1; x <= 1; x++)
		{
			for(int y = -1; y <= 1; y++)
			{
				if(!worldObj.canBlockSeeSky(this.getPos().add(x, 1, y)))
				{
					return false;
				}
			}	
		}
		return true;
	}
	public boolean isLit;
	private void shareTemp()
	{
		isLit = isLit();
		shareTo(-1, 0);
		shareTo(1, 0);
		shareTo(0, -1);
		shareTo(0, 1);
	}

	private void shareTo(int x, int z) 
	{
		
		if (fuel <= 0)
			return;

		int share = 2;
		TileEntity tile = worldObj.getTileEntity(this.getPos().add(x, 0, z));
		if (tile == null)
			return;

		if (tile instanceof TileEntityForge)
		{
			TileEntityForge forge = (TileEntityForge) tile;

			if (isLit && !forge.isLit && forge.fuel > 0) 
			{
				forge.fireUpForge();
			}
			if (!forge.isBurning() && temperature > 1) 
			{
				forge.temperature = 1;
			}
			if (forge.temperature < (temperature - share)) {
				forge.temperature += share;
				temperature -= share;
			}
			share = 1200;
			if (forge.fuel < (fuel - share)) {
				forge.fuel += share;
				fuel -= share;
			}
		}
	}

	private void tickFuel()
	{
		if(fuel > 0)
		{
			--fuel;
		}
		
		if(fuel < 0)fuel = 0;
	}

	private boolean isBurning() 
	{
		return fuel > 0 && temperature > 0;
	}

	private void modifyItem(ItemStack item, int slot) 
	{
		if(item.getItem() == ComponentListMF.hotItem)
		{
			int temp = ItemHeated.getTemp(item);
			if(temp > temperature)
			{
				int i = (int)(temperature/5F);
				MFLogUtil.logDebug("Taken " + i + " from slot " + slot);
				MFLogUtil.logDebug("Forge Temp = " + temperature);
				temp -= i;
			}
			else
			{
				int increase = (int)(temperature / 10F);
				if(temp >= (temperature-increase))
				{
					temp = (int) temperature;
				}
				else
				{
					temp += increase;
				}
			}
			if(temp <= 0)
			{
				this.setInventorySlotContents(slot, ItemHeated.getItem(item));
			}
			else
			{
				ItemHeated.setTemp(item, Math.max(0, temp));
			}
		}
		else if(temperature > 0)
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
	public float getUnderTemperature()
	{
		Block under = worldObj.getBlockState(this.getPos().add(0, -1, 0)).getBlock();
		
		if(under.getMaterial() == Material.fire)
		{
			return 50F;
		}
		if(under.getMaterial() == Material.lava)
		{
			return 100F;
		}
		return 0F;
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setFloat("temperature", temperature);
		nbt.setFloat("fuelTemperature", fuelTemperature);
		nbt.setFloat("dragonHeartPower", dragonHeartPower);
		nbt.setFloat("fuel", fuel);
		nbt.setFloat("maxFuel", maxFuel);
		
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
		fuelTemperature = nbt.getFloat("fuelTemperature");
		dragonHeartPower = nbt.getFloat("dragonHeartPower");
		fuel = nbt.getFloat("fuel");
		maxFuel = nbt.getFloat("maxFuel");
		
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
	public String getName()
	{
		return "gui.crucible.name";
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer user)
	{
		int xCoord = this.getPos().getX();
		int yCoord = this.getPos().getY();
		int zCoord = this.getPos().getZ();
		return user.getDistance(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D) < 8D;
	}

	@Override
	public void openInventory(EntityPlayer player)
	{
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item)
	{
		return true;
	}

	public BlockForge getActiveBlock()
	{
		if(worldObj == null)return null;
		
		Block block = worldObj.getBlockState(this.getPos()).getBlock();
		
		if(block != null && block instanceof BlockForge)
		{
			return (BlockForge)block;
		}
		return null;
	}
	public String getTextureName() 
	{
		BlockForge forge = getActiveBlock();
		if(forge == null)return "forge_"+texTypeForRender;
		
		return "forge_" + forge.type + (forge.isActive ? "_active" : "");
	}

	public boolean hasFuel()
	{
		return worldObj != null && fuel > 0;
	}
	
	public boolean isLit()
	{
		BlockForge forge =  getActiveBlock();
		return forge != null && forge.isActive;
	}
	
	/**
	 * Puts the fire out
	 */
	public void extinguish()
	{
		BlockForge.updateFurnaceBlockState(false, worldObj,this.getPos());
	}
	/**
	 * Fires the forge up
	 */
	public void fireUpForge()
	{
		BlockForge.updateFurnaceBlockState(true, worldObj, this.getPos());
	}
	public float getBellowsEffect()
	{
		return 1.5F;
	}
	private float getBellowsMax() 
	{
		return Math.min(fuelTemperature*getBellowsEffect(), getMaxTemp());
	}
	public int getMaxTemp() 
	{
		return 1000;
	}
	public boolean addFuel(ForgeFuel stats, boolean hand)
	{
		boolean hasUsed = false;
		
		if(stats.baseHeat > this.fuelTemperature) // uses if hotter
		{
			hasUsed = true;
		}
		int room_left = (int) (maxFuel - fuel);
		if(hand && room_left > 0)
		{
			hasUsed = true;
			fuel = Math.min(fuel + stats.duration, maxFuel);//Fill as much as can fit
		}
		else if(!hand && (fuel == 0 || room_left >= stats.duration))//For hoppers: only fill when there's full room
		{
			hasUsed = true;
			fuel = Math.min(fuel + stats.duration, maxFuel);//Fill as much as can fit
		}
		if(stats.doesLight && !isLit())
		{
			fireUpForge();
			hasUsed = true;
		}
		if(hasUsed)
		{
			fuelTemperature = stats.baseHeat;
		}
		return hasUsed;
	}

	@Override
	public int getMetreScale(int size)
	{
		if(shouldShowMetre())
		{
			return (int)((float)size / maxFuel * fuel);
		}
		return 0;
	}

	public int[] getTempsScaled(int size)
	{
		int[] temps = new int[2];
		if(shouldShowMetre())
		{
			temps[0] = (int)((float)size / this.maxTemperature * this.temperature);
			temps[1] = (int)((float)size / this.maxTemperature * this.fuelTemperature);
		}
		if(temps[0] > size)temps[0] = size;
		if(temps[1] > size)temps[1] = size;
		return temps;
	}

	@Override
	public boolean shouldShowMetre() 
	{
		return true;
	}

	@Override
	public String getLocalisedName() 
	{
		return StatCollector.translateToLocal("forge.fuel.name");
	}

	public boolean[] showSides() {
		if (worldObj == null) {
			return new boolean[] { true, true, true, true };
		}
		boolean front = !isForge(0, 0, 1);
		boolean left = !isForge(-1, 0, 0);
		boolean right = !isForge(1, 0, 0);
		boolean back = !isForge(0, 0, -1);

		return new boolean[] { front, left, right, back };
	}
	
	private boolean isForge(int x, int y, int z) {
		return worldObj.getBlockState(this.getPos().add(x, y, z)).getBlock() instanceof BlockForge;
	}
	public void syncData()
	{
		
		if(worldObj.isRemote)return;
		
		List<EntityPlayer> players = ((WorldServer)worldObj).playerEntities;
		for(int i = 0; i < players.size(); i++)
		{
			EntityPlayer player = players.get(i);
			//((WorldServer)worldObj).getEntityTracker().func_151248_b(player, new ForgePacket(this).generatePacket());
		}
	}
	int justShared;
	public void onUsedWithBellows(float powerLevel) 
	{
		int xCoord = this.getPos().getX();
		int yCoord = this.getPos().getY();
		int zCoord = this.getPos().getZ();
		if(!isBurning())return;
		if(justShared > 0)
		{
			return;
		}
		if(fuel <= 0)
		{
			return;
		}
		float max = getBellowsMax();
		justShared = 5;
		if(temperature < max)
        {
        	temperature = Math.min(max, temperature + (50F*powerLevel));
        }
		
		for(int a = 0; a < 10; a ++)
		{
			worldObj.spawnParticle(EnumParticleTypes.FLAME, xCoord+(rand.nextDouble()*0.8D)+0.1D, yCoord + 0.4D, zCoord+(rand.nextDouble()*0.8D)+0.1D, 0, 0.01, 0);
		}
		
		pumpBellows(-1, 0, powerLevel*0.9F);
		pumpBellows(0, -1, powerLevel*0.9F);
		pumpBellows(0, 1, powerLevel*0.9F);
		pumpBellows(1, 0, powerLevel*0.9F);
	
	}

	private void pumpBellows(int x, int z, float pump)
	{
		if(fuel <= 0)return;
		
		int share = 2;
		TileEntity tile = worldObj.getTileEntity(this.getPos().add(x, 0, z));
		if(tile == null)return;
		
		if(tile instanceof TileEntityForge)
		{
			TileEntityForge forge = (TileEntityForge)tile;
			forge.onUsedWithBellows(pump);
		}
	}

	public float getBlockTemperature()
	{
		if(this.isLit)
		{
			return temperature;
		}
		return 0;
	}
	
	public boolean hasCrucibleAbove()
	{
		if(worldObj == null)return false;
		
		TileEntity tile = worldObj.getTileEntity(this.getPos().add(0, 1, 0));
		return tile != null && tile instanceof TileEntityCrucible || tile instanceof TileEntityFurnace;
	}
	
	private void averageAllItems()
	{
		/*
		int temp = 0;
		int items = 0;
		for(ItemStack item: inv)
		{
			if(item != null && item.getItem() instanceof IHotItem)
			{
				++items;
				temp += ItemHeated.getTemp(item);
			}
		}
		int average = (int)((float)temp / items);
		for(ItemStack item: inv)
		{
			if(item != null && item.getItem() instanceof IHotItem)
			{
				ItemHeated.setTemp(item, average);
			}
		}
		*/
	}

	@Override
	public IChatComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
