/*
 * Copyright (C) 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.util;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GamesPropsUtils {

    private static final String TAG = GamesPropsUtils.class.getSimpleName();
    private static final boolean DEBUG = false;

    private static final Map<String, Map<String, Object>> propsToChange = new HashMap<>();
    private static final Map<String, String[]> packagesToChange = new HashMap<>();

    static {
        propsToChange.put("F4", createF4Props());
        packagesToChange.put("F4", new String[]{
                "com.levelinfinite.hotta.gp",
                "com.mobile.legends",
                "com.vng.mlbbvn"
        });

        propsToChange.put("iQ11", createiQ11Props());
        packagesToChange.put("iQ11", new String[]{
                "com.tencent.KiHan",
                "com.tencent.tmgp.cf",
                "com.tencent.tmgp.cod",
                "com.tencent.tmgp.gnyx"
        });

        propsToChange.put("MI13P", createMI13PProps());
        packagesToChange.put("MI13P", new String[]{
                "com.levelinfinite.sgameGlobal",
                "com.tencent.tmgp.sgame",
                "com.YoStar.AetherGazer"
        });

        propsToChange.put("OP9P", createOP9PProps());
        packagesToChange.put("OP9P", new String[]{
                "com.epicgames.fortnite",
                "com.epicgames.portal",
                "com.riotgames.league.wildrift",
                "com.riotgames.league.wildrifttw",
                "com.riotgames.league.wildriftvn",
                "com.tencent.lolm"
        });

        propsToChange.put("ROG1", createROG1Props());
        packagesToChange.put("ROG1", new String[]{
                "com.dts.freefiremax",
                "com.dts.freefireth",
                "com.madfingergames.legends"
        });

        propsToChange.put("ROG8", createROG8Props());
        packagesToChange.put("ROG8", new String[]{
                "com.pearlabyss.blackdesertm",
                "com.pearlabyss.blackdesertm.gl",
                "com.pubg.imobile",
                "com.pubg.krmobile",
                "com.rekoo.pubgm",
                "com.tencent.ig",
                "com.tencent.tmgp.pubgmhd",
                "com.vng.pubgmobile"
        });

        propsToChange.put("XP5", createXP5Props());
        packagesToChange.put("XP5", new String[]{
                "com.activision.callofduty.shooter",
                "com.garena.game.codm",
                "com.proximabeta.mf.uamo",
                "com.tencent.tmgp.kr.codm",
                "com.vng.codmvn"
        });
    }

    private static Map<String, Object> createF4Props() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "Xiaomi");
        props.put("MANUFACTURER", "Xiaomi");
        props.put("MODEL", "22021211RG");
        return props;
    }

    private static Map<String, Object> createiQ11Props() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "vivo");
        props.put("MANUFACTURER", "vivo");
        props.put("MODEL", "V2243A");
        return props;
    }

    private static Map<String, Object> createMI13PProps() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "Xiaomi");
        props.put("MANUFACTURER", "Xiaomi");
        props.put("MODEL", "2210132C");
        return props;
    }

    private static Map<String, Object> createOP9PProps() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "OnePlus");
        props.put("DEVICE", "OnePlus9Pro");
        props.put("MANUFACTURER", "OnePlus");
        props.put("MODEL", "LE2101");
        return props;
    }

    private static Map<String, Object> createROG1Props() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "asus");
        props.put("MANUFACTURER", "asus");
        props.put("MODEL", "ASUS_Z01QD");
        return props;
    }

    private static Map<String, Object> createROG8Props() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "asus");
        props.put("MANUFACTURER", "asus");
        props.put("MODEL", "ASUS_AI2401_A");
        return props;
    }

    private static Map<String, Object> createXP5Props() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "Sony");
        props.put("MANUFACTURER", "Sony");
        props.put("MODEL", "SO-52A");
        return props;
    }

    public static void setProps(Context context) {
        final String packageName = context.getPackageName();

        if (packageName == null || packageName.isEmpty()) {
            return;
        }

        for (String device : packagesToChange.keySet()) {
            String[] packages = packagesToChange.get(device);
            if (Arrays.asList(packages).contains(packageName)) {
                if (DEBUG) {
                    Log.d(TAG, "Defining props for: " + packageName);
                }
                Map<String, Object> props = propsToChange.get(device);
                for (Map.Entry<String, Object> prop : props.entrySet()) {
                    String key = prop.getKey();
                    Object value = prop.getValue();
                    setPropValue(key, value);
                }
                break;
            }
        }
    }

    private static void setPropValue(String key, Object value) {
        try {
            if (DEBUG) {
                Log.d(TAG, "Defining prop " + key + " to " + value.toString());
            }
            Field field = Build.class.getDeclaredField(key);
            field.setAccessible(true);
            field.set(null, value);
            field.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Log.e(TAG, "Failed to set prop " + key, e);
        }
    }
}
