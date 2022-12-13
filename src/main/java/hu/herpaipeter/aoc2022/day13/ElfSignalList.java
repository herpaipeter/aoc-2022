package hu.herpaipeter.aoc2022.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ElfSignalList implements ElfSignal {
    private List<ElfSignal> signals;

    public ElfSignalList(List<ElfSignal> signals) {
        this.signals = new ArrayList<>(signals);
    }

    public List<ElfSignal> getSignals() {
        return signals;
    }

    @Override
    public int compare(ElfSignal other) {
        if (!this.signals.isEmpty() && !other.getSignals().isEmpty()) {
            for (int i = 0; i < Math.min(this.signals.size(), other.getSignals().size()); i++) {
                int compared = this.signals.get(i).compare(other.getSignals().get(i));
                if (0 != compared)
                    return compared;
            }
        }
        return Integer.compare(this.signals.size(),other.getSignals().size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElfSignalList that = (ElfSignalList) o;

        return signals.equals(that.signals);
    }

    @Override
    public int hashCode() {
        return signals.hashCode();
    }

    @Override
    public String toString() {
        return "[" +  signals.stream().map(Object::toString).collect(Collectors.joining(",")) + "]";
    }
}
