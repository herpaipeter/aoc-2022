package hu.herpaipeter.aoc2022.common;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtils {

    public static String findMatch(String text, String s) {
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(text);
        return matcher.results().map(MatchResult::group).findFirst().orElse("");
    }

    public static List<String> findAllMatch(String text, String s) {
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(text);
        return matcher.results().map(MatchResult::group).toList();
    }

}
