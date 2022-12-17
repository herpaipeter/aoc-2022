package hu.herpaipeter.aoc2022.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValveGraphParser {

    public List<Tunnel> getTunnels(List<String> inputText) {
        if (inputText.isEmpty())
            return List.of();
        Map<String, Valve> valves = new HashMap<>();
        Map<Valve, List<String>> tunnelToValves = new HashMap<>();
        for (String s : inputText) {
            List<String> valvesText = findAllMatch(s, "\\b[A-Z]{2}\\b");
            String flowRateText = findMatch(s, "rate=\\d+");
            Valve valve = new Valve(valvesText.get(0), Integer.parseInt(flowRateText.substring("rate=".length())));
            valves.put(valvesText.get(0), valve);
            tunnelToValves.put(valve, valvesText.subList(1, valvesText.size()));
        }
        List<Tunnel> tunnels = new ArrayList<>();
        for (Valve valve : valves.values()) {
            List<String> toTunnels = tunnelToValves.get(valve);
            for (String toTunnel : toTunnels) {
                tunnels.add(new Tunnel(valve, valves.get(toTunnel)));
            }
        }
        return tunnels;
    }

    private String findMatch(String text, String s) {
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(text);
        return matcher.results().map(MatchResult::group).findFirst().orElse("");
    }

    private List<String> findAllMatch(String text, String s) {
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(text);
        return matcher.results().map(MatchResult::group).toList();
    }
}
