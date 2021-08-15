package com.learnview.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortQuestions {

    public static List<Integer> getRandomQuestions(int listSize) {

        List<Integer> numbers = new ArrayList<>();
        for (int a = 1; a < listSize + 1; a++) {
            numbers.add(a);
        }
        Collections.shuffle(numbers);

        return numbers;

    }
}
