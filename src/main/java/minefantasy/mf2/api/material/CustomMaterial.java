package minefantasy.mf2.api.material;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public class CustomMaterial
{
	public static final String NBTBase = "MF_CustomMaterials";
	public static HashMap<String, CustomMaterial> materialList = new HashMap<String, CustomMaterial>();
	public static HashMap<String, ArrayList<CustomMaterial>> typeList = new HashMap<String, ArrayList<CustomMaterial>>();
	
	public ArrayList<ItemStack>items = new ArrayList<ItemStack>();
	/**
	 * The colour of the material
	 */
	public int[] colourRGB = new int[]{255, 255, 255};
	/**
	 * The Modifier for durability and Armour Rating
	 */
	public float hardness;
	/**
	 * The Modifier for flexibility (Bow damage and workability)
	 */
	public float flexibility;
	/**
	 * How much damage is done (Such as ToolMaterial)
	 */
	public float sharpness;
	/**
	 * The weight (Kg) A single unit is such as a plank, strip or ingot.
	 */
	public float weight;
	public int tier;
	public String type, name;
	
	public CustomMaterial(String name, String type, int tier, float hardness, float flexibility, float sharpness, float weight)
	{
		this.name = name;
		this.type = type;
		this.tier = tier;
		this.hardness = hardness;
		this.flexibility = flexibility;
		this.sharpness = sharpness;
		this.weight = weight;
	}
	public CustomMaterial setColour(int red, int green, int blue)
	{
		colourRGB = new int[]{red, green, blue};
		return this;
	}
	
	public CustomMaterial register()
	{
		materialList.put(name.toLowerCase(), this);
		getList(type).add(this);
		return this;
	}
	public int getColourInt() 
	{
		return (colourRGB[0] << 16) + (colourRGB[1] << 8) + colourRGB[2];
	}
	
	/**
	 * Gets a material by name
	 */
	public static CustomMaterial getMaterial(String name)
	{
		return materialList.get(name.toLowerCase());
	}
	
	public static CustomMaterial getOrAddMaterial(String name, String type, int tier, float hardness, float flexibility, float sharpness, float weight, int red, int green, int blue)
	{
		if(getMaterial(name) != null)
		{
			return getMaterial(name);
		}
		return new CustomMaterial(name, type, tier, hardness, flexibility, sharpness, weight).setColour(red, green, blue).register();
	}
	
	public static void addMaterial(ItemStack item, String slot, String material)
	{
		if(material == null || material.isEmpty())
		{
			return;
		}
		NBTTagCompound nbt = getNBT(item, true);
		nbt.setString(slot, material);
	}
	public static CustomMaterial getMaterialFor(ItemStack item, String slot)
	{
		NBTTagCompound nbt = getNBT(item, false);
		if(nbt != null)
		{
			if(nbt.hasKey(slot))
			{
				return getMaterial(nbt.getString(slot));
			}
		}
		return null;
	}
	public static NBTTagCompound getNBT(ItemStack item, boolean createNew)
	{
		if(item != null && item.hasTagCompound() && item.getTagCompound().hasKey(NBTBase))
		{
			return (NBTTagCompound) item.getTagCompound().getTag(NBTBase);
		}
		if(createNew)
		{
			NBTTagCompound nbt = new NBTTagCompound();
			NBTTagCompound nbt2 = new NBTTagCompound();
			item.setTagCompound(nbt);
			nbt.setTag(NBTBase, nbt2);
			return nbt2;
		}
		return null;
	}
	
	public static ArrayList<CustomMaterial> getList(String type)
	{
		if(typeList.get(type) == null)
		{
			typeList.put(type, new ArrayList<CustomMaterial>());
		}
		return typeList.get(type);
	}
	
	public static void addOreDict(String material, String name)
	{
		if(getMaterial(name) != null)
		{
			getMaterial(name).addOreDict(material);
		}
	}
	public void addOreDict(String name)
	{
		for(ItemStack item: OreDictionary.getOres(name))
		{
			items.add(item);
		}
	}
	public boolean isItemApplicable(ItemStack item)
	{
		return this.items.contains(item);
	}
}
