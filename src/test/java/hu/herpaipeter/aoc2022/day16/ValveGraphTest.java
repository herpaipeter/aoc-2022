package hu.herpaipeter.aoc2022.day16;

import hu.herpaipeter.common.FileReader;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ValveGraphTest {

    @Test
    void empty_graph_should_have_0_flow() {
        ValveGraph valveGraph = new ValveGraph();
        assertEquals(0, valveGraph.findMaxFlowPath(null, 30));
    }

    @Test
    void one_valve_graph_should_have_0_flow() {
        ValveGraph valveGraph = new ValveGraph();
        Valve valveAA = new Valve("AA", 0);
        //valveGraph.findMaxFlowPath(valveAA);
        assertEquals(0, valveGraph.findMaxFlowPath(valveAA, 30));
    }

    @Test
    void two_valve_graph_with_tunnel() {
        ValveGraph valveGraph = new ValveGraph();
        Valve valveAA = new Valve("AA", 0);
        Valve valveBB = new Valve("BB", 0);
        valveGraph.addTunnels(List.of(new Tunnel(valveAA, valveBB)));
        assertEquals(0, valveGraph.findMaxFlowPath(valveAA, 30));
    }

    @Test
    void one_valve_graph_1_flow_rate_should_have_30_flow() {
        ValveGraph valveGraph = new ValveGraph();
        Valve valveAA = new Valve("AA", 1);
        assertEquals(29, valveGraph.findMaxFlowPath(valveAA, 30));
    }

    @Test
    void one_valve_graph_2_flow_rate_should_have_60_flow() {
        ValveGraph valveGraph = new ValveGraph();
        Valve valveAA = new Valve("AA", 2);
        assertEquals(58, valveGraph.findMaxFlowPath(valveAA, 30));
    }

    @Test
    void two_valves_non_zero_flow_graph_with_tunnel() {
        ValveGraph valveGraph = new ValveGraph();
        Valve valveAA = new Valve("AA", 0);
        Valve valveBB = new Valve("BB", 1);
        valveGraph.addTunnels(List.of(new Tunnel(valveAA, valveBB)));
        assertEquals(28, valveGraph.findMaxFlowPath(valveAA, 30));
    }

    @Test
    void three_valves_non_zero_flow_graph_with_tunnel_the_greater_one() {
        ValveGraph valveGraph = new ValveGraph();
        Valve valveAA = new Valve("AA", 0);
        Valve valveBB = new Valve("BB", 1);
        Valve valveCC = new Valve("CC", 2);
        valveGraph.addTunnels(List.of(new Tunnel(valveAA, valveBB), new Tunnel(valveAA, valveCC)));
        assertEquals(56, valveGraph.findMaxFlowPath(valveAA, 30));
    }

    @Test
    void four_valves_non_zero_flow_graph_with_tunnel_the_greater_one() {
        ValveGraph valveGraph = new ValveGraph();
        Valve valveAA = new Valve("AA", 0);
        Valve valveBB = new Valve("BB", 1);
        Valve valveCC = new Valve("CC", 2);
        Valve valveDD = new Valve("DD", 10);
        valveGraph.addTunnels(List.of(new Tunnel(valveAA, valveBB), new Tunnel(valveAA, valveCC), new Tunnel(valveBB, valveDD)));
        assertEquals(28 * 1 + 26 * 10, valveGraph.findMaxFlowPath(valveAA, 30));
    }

    @Test
    void four_valves_with_big_flow() {
        ValveGraph valveGraph = new ValveGraph();
        Valve valveAA = new Valve("AA", 0);
        Valve valveBB = new Valve("BB", 1);
        Valve valveCC = new Valve("CC", 2);
        Valve valveDD = new Valve("DD", 20);
        valveGraph.addTunnels(List.of(new Tunnel(valveAA, valveBB), new Tunnel(valveAA, valveCC), new Tunnel(valveBB, valveDD)));
        assertEquals(28 * 1 + 26 * 20, valveGraph.findMaxFlowPath(valveAA, 30));
    }

//    @Test
//    void example_part_1() {
//        List<String> inputTxt = FileReader.readAoCInputFileLines("day16", "example.txt");
//        List<Tunnel> tunnels = new ValveGraphParser().getTunnels(inputTxt);
//        ValveGraph valveGraph = new ValveGraph();
//        valveGraph.addTunnels(tunnels);
//        Valve valveAA = tunnels.stream().filter(tunnel -> tunnel.from().name().equals("AA")).map(Tunnel::from).findFirst().get();
//        assertEquals(1651, valveGraph.findMaxFlowPath(valveAA, 30));
//    }
}
