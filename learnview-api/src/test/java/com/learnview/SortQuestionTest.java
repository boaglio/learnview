package com.learnview;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.learnview.util.SortQuestions;

public class SortQuestionTest {

    @Test
    public void testRandom3() {

        List<Integer> numbers1 = SortQuestions.getRandomQuestions(3);
        assertThat(numbers1).isNotEmpty();
        assertThat(numbers1).contains(1);
        assertThat(numbers1).contains(2);
        assertThat(numbers1).contains(3);
        System.out.println(numbers1);

        List<Integer> numbers2 = SortQuestions.getRandomQuestions(3);
        assertThat(numbers2).isNotEmpty();
        assertThat(numbers2).contains(1);
        assertThat(numbers2).contains(2);
        assertThat(numbers2).contains(3);
        System.out.println(numbers2);

        assertThat(numbers2).containsAll(numbers2);

    }

    @Test
    public void testRandom5() {

        List<Integer> numbers1 = SortQuestions.getRandomQuestions(5);
        assertThat(numbers1).isNotEmpty();
        assertThat(numbers1).contains(1);
        assertThat(numbers1).contains(2);
        assertThat(numbers1).contains(3);
        assertThat(numbers1).contains(4);
        assertThat(numbers1).contains(5);
        System.out.println(numbers1);

        List<Integer> numbers2 = SortQuestions.getRandomQuestions(5);
        assertThat(numbers2).isNotEmpty();
        assertThat(numbers2).contains(1);
        assertThat(numbers2).contains(2);
        assertThat(numbers2).contains(3);
        assertThat(numbers2).contains(4);
        assertThat(numbers2).contains(5);
        System.out.println(numbers2);

        assertThat(numbers2).containsAll(numbers2);

    }

}
