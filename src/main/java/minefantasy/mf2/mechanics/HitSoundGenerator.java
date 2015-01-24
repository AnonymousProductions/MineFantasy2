package minefantasy.mf2.mechanics;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.config.ConfigClient;
import minefantasy.mf2.config.ConfigExperiment;
import minefantasy.mf2.network.packet.HitSoundPacket;
import minefantasy.mf2.network.packet.StaminaPacket;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.world.WorldServer;

public class HitSoundGenerator
{
	private static String getType(String type, EnumWeaponType wC) 
	{
		if(wC == EnumWeaponType.AXE
	    || wC == EnumWeaponType.BLADE
	    || wC == EnumWeaponType.POLEARM
	   	)
		{
			return "blade";
		}
		return "blunt";
	}

	
	public static void makeHitSound(ItemStack weapon, Entity target)
	{
		EnumWeaponType WC = WeaponClass.getClassFor(weapon);
		String material = "metal";
		String type = "blunt";
		
		material = getMaterial(material, weapon);
		if(WC != null)
		{
			type = getType(type, WC);
			String sndString = "minefantasy2:weapon.hit." + type + "." + material;
			
			if(!target.worldObj.isRemote)
			{
				((WorldServer)target.worldObj).getEntityTracker().func_151248_b(target, new HitSoundPacket(sndString, target).generatePacket());
			}
		}
	}

	public static String getMaterial(String material, ItemStack itemstack) 
	{
		if(itemstack == null)
		{
			return "metal";
		}
		Item item = itemstack.getItem();
		if(item instanceof ItemTool)
		{
			if(((ItemTool)item).getToolMaterialName().equalsIgnoreCase("WOOD"))
			{
				material = "wood";
			}
			if(((ItemTool)item).getToolMaterialName().equalsIgnoreCase("STONE"))
			{
				material = "stone";
			}
		}
		
		if(item instanceof ItemSword)
		{
			if(((ItemSword)item).getToolMaterialName().equalsIgnoreCase("WOOD"))
			{
				material = "wood";
			}
			if(((ItemSword)item).getToolMaterialName().equalsIgnoreCase("STONE"))
			{
				material = "stone";
			}
		}
		
		return material;
	}
}

enum EnumWeaponType 
{
	BLADE,
	BLUNT,
	AXE,
	POLEARM;
}


class WeaponClass {
	
	/**
	 * Gets the weapon class for an item
	 * @param item the weapon
	 * @return the custom class(or default based on name
	 */
	public static EnumWeaponType getClassFor(ItemStack item)
	{
		if(item != null && item.getItem() != null)
		{
			return getDefaultOn(item.getItem().getUnlocalizedName(item));
		}
		return EnumWeaponType.BLUNT;
	}

	/**
	 * Finds a default for an item, based on it's name
	 * @param unlocalizedName the name of the item(not display)
	 * @return a class based on what the item is called, by guessing, DEFAULT: medium blade
	 */
	private static EnumWeaponType getDefaultOn(String name) 
	{
		if(guessClass(name, new String[]
		{
			"waraxe",
		}))
		{
			return EnumWeaponType.AXE;
		}
		
		
		if(guessClass(name, new String[]
				{
					"battleaxe",
					"beardedaxe",
					"greataxe",
				}))
				{
					return EnumWeaponType.AXE;
				}
		
		
		
		if(guessClass(name, new String[]
				{
					"bastard",
					"bastardsword",
					"claymore",
					"greatsword",
				}))
				{
					return EnumWeaponType.BLADE;
				}
		
		
		
		if(guessClass(name, new String[]
				{
					"warhammer",
					"morningstar",
					"maul",
				}))
				{
					return EnumWeaponType.BLUNT;
				}
		
		
		if(guessClass(name, new String[]
				{
					"halbeard",
					"trident",
				}))
				{
					return EnumWeaponType.POLEARM;
				}
		
		
		
		if(guessClass(name, new String[]
				{
					"longsword",
					"broadsword",
					"broad",
					"katana",
					"rapier",
					"sabre",
					"scimitar",
					"cutlass",
				}))
				{
					return EnumWeaponType.BLADE;
				}
		
		
		
		if(guessClass(name, new String[]
				{
					"spear",
					"javelin",
					"pike",
				}))
				{
					return EnumWeaponType.POLEARM;
				}
		
		
		if(guessClass(name, new String[]
				{
					"axe",
					"tommahawk",
					"hatchet",
				}))
				{
					return EnumWeaponType.AXE;
				}
		
		
		if(guessClass(name, new String[]
				{
					"knife",
					"dagger",
					"dirk",
					"stiletto",
				}))
				{
					return EnumWeaponType.BLADE;
				}
		
		
		
		if(guessClass(name, new String[]
				{
					"mace",
					"club",
					"blackjack",
				}))
				{
					return EnumWeaponType.BLUNT;
				}
		
		
		
		
		
		if(guessClass(name, new String[]
				{
					"staff",
					"quaterstaff",
				}))
				{
					return EnumWeaponType.BLUNT;
				}
		
		
		
		return EnumWeaponType.BLADE;
	}
	
	private static boolean guessClass(String name, String[] keys)
	{
		for(String key : keys)
		{
			if(name.contains(key))
			{
				return true;
			}
		}
		return false;
	}
}

