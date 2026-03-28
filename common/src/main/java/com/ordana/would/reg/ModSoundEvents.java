package com.ordana.would.reg;

import com.ordana.would.Would;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.sounds.SoundEvent;
import org.apache.commons.lang3.StringUtils;

public interface ModSoundEvents {

    String ENTITY = "entity";
    String WALNUT = "walnut";

    RegSupplier<SoundEvent> WALNUT_THROW = registerSound(ENTITY, WALNUT, "throw");
    RegSupplier<SoundEvent> WALNUT_CRACK = registerSound(ENTITY, WALNUT, "crack");
    RegSupplier<SoundEvent> SYRUP_DRINK = registerSound("item", "syrup_bottle", "drink");

    private static RegSupplier<SoundEvent> registerSound(String... definitions) {
        return RegHelper.registerSound(Would.res(StringUtils.join(definitions, '.')));
    }

    static void init() {}

}
