package practice;

import java.util.Collections;
import java.util.List;
import model.Candidate;
import model.Person;
import model.Cat;
import model.Person.Sex;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StreamPractice {
    public int findMinEvenNumber(List<String> numbers) {
        return numbers.stream()
                .flatMap(s -> Arrays.stream(s.split(",")))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .filter(n -> n % 2 == 0)
                .min()
                .orElseThrow(() -> new RuntimeException("Can't get min value from list: " + numbers));
    }

    public Double getOddNumsAverage(List<Integer> numbers) {
        List<Integer> tempNumbers = new java.util.ArrayList<>(numbers);
        for (int i = 0; i < tempNumbers.size(); i++) {
            if (i % 2 != 0) {
                tempNumbers.set(i, tempNumbers.get(i) - 1);
            }
        }
        return tempNumbers.stream()
                .filter(n -> n % 2 != 0)
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElseThrow(() -> new NoSuchElementException("No odd numbers found."));
    }

    public List<Person> selectMenByAge(List<Person> peopleList, int fromAge, int toAge) {
        return peopleList.stream()
                .filter(p -> p.getSex() == Sex.MAN && p.getAge() >= fromAge && p.getAge() <= toAge)
                .collect(Collectors.toList());
    }

    public List<Person> getWorkablePeople(int fromAge, int femaleToAge,
                                          int maleToAge, List<Person> peopleList) {
        return peopleList.stream()
                .filter(p -> p.getAge() >= fromAge)
                .filter(p -> (p.getSex() == Sex.MAN && p.getAge() <= maleToAge) ||
                        (p.getSex() == Sex.WOMAN && p.getAge() <= femaleToAge))
                .collect(Collectors.toList());
    }

    public List<String> getCatsNames(List<Person> peopleList, int femaleAge) {
        return peopleList.stream()
                .filter(p -> p.getSex() == Sex.WOMAN && p.getAge() >= femaleAge)
                .flatMap(p -> p.getCats().stream())
                .map(Cat::getName)
                .collect(Collectors.toList());
    }

    public List<String> validateCandidates(List<Candidate> candidates) {
        CandidateValidator validator = new CandidateValidator();
        return candidates.stream()
                .filter(validator)
                .map(Candidate::getName)
                .sorted()
                .collect(Collectors.toList());
    }
}
