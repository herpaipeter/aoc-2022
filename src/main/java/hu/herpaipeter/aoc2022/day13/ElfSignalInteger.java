package hu.herpaipeter.aoc2022.day13;

import java.util.List;

public class ElfSignalInteger implements ElfSignal {
    private int element;

    public ElfSignalInteger(int element) {
        this.element = element;
    }

    public int getElement() {
        return element;
    }

    @Override
    public List<ElfSignal> getSignals() {
        return List.of(this);
    }

    @Override
    public int compare(ElfSignal other) {
        if (other instanceof ElfSignalInteger) {
            return Integer.compare(element, ((ElfSignalInteger) other).getElement());
        } else if (other instanceof ElfSignalList) {
            return new ElfSignalList(getSignals()).compare(other);
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElfSignalInteger that = (ElfSignalInteger) o;

        return element == that.element;
    }

    @Override
    public int hashCode() {
        return element;
    }

    @Override
    public String toString() {
        return Integer.toString(element);
    }
}
