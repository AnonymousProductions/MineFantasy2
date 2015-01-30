package minefantasy.mf2.recipe;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.list.ArmourListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class ForgingRecipes
{
	public static void init()
	{
		for(int id = 0; id < ToolListMF.mats.length; id ++)
		{
			int time;
			BaseMaterialMF material = ToolListMF.mats[id];
			
			//PICKS
			time = 15;
			Item tool;
			tool = ToolListMF.picks[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"L I",
					"SSI",
					"L I",
					'L', Items.leather,
					'S', ComponentListMF.plank,
					'I', ingot,
				});
			}
			
			//AXES
			time = 12;
			tool = ToolListMF.axes[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"LII",
					"SSI",
					"L  ",
					'L', Items.leather,
					'S', ComponentListMF.plank,
					'I', ingot,
				});
			}
			
			//SPADES
			time = 8;
			tool = ToolListMF.spades[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"L  ",
					"SSI",
					"L  ",
					'L', Items.leather,
					'S', ComponentListMF.plank,
					'I', ingot,
				});
			}
			
			//HOES
			time = 15;
			tool = ToolListMF.hoes[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"L I",
					"SSI",
					"L  ",
					'L', Items.leather,
					'S', ComponentListMF.plank,
					'I', ingot,
				});
			}
			
			//SHEARS
			time = 12;
			tool = ToolListMF.shears[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I ",
					"SLI",
					" S ",
					'L', Items.leather,
					'S', ComponentListMF.plank,
					'I', ingot,
				});
			}
		
			//HAMMERS
			time = 10;
			tool = ToolListMF.hammers[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier-1, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"I",
					"L",
					"S",
					'L', Items.leather,
					'S', ComponentListMF.plank,
					'I', ingot,
				});
			}
			
			//TONGS
			time = 10;
			tool = ToolListMF.tongs[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"I ",
					" I",
					'I', ingot,
				});
			}
		
			//SAWS
			time = 20;
			tool = ToolListMF.saws[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"SIIII",
					"SIII ",
					'S', ComponentListMF.plank,
					'I', ingot,
				});
			}
		
			//KNIVES
			time = 12;
			tool = ToolListMF.knives[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I",
					" I",
					"LS",
					'L', Items.leather,
					'S', ComponentListMF.plank,
					'I', ingot,
				});
			}
		
			//NEEDLES
			time = 5;
			tool = ToolListMF.needles[id];
			for(ItemStack ingot: OreDictionary.getOres("hunk"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"I",
					"I",
					"I",
					"I",
					'I', ingot,
				});
			}
			
			if(id > 0)
			{
				//HVYPICKS
				time = 30;
				tool = ToolListMF.hvypicks[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L I ",
						"SSII",
						"L II",
						'L', Items.leather,
						'S', ComponentListMF.plank,
						'I', ingot,
					});
				}
				//HVYSHOVELS
				time = 20;
				tool = ToolListMF.hvyshovels[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L II",
						"SSII",
						"L II",
						'L', Items.leather,
						'S', ComponentListMF.plank,
						'I', ingot,
					});
				}
				//HANDPICKS
				time = 12;
				tool = ToolListMF.handpicks[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"LI",
						"SI",
						"L ",
						'L', Items.leather,
						'S', ComponentListMF.plank,
						'I', ingot,
					});
				}
				//TROWS
				time = 15;
				tool = ToolListMF.trows[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L ",
						"SI",
						"L ",
						'L', Items.leather,
						'S', ComponentListMF.plank,
						'I', ingot,
					});
				}
				//HVYHAMMERS
				time = 25;
				tool = ToolListMF.hvyHammers[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"II",
						"LI",
						"S ", 
						'L', Items.leather,
						'S', ComponentListMF.plank,
						'I', ingot,
					});
				}
				//SCYTHES
				time = 30;
				tool = ToolListMF.scythes[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"  I ",
						"   I",
						" L I",
						"SSSI",
						
						'L', Items.leather,
						'S', ComponentListMF.plank,
						'I', ingot,
					});
				}
			}
		}
		//WEAPONS
		for(int id = 0; id < ToolListMF.weaponMats.length; id ++)
		{
			int time;
			Item tool;
			BaseMaterialMF material = ToolListMF.weaponMats[id];
			
			
			//ARROWS
			time = 10;
			tool = ComponentListMF.arrowheads[id];
			for(ItemStack ingot: OreDictionary.getOres("hunk"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool, 4), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"I ",
					"II",
					"I ",
					
					'I', ingot,
				});
			}
			
			
			if(material != BaseMaterialMF.ornate)
		{
			
			//DAGGERS
			time = 12;
			tool = ToolListMF.daggers[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"L  ",
					"SII",
					"L  ",
					'L', Items.leather,
					'S', ComponentListMF.plank,
					'I', ingot,
				});
			}
			
			//SWORDS
			time = 25;
			tool = ToolListMF.swords[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"LI  ",
					"SIII",
					"LI  ",
					'L', Items.leather,
					'S', ComponentListMF.plank,
					'I', ingot,
				});
			}
			
			//AXES
			time = 20;
			tool = ToolListMF.waraxes[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"LII",
					"SSI",
					"L I",
					'L', Items.leather,
					'S', ComponentListMF.plank,
					'I', ingot,
				});
			}
			
			//MACES
			time = 18;
			tool = ToolListMF.maces[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"L II",
					"SSII",
					"L   ",
					'L', Items.leather,
					'S', ComponentListMF.plank,
					'I', ingot,
				});
			}
			
			//SPEARS
			time = 20;
			tool = ToolListMF.spears[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" L I ",
					"SSSSI",
					" L I ",
					'L', Items.leather,
					'S', ComponentListMF.plank,
					'I', ingot,
				});
			}
			//BOWS
			time = 30;
			tool = ToolListMF.bows[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"ISSSI",
					" ILI ",
					
					'L', Items.leather,
					'S', Items.string,
					'I', ingot,
				});
			}
			
			if(id > 0)
			{
				//HALBEARDS
				time = 30;
				tool = ToolListMF.halbeards[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						" L II",
						"SSSSI",
						" L I ",
						'L', Items.leather,
						'S', ComponentListMF.plank,
						'I', ingot,
					});
				}
				
				//GREATSWORDS
				time = 35;
				tool = ToolListMF.greatswords[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"LI   ",
						"SIIII",
						"LI   ",
						'L', Items.leather,
						'S', ComponentListMF.plank,
						'I', ingot,
					});
				}
				
				//KATANAS
				time = 40;
				tool = ToolListMF.katanas[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L    I",
						"SIIII ",
						"LI    ",
						'L', Items.leather,
						'S', ComponentListMF.plank,
						'I', ingot,
					});
				}
				
				//BATTLEAXES
				time = 30;
				tool = ToolListMF.battleaxes[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L II",
						"SSSI",
						"L II",
						'L', Items.leather,
						'S', ComponentListMF.plank,
						'I', ingot,
					});
				}
				
				//WARHAMMERS
				time = 28;
				tool = ToolListMF.warhammers[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L  II",
						"SSSII",
						"L   I",
						'L', Items.leather,
						'S', ComponentListMF.plank,
						'I', ingot,
					});
				}
				//LANCES
				time = 50;
				tool = ToolListMF.lances[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"I     ",
						"SIIIII",
						"I     ",
						'L', Items.leather,
						'S', ComponentListMF.plank,
						'I', ingot,
					});
				}
			}
		}
		}
		
		
		//MISC
		
		BaseMaterialMF material;
		int time;
		time = 1;
		material = BaseMaterialMF.encrusted;
		
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.diamond_shards), false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"D",
			'D', Items.diamond,
		});
		time = 2;
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.diamond_dust, 8), false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"D",
			'D', ComponentListMF.diamond_shards
		});
		
		time = 3;
		for(ItemStack ingot: OreDictionary.getOres("ingotSteel"))
		{
			MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.ingots[5]), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"D",
				"I",
				'D', ComponentListMF.diamond_shards,
				'I', ingot,
			});
		}
		
		material = BaseMaterialMF.dragonforge;
		time = 5;
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.ingots[8]), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			" I ",
			"IDI",
			" I ",
			'D', ComponentListMF.dragon_heart,
			'I', ComponentListMF.hunks[7],
		});
		
		//HUNKS
		time = 1;
		for(int id = 0; id < ComponentListMF.hunkMats.length; id ++)
		{
			material = ComponentListMF.hunkMats[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.hunks[id], 4), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"I", 'I', ingot
				});
				MineFantasyAPI.addAnvilRecipe(ingot, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"II",
					"II",
					'I', ComponentListMF.hunks[id]
				});
			}
		}
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.coalDust, 4), false, -1, -1, 2, new Object[]
		{
			"H",
			'H', Items.coal
		});
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.shrapnel, 4), false, 2, 2, 10, new Object[]
		{
			"H",
			'H', Items.flint
		});
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.kaolinite_dust, 4), false, -1, -1, 4, new Object[]
		{
			"H",
			'H', ComponentListMF.kaolinite
		});
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.flux, 4), false, -1, -1, 2, new Object[]
		{
			"H",
			'H', BlockListMF.limestone_cobblestone,
		});
		
		
		
		//ARMOUR STUFF//
		
		//Armour Components
		for(int id = 0; id < ArmourListMF.mats.length; id ++)
		{
			material = ArmourListMF.mats[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				time = 5;
				if(id > 0)
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.plates[id-1]), true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"II",
						'I', ingot
					});
				}
			}
			for(ItemStack hunk: OreDictionary.getOres("hunk"+material.name))
			{
				time = 5;
				MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.chainmeshes[id]), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I ",
					"I I",
					" I ",
					'I', hunk
				});
			}
			
		}
		//MAIL ARMOUR
		for(int id = 0; id < ArmourListMF.mats.length; id ++)
		{
			material = ArmourListMF.mats[id];
			
			//HELMET
			time = 10;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.chainmail, id, 0), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"MMM",
				"MPM",
				'M', ComponentListMF.chainmeshes[id],
				'P', Items.leather,
			});
			
			//CHEST
			time = 20;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.chainmail, id, 1), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"MMPMM",
				" MPM ",
				" MPM ",
				'M', ComponentListMF.chainmeshes[id],
				'P', Items.leather,
			});
			
			//HELMET
			time = 15;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.chainmail, id, 2), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"PPP",
				"MMM",
				"M M",
				"M M",
				'M', ComponentListMF.chainmeshes[id],
				'P', Items.leather,
			});
			
			//BOOTS
			time = 8;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.chainmail, id, 3), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" M M ",
				"M P M",
				'M', ComponentListMF.chainmeshes[id],
				'P', Items.leather,
			});
		}
				
				
		//PLATE ARMOUR
		for(int id = 1; id < ArmourListMF.mats.length; id ++)
		{
			material = ArmourListMF.mats[id];
			
			//HELMET
			time = 20;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.fieldplate, id, 0), true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" M ",
				"MPM",
				"P P",
				" M ",
				'M', ComponentListMF.plates[id-1],
				'P', ComponentListMF.padding
			});
			
			//CHEST
			time = 40;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.fieldplate, id, 1), true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"M  M",
				"MPPM",
				"MPPM",
				" PP ",
				'M', ComponentListMF.plates[id-1],
				'P', ComponentListMF.padding
			});
			
			//HELMET
			time = 30;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.fieldplate, id, 2), true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"  P  ",
				"MP PM",
				"MP PM",
				'M', ComponentListMF.plates[id-1],
				'P', ComponentListMF.padding
			});
			
			//BOOTS
			time = 15;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.fieldplate, id, 3), true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"MP PM",
				'M', ComponentListMF.plates[id-1],
				'P', ComponentListMF.padding
			});
			
			//ADV LEATHER
			
			//STUDDED
			material = BaseMaterialMF.iron;
			//HELMET
			time = 10;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 3, 0), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" I ",
				"IAI",
				" I ",
				'I', Items.iron_ingot,
				'A', Items.leather_helmet,
			});
			//CHEST
			time = 20;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 3, 1), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" I ",
				"IAI",
				" I ",
				'I', Items.iron_ingot,
				'A', Items.leather_chestplate,
			});
			//LEGS
			time = 15;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 3, 2), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" I ",
				"IAI",
				" I ",
				'I', Items.iron_ingot,
				'A', Items.leather_leggings,
			});
			//BOOTS
			time = 6;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 3, 3), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" I ",
				"IAI",
				" I ",
				'I', Items.iron_ingot,
				'A', Items.leather_boots,
			});
			
			//SCALED
			material = BaseMaterialMF.steel;
			for(ItemStack ingot: OreDictionary.getOres("ingotSteel"))
			{
				//HELMET
				time = 10;
				MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 4, 0), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I ",
					"IAI",
					" I ",
					'I', ingot,
					'A', Items.leather_helmet,
				});
				//CHEST
				time = 20;
				MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 4, 1), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I ",
					"IAI",
					" I ",
					'I', ingot,
					'A', Items.leather_chestplate,
				});
				//LEGS
				time = 15;
				MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 4, 2), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I ",
					"IAI",
					" I ",
					'I', ingot,
					'A', Items.leather_leggings,
				});
				//BOOTS
				time = 6;
				MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 4, 3), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I ",
					"IAI",
					" I ",
					'I', ingot,
					'A', Items.leather_boots,
				});
			}
		}
		
		//BOMBS
		material = BaseMaterialMF.iron;
		
		time = 3;
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.bomb_casing_iron, 2), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			" I ",
			"IRI",
			" I ",
			'I', ComponentListMF.hunks[3],
			'R', Items.redstone,
		});
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.mine_casing_iron, 2), true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"  P  ",
			" IRI ",
			"I R I",
			'P', Blocks.heavy_weighted_pressure_plate,
			'I', ComponentListMF.hunks[3],
			'R', Items.redstone,
		});
	}
}
