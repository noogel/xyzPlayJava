package noogel.xyz.concurrent.thread;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        System.out.println(Stream.generate(Math::random).limit(2).collect(Collectors.toList()));

        String[] str1 = {"Hello", "World"};
        System.out.println(
                Stream.of(str1).map(str -> str.split("")).flatMap(strings -> Arrays.stream(strings)).collect(Collectors.toList())
        );
    }
}
