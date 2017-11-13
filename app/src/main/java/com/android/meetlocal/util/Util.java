package com.android.meetlocal.util;

import java.util.List;

/**
 * Created by hp pc on 13-11-2017.
 */

public final class Util {
    private Util() {

    }

    public static boolean listNotNull(List<?> list) {
        return list != null && !list.isEmpty();
    }
}
