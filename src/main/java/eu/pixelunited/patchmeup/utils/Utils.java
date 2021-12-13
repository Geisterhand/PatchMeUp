package eu.pixelunited.patchmeup.utils;

import net.minecraft.util.text.ITextComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static ITextComponent toText(String text) {
        return TextSerializer.parse(text);
    }

    private static final String regex = "&(?=[0-9a-ff-or])";
    private static final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

    public static String regex(String text) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            text = text.replaceAll(regex, "§");
        }
        return text;
    }
}
