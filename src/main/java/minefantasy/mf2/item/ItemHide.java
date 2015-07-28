package minefantasy.mf2.item;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHide extends ItemComponentMF 
{
	private final Item result;
	private float hardness;
	public ItemHide(String name, Item result, float hardness)
	{
		super(name, -1);
		this.result = result;
		this.hardness = hardness;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

        if (movingobjectposition == null)
        {
            return item;
        }
        else
        {
        	if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                BlockPos pos = movingobjectposition.getBlockPos();


                if (!world.canMineBlockBody(player, pos))
                {
                    return item;
                }

                if (!player.canPlayerEdit(pos, movingobjectposition.sideHit, item))
                {
                    return item;
                }

                if (isWaterSource(world, pos))
                {
                	tryClean(item, world, player);
                }
            }

            return item;
        }
    }
    
	private  Random rand = new Random();
    private void tryClean(ItemStack item, World world, EntityPlayer player)
    {
        player.swingItem();
        if (!world.isRemote) 
        {
            world.playSoundAtEntity(player, "random.splash", 0.125F + rand.nextFloat()/4F, 0.5F + rand.nextFloat());
            if (rand.nextFloat()*2*hardness < 1.0F)
            {
                item.stackSize--;
                EntityItem resultItem = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(result));
                world.spawnEntityInWorld(resultItem);
            }
        }
	}
    
    private boolean isWaterSource(World world, BlockPos pos) {
		if(world.getBlockState(pos).getBlock().getMaterial() == Material.water)
		{
			return true;
		}
		if(isCauldron(world, pos))
		{
			return true;
		}
		return false;
	}
    
    public boolean isCauldron(World world, BlockPos pos)
    {
    	return world.getBlockState(pos).getBlock() == Blocks.cauldron && world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos))>0;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
