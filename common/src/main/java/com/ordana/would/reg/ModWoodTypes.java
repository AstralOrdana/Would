package com.ordana.would.reg;

import com.ordana.would.Would;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public interface ModWoodTypes {

    WoodType ASPEN = register("aspen", ModBlockSetTypes.ASPEN);
    WoodType AZALEA = register("azalea", ModBlockSetTypes.AZALEA);
    WoodType BAOBAB = register("baobab", ModBlockSetTypes.BAOBAB);
    WoodType CEDAR = register("cedar", ModBlockSetTypes.CEDAR);
    WoodType EBONY = register("ebony", ModBlockSetTypes.EBONY);
    WoodType FIR = register("fir", ModBlockSetTypes.FIR);
    WoodType MAHOGANY = register("mahogany", ModBlockSetTypes.MAHOGANY);
    WoodType MAPLE = register("maple", ModBlockSetTypes.MAPLE);
    WoodType PALM = register("palm", ModBlockSetTypes.PALM);
    WoodType PINE = register("pine", ModBlockSetTypes.PINE);
    WoodType WALNUT = register("walnut", ModBlockSetTypes.WALNUT);
    WoodType WILLOW = register("willow", ModBlockSetTypes.WILLOW);
    WoodType BLUE_SPRUCE = register("blue_spruce", ModBlockSetTypes.BLUE_SPRUCE);

    private static WoodType register(String name, BlockSetType blockSetType) {
        WoodType woodType = WoodType.register(new WoodType(Would.res(name).toString(), blockSetType));

        if (PlatHelper.getPhysicalSide().isClient()) {
            Sheets.SIGN_MATERIALS.put(woodType, new Material(Sheets.SIGN_SHEET, Would.res("entity/signs/" + woodType.name())));
            Sheets.HANGING_SIGN_MATERIALS.put(woodType, new Material(Sheets.SIGN_SHEET, Would.res("entity/signs/hanging/" + woodType.name())));
        }

        return woodType;
    }

    static void init() {}

}
