package com.Taitai.taitaimod.entity.model;


import com.Taitai.taitaimod.entity.TaitaiAkumaEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.entity.model.IHeadToggle;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class TaitaiAkumaModel <T extends TaitaiAkumaEntity> extends SegmentedModel<T> implements IHasHead, IHeadToggle {
	private final ModelRenderer bipedHead;
	private final ModelRenderer bipedBody;
	private final ModelRenderer bipedRightArm;
	private final ModelRenderer bipedLeftArm;
	private final ModelRenderer bipedRightLeg;
	private final ModelRenderer bipedLeftLeg;
	private final ModelRenderer bone;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer bb_main;
	private final ModelRenderer cube_r9;

	public TaitaiAkumaModel(){
		this(64,64);
	}

	public TaitaiAkumaModel(int w, int h) {
		texWidth = 64;
		texHeight = 64;

		bipedHead = new ModelRenderer(this).setTexSize(w, h);
		bipedHead.setPos(0.0F, 0.0F, 0.0F);
		bipedHead.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		bipedBody = new ModelRenderer(this).setTexSize(w, h);
		bipedBody.setPos(0.0F, 0.0F, 0.0F);
		bipedBody.texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		bipedRightArm = new ModelRenderer(this).setTexSize(w, h);
		bipedRightArm.setPos(-5.0F, 2.0F, 0.0F);
		bipedRightArm.texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		bipedLeftArm = new ModelRenderer(this).setTexSize(w, h);
		bipedLeftArm.setPos(5.0F, 2.0F, 0.0F);
		bipedLeftArm.texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		bipedRightLeg = new ModelRenderer(this).setTexSize(w, h);
		bipedRightLeg.setPos(-1.9F, 12.0F, 0.0F);
		bipedRightLeg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		bipedLeftLeg = new ModelRenderer(this).setTexSize(w, h);
		bipedLeftLeg.setPos(1.9F, 12.0F, 0.0F);
		bipedLeftLeg.texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		bone = new ModelRenderer(this).setTexSize(w, h);
		bone.setPos(0.0F, 24.0F, 0.0F);
		

		cube_r1 = new ModelRenderer(this).setTexSize(w, h);
		cube_r1.setPos(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r1);
		cube_r1.texOffs(35, 2).addBox(8.0F, -15.0F, -0.25F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r1.texOffs(35, 0).addBox(10.0F, -20.0F, -0.25F, 4.0F, 5.0F, 2.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this).setTexSize(w, h);
		cube_r2.setPos(11.5F, -21.0F, 3.0F);
		bone.addChild(cube_r2);
		cube_r2.texOffs(35, 0).addBox(-2.5F, -1.0F, 2.5F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this).setTexSize(w, h);
		cube_r3.setPos(7.0F, -23.0F, 3.0F);
		bone.addChild(cube_r3);
		cube_r3.texOffs(35, 2).addBox(-3.0F, -1.0F, 0.5F, 6.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this).setTexSize(w, h);
		cube_r4.setPos(4.0F, -21.0F, 3.0F);
		bone.addChild(cube_r4);
		cube_r4.texOffs(35, 0).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this).setTexSize(w, h);
		bone2.setPos(-7.1974F, 4.7F, 5.4848F);


		cube_r5 = new ModelRenderer(this).setTexSize(w, h);
		cube_r5.setPos(-7.8026F, 19.3F, -4.9848F);
		bone2.addChild(cube_r5);
		cube_r5.texOffs(35, 2).addBox(8.0F, -15.0F, -0.25F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r5.texOffs(35, 0).addBox(10.0F, -20.0F, -0.25F, 4.0F, 5.0F, 2.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this).setTexSize(w, h);
		cube_r6.setPos(3.6974F, -1.7F, -1.9848F);
		bone2.addChild(cube_r6);
		cube_r6.texOffs(35, 0).addBox(-2.5F, -1.0F, 2.5F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this).setTexSize(w, h);
		cube_r7.setPos(-0.8026F, -3.7F, -1.9848F);
		bone2.addChild(cube_r7);
		cube_r7.texOffs(35, 2).addBox(-3.0F, -1.0F, 0.5F, 6.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this).setTexSize(w, h);
		cube_r8.setPos(-3.8026F, -1.7F, -1.9848F);
		bone2.addChild(cube_r8);
		cube_r8.texOffs(35, 0).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);

		bb_main = new ModelRenderer(this).setTexSize(w, h);
		bb_main.setPos(0.0F, 24.0F, 0.0F);
		

		cube_r9 = new ModelRenderer(this).setTexSize(w, h);
		cube_r9.setPos(2.0F, -33.5F, -4.0F);
		bb_main.addChild(cube_r9);
		cube_r9.texOffs(36, 0).addBox(-5.0F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cube_r9.texOffs(36, 0).addBox(0.0F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
		this.cube_r1.yRot = -0.48F;
		this.cube_r2.yRot = -0.48F;
		this.cube_r3.yRot = -0.48F;
		this.cube_r4.yRot = -0.48F;
		this.cube_r5.yRot = -0.48F;
		this.cube_r6.yRot = -0.48F;
		this.cube_r7.yRot = -0.48F;
		this.cube_r8.yRot = -0.48F;
		this.cube_r9.xRot = -0.3491F;

		this.bone2.xRot = 3.0598F;
		this.bone2.yRot = -1.0454F;
		this.bone2.zRot = -3.0558F;


		this.bipedLeftLeg.xRot = -1.5F * MathHelper.triangleWave(p_225597_2_, 13.0F) * p_225597_3_;
		this.bipedRightArm.xRot = 1.5F * MathHelper.triangleWave(p_225597_2_, 13.0F) * p_225597_3_;
		this.bipedLeftLeg.yRot = 0.0F;
		this.bipedRightLeg.yRot = 0.0F;
	}

	@Override
	public void prepareMobModel(T p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
		int i = p_212843_1_.getAttackTimer();
		if (i > 0) {
			this.bipedRightArm.xRot = -2.0F + 1.5F * MathHelper.triangleWave((float)i - p_212843_4_, 10.0F);
			this.bipedLeftArm.xRot = -2.0F + 1.5F * MathHelper.triangleWave((float)i - p_212843_4_, 10.0F);
		} else {this.bipedRightArm.xRot = (-0.2F + 1.5F * MathHelper.triangleWave(p_212843_2_, 13.0F)) * p_212843_3_;
			this.bipedLeftArm.xRot = (-0.2F - 1.5F * MathHelper.triangleWave(p_212843_2_, 13.0F)) * p_212843_3_;
		}
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableList.of(this.bipedHead, this.bipedBody, this.bipedRightArm, this.bipedLeftArm, this.bipedRightLeg, this.bipedLeftLeg);
	}

	@Override
	public ModelRenderer getHead() {
		return this.bipedHead;
	}

	@Override
	public void hatVisible(boolean p_217146_1_) {
		this.bipedHead.visible = p_217146_1_;
	}
}