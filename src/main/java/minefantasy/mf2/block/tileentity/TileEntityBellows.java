package minefantasy.mf2.block.tileentity;

import java.util.Iterator;
import java.util.List;

import minefantasy.mf2.api.refine.IBellowsUseable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class TileEntityBellows extends TileEntity implements IUpdatePlayerListBox 
{
	public int direction;
	public int press = 0;
	public float lidAngle;
    /** The angle of the lid last tick */
    public float prevLidAngle;
    private int ticksSinceSync;
	
	public TileEntityBellows()
	{
		
	}
	
	public void interact(EntityPlayer player, float powerLevel) 
	{
		int xCoord = this.getPos().getX();
		int yCoord = this.getPos().getY();
		int zCoord = this.getPos().getZ();
		IBellowsUseable forge = getFacingForge();
		if(press < 10)
		{
			if(player != null)
			{
				player.playSound("minefantasy2:block.bellows", 1, 1);
			}
			else
			{
				worldObj.playSound(xCoord, yCoord, zCoord, "minefantasy2:block.bellows", 1.0F, 1.0F, false);
			}
			press = 50;
			if(forge != null)
			{
				forge.onUsedWithBellows(powerLevel);
			}
			sendPacketToClients();
		}
	}
	
	@Override
	public void update()
	{
		update();
		if(press > 0)press -= 2;
		if(press < 0)press = 0;
		sendPacketToClients();
		
		
//		int i = this.pos.getX();
//        int j = this.pos.getY();
//        int k = this.pos.getZ();
//		++ticksSinceSync;
//        float f = 5.0F;
//
//
//        this.prevLidAngle = this.lidAngle;
//        f = 0.1F;
//        double d2;
//
//        if (this.lidAngle == 0.0F )
//        {
//            double d1 = (double)i + 0.5D;
//            d2 = (double)k + 0.5D;
//
//            this.worldObj.playSoundEffect(d1, (double)j + 0.5D, d2, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
//        }
//
//        if (this.lidAngle > 0.0F && this.lidAngle < 1.0F)
//        {
//            float f1 = this.lidAngle;
//
//            if (this.lidAngle == 0)
//            {
//                this.lidAngle += f;
//            }
//            else
//            {
//                this.lidAngle -= f;
//            }
//
//            if (this.lidAngle > 1.0F)
//            {
//                this.lidAngle = 1.0F;
//            }
//
//            float f2 = 0.5F;
//
//            if (this.lidAngle < f2 && f1 >= f2 )
//            {
//                d2 = (double)i + 0.5D;
//                double d0 = (double)k + 0.5D;
//            }
//
//            if (this.lidAngle < 0.0F)
//            {
//                this.lidAngle = 0.0F;
//            }
//        }
	}
	
	private void sendPacketToClients() {
		// TODO Auto-generated method stub
		
	}
	public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        direction = nbt.getInteger("direction");
        press = nbt.getInteger("press");
    }


    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("direction", direction);
        nbt.setInteger("press", press);
    }
	
	
	
	public EnumFacing getFacing()
    {
		int dir = worldObj.getBlockState(this.getPos()).getBlock().getMetaFromState(worldObj.getBlockState(this.getPos()));
    	switch(dir)//clockwise
        {
        	case 0: //SOUTH
        	    return EnumFacing.SOUTH;
        	case 1: //WEST
        	    return EnumFacing.WEST;
        	case 2: //NORTH
        	    return EnumFacing.NORTH;
        	case 3: //EAST
        	    return EnumFacing.EAST;
        }
    	return EnumFacing.SOUTH;
    }
    public IBellowsUseable getFacingForge()
    {
    	EnumFacing dir = getFacing();    
    	int xCoord = this.getPos().getX();
		int yCoord = this.getPos().getY();
		int zCoord = this.getPos().getZ();
    	int x2 = xCoord + dir.getFrontOffsetX();
    	int y2 = yCoord + dir.getFrontOffsetY();
    	int z2 = zCoord + dir.getFrontOffsetZ();
    	
    	TileEntity tile = worldObj.getTileEntity(new BlockPos(x2, y2, z2));
    	
    	if(tile != null && tile instanceof IBellowsUseable)
    		return (IBellowsUseable)tile;
    	
    	if(worldObj.getBlockState(new BlockPos(x2, y2, z2)).getBlock().getMaterial() != null && worldObj.getBlockState(new BlockPos(x2, y2, z2)).getBlock().getMaterial().isSolid())
    	{
    		return getFacingForgeThroughWall();
    	}
    	return null;
    }
    
    public IBellowsUseable getFacingForgeThroughWall()
    {
    	EnumFacing dir = getFacing();  
    	int xCoord = this.getPos().getX();
		int yCoord = this.getPos().getY();
		int zCoord = this.getPos().getZ();
    	int x2 = xCoord + (dir.getFrontOffsetX()*2);
    	int y2 = yCoord + (dir.getFrontOffsetY()*2);
    	int z2 = zCoord + (dir.getFrontOffsetZ()*2);
    	
    	TileEntity tile = worldObj.getTileEntity(new BlockPos(x2, y2, z2));
    	if(tile == null)
    		return null;
    	if(tile instanceof IBellowsUseable)
    	{
    		return (IBellowsUseable)tile;
    	}
    	return null;
    }
}
