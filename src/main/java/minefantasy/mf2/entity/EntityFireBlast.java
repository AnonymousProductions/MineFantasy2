package minefantasy.mf2.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFireBlast extends EntityFireball
{
    private static final float size =0.75F;
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
    	if(ticksExisted % 5 == 0)
    	{
	    	int lifeScale = (int) Math.floor((float)ticksExisted / 5F);
	    	float newSize = size + (size/4*lifeScale);
	    	this.setSize(newSize, newSize);
    	}
    }

    private int getLifeSpan() 
    {
    	if(isPreset("BlastFurnace"))
    	{
    		return 15;
    	}
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
                if (!pos.entityHit.isImmuneToFire())
                {
                	pos.entityHit.setFire(10);
                	pos.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), getDamage());
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
                boolean tnt = worldObj.getBlock(pos.blockX, pos.blockY, pos.blockZ).getMaterial() == Material.tnt;
                if(isPreset("BlastFurnace") && (rand.nextInt(100) == 0) || tnt)
                {
                	boolean solid = worldObj.isBlockNormalCubeDefault(pos.blockX, pos.blockY, pos.blockZ, false);
                	worldObj.newExplosion(this, posX, posY, posZ, solid ? 1.5F : 0.5F, true, true);
                }
            }

            this.setDead();
        }
    }

    private float getDamage() 
    {
    	if(isPreset("BlastFurnace"))
    	{
    		return 8F;
    	}
		return 2.0F;
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
    
    public void modifySpeed(float mod)
    {
    	this.accelerationX *= mod;
    	this.accelerationY *= mod;
    	this.accelerationZ *= mod;
    }
    
    public boolean isPreset(String s)
    {
    	if(getEntityData().hasKey("Preset"))
    	{
    		return getEntityData().getString("Preset").equalsIgnoreCase(s);
    	}
    	return false;
    }
}