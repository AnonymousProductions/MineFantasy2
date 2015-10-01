package minefantasy.mf2.item.armour;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.api.armour.ArmourDesign;
import minefantasy.mf2.material.BaseMaterialMF;

public class ItemApron extends ItemArmourMF
{
	public ItemApron(String name, BaseMaterialMF material, String tex, int rarity)
	{
		super(name, material, ArmourDesign.CLOTH, 1, tex, rarity);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
		ModelBiped model = new ModelBiped(0.25F);
		model.heldItemRight = entityLiving.getHeldItem() != null ? 1 : 0;
        return model;
    }
	
	public static boolean isUserProtected(EntityPlayer user)
	{
		ItemStack worn = user.getCurrentArmor(2);
		if(worn == null)
		{
			return false;
		}
		return worn.getItem() instanceof ItemApron;
	}
}
