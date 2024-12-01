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

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Binder;
import android.os.Process;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;

import com.android.internal.R;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @hide
 */
public class GamesPropsUtils {

    private static final String TAG = "GamesPropsUtils";
    private static final boolean DEBUG = false;

    private static final String PACKAGE_AETHER_GAZER = "com.YoStar.AetherGazer";
    private static final String PACKAGE_ARENA_BREAKOUT = "com.proximabeta.mf.uamo";
    private static final String PACKAGE_ARENA_OF_VALOR = "com.ngame.allstar.eu";
    private static final String PACKAGE_ASPHALT_LEGENDS = "com.gameloft.android.ANMP.GloftA9HM";

    private static final String PACKAGE_BLACK_DESERT = "com.pearlabyss.blackdesertm.gl";
    private static final String PACKAGE_BLACK_DESERT_KR = "com.pearlabyss.blackdesertm";

    private static final String PACKAGE_COD_MOBILE = "com.activision.callofduty.shooter";
    private static final String PACKAGE_COD_MOBILE_AS = "com.garena.game.codm";
    private static final String PACKAGE_COD_MOBILE_CN = "com.tencent.tmgp.cod";
    private static final String PACKAGE_COD_MOBILE_KR = "com.tencent.tmgp.kr.codm";
    private static final String PACKAGE_COD_MOBILE_VN = "com.vng.codmvn";
    private static final String PACKAGE_COD_MOBILE_WARZONE = "com.activision.callofudty.warzone";

    private static final String PACKAGE_CROSSFIRE = "com.tencent.tmgp.cft";
    private static final String PACKAGE_CYBER_HUNTER = "com.netease.lztgglobal";
    private static final String PACKAGE_DEAD_BY_DAYLIGHT = "com.netease.dbdena";
    private static final String PACKAGE_EFOOTBALL = "jp.konami.pesam";

    private static final String PACKAGE_EPICGAMES_LAUNCHER = "com.epicgames.portal";
    private static final String PACKAGE_FARLIGHT84 = "com.miraclegames.farlight84";
    private static final String PACKAGE_FIFA_MOBILE = "com.ea.gp.fifamobile";
    private static final String PACKAGE_FORTNITE = "com.epicgames.fortnite";

    private static final String PACKAGE_FREE_FIRE = "com.dts.freefire";
    private static final String PACKAGE_FREE_FIRE_MAX = "com.dts.freefiremax";
    private static final String PACKAGE_HIGH_ENERGY_HEROES = "com.tencent.tmgp.gnyx";

    private static final String PACKAGE_HONOR_OF_KINGS = "com.levelinfinite.sgameGlobal";
    private static final String PACKAGE_HONOR_OF_KINGS_CN = "com.tencent.tmgp.sgame";

    private static final String PACKAGE_LEAGUE_OF_LEGENDS = "com.riotgames.league.wildrift";
    private static final String PACKAGE_LEAGUE_OF_LEGENDS_CN = "com.tencent.lolm";
    private static final String PACKAGE_LEAGUE_OF_LEGENDS_TW = "com.riotgames.league.wildrifttw";
    private static final String PACKAGE_LEAGUE_OF_LEGENDS_VN = "com.riotgames.league.wildriftvn";

    private static final String PACKAGE_LIEN_QUAN_MOBILE = "com.garena.game.kgvn";

    private static final String PACKAGE_MOBILE_LEGENDS = "com.mobile.legends";
    private static final String PACKAGE_MOBILE_LEGENDS_VN = "com.vng.mlbbvn";

    private static final String PACKAGE_NARUTO_STORM = "com.tencent.KiHan";
    private static final String PACKAGE_NEED_FOR_SPEED = "com.ea.game.nfs14_row";

    private static final String PACKAGE_PUBG_MOBILE = "com.tencent.ig";
    private static final String PACKAGE_PUBG_MOBILE_CN = "com.tencent.tmgp.pubgmhd";
    private static final String PACKAGE_PUBG_MOBILE_IN = "com.pubg.imobile";
    private static final String PACKAGE_PUBG_MOBILE_KR = "com.pubg.krmobile";
    private static final String PACKAGE_PUBG_MOBILE_LITE = "com.tencent.iglite";
    private static final String PACKAGE_PUBG_MOBILE_TW = "com.rekoo.pubgm";
    private static final String PACKAGE_PUBG_MOBILE_VN = "com.vng.pubgmobile";

