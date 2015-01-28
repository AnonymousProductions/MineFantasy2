package minefantasy.mf2.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class EntityDamageSourceBomb extends EntityDamageSourceIndirect
{
    private Entity thrower;

    public EntityDamageSourceBomb(Entity bomb, Entity user)
    {
        super("bomb", bomb, user);
        thrower = user;
    }

    /**
     * Returns the message to be displayed on player death.
     */
}
