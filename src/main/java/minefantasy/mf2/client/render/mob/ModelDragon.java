package minefantasy.mf2.client.render.mob;

import minefantasy.mf2.entity.mob.EntityDragon;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDragon extends ModelBase
{
    //fields

	ModelRenderer footBL;
    ModelRenderer legBL;
    ModelRenderer legFR;
    ModelRenderer footBR;
    ModelRenderer legBR;
    ModelRenderer thighBR;
    ModelRenderer mouth2;
    ModelRenderer tail3;
    ModelRenderer neck;
    ModelRenderer horn2;
    ModelRenderer nose;
    ModelRenderer horn1;
    ModelRenderer eyes;
    ModelRenderer thighBL;
    ModelRenderer legFL;
    ModelRenderer wing2L;
    ModelRenderer tail1;
    ModelRenderer body;
    ModelRenderer wing2R;
    ModelRenderer wingCR;
    ModelRenderer wingCL;
    ModelRenderer wing1L;
    ModelRenderer wing1R;
    ModelRenderer mouth;
    ModelRenderer nose2;
    ModelRenderer tail2;
    ModelRenderer head;
  
  public ModelDragon()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      
      footBL = new ModelRenderer(this, 102, 29);
      footBL.addBox(-3F, 8F, -3F, 5, 1, 8);
      footBL.setRotationPoint(8F, 15F, 13F);
      footBL.setTextureSize(128, 128);
      setRotation(footBL, 0F, 0F, 0F);
      legBL = new ModelRenderer(this, 108, 16);
      legBL.addBox(-3F, 0F, 1F, 5, 8, 5);
      legBL.setRotationPoint(8F, 15F, 13F);
      legBL.setTextureSize(128, 128);
      setRotation(legBL, 0F, 0F, 0F);
      legFR = new ModelRenderer(this, 86, 0);
      legFR.addBox(0F, -2F, -2F, 4, 12, 4);
      legFR.setRotationPoint(-7F, 14F, -4F);
      legFR.setTextureSize(128, 128);
      setRotation(legFR, 0F, 0F, 0F);
      footBR = new ModelRenderer(this, 102, 29);
      footBR.addBox(-2F, 8F, -3F, 5, 1, 8);
      footBR.setRotationPoint(-8F, 15F, 13F);
      footBR.setTextureSize(128, 128);
      setRotation(footBR, 0F, 0F, 0F);
      legBR = new ModelRenderer(this, 108, 16);
      legBR.addBox(-2F, 0F, 1F, 5, 8, 5);
      legBR.setRotationPoint(-8F, 15F, 13F);
      legBR.setTextureSize(128, 128);
      setRotation(legBR, 0F, 0F, 0F);
      thighBR = new ModelRenderer(this, 102, 0);
      thighBR.addBox(-2F, -8F, -1F, 5, 8, 8);
      thighBR.setRotationPoint(-8F, 15F, 13F);
      thighBR.setTextureSize(128, 128);
      setRotation(thighBR, 0F, 0F, 0F);
      mouth2 = new ModelRenderer(this, 0, 64);
      mouth2.addBox(-1.5F, 1F, -14F, 3, 1, 7);
      mouth2.setRotationPoint(0F, 4F, -14F);
      mouth2.setTextureSize(128, 128);
      tail3 = new ModelRenderer(this, 0, 0);
      tail3.addBox(-1.5F, 6.5F, 28F, 3, 3, 18);
      tail3.setRotationPoint(0F, 13F, 18F);
      tail3.setTextureSize(128, 128);
      setRotation(tail3, 0.1396263F, 0F, 0F);
      neck = new ModelRenderer(this, 52, 46);
      neck.addBox(-3F, -3F, -14F, 6, 8, 10);
      neck.setRotationPoint(0F, 5F, 0F);
      neck.setTextureSize(128, 128);
      setRotation(neck, -0.122173F, 0F, 0F);
      horn2 = new ModelRenderer(this, 104, 52);
      horn2.addBox(3F, -3F, 1F, 1, 1, 11);
      horn2.setRotationPoint(0F, 4F, -14F);
      horn2.setTextureSize(128, 128);
      setRotation(horn2, 0.6108652F, 0F, 0F);
      nose = new ModelRenderer(this, 0, 74);
      nose.addBox(-2F, 2F, -15F, 4, 2, 8);
      nose.setRotationPoint(0F, 4F, -14F);
      nose.setTextureSize(128, 128);
      horn1 = new ModelRenderer(this, 104, 52);
      horn1.addBox(-4F, -3F, 1F, 1, 1, 11);
      horn1.setRotationPoint(0F, 4F, -14F);
      horn1.setTextureSize(128, 128);
      setRotation(horn1, 0.6108652F, 0F, 0F);
      eyes = new ModelRenderer(this, 0, 37);
      eyes.addBox(-5F, -3F, -3F, 10, 3, 3);
      eyes.setRotationPoint(0F, 4F, -14F);
      eyes.setTextureSize(128, 128);
      setRotation(eyes, 0F, 0F, 0F);
      thighBL = new ModelRenderer(this, 102, 0);
      thighBL.addBox(-3F, -8F, -1F, 5, 8, 8);
      thighBL.setRotationPoint(8F, 15F, 13F);
      thighBL.setTextureSize(128, 128);
      setRotation(thighBL, 0F, 0F, 0F);
      legFL = new ModelRenderer(this, 86, 0);
      legFL.addBox(-4F, -2F, -2F, 4, 12, 4);
      legFL.setRotationPoint(7F, 14F, -4F);
      legFL.setTextureSize(128, 128);
      setRotation(legFL, -0.0174533F, 0F, 0F);
      wing2L = new ModelRenderer(this, 0, 88);
      wing2L.addBox(13F, -1F, 1F, 2, 2, 16);
      wing2L.setRotationPoint(5F, 4F, -2F);
      wing2L.setTextureSize(128, 128);
      setRotation(wing2L, 0F, 0.5235988F, -0.3490659F);
      tail1 = new ModelRenderer(this, 47, 19);
      tail1.addBox(-3F, -4F, -1F, 6, 9, 14);
      tail1.setRotationPoint(0F, 13F, 18F);
      tail1.setTextureSize(128, 128);
      setRotation(tail1, -0.1745329F, 0F, 0F);
      body = new ModelRenderer(this, 56, 67);
      body.addBox(-6F, -5F, -5F, 12, 12, 24);
      body.setRotationPoint(0F, 7F, 0F);
      body.setTextureSize(128, 128);
      setRotation(body, -0.296706F, 0F, 0F);
      wing2R = new ModelRenderer(this, 0, 88);
      wing2R.addBox(-15F, -1F, 1F, 2, 2, 16);
      wing2R.setRotationPoint(-5F, 4F, -2F);
      wing2R.setTextureSize(128, 128);
      setRotation(wing2R, 0F, -0.5235988F, 0.3490659F);
      wingCR = new ModelRenderer(this, 48, 103);
      wingCR.addBox(-13F, -1F, 1F, 16, 1, 24);
      wingCR.setRotationPoint(-5F, 4F, -2F);
      wingCR.setTextureSize(128, 128);
      setRotation(wingCR, 0F, -0.5235988F, 0.3490659F);
      wingCL = new ModelRenderer(this, 48, 103);
      wingCL.addBox(-3F, -1F, 1F, 16, 1, 24);
      wingCL.setRotationPoint(5F, 4F, -2F);
      wingCL.setTextureSize(128, 128);
      setRotation(wingCL, 0F, 0.5235988F, -0.3490659F);
      wing1L = new ModelRenderer(this, 0, 84);
      wing1L.addBox(-1F, -1F, -1F, 16, 2, 2);
      wing1L.setRotationPoint(5F, 4F, -2F);
      wing1L.setTextureSize(128, 128);
      setRotation(wing1L, 0F, 0.5235988F, -0.3490659F);
      wing1R = new ModelRenderer(this, 0, 84);
      wing1R.addBox(-15F, -1F, -1F, 16, 2, 2);
      wing1R.setRotationPoint(-5F, 4F, -2F);
      wing1R.setTextureSize(128, 128);
      setRotation(wing1R, 0F, -0.5235988F, 0.3490659F);
      mouth = new ModelRenderer(this, 0, 55);
      mouth.addBox(-1.5F, 2F, -14F, 3, 2, 7);
      mouth.setRotationPoint(0F, 4F, -14F);
      mouth.setTextureSize(128, 128);
      setRotation(mouth, -0.122173F, 0F, 0F);
      nose2 = new ModelRenderer(this, 0, 43);
      nose2.addBox(-2F, -2F, -15F, 4, 4, 8);
      nose2.setRotationPoint(0F, 4F, -14F);
      nose2.setTextureSize(128, 128);
      tail2 = new ModelRenderer(this, 0, 106);
      tail2.addBox(-2F, 2F, 12F, 4, 4, 18);
      tail2.setRotationPoint(0F, 13F, 18F);
      tail2.setTextureSize(128, 128);
      setRotation(tail2, -0.0174533F, 0F, 0F);
      head = new ModelRenderer(this, 24, 48);
      head.addBox(-3F, -4F, -7F, 6, 8, 8);
      head.setRotationPoint(0F, 4F, -14F);
      head.setTextureSize(128, 128);
      setRotation(head, 0F, 0F, 0F);
  }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles((EntityDragon) entity, f, f1, f2, f3, f4, f5);
        footBL.render(f5);
        legBL.render(f5);
        legFR.render(f5);
        footBR.render(f5);
        legBR.render(f5);
        thighBR.render(f5);
        tail2.render(f5);
        tail3.render(f5);
        neck.render(f5);
        horn2.render(f5);
        horn1.render(f5);
        head.render(f5);
        thighBL.render(f5);
        legFL.render(f5);
        wing2L.render(f5);
        tail1.render(f5);
        body.render(f5);
        wing2R.render(f5);
        wingCR.render(f5);
        wingCL.render(f5);
        wing1L.render(f5);
        wing1R.render(f5);
        mouth.render(f5);
        nose.render(f5);
        nose2.render(f5);
        eyes.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(EntityDragon dragon, float step1, float step2, float head1, float head2, float head3, float head4)
    {
        super.setRotationAngles(step1, step2, head1, head2, head3, head4, dragon);
        float wingFlap = (float) Math.toRadians(dragon.wingFlap());
        float jawAngle = (float)Math.toRadians(dragon.getJawMove());
        float neckAngle = -(float)Math.toRadians(4.5F * dragon.getNeckAngle());

        this.head.rotateAngleX = head4 / (180F / (float) Math.PI) + neckAngle;
        this.head.rotateAngleY = head3 / (180F / (float) Math.PI);
        this.eyes.rotateAngleX = head.rotateAngleX;
        this.eyes.rotateAngleY = head.rotateAngleY;

        if (!dragon.isTerrestrial()) 
        {
            if (dragon.motionX == 0 && dragon.motionZ == 0) 
            {
                wingFlap = (float) Math.toRadians(dragon.wingFlap());
            } else {
                  wingFlap = MathHelper.cos(step1 * 0.6662F) * 1.4F * step2;
            }
        } else 
        {
            wingFlap = (float) Math.toRadians(-40);
            this.legBR.rotateAngleX = MathHelper.cos(step1 * 0.6662F) * 1.4F * step2;
            this.legBL.rotateAngleX = MathHelper.cos(step1 * 0.6662F + (float) Math.PI) * 1.4F * step2;
            this.legFR.rotateAngleX = MathHelper.cos(step1 * 0.6662F + (float) Math.PI) * 1.4F * step2;
            this.legFL.rotateAngleX = MathHelper.cos(step1 * 0.6662F) * 1.4F * step2;
        }
            this.wing1R.rotateAngleZ = (float) wingFlap;
            this.wing2R.rotateAngleZ = (float) wingFlap;
            this.wingCR.rotateAngleZ = (float) wingFlap;

            this.wing1L.rotateAngleZ = (float) -wingFlap;
            this.wing2L.rotateAngleZ = (float) -wingFlap;
            this.wingCL.rotateAngleZ = (float) -wingFlap;
        

        this.footBL.rotateAngleX = this.legBL.rotateAngleX;
        this.footBR.rotateAngleX = this.legBR.rotateAngleX;
        this.thighBL.rotateAngleX = this.legBL.rotateAngleX;
        this.thighBR.rotateAngleX = this.legBR.rotateAngleX;

        double wingSpread = Math.toRadians(dragon.wingAngle());
        this.wing1R.rotateAngleY = (float) -wingSpread;
        this.wing2R.rotateAngleY = (float) -wingSpread;
        this.wingCR.rotateAngleY = (float) -wingSpread;

        this.wing1L.rotateAngleY = (float) wingSpread;
        this.wing2L.rotateAngleY = (float) wingSpread;
        this.wingCL.rotateAngleY = (float) wingSpread;

        this.mouth2.rotateAngleY = this.nose2.rotateAngleX = this.head.rotateAngleX - (jawAngle/2);
        this.mouth.rotateAngleY = this.nose.rotateAngleX = this.head.rotateAngleX + jawAngle;

        this.mouth2.rotateAngleY = this.nose2.rotateAngleY = this.head.rotateAngleY;
        this.mouth.rotateAngleY = this.nose.rotateAngleY = this.head.rotateAngleY;

        this.horn1.rotateAngleX = this.horn2.rotateAngleX = this.head.rotateAngleX + 0.6108652F;
        this.horn1.rotateAngleY = this.horn2.rotateAngleY = this.head.rotateAngleY;
    }
}
