package hu.herpaipeter.aoc2022.day13;

import java.util.List;

public interface ElfSignal {

    List<ElfSignal> getSignals();
    int compare(ElfSignal other);

}