    private static final String PACKAGE_REVELATION_MOBILE = "vng.games.revelation.mobile";
    private static final String PACKAGE_SHADOWGUN_LEGENDS = "com.madfingergames.legends";

    private static final String PACKAGE_TEAMFIGHT_TACTICS = "com.riotgames.league.teamfighttactics";
    private static final String PACKAGE_TEAMFIGHT_TACTICS_TW = "com.riotgames.league.teamfighttacticstw";
    private static final String PACKAGE_TEAMFIGHT_TACTICS_VN = "com.riotgames.league.teamfighttacticsvn";

    private static final String PACKAGE_TOWER_OF_FANTASY = "com.levelinfinite.hotta.gp";
    private static final String PACKAGE_UNDAWN_CN = "com.garena.game.lmjx";
    private static final String PACKAGE_WORLD_OF_TANKS = "net.wargaming.wot.blitz";

    private static final Map<String, String> sBlackSharkFiveProProps = Map.of(
            "PRODUCT", "KTUS-A0",
            "DEVICE", "KTUS-A0",
            "MANUFACTURER", "blackshark",
            "BRAND", "blackshark",
            "MODEL", "Shark KTUS-A0"
    );

    private static final Map<String, String> sOnePlusTwelveProps = Map.of(
            "PRODUCT", "CPH2581",
            "DEVICE", "CPH2581",
            "MANUFACTURER", "OnePlus",
            "BRAND", "OnePlus",
            "MODEL", "CPH2581"
    );

    private static final Map<String, String> sPocoSixProProps = Map.of(
            "PRODUCT", "vermeer",
            "DEVICE", "23113RKC6G",
            "MANUFACTURER", "Xiaomi",
            "BRAND", "Xiaomi",
            "MODEL", "23049PCD8G"
    );

    private static final Map<String, String> sRealmeSixProps = Map.of(
            "PRODUCT", "RMX3800",
            "DEVICE", "RE5C4FL1",
            "MANUFACTURER", "realme",
            "BRAND", "realme",
            "MODEL", "RMX3800"
    );

    private static final Map<String, String> sRogPhoneEightProps = Map.of(
            "PRODUCT", "WW_AI240",
            "DEVICE", "AI2401",
            "MANUFACTURER", "asus",
            "BRAND", "asus",
            "MODEL", "ASUS_AI240"
    );

    private static final Map<String, String> sSamsungFourUltraProps = Map.of(
            "PRODUCT", "e3qxeea",
            "DEVICE", "SM-S928B",
            "MANUFACTURER", "samsung",
            "BRAND", "samsung",
            "MODEL", "SM-S928B"
    );

    private static final Map<String, String> sVivoElevenProps = Map.of(
            "PRODUCT", "V2243A",
            "DEVICE", "V2243A",
            "MANUFACTURER", "vivo",
            "BRAND", "vivo",
            "MODEL", "V2243A"
    );

    private static final Map<String, String> sXiaomiFourProProps = Map.of(
            "PRODUCT", "shennong",
            "DEVICE", "23116PN5BG",
            "MANUFACTURER", "Xiaomi",
            "BRAND", "Xiaomi",
            "MODEL", "23116PN5BG"
    );

