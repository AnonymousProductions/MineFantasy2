package minefantasy.mf2.item;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBucketMF extends Item
{
    /** field for checking if the bucket has been filled. */
    private Block isFull;
    private static final String __OBFID = "CL_00000000";
    public String Name;
    public ItemBucketMF(String Name, Block filler)
    {
    	final String name = Name;
    	this.maxStackSize = 1;
        this.isFull = filler;
        this.setCreativeTab(CreativeTabMF.tabGadget);
        
        //setTextureName("minefantasy2:Tool/"+name);
        setUnlocalizedName("minefantasy2:Tool/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		Name=name;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
	public ItemStack onItemRightClick(ItemStack bucket, World world, EntityPlayer user)
    {
        boolean flag = this.isFull == Blocks.air;
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, user, flag);

        if (movingobjectposition == null)
        {
            return bucket;
        }
        else
        {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                BlockPos pos = movingobjectposition.getBlockPos();

                
                if (!world.canMineBlockBody(user, pos))
                {
                    return bucket;
                }

                if (flag)
                {
                    if (!user.canPlayerEdit(pos, movingobjectposition.sideHit, bucket))
                    {
                        return bucket;
                    }

                    Material material = world.getBlockState(pos).getBlock().getMaterial();
                    int l = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));

                    if (material == Material.water && l == 0)
                    {
                        world.setBlockToAir(pos);
                        return this.createNewInstance(bucket, user, ToolListMF.bucketwood_water, 0);
                    }

                    if (material == Material.lava && l == 0)
                    {
                    	world.playSound(pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, "random.fizz", 1.0F, 1.0F, true);
                        world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX()+0.5, pos.getY()+1F, pos.getZ()+0.5, 0F, 0.5F, 0F);
                    	return this.createNewInstance(bucket, user, Items.coal, 1);
                    }
                }
                else
                {
                    if (this.isFull == Blocks.air)
                    {
                        return new ItemStack(ToolListMF.bucketwood_empty);
                    }
                    

                    switch(movingobjectposition.sideHit)
                    {
					case DOWN:
						pos.subtract(new Vec3i(0,1,0));
						break;
					case EAST:
						pos.subtract(new Vec3i(1,0,0));
						break;
					case NORTH:
						pos.subtract(new Vec3i(0,0,1));
						break;
					case SOUTH:
						pos.add(new Vec3i(0,0,1));
						break;
					case UP:
						pos.add(new Vec3i(0,1,0));
						break;
					case WEST:
						pos.add(new Vec3i(1,0,0));
						break;
					default:
						break;
                    }
                    	
                }

                    if (!user.canPlayerEdit(pos, movingobjectposition.sideHit, bucket))
                    {
                        return bucket;
                    }

                    if (this.tryPlaceContainedLiquid(world, pos) && !user.capabilities.isCreativeMode)
                    {
                        return new ItemStack(ToolListMF.bucketwood_empty);
                    }
                }
            }

            return bucket;
        }

    private ItemStack createNewInstance(ItemStack bucket, EntityPlayer user, Item newstack, int m)
    {
        if (user.capabilities.isCreativeMode)
        {
            return bucket;
        }
        else if (--bucket.stackSize <= 0)
        {
            return new ItemStack(newstack, 1, m);
        }
        else
        {
            if (!user.inventory.addItemStackToInventory(new ItemStack(newstack, 1, m)))
            {
                user.dropPlayerItemWithRandomChoice(new ItemStack(newstack, 1, m), false);
            }

            return bucket;
        }
    }

    /**
     * Attempts to place the liquid contained inside the bucket.
     */
    public boolean tryPlaceContainedLiquid(World world, BlockPos pos)
    {
        if (this.isFull == Blocks.air)
        {
            return false;
        }
        else
        {
            Material material = world.getBlockState(pos).getBlock().getMaterial();
            boolean flag = !material.isSolid();

            if (!world.isAirBlock(pos) && !flag)
            {
                return false;
            }
            else
            {
                if (world.provider.doesWaterVaporize() && this.isFull == Blocks.flowing_water)
                {
                    world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l)
                    {
                        world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX()+ Math.random(), pos.getY() + Math.random(), pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
                else
                {
                    if (!world.isRemote && flag && !material.isLiquid())
                    {
                        world.canBlockFreezeBody(pos, true);
                    }

                    world.setBlockState(pos, this.isFull.getStateFromMeta(0), 3);
                }

                return true;
            }
        }
    }
    
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity)
    {
        if(this == ToolListMF.bucketwood_empty && entity instanceof EntityCow)
        {
        	ItemStack newStack = createNewInstance(player.getHeldItem(), player, ToolListMF.bucketwood_water, 0);
        	player.setCurrentItemOrArmor(0, newStack);
        	return true;
        }
        return super.itemInteractionForEntity(itemstack, player, entity);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}