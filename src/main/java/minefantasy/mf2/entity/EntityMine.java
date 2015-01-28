package minefantasy.mf2.entity;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityMine extends Entity
{
    /** How long the fuse is */
    public int fuse;
    private EntityLivingBase thrower;

    public EntityMine(World world)
    {
        super(world);
        this.preventEntitySpawning = true;
        this.setSize(0.5F, 0.25F);
        this.yOffset = this.height / 2.0F;
    }

    public EntityMine(World world, EntityLivingBase thrower)
    {
        this(world);
        this.thrower = thrower;
        this.fuse = 40;
        
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

 
    @Override
    public boolean interactFirst(EntityPlayer user)
    {
    	if(user.isSneaking())
    	{
    		//DISARM
    		setDead();
    		user.swingItem();
        	if(!worldObj.isRemote)
        	{
        		ItemStack item = new ItemStack(ToolListMF.mine_shrapnel);
        		if(!user.inventory.addItemStackToInventory(item))
        		{
        			this.entityDropItem(item, 0.0F);
        		}
        	}
    	}
    	return super.interactFirst(user);
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
        return 0.5F;
    }

	protected void entityInit() {}

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
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX = 0;
            this.motionZ = 0;
            this.motionY *= -0.99D;
        }

        if(fuse > 0)
    	{
        	--fuse;
    	}
        if (fuse == 0 && !worldObj.getEntitiesWithinAABB(EntityLivingBase.class, boundingBox.expand(2F, 0.5F, 2F)).isEmpty())
        {
        	worldObj.playSoundEffect(posX, posY, posZ, "game.tnt.primed", 1.0F, 0.75F);
        	--fuse;
        }
        if(fuse < 0)
        {
        	fuse --;
        	
        	if(fuse < -10)
        	{
	            this.setDead();
	
	            if (!this.worldObj.isRemote)
	            {
	                this.explode();
	            }
        	}
        }
    }

    private void explode2()
    {
        float power = 5.0F;
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
                Iterator var5 = var4.iterator();

                while (var5.hasNext())
                {
                    Entity var6 = (Entity)var5.next();
                    double var7 = this.getDistanceToEntity(var6);

                    double radius = getRangeOfBlast();
                    if (var7 < radius)
                    {
                    	applyEffects(var6);
                    	
                    	int dam = getDamage();
                    	
                    	if(var7 < radius/2)
                    	{
                    		double sc = (radius/2)-var7;
                    		if(sc < 0)sc = 0;
                    		if(sc > (radius/2))sc = (radius);
                    		dam *= (1 + (0.5D / (radius/2) * sc));
                    	}
                    	if(!(var6 instanceof EntityItem))
                    	{
                    		DamageSource source = causeBombDamage(this, thrower != null ? thrower:this);
                    		source.setExplosion();
                    		if(this.canEntityBeSeen(var6))
                    		var6.attackEntityFrom(source, dam);
                    	}
                    }
                }
            }
            this.setDead();
        }
	}
    private void applyEffects(Entity hit) 
    {
    	if(hit instanceof EntityLivingBase)
    	{
    		EntityLivingBase live = (EntityLivingBase)hit;
    	}
	}

	private double getRangeOfBlast()
	{
		return 3.5D;
	}

	private int getDamage() 
	{
		return 30;
	}
	
	public static DamageSource causeBombDamage(Entity bomb, Entity user)
    {
        return (new EntityDamageSourceBomb(bomb, user)).setProjectile();
    }
	
	public boolean canEntityBeSeen(Entity entity)
    {
        return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ), Vec3.createVectorHelper(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ)) == null;
    }
}