package com.ordana.would.reg;

import com.ordana.would.Would;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import org.apache.commons.lang3.StringUtils;

public interface ModSoundEvents {

    String ENTITY = "entity";
    String WALNUT = "walnut";

    SoundEvent WALNUT_THROW = registerSound(ENTITY, WALNUT, "throw");
    SoundEvent WALNUT_CRACK = registerSound(ENTITY, WALNUT, "crack");
    SoundEvent SYRUP_DRINK = registerSound("item", "syrup_bottle", "drink");

    private static SoundEvent registerSound(String... definitions) {
        Identifier res = Would.res(StringUtils.join(definitions, '.'));
        return Registry.register(BuiltInRegistries.SOUND_EVENT, res, SoundEvent.createVariableRangeEvent(res));
    }

    static void init() {}

}
