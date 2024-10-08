package cl.franciscosolis.rubybox.utils;

public record Pair<K,V>(K k, V v){

    public static <K,V> Pair<K,V> of(K k, V v){
        return new Pair<>(k, v);
    }
}
