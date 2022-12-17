package hu.herpaipeter.aoc2022.day16;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ValveGraphParserTest {

    @Test
    void empty_strings_should_return_empty_list() {
        ValveGraphParser parser = new ValveGraphParser();
        assertEquals(List.of(), parser.getTunnels(List.of()));
    }

    @Test
    void one_element_should_return_a_tunnel_back() {
        ValveGraphParser parser = new ValveGraphParser();
        assertEquals(List.of(new Tunnel(new Valve("AA", 0), new Valve("AA", 0))),
                parser.getTunnels(List.of("Valve AA has flow rate=0; tunnels lead to valves AA")));
    }

    @Test
    void two_element_should_return_a_tunnel_to_each_other() {
        ValveGraphParser parser = new ValveGraphParser();
        assertEquals(List.of(new Tunnel(new Valve("AA", 0), new Valve("BB", 12)),
                             new Tunnel(new Valve("BB", 12), new Valve("AA", 0))),
                parser.getTunnels(List.of("Valve AA has flow rate=0; tunnels lead to valves BB",
                                          "Valve BB has flow rate=12; tunnels lead to valves AA")));
    }
}
