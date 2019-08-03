package com.neuedu.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String, Image>map =new HashMap<>();
    static {
        map.put("bg01",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg1.png"));
        map.put("bg02",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg2.jpg"));

        map.put("my01",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\my_1.png"));

        map.put("mb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_1.png"));
        map.put("mb02",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_2.png"));
        map.put("ep01",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_1.png"));
        map.put("ep02",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_2.png"));

        map.put("epb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_1.png"));
        map.put("ebp02",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\ebp_2.png"));

        map.put("blood01",ImageUtil.getImage("com\\neuedu\\imgs\\skill\\blood_1.png"));


        map.put("boss1",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_01.png"));
        map.put("boss2",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_02.png"));
        map.put("boss3",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_03.png"));
        map.put("boss4",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_04.png"));
        map.put("boss5",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_05.png"));
        map.put("boss6",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_06.png"));
        map.put("boss7",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_07.png"));
        map.put("boss8",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_08.png"));
        map.put("boss9",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_09.png"));

    }
    public static Image get(String key){
        return map.get(key);
    }
}
