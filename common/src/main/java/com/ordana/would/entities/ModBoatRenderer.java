package com.ordana.would.entities;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import com.ordana.would.Would;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

public class ModBoatRenderer extends BoatRenderer {
    private final Map<ModBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public ModBoatRenderer(EntityRendererProvider.Context pContext, boolean pChestBoat) {
        super(pContext, pChestBoat);
        this.boatResources = Stream.of(ModBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap(type -> type,
                type -> Pair.of(new ResourceLocation(Would.MOD_ID, getTextureLocation(type, pChestBoat)), this.createBoatModel(pContext, type, pChestBoat))));
    }

    private static String getTextureLocation(ModBoatEntity.Type pType, boolean pChestBoat) {
        return pChestBoat ? "textures/entity/chest_boat/" + pType.getName() + ".png" : "textures/entity/boat/" + pType.getName() + ".png";
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context pContext, ModBoatEntity.Type pType, boolean pChestBoat) {
        ModelLayerLocation modellayerlocation = pChestBoat ? ModBoatRenderer.createChestBoatModelName(pType) : ModBoatRenderer.createBoatModelName(pType);
        ModelPart modelpart = pContext.bakeLayer(modellayerlocation);
        return pChestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }

    public static ModelLayerLocation createBoatModelName(ModBoatEntity.Type pType) {
        return createLocation("boat/" + pType.getName(), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(ModBoatEntity.Type pType) {
        return createLocation("chest_boat/" + pType.getName(), "main");
    }

    private static ModelLayerLocation createLocation(String pPath, String pModel) {
        return new ModelLayerLocation(new ResourceLocation(Would.MOD_ID, pPath), pModel);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Boat boat) {
        if (boat instanceof ModBoatEntity modBoat) {
            return this.boatResources.get(modBoat.getModVariant()).getFirst();
        } else if(boat instanceof ModChestBoatEntity modChestBoatEntity) {
            return this.boatResources.get(modChestBoatEntity.getModVariant()).getFirst();
        }
        return null;
    }

    public void render(Boat entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.375F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
        float f = (float)entity.getHurtTime() - partialTicks;
        float g = entity.getDamage() - partialTicks;
        if (g < 0.0F) {
            g = 0.0F;
        }

        if (f > 0.0F) {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * g / 10.0F * (float)entity.getHurtDir()));
        }

        float h = entity.getBubbleAngle(partialTicks);
        if (!Mth.equal(h, 0.0F)) {
            poseStack.mulPose((new Quaternionf()).setAngleAxis(entity.getBubbleAngle(partialTicks) * 0.017453292F, 1.0F, 0.0F, 1.0F));
        }


        Pair<ResourceLocation, ListModel<Boat>> pair = null;
        ResourceLocation resourceLocation = null;
        ListModel<Boat> listModel = null;

        if (entity instanceof ModBoatEntity boatEntity) {
            pair = this.boatResources.get(boatEntity.getModVariant());
            resourceLocation = pair.getFirst();
            listModel = pair.getSecond();
        }
        if (entity instanceof ModChestBoatEntity boatEntity) {
            pair = this.boatResources.get(boatEntity.getModVariant());
            resourceLocation = pair.getFirst();
            listModel = pair.getSecond();
        }

        if (pair == null) return;
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        listModel.setupAnim(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = buffer.getBuffer(listModel.renderType(resourceLocation));
        listModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!entity.isUnderWater()) {
            VertexConsumer vertexConsumer2 = buffer.getBuffer(RenderType.waterMask());
            if (listModel instanceof WaterPatchModel) {
                WaterPatchModel waterPatchModel = (WaterPatchModel) listModel;
                waterPatchModel.waterPatch().render(poseStack, vertexConsumer2, packedLight, OverlayTexture.NO_OVERLAY);
            }
        }

        poseStack.popPose();
        if (this.shouldShowName(entity)) {
            this.renderNameTag(entity, entity.getDisplayName(), poseStack, buffer, packedLight);
        }

    }
}