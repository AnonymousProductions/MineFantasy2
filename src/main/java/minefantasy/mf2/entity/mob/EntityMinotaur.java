package minefantasy.mf2.entity.mob;

import minefantasy.mf2.api.armour.IArmourPenetrationMob;
import minefantasy.mf2.api.armour.IArmouredEntity;
import minefantasy.mf2.api.helpers.ArmourCalculator;
import minefantasy.mf2.api.helpers.TacticalManager;
import minefantasy.mf2.config.ConfigMobs;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;

public class EntityMinotaur extends EntityMob implements IArmouredEntity, IArmourPenetrationMob
{

	public int swing;
	private int grabCooldown;

	public EntityMinotaur(World world)
	{
		super(world);
		this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.setSize(1.5F, 3F);
        this.stepHeight = 1.0F;
        this.experienceValue = 40;
        
        for (int i = 0; i < this.equipmentDropChances.length; ++i)
        {
            this.equipmentDropChances[i] = 1F;
        }
	}
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4F);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(ConfigMobs.minotaurMD);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(ConfigMobs.minotaurHP);
    }
	@Override
	protected boolean isAIEnabled()
    {
        return true;
    }
	private static final int dataID = 12;
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(dataID, Byte.valueOf((byte)0));
    }
    @Override
    public void onUpdate()
    {
    	super.onUpdate();
    	
    	if(!worldObj.isRemote)
    	{
    		if(grabCooldown > 0)
    		{
    			--grabCooldown;
    		}
    		if(headbuttTime > 0)
    		{
    			--headbuttTime;
    		}
    		if(getAttack() == (byte)1 && headbuttTime == 0)
    		{
    			initBasicAttack();
    		}
    		
    		if(this.getAttackTarget() != null)
    		{
    			if(getAttack() != (byte)2 && getHeldItem() == null && rand.nextInt(100) == 0)
    			{
    				this.initHeadbutt();
    			}
    			EntityLivingBase target = getAttackTarget();
    			if(onGround && target instanceof EntityPlayer && ((EntityPlayer)target).isBlocking() && this.getDistanceToEntity(target) < 4F)
    			{
    				this.jump();
    			}
    		}
    		
    		boolean bloodied = getHealth() < (getMaxHealth()*0.25F);
    		if(getAttack() == (byte)2)
    		{
    			if(!bloodied)
    			{
    				initBasicAttack();
    			}
    			else
    			{
    				this.swingItem();
    			}
    		}
    		if(getAttack() != (byte)2 && bloodied)
    		{
				initBeserk();
    		}
    	}
    }

    @Override
    protected void dropFewItems(boolean playerKill, int looting)
    {
    	int count = rand.nextInt(looting+getLootCount()+1);
    	for(int a = 0; a < count; a++)
    	{
    		Item drop = getLoot();
    		this.dropItem(drop, 1);
    	}
    	count = rand.nextInt(looting+4)+1;
    	for(int a = 0; a < count; a++)
    	{
    		this.dropItem(ComponentListMF.hideLarge, 1);
    	}
    	count = rand.nextInt(looting+2)+1;
    	for(int a = 0; a < count; a++)
    	{
    		this.dropItem(isBurning() ? Items.cooked_beef : Items.beef, 1);
    	}
    }

    private Item getLoot() 
    {
		return rand.nextInt(3) == 0 ? ToolListMF.loot_sack_uc : ToolListMF.loot_sack;
	}
    private int getLootCount() 
    {
		return 2;
	}
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage)
    {
        if (!super.attackEntityFrom(source, damage))
        {
            return false;
        }
        else
        {
            EntityLivingBase entitylivingbase = this.getAttackTarget();

            if (entitylivingbase == null && this.getEntityToAttack() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase)this.getEntityToAttack();
            }

            if (entitylivingbase == null && source.getEntity() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase)source.getEntity();
            }
        }
        return true;
    }
	
	public void initBeserk()
	{
		TacticalManager.tryDisarm(this);
		this.playSound("minefantasy2:mob.minotaur.beserk", 1.0F, 1.0F);
		setSprinting(true);
		setAttack((byte)2);
	}
	public void initHeadbutt()
	{
		setSprinting(true);
		headbuttTime = 20;
		setAttack((byte)1);
	}
	public void initBasicAttack()
	{
		setSprinting(false);
		headbuttTime = 0;
		setAttack((byte)0);
	}
	protected String getLivingSound()
    {
        return "minefantas2:mob.minotaur.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "minefantas2:mob.minotaur.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "minefantas2:mob.minotaur.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("minefantas2:mob.minotaur.step", 0.5F, 1.0F);
    }

    protected Item getDropItem()
    {
        return ComponentListMF.hideLarge;
    }
	public double getHeadChargeAngle() 
	{
		return getAttack() == (byte)1 ? 80F : 0F;
	}
	@Override
	public float getThreshold(DamageSource src) 
	{
		float[] armour = getThresholds();
		return ArmourCalculator.adjustACForDamage(src, getDT(), armour[0], armour[1], armour[2]);
	}
	
	private float getDT()
	{
		byte att = getAttack();
		if(att == (byte)2)
		{
			return 5F;
		}
		return 2F;
	}
	/**
	 * Cutting, Blunt, Piercing
	 */
	private float[] getThresholds()
	{
		return new float[]{0.75F, 1F, 0.5F};
	}
	
	/**
	 * 0=Normal, 1=Headbutt, 2=Beserk
	 */
	public byte getAttack()
	{
		return dataWatcher.getWatchableObjectByte(dataID);
	}
    
	/**
	 * 0=Normal, 1=Headbutt, 2=Beserk
	 */
    public void setAttack(byte type)
    {
    	dataWatcher.updateObject(dataID, type);
    }
    private int headbuttTime;

    private float[]punch = new float[]{0F, 1F, 0F};
    private float[]headbutt = new float[]{0F, 1F, 4F};
	@Override
	public float[] getHitTraits() 
	{
		byte att = getAttack();
		if(att == (byte)1)
		{
			return headbutt;
		}
		return punch;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity target)
    {
		if(!canEntityBeSeen(target))
		{
			return false;
		}
		if(motionY > 0 && target.posY < posY)
		{
			return false;
		}
		
		
		float dam = getHitDamage();
		if(getAttack() == 2)
		{
			if(target instanceof EntityLivingBase)
			{
				ArmourCalculator.damageArmour((EntityLivingBase) target, (int)(dam*0.25F));
			}
			if(this.riddenByEntity == null && rand.nextInt(100) < ConfigMobs.minotaurGC)
			{
				if(grabCooldown <= 0)
				{
					target.mountEntity(this);
				}
			}
			else if(rand.nextInt(100) < ConfigMobs.minotaurTC && riddenByEntity == target)
			{
				riddenByEntity.mountEntity(null);
				grabCooldown = 60;
				TacticalManager.knockbackEntity(target, this, 4F, 1F);
			}
		}
		if(getAttack() == 1 && target instanceof EntityPlayer && ((EntityPlayer)target).isBlocking() && rand.nextInt(100) < ConfigMobs.minotaurDC)
		{
			TacticalManager.tryDisarm(this, (EntityLivingBase)target, true);
		}
		
        int i = getKnockbackBoost();

        if (target instanceof EntityLivingBase)
        {
            dam += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)target);
            i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)target);
        }

        boolean flag = target.attackEntityFrom(new DamageSourceMobMF(getAttackType(), this), dam);

        if (flag)
        {
        	this.swingItem();
            if (i > 0)
            {
                target.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                target.setFire(j * 4);
            }

            if (target instanceof EntityLivingBase)
            {
                EnchantmentHelper.func_151384_a((EntityLivingBase)target, this);
            }

            EnchantmentHelper.func_151385_b(this, target);
        }

        return flag;
    }
	private String getAttackType() 
	{
		byte att = getAttack();
		if(att == (byte)1)
		{
			return "gore";
		}
		if(att == (byte)2)
		{
			return "beserk";
		}
		return "pound";
	}
	private int getKnockbackBoost() 
	{
		byte att = getAttack();
		if(att == (byte)1)
		{
			return 2;
		}
		return 0;
	}
	private float getHitDamage() 
	{
		if(getHeldItem() != null)
		{
			return 2F;//Weapon Dam
		}
		byte att = getAttack();
		if(att == (byte)1)
		{
			return ConfigMobs.minotaurGD;
		}
		if(att == (byte)2)
		{
			return ConfigMobs.minotaurBD;
		}
		return ConfigMobs.minotaurMD;
	}
}
