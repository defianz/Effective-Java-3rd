import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.function.DoubleBinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.minBy;
import static org.assertj.core.api.Assertions.*;

class MainTest {

    @BeforeEach
    public void before() {
        System.out.println("Before===========================");
    }

    @Test
    @DisplayName("시작")
    public void Hello() throws Exception {
        // given
        Object obj = new Object();
        Object obj2 = new Object();

        //when

        //then
        assertThat(obj.getClass()).isEqualTo(obj2.getClass());
    }

    @Test
    public void Clone() throws Exception {
        // given
        Tdd_test test = new Tdd_test();
        Object obj = new Object();
        //when
        Object obj2 = test.clone();
        //then
        System.out.println(obj2.getClass());

        int[] tt = new int[10];
        int[] tt2 = tt.clone();
        // 배열은 Clone 이 가능하다.
        // 하지만 커스텀 객체를 Clonable을 구현하여 clone 할 경우, 원본 인스턴스의 배열 필드를 똑같이 참조한다.

    }

    @Test
    public void Comparable() throws Exception {
        // given
        int compare = Integer.compare(10, 10);
        //when
        System.out.println("compare = " + compare);

        String hi = "HIHIHI";
      BigInteger a = new BigInteger("100");


        //then
    }

    class Tdd_test implements Cloneable {
        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    @Test
    public void extend() throws Exception {
        // given
        Object a = new Object();
        Object b = new Object();

        //when
        extend_sub test = new extend_sub(1L,2L);
        test.hi();
        //then
    }

    public class extend_sub extends extend_super{
        private Long id_sub;

        public extend_sub(Long id, Long id_sub) {
            super(id);
            this.id_sub = id_sub;
        }


        public void hi(){
            System.out.println("this = ");
        }
    }

    class extend_super {
        private Long id;

        public extend_super(Long id) {
            this.id = id;
        }

        public void hi(){
            System.out.println("hi");
        }
    }

    @Test
    public void abstract_test() throws Exception {
        // given
        abstract_sub a = new abstract_sub_sub();
        a.hi();
        a.hi2();


        //when

        new AbstractList<>(){

            @Override
            public int size() {
                return 0;
            }

            @Override
            public Object get(int index) {
                return null;
            }
        };
        //then
    }

    class abstract_sub_sub extends abstract_sub {

    }

    abstract class abstract_sub extends abstract_super implements interface_super, Map.Entry {
        @Override
        public Object getKey() {
            return null;
        }

        @Override
        public Object getValue() {
            return null;
        }

        @Override
        public Object setValue(Object value) {
            return null;
        }

        @Override
        public void hi(){
            System.out.println("hi2");
        }
    }

    abstract class abstract_super {

        public void hi(){
            System.out.println("hi");
        }
    }

    interface interface_super {
        public void hi();

        default void hi2(){
            System.out.println("hi default");
        }
    }


    @Test
    public void generic() throws Exception {
        // given
        List<Integer> li = new ArrayList<>();
        List<String> ls = new ArrayList<>();

        List<Object> tt = new ArrayList<>();
        List<Object> tt2 = new ArrayList<>();


        List<abstract_sub_sub> la = new ArrayList<>();
        List<?> ttt = new ArrayList<>();


        Set<abstract_sub> aa = new HashSet<>();
        Comparator<Object> objectComparator = Collections.reverseOrder();

        String[] strings = {"aa","bb","cc"};
        UnaryOperator<String> sameString = identityFunction();
        for (String string : strings) {
            System.out.println(sameString.apply(string));
        }

        tt.add("ggasdfa");

        //when
        String[] hi = ttt("a","b","b","c");
        //then
    }


    private static UnaryOperator<Object> IDENTIFY_FN = (t) -> t;

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction(){
        return (UnaryOperator<T>) IDENTIFY_FN;
    }


    static <T> T[] ttt(T... arg){
        for (T s: arg) {
            System.out.println("s = " + s);
        }
        return arg;
    }



    @Test
    public void favorite_test() throws Exception {
        // given
        Favorite f = new Favorite();
        f.putFavorite(String.class,"Java");
        f.putFavorite(Integer.class,0xcafebabe);
        f.putFavorite(Class.class,Favorite.class);
        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);
        //when
        System.out.printf("%s %x %s%n",favoriteString, favoriteInteger, favoriteClass.getName());
        //then
    }

    public class Favorite{
        private final Map<Class<?>, Object> favorites;

        Favorite(){
            favorites = new HashMap<>();
        }

        public <T> void putFavorite(Class<T> t, T o){
            favorites.put(t,o);
        }

        public <T> T getFavorite(Class<T> t){
            return t.cast(favorites.get(t));
        }
    }

    @Test
    public void ENUM() throws Exception {
        // given
        System.out.println("plant.flower.leaf = " + plant.flower.leaf);
        //when

        for (plant value : plant.values()) {
            System.out.println("value = " + value.leaf);
            System.out.println("value = " + value.toString());
            System.out.println("value = " + value);
        }
        //then
        System.out.println("Operation.PLUS.apply(10,2) = " + Operation.PLUS.apply(10, 2));
        EnumSet<Operation> plus = EnumSet.of(Operation.PLUS, Operation.MINUS);
    }

