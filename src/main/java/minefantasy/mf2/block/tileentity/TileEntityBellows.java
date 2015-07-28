package minefantasy.mf2.block.tileentity;

import minefantasy.mf2.api.refine.IBellowsUseable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class TileEntityBellows extends TileEntity implements IUpdatePlayerListBox 
{
	public int direction;
	public int press = 0;
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
