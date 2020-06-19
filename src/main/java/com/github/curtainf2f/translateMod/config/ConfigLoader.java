package com.github.curtainf2f.translateMod.config;

import com.github.curtainf2f.translateMod.TranslateMod;

import net.minecraftforge.common.config.Config;

@Config(modid=TranslateMod.MODID)
@Config.LangKey("config.translatemod.general")
public class ConfigLoader {
    @Config.Comment("百度翻译api 开发者 appid https://api.fanyi.baidu.com/") // 有了这个就会多一个注释。
    @Config.LangKey("config.translatemod.general.APPID") // 供配置 GUI 界面使用的本地化键，参阅“可视化配置文件编辑界面”一节
    @Config.Name("APPID") // 默认配置选项名是字段名，如果需要别的名字就用这个。
    public static String appid = "";

    @Config.Comment("百度翻译api 开发者 密钥 https://api.fanyi.baidu.com/")
    @Config.LangKey("config.translatemod.general.securityKey")
    @Config.Name("securityKey")
    public static String securityKey = "";
}
