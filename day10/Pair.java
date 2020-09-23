package day10;

public class Pair<K, V> {
    private final K first;
    private final V second;

    private Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <K, V> Pair<K, V> createNewInstance(K first, V second) {
        return new Pair<K, V>(first, second);
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        Pair<?, ?> p = (Pair<?, ?>) obj;
        return (this.getFirst() == p.getFirst() || (this.getFirst() != null && this.getFirst().equals(p.getFirst())))
                && (this.getSecond() == p.getSecond()
                        || (this.getSecond() != null && this.getSecond().equals(p.getSecond())));
    }

    // @Override
    // public boolean equals(Object o) {
    // if (!(o instanceof Pair)) {
    // return false;
    // }
    // Pair<?, ?> p = (Pair<?, ?>) o;
    // return Objects.equals(p.first, first) && Objects.equals(p.second, second);
    // }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash ^ (null == this.getFirst() ? 0 : this.getFirst().hashCode());
        hash = 31 * hash ^ (null == this.getSecond() ? 0 : this.getSecond().hashCode());
        return hash;
    }

    // @Override
    // public int hashCode() {
    // return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 :
    // second.hashCode());
    // }
}
