package minefantasy.mf2.entity;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.api.helpers.TacticalManager;
import minefantasy.mf2.item.gadget.EnumCasingType;
import minefantasy.mf2.item.gadget.EnumExplosiveType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBomb extends Entity
{
    /** How long the fuse is */
    public int fuse;
    private EntityLivingBase thrower;

    public EntityBomb(World world)
    {
        super(world);
        this.preventEntitySpawning = true;
        this.setSize(0.5F, 0.5F);
        this.yOffset = this.height / 2.0F;
    }

    public EntityBomb(World world, EntityLivingBase thrower)
    {
        this(world);
        this.thrower = thrower;
        this.fuse = 30;
        
        this.setLocationAndAngles(thrower.posX, thrower.posY + (double)thrower.getEyeHeight(), thrower.posZ, thrower.rotationYaw, thrower.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        
        float f = 0.4F;
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
        this.motionY = (double)(-MathHelper.sin((this.rotationPitch + this.getThrownOffset()) / 180.0F * (float)Math.PI) * f);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.getThrownForce(), 1.0F);
        
        this.prevPosX = posX;
        this.prevPosY = posY;
        this.prevPosZ = posZ;
    }

 
    public void setThrowableHeading(double x, double y, double z, float offset, float force)
    {
        float f2 = MathHelper.sqrt_double(x * x + y * y + z * z);
        x /= (double)f2;
        y /= (double)f2;
        z /= (double)f2;
        x += this.rand.nextGaussian() * 0.007499999832361937D * (double)force;
        y += this.rand.nextGaussian() * 0.007499999832361937D * (double)force;
        z += this.rand.nextGaussian() * 0.007499999832361937D * (double)force;
        x *= (double)offset;
        y *= (double)offset;
        z *= (double)offset;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f3 = MathHelper.sqrt_double(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, (double)f3) * 180.0D / Math.PI);
    }
    protected float getThrownOffset()
    {
        return 0.0F;
    }
    protected float getThrownForce()
    {
        return 1.5F;
    }
    
    @Override
	protected void entityInit()
    {
    	dataWatcher.addObject(typeId, (byte)0);
    	dataWatcher.addObject(typeId+1, (byte)0);
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= (0.03999999910593033D * getGravityModifier());
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
        	double d = 0.75D;
            this.motionX *= d;
            this.motionZ *= d;
            this.motionY *= -0.99D;
        }
        List collide = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox);
        if (!collide.isEmpty() && !(thrower != null && collide.contains(thrower)))
        {
        	double d = 0.5D;
            this.motionX *= d;
            this.motionZ *= d;
        }

        if (this.fuse-- <= 0)
        {
            this.setDead();

            if (!this.worldObj.isRemote)
            {
                this.explode();
            }
        }
        else
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.125D, this.posZ, 0.0D, 0.0D, 0.0D);
            this.worldObj.spawnParticle("flame", this.posX, this.posY + 0.125D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private double getGravityModifier()
	{
		return getCase().weightModifier;
	}

	private void explode2()
    {
        float power = 3.5F;
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, power, false);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setByte("Fuse", (byte)this.fuse);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound nbt)
    {
        this.fuse = nbt.getByte("Fuse");
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.25F;
    }

    /**
     * returns null or the entityliving it was placed or ignited by
     */
    public EntityLivingBase getTntPlacedBy()
    {
        return this.thrower;
    }
    
    public void applyEntityCollision(Entity entity)
    {
    	if(!(thrower != null && thrower == entity))
    	{
            this.motionX = 0;
            this.motionZ = 0;
    	}
    }
    
	public void explode() 
    {
		worldObj.playSoundAtEntity(this, "random.explode", 0.3F, 10F - 5F);
    	worldObj.createExplosion(this, posX, posY, posZ, 0, false);
        if (!this.worldObj.isRemote)
        {
        	double area = getRangeOfBlast()*2D;
            AxisAlignedBB var3 = this.boundingBox.expand(area, area/2, area);
            List var4 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, var3);

            if (var4 != null && !var4.isEmpty())
            {
                Iterator splashDamage = var4.iterator();

                while (splashDamage.hasNext())
                {
                    Entity entityHit = (Entity)splashDamage.next();
                    double distanceToEntity = this.getDistanceToEntity(entityHit);

                    double radius = getRangeOfBlast();
                    if (distanceToEntity < radius)
                    {
                    	float dam = getDamage();
                    	
                    	if(distanceToEntity > radius/2)
                    	{
                    		double sc = distanceToEntity-(radius/2);
                    		if(sc < 0)sc = 0;
                    		if(sc > (radius/2))sc = (radius/2);
                    		dam *= (sc / (radius/2));
                    	}
                    	if(!(entityHit instanceof EntityItem))
                    	{
                    		DamageSource source = causeBombDamage(this, thrower != null ? thrower:this);
                    		source.setExplosion();
                    		if(getFilling() == 2)
                    		{
                    			source.setFireDamage();
                    		}
                    		if(entityHit.attackEntityFrom(source, dam))
                    		{
                    			applyEffects(entityHit);
                    		}
                    	}
                    }
                }
            }
            this.setDead();
        }
        
        int t = getFilling();
        if(t > 0)
        {
        	for(int a = 0; a < 16; a++)
        	{
        		float range = 0.6F;
        		EntityShrapnel shrapnel = new EntityShrapnel(worldObj, posX, posY+0.5D, posZ, (rand.nextDouble()-0.5)*range, (rand.nextDouble())*(range/2), (rand.nextDouble()-0.5)*range);
        		
        		if(t == 2)
        		{
        			shrapnel.setFire(10);
        		}
        		worldObj.spawnEntityInWorld(shrapnel);
        	}
        }
	}
    private void applyEffects(Entity hit) 
    {
    	if(getFilling() == 2)
    	{
    		hit.setFire(5);
    	}
    	if(hit instanceof EntityLivingBase)
    	{
    		EntityLivingBase live = (EntityLivingBase)hit;
    	}
	}

	private double getRangeOfBlast()
	{
		return getBlast().range * getCase().rangeModifier;
	}

	private int getDamage() 
	{
		return (int)(getBlast().damage* getCase().damageModifier);
	}
	
	public static DamageSource causeBombDamage(Entity bomb, Entity user)
    {
        return (new EntityDamageSourceBomb(bomb, user)).setProjectile();
    }
	
	public boolean canEntityBeSeen(Entity entity)
    {
        return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ), Vec3.createVectorHelper(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ)) == null;
    }
	
	private final int typeId = 2;
	public byte getFilling()
	{
		return dataWatcher.getWatchableObjectByte(typeId);
	}
	public byte getCasing()
	{
		return dataWatcher.getWatchableObjectByte(typeId+1);
	}
	public EntityBomb setType(byte fill, byte casing)
	{
		dataWatcher.updateObject(typeId, fill);
		dataWatcher.updateObject(typeId+1, casing);
		return this;
	}
	private EnumExplosiveType getBlast()
	{
		return EnumExplosiveType.getType(getFilling());
	}
	private EnumCasingType getCase()
	{
		return EnumCasingType.getType(getCasing());
	}
}