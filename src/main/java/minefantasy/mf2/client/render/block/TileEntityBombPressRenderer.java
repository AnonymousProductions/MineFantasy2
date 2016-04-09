package minefantasy.mf2.client.render.block;

import java.util.Random;

import minefantasy.mf2.api.helpers.TextureHelperMF;
import minefantasy.mf2.block.tileentity.TileEntityBombPress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;


/**
 *
 * @author Anonymous Productions
 * 
 * Sources are provided for educational reasons.
 * though small bits of code, or methods can be used in your own creations.
 * 
 * Custom renderers based off render tutorial by MC_DucksAreBest
 */
public class TileEntityBombPressRenderer extends TileEntitySpecialRenderer 
{
    public TileEntityBombPressRenderer() 
    {
        model = new ModelBombPress();
    }

	public void renderAModelAt(TileEntity tile, double d, double d1, double d2, float f) 
    {
    	int i = 0;
		if (tile.getWorldObj() != null)
		{
        	i = tile.getBlockMetadata();
        }
    	for(int a = 0; a < 2; a ++)
    	{
    		if(shouldRender(tile, a))
    		{
    			this.renderModelAt("bombPress", i, d, d1, d2, f, a, ((TileEntityBombPress)tile).animation);
    		}
    	}
    }
    
	public void renderModelAt(String tex, int meta, double d, double d1, double d2, float f, int renderPass, float animation) 
    {
        int i = meta;
        

        int j = 90 * i;

        if (i == 0) {
            j = 0;
        }

        if (i == 1) {
            j = 270;
        }

        if (i == 2) {
            j = 180;
        }

        if (i == 3) {
            j = 90;
        }
        if (i == 4) {
            j = 90;
        }
    	bindTextureByName("textures/models/tileentity/"+tex+".png"); //texture
        
        GL11.glPushMatrix(); //start
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1, (float) d2 + 0.5F); //size
        GL11.glRotatef(j+180F, 0.0F, 1.0F, 0.0F); //rotate based on metadata
        GL11.glScalef(1F, -1F, -1F); //if you read this comment out this line and you can see what happens
        GL11.glPushMatrix();
        model.renderModel(0.0625F, animation); 
        
        GL11.glPopMatrix();
        GL11.glColor3f(255, 255, 255);
        GL11.glPopMatrix(); //end

    }
    private void bindTextureByName(String image)
    {
    	Minecraft.getMinecraft().renderEngine.bindTexture(TextureHelperMF.getResource(image));
	}
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        renderAModelAt (tileentity, d, d1, d2, f); //where to render
    }
	
    private ModelBombPress model;
    private Random random = new Random();
    
    
    private boolean shouldRender(TileEntity tile, int p) 
    {
    	Minecraft mc = Minecraft.getMinecraft();
    	EntityPlayer sp = mc.thePlayer;
    	if(p == 1)//GRID
    	{
    		return false;
    	}
    	return true;
	}
}
