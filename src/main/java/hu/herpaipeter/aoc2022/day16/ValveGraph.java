package hu.herpaipeter.aoc2022.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValveGraph {

    private Map<String, Integer> valveStateFlow = new HashMap<>();
    private List<Tunnel> tunnels = new ArrayList<>();

    public ValveGraph() {
    }

    public void addTunnels(List<Tunnel> tunnels) {
        this.tunnels = tunnels;
    }

    public int findMaxFlowPath(Valve start, int steps) {
        return findMaxFlowPath(start,steps,"");
    }

    public int findMaxFlowPath(Valve start, int steps, String openedValves) {
        int maxFlowOpened = 0;
        int maxFlowClosed = 0;
        int actualSteps = steps;
        if (start != null) {
            List<Tunnel> nextTunnels = this.tunnels.stream().filter(t -> t.from().equals(start)).toList();
            if (0 < start.flowRate() && !openedValves.contains(start.name())) {
                actualSteps--;
                openedValves += " " + start.name();
                maxFlowOpened += start.flowRate() * actualSteps;
                int finalActualSteps = actualSteps - 1;
                String finalOpenedValves = openedValves;
                if (valveStateFlow.containsKey(getStateKey(start, finalActualSteps, openedValves)))
                    maxFlowOpened += valveStateFlow.get(getStateKey(start, finalActualSteps, openedValves));
                else {
                    Integer maxFlow = nextTunnels.stream().map(t -> findMaxFlowPath(t.to(), finalActualSteps, finalOpenedValves)).max(Integer::compareTo).orElse(0);
                    valveStateFlow.put(getStateKey(start, finalActualSteps, openedValves), maxFlow);
                    maxFlowOpened += maxFlow;
                }

            }
            String finalOpenedValves = openedValves;
            int finalActualSteps = actualSteps - 1;
            if (valveStateFlow.containsKey(getStateKey(start, finalActualSteps, openedValves)))
                maxFlowClosed += valveStateFlow.get(getStateKey(start, finalActualSteps, openedValves));
            else {
                Integer maxFlow = nextTunnels.stream().map(t -> findMaxFlowPath(t.to(), finalActualSteps, finalOpenedValves)).max(Integer::compareTo).orElse(0);
                valveStateFlow.put(getStateKey(start, finalActualSteps, openedValves), maxFlow);
                maxFlowClosed += maxFlow;
            }
        }
        return Math.max(maxFlowClosed, maxFlowOpened);
    }

    private String getStateKey(Valve start, int finalActualSteps, String openedValves) {
        String opened = !openedValves.contains(start.name()) ? "F" : "T";
        return start.name() + opened + " " + finalActualSteps/* + " " + openedValves*/;
    }

}
