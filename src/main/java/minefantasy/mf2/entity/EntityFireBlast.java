package minefantasy.mf2.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFireBlast extends EntityFireball
{
    private static final float size = 1.0F;
    public EntityFireBlast(World world)
    {
        super(world);
        this.setInvisible(true);
        this.setSize(size, size);
    }

    public EntityFireBlast(World world, EntityLivingBase shooter, double xv, double yv, double zv)
    {
        super(world, shooter, xv, yv, zv);
        this.setInvisible(true);
        this.setSize(size, size);
    }

    public EntityFireBlast(World world, double x, double y, double z, double xv, double yv, double zv)
    {
        super(world, x, y, z, xv, yv, zv);
        this.setInvisible(true);
        this.setSize(size, size);
    }
    
    @Override
    public void onUpdate()
    {
    	super.onUpdate();
    	if(this.ticksExisted >= getLifeSpan())
    	{
    		setDead();
    	}
    }

    private int getLifeSpan() 
    {
		return 30;
	}

	/**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition pos)
    {
        if (!this.worldObj.isRemote)
        {
            if (pos.entityHit != null)
            {
                if (!pos.entityHit.isImmuneToFire() && pos.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 12.0F))
                {
                    pos.entityHit.setFire(10);
                }
            }
            else
            {
                int i = pos.blockX;
                int j = pos.blockY;
                int k = pos.blockZ;

                switch (pos.sideHit)
                {
                    case 0:
                        --j;
                        break;
                    case 1:
                        ++j;
                        break;
                    case 2:
                        --k;
                        break;
                    case 3:
                        ++k;
                        break;
                    case 4:
                        --i;
                        break;
                    case 5:
                        ++i;
                }

                if (this.worldObj.isAirBlock(i, j, k))
                {
                    this.worldObj.setBlock(i, j, k, Blocks.fire);
                }
            }

            this.setDead();
        }
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource src, float power)
    {
        return false;
    }
    
    @Override
    public boolean isInvisible()
    {
        return true;
    }
}