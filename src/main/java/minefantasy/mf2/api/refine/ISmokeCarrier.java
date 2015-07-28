package minefantasy.mf2.api.refine;

import net.minecraft.util.EnumFacing;

public interface ISmokeCarrier
{
	public int getSmokeValue();
	public void setSmokeValue(int smoke);
	public int getMaxSmokeStorage();
	public boolean canAbsorbIndirect();
	void onEntityUpdate();
	int[] getAccessibleSlotsFromSide(EnumFacing side);
}
