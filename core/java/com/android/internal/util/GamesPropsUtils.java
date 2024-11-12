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
        propsToChange.put("BS5P", createBS5PProps());
        packagesToChange.put("BS5P", new String[]{
                "com.proximabeta.mf.uamo",
                "com.tencent.tmgp.kr.codm",
                "com.vng.codmvn"
        });

        propsToChange.put("F6P", createF6PProps());
        packagesToChange.put("F6P", new String[]{
                "com.dts.freefire",
                "com.dts.freefiremax",
                "com.garena.game.codm",
                "com.garena.game.kgvn",
                "com.mobile.legends"
        });

        propsToChange.put("GT6", createGT6Props());
        packagesToChange.put("GT6", new String[]{
                "com.garena.game.lmjx",
                "com.ngame.allstar.eu"
        });

        propsToChange.put("iQ11", createiQ11Props());
        packagesToChange.put("iQ11", new String[]{
                "com.tencent.KiHan",
                "com.tencent.iglite",
                "com.tencent.tmgp.cf",
                "com.tencent.tmgp.cod",
                "com.tencent.tmgp.gnyx"
        });

        propsToChange.put("MI14P", createMI14PProps());
        packagesToChange.put("MI14P", new String[]{
                "com.ea.gp.apexlegendsmobilefps",
                "com.levelinfinite.hotta.gp",
                "com.levelinfinite.sgameGlobal",
                "com.mobilelegends.mi",
                "com.supercell.clashofclans",
                "com.tencent.tmgp.sgame",
                "com.vng.mlbbvn"
        });

        propsToChange.put("OP12", createOP12Props());
        packagesToChange.put("OP12", new String[]{
                "com.YoStar.AetherGazer",
                "com.epicgames.fortnite",
                "com.epicgames.portal",
                "com.mojang.minecraftpe",
                "com.riotgames.league.wildrift",
                "com.riotgames.league.wildrifttw",
                "com.riotgames.league.wildriftvn",
                "com.tencent.lolm",
                "jp.konami.pesam"
        });

        propsToChange.put("ROG8", createROG8Props());
        packagesToChange.put("ROG8", new String[]{
                "com.activision.callofduty.shooter",
                "com.activision.callofudty.warzone",
                "com.ea.game.nfs14_row",
                "com.gameloft.android.ANMP.GloftA9HM",
                "com.madfingergames.legends",
                "com.pearlabyss.blackdesertm",
                "com.pearlabyss.blackdesertm.gl",
                "com.pubg.imobile",
                "com.pubg.krmobile",
                "com.rekoo.pubgm",
                "com.riotgames.league.teamfighttactics",
                "com.riotgames.league.teamfighttacticstw",
                "com.riotgames.league.teamfighttacticsvn",
                "com.tencent.ig",
                "com.tencent.tmgp.pubgmhd",
                "com.vng.pubgmobile",
                "net.wargaming.wot.blitz"
        });

        propsToChange.put("S24U", createS24UProps());
        packagesToChange.put("S24U", new String[]{
                "com.ea.gp.fifamobile",
                "com.miraclegames.farlight84",
                "com.netease.dbdena",
                "com.netease.lztgglobal"
                "vng.games.revelation.mobile"
        });
    }

    private static Map<String, Object> createBS5PProps() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "blackshark");
        props.put("DEVICE", "KTUS-A0");
        props.put("MANUFACTURER", "blackshark");
        props.put("MODEL", "Shark KTUS-A0");
        props.put("PRODUCT", "KTUS-A0");
        return props;
    }

    private static Map<String, Object> createF6PProps() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "Xiaomi");
        props.put("DEVICE", "23113RKC6G");
        props.put("MANUFACTURER", "Xiaomi");
        props.put("MODEL", "23049PCD8G");
        props.put("PRODUCT", "vermeer");
        return props;
    }

    private static Map<String, Object> createGT6Props() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "realme");
        props.put("DEVICE", "RE5C4FL1");
        props.put("MANUFACTURER", "realme");
        props.put("MODEL", "RMX3800");
        props.put("PRODUCT", "RMX3800");
        return props;
    }

    private static Map<String, Object> createiQ11Props() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "vivo");
        props.put("DEVICE", "V2243A");
        props.put("MANUFACTURER", "vivo");
        props.put("MODEL", "V2243A");
        props.put("PRODUCT", "V2243A");
        return props;
    }

    private static Map<String, Object> createMI14PProps() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "Xiaomi");
        props.put("DEVICE", "23116PN5BG");
        props.put("MANUFACTURER", "Xiaomi");
        props.put("MODEL", "23116PN5BG");
        props.put("PRODUCT", "shennong");
        return props;
    }

    private static Map<String, Object> createOP12Props() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "OnePlus");
        props.put("DEVICE", "CPH2581");
        props.put("MANUFACTURER", "OnePlus");
        props.put("MODEL", "CPH2581");
        props.put("PRODUCT", "CPH2581");
        return props;
    }

    private static Map<String, Object> createROG8Props() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "asus");
        props.put("DEVICE", "AI2401");
        props.put("MANUFACTURER", "asus");
        props.put("MODEL", "ASUS_AI2401");
        props.put("PRODUCT", "WW_AI2401");
        return props;
    }

    private static Map<String, Object> createS24UProps() {
        Map<String, Object> props = new HashMap<>();
        props.put("BRAND", "samsung");
        props.put("DEVICE", "SM-S928B");
        props.put("MANUFACTURER", "samsung");
        props.put("MODEL", "SM-S928B");
        props.put("PRODUCT", "e3qxeea");
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