    public enum Operation {
        PLUS {
            public double apply(double a, double b){
                return a + b;
            }
        },
        MINUS {
            public double apply(double a, double b){
                return a - b;
            }
        },
        DEVIDE  {
            public double apply(double a, double b){
                return a / b;
            }
        },
        TIMES {
            public double apply(double a, double b){
                return a * b;
            }
        };

        public abstract double apply(double a, double b);
    }

    public enum plant {

        flower(5),
        tree(100),
        glass(10);

        private final int leaf;

        plant(int leaf){
            this.leaf = leaf;
        }
    }

    @Test
    public void lambda() throws Exception {
        // given
        List<String> words = new ArrayList<>();
        words.add("hasdfas");
        words.add("zv zlxv");
        words.sort(comparingInt(String::length));

        List<String> test = List.of("zxcvz","sdkafnlsdf");
        for (String s : test) {
            System.out.println("s = " + s);
        }
        //when
        for (String word : words) {
            System.out.println("word = " + word);
        }
        //then

        test.sort(comparingInt((s) -> s.length()));


//        (s) -> s.length == String::length; ??

    }


    public enum Opertaion {
        PLUS("+",(x,y) -> x + y);

        private final String symbol;
        private final DoubleBinaryOperator op;

        Opertaion(String symbol, DoubleBinaryOperator op){
            this.symbol = symbol;
            this.op = op;
        }


        @Override
        public String toString() {
            return symbol;
        }

        public double apply(double a, double b){
            return this.op.applyAsDouble(a,b);
        }
    }

    @Test
    public void Operation_test(){
        //given
        int a = 10;
        int b = 20;

        //when
        double result = Opertaion.PLUS.apply(10, 20);
        //then
        assertThat(result).isEqualTo(30);


    }

    @Test
    public void lambda_method(){
        //given
        Map<Integer, String> map = new HashMap<>();
        map.put(2,"aaa");
        Integer key = 2;
        map.merge(key,"Hi",(count, incr) -> {
            System.out.println("count = " + count);
            System.out.println("incr = " + incr);
            return count + incr;
        });

        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
        lhm.put("hi",10);

        //when

        //then
    }

    @Test
    public void Mersenne_prime() {
        primes()
                .map(p -> BigInteger.TWO.pow(p.intValueExact()).subtract(BigInteger.ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);
    }

    @Test
    public void stream_flatmap(){
        //given
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("key"+i,i);
        }

        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map2.put("key2"+i,i);
        }

        Set<String> h1 = map.keySet();
        Set<String> h2 = map2.keySet();

        List<String> collect = h1.stream().flatMap(k1 ->
                h2.stream().map(
                        k2 -> k1 + k2
                ).filter(
                        key -> key.equals("key1key26")
                )
        ).collect(Collectors.toList());
        for (String s : collect) {
            System.out.println("s = " + s);
        }
        //when

        //then
    }

    static Stream<BigInteger> primes(){
        return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
    }


    @Test
    public void make_stream(){
        //given
        List<String> word = List.of("hi","asdfa");
        Stream<String> stream = word.stream();
        Stream<List<String>> word1 = Stream.of(word);
        Stream<String> stringStream = Stream.of(word).flatMap(v1 -> Stream.of("v1" + v1));
        List<String> collect = stringStream.collect(Collectors.toList());
        for (String s : collect) {
            System.out.println("s = " + s);
        }

        List<String> collect1 = stream.collect(Collectors.toList());
        for (String s : collect1) {
            System.out.println("s = " + s);
        }


        List<String> z = Stream.of(word).flatMap(v1 -> Stream.of("z").map(
                v2 -> v1 + v2
        )).collect(Collectors.toList());
        for (String s : z) {
            System.out.println("s = " + s);
        }


        Set<String> set = new HashSet<>();
        set.add("h1");
        set.add("asdfa");

        Stream.of(set).forEach(
                v1 -> System.out.println("v1 = " + v1)
        );


        Map<String, Integer> freq = new HashMap<>();

        Map<String, Long> collect2 = freq.keySet().stream().collect(Collectors.groupingBy(v1 -> v1.toLowerCase(), counting()));

        List<String> topTen = freq.keySet().stream()
                .sorted(comparing(freq::get,Collections.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());

        List<String> str = new ArrayList<>();
        str.add("1");
        str.add("sdafasfdl");

        String collect3 = str.stream().collect(Collectors.joining(","));
        System.out.println("collect3 = " + collect3);
        //when

        //then
    }


    @Test
    public void optional(){

        List<String> t = new ArrayList<>();
        t.add("hi");
        t.add("aa");

        String s = max(t).get();
        System.out.println("s = " + s);


    }

    @Test
    public void streamOptinal(){
        List<String> t = new ArrayList<>();
        t.add("hi");
        t.add("aa");

        String s = maxStream(t).get();
        System.out.println("s = " + s);

    }


    public static  <E extends Comparable<E>>
        Optional<E> max(Collection<E> c){
        if(c.isEmpty())
            return Optional.empty();
        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0)
                result = Objects.requireNonNull(e);
        }
        return Optional.of(result);
    }

    public static <E extends Comparable<E>>
        Optional<E> maxStream(Collection<E> C){
        return C.stream().max(Comparator.naturalOrder());
    }
}
    
