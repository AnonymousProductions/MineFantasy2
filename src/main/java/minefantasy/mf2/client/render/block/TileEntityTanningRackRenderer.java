package minefantasy.mf2.client.render.block;


import minefantasy.mf2.api.helpers.TextureHelperMF;
import minefantasy.mf2.block.tileentity.TileEntityAnvilMF;
import minefantasy.mf2.block.tileentity.TileEntityTanningRack;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.storage.MapData;
import minefantasy.mf2.block.crafting.BlockTanningRack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntityTanningRackRenderer extends TileEntitySpecialRenderer 
{
	private ModelResourceLocation tannerModel = new ModelResourceLocation("tanner", "normal");
	//private RenderItem itemRenderer;
	ItemRenderer rendereritem = Minecraft.getMinecraft().getItemRenderer();
	
	private RenderItem itemRenderer;
	private ModelTanningRack model;
	
    public TileEntityTanningRackRenderer() 
    {
    	model = new ModelTanningRack();
    }

    BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
	//ModelManager modelmanager = blockrendererdispatcher.getBlockModelShapes().getModelManager();
    
    public void renderAModelAt(TileEntityTanningRack tile, double d, double d1, double d2,float f) {
//    	Minecraft mc = Minecraft.getMinecraft();
//    	Block block = tile.getWorld().getBlockState(new BlockPos(d,d1,d2)).getBlock();
//    	
////    	if( block.getClass() == BlockTanningRack.class){
////    		int tier = ((BlockTanningRack)block).tier;
////    		if(tier == 1){
////    			tannerModel = new ModelResourceLocation("tannerStrong", "normal");
////    		}
////    	}
//    	
//		IBakedModel ibakedmodel;
//		
//
//		//ibakedmodel = (IBakedModel) model;
//    	GlStateManager.pushMatrix();
//        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
//        //blockrendererdispatcher.getBlockModelRenderer().renderModelBrightnessColor(ibakedmodel, 1.0F, 1.0F, 1.0F, 1.0F);
//        GlStateManager.popMatrix();
//        GlStateManager.translate(0.0F, 0.0F, 0.4375F);
//        this.renderHungItem(tile,d,d1,d2);
//        GlStateManager.popMatrix();
    	
    	
    	int i = 0;
		if (tile.getWorld() != null)
		{
        	i = tile.getBlockMetadata();
        }
    	for(int a = 0; a < 2; a ++)
    	{
    		if(shouldRender(tile, a))
    		{
    			this.renderModelAt(tile, i, d, d1, d2, f, a);
    		}
    	}

    }
    
    public void renderModelAt(TileEntityTanningRack tile, int meta, double d, double d1, double d2, float f, int renderPass) 
    {
        int i = meta;
        String tex = tile.getName();

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

        if(renderPass == 1)
        {
        	bindTextureByName("textures/models/tileentity/anvilGrid.png"); //texture
        }
        else
        {
        	bindTextureByName("textures/models/tileentity/"+tex+".png"); //texture
        }
        
        GL11.glPushMatrix(); //start
        float scale = 1.0F;
        float yOffset = 1.19F;
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + (0.3F*scale)+yOffset, (float) d2 + 0.5F); //size
        GL11.glRotatef(j+180F, 0.0F, 1.0F, 0.0F); //rotate based on metadata
        GL11.glScalef(scale, -scale, -scale); //if you read this comment out this line and you can see what happens
        GL11.glPushMatrix();
        model.renderModel(0.0625F); 
        GL11.glPopMatrix();
        GL11.glColor3f(255, 255, 255);
        GL11.glPopMatrix(); //end
        this.renderHungItem(tile,d,d1,d2,j);

    }
    
    
    private void bindTextureByName(String image)
    {
    	Minecraft.getMinecraft().renderEngine.bindTexture(TextureHelperMF.getResource(image));
	}

	
	private void renderHungItem(TileEntityTanningRack tile, double d, double d1,double d2,int j) {
		
		ItemStack itemstack = tile.getStackInSlot(0);
		
		if (itemstack != null)
        {
            EntityItem entityitem = new EntityItem(tile.getWorld(), 0.0D, 0.0D, 0.0D, itemstack);
            Item item = entityitem.getEntityItem().getItem();
            entityitem.getEntityItem().stackSize = 1;
            entityitem.hoverStart = 0.0F;
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GL11.glTranslatef((float) d + 0.5F, (float) d1+(0.3F*1f)+0.5F, (float) d2 + 0.5F);
            GL11.glRotatef(j+180F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(1F, 1F, 1F);
            //GlStateManager.rotate((float) 360.0F / 8.0F, 0.0F, 0.0F, 1.0F);

                //GlStateManager.scale(0.5F, 0.5F, 0.5F);
                //GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);

                GlStateManager.pushAttrib();
                RenderHelper.enableStandardItemLighting();
                Minecraft.getMinecraft().getRenderItem().renderItemModel(entityitem.getEntityItem());
                RenderHelper.disableStandardItemLighting();
                GlStateManager.popAttrib();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
			

//			Item item = itemstack.getItem();
//			mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
//	
//	        Tessellator image = Tessellator.getInstance();
//	        IIcon index = item.getIconFromDamage(itemstack.getItemDamage());
//	        float x1 = index.getMinU();
//	        float x2 = index.getMaxU();
//	        float y1 = index.getMinV();
//	        float y2 = index.getMaxV();
//	        float xPos = 0.5F;
//	        float yPos = -0.5F;
//	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
//	        GL11.glTranslatef(-xPos, -yPos, 0.0F);
//	        float var13 = 1F;
//	        GL11.glScalef(var13, var13, var13);
//	        //GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
//	        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
//	        GL11.glTranslatef(-1F, -1F, 0.0F);
//	        ItemRenderer.renderItemIn2D(image, x2, y1, x1, y2, index.getIconWidth(), index.getIconHeight(), 0.0625F);
		
	}
	
    

    @Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z,
			float f, int destroyStage) {
    	renderAModelAt((TileEntityTanningRack) te, x, y, z,f); //where to render
		
	}
    
    private boolean shouldRender(TileEntityTanningRack tile, int p) 
    {
    	Minecraft mc = Minecraft.getMinecraft();
    	EntityPlayer sp = mc.thePlayer;
    	return true;
    }
}