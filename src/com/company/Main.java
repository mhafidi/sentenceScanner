package com.company;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
//by @mhi 27/12/2017
public class Main {

    private static Map<String, Long> wordMapOccurrences =null;

    private final static String SPACE_AND_PUNCTUATIONS_DELEMITER="[ \\t.,?!:;\\r]";
    private final static String ESCAPE_CHARS=":q!";
    private final static String MESSAGE_1="If you want to quit the program enter \":q!\"";
    private final static String MESSAGE_2="Enter the sentence to be scanned";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(MESSAGE_1);
        System.out.println(MESSAGE_2);

        String sentence;
        try
        {
            sentence = scanner.nextLine();
            while(!sentence.isEmpty() && !sentence.equals(ESCAPE_CHARS))
            {
                System.out.println("The scanning of the following sentence:\n" + sentence + " is:\n");
                scan(sentence);
                printWordsMap();
                System.out.println(MESSAGE_1);
                System.out.println(MESSAGE_2);
                sentence = scanner.nextLine();
            }

        } catch (NoSuchElementException e)
        {
            System.out.println("No input"); // can be triggered by CTRL-D
        }

    }
    private static void scan(String aInString){

        if(aInString!=null && !aInString.isEmpty())
        {
            String[] splitArray = aInString.split(SPACE_AND_PUNCTUATIONS_DELEMITER);

            wordMapOccurrences = Arrays.stream(splitArray).
                    filter(str->!str.isEmpty()).
                    map(String::toLowerCase).// reference to the same word whatever the case is
                    collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));


        }

    }

    private static Map<String, Long> getWordMapOccurrences()
    {

        return wordMapOccurrences;
    }

    private static void printWordsMap()
    {
        getWordMapOccurrences().keySet().forEach(str->
        {
            System.out.println(str+": "+ wordMapOccurrences.get(str));
        });
    }

}