    public static void setProps(Context context) {
        final String packageName = context.getPackageName();

        if (TextUtils.isEmpty(packageName)) {
            Log.e(TAG, "Null package name");
            return;
        }

        switch (packageName) {
            case PACKAGE_ARENA_BREAKOUT:
            case PACKAGE_COD_MOBILE_KR:
            case PACKAGE_COD_MOBILE_VN:
                dlog("Spoofing Black Shark 5 Pro for: " + packageName);
                setProps(sBlackSharkFiveProProps);
                return;
            case PACKAGE_AETHER_GAZER:
            case PACKAGE_EFOOTBALL:
            case PACKAGE_EPICGAMES_LAUNCHER:
            case PACKAGE_FORTNITE:
            case PACKAGE_LEAGUE_OF_LEGENDS:
            case PACKAGE_LEAGUE_OF_LEGENDS_CN:
            case PACKAGE_LEAGUE_OF_LEGENDS_TW:
            case PACKAGE_LEAGUE_OF_LEGENDS_VN:
                dlog("Spoofing OnePlus 12 for: " + packageName);
                setProps(sOnePlusTwelveProps);
                return;
            case PACKAGE_COD_MOBILE_AS:
            case PACKAGE_FREE_FIRE:
            case PACKAGE_FREE_FIRE_MAX:
            case PACKAGE_LIEN_QUAN_MOBILE:
            case PACKAGE_MOBILE_LEGENDS:
                dlog("Spoofing POCO F6 Pro for: " + packageName);
                setProps(sPocoSixProProps);
                return;
            case PACKAGE_ARENA_OF_VALOR:
            case PACKAGE_UNDAWN_CN:
                dlog("Spoofing Realme GT 6 for: " + packageName);
                setProps(sRealmeSixProps);
                return;
            case PACKAGE_ASPHALT_LEGENDS:
            case PACKAGE_BLACK_DESERT:
            case PACKAGE_BLACK_DESERT_KR:
            case PACKAGE_COD_MOBILE:
            case PACKAGE_COD_MOBILE_WARZONE:
            case PACKAGE_NEED_FOR_SPEED:
            case PACKAGE_PUBG_MOBILE:
            case PACKAGE_PUBG_MOBILE_CN:
            case PACKAGE_PUBG_MOBILE_IN:
            case PACKAGE_PUBG_MOBILE_KR:
            case PACKAGE_PUBG_MOBILE_TW:
            case PACKAGE_PUBG_MOBILE_VN:
            case PACKAGE_SHADOWGUN_LEGENDS:
            case PACKAGE_TEAMFIGHT_TACTICS:
            case PACKAGE_TEAMFIGHT_TACTICS_TW:
            case PACKAGE_TEAMFIGHT_TACTICS_VN:
            case PACKAGE_WORLD_OF_TANKS:
                dlog("Spoofing RogPhone 8 for: " + packageName);
                setProps(sRogPhoneEightProps);
                return;
            case PACKAGE_CYBER_HUNTER:
            case PACKAGE_DEAD_BY_DAYLIGHT:
            case PACKAGE_FARLIGHT84:
            case PACKAGE_FIFA_MOBILE:
            case PACKAGE_REVELATION_MOBILE:
                dlog("Spoofing Samsung Galaxy S24 Ultra for: " + packageName);
                setProps(sSamsungFourUltraProps);
                return;
            case PACKAGE_COD_MOBILE_CN:
            case PACKAGE_CROSSFIRE:
            case PACKAGE_HIGH_ENERGY_HEROES:
            case PACKAGE_NARUTO_STORM:
            case PACKAGE_PUBG_MOBILE_LITE:
                dlog("Spoofing Vivo iQOO 11 for: " + packageName);
                setProps(sVivoElevenProps);
                return;
            case PACKAGE_HONOR_OF_KINGS:
            case PACKAGE_HONOR_OF_KINGS_CN:
            case PACKAGE_MOBILE_LEGENDS_VN:
            case PACKAGE_TOWER_OF_FANTASY:
                dlog("Spoofing Xiaomi 14 Pro for: " + packageName);
                setProps(sXiaomiFourProProps);
                return;
        }
    }

    private static void setProps(Map<String, String> props) {
        props.forEach(GamesPropsUtils::setPropValue);
    }

    private static void setPropValue(String key, String value) {
        try {
            dlog("Setting prop " + key + " to " + value.toString());
            Class clazz = Build.class;
            Field field = clazz.getDeclaredField(key);
            field.setAccessible(true);
            field.set(null, field.getType().equals(Integer.TYPE) ? Integer.parseInt(value) : value);
            field.setAccessible(false);
        } catch (Exception e) {
            Log.e(TAG, "Failed to set prop " + key, e);
        }
    }

    public static void dlog(String msg) {
        if (DEBUG) Log.d(TAG, msg);
    }
}
