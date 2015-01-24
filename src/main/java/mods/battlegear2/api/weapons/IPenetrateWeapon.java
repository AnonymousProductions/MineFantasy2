package mods.battlegear2.api.weapons;

import net.minecraft.item.ItemStack;

public interface IPenetrateWeapon {
	/**
	 * The amount of damage bypassing armor
	 * @param stack The {@link net.minecraft.item.ItemStack} representative of the item dealing the hit.
	 * @return the amount of damage that bypasses armour
	 */
	public float getPenetratingPower(ItemStack stack);
}
