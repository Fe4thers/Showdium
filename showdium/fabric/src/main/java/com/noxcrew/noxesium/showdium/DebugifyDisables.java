package com.noxcrew.noxesium.showdium;

import dev.isxander.debugify.api.DebugifyApi;
import java.util.Map;
import java.util.Set;

public class DebugifyDisables implements DebugifyApi {

    @Override
    public String[] getDisabledFixes() {
        return new String[] {"MC-136249"};
    }

    @Override
    public Map<String, Set<String>> getProvidedDisabledFixes() {
        return DebugifyApi.super.getProvidedDisabledFixes();
    }
}
