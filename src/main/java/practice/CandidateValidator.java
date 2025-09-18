package practice;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Candidate;

public class CandidateValidator implements Predicate<Candidate> {
    @Override
    public boolean test(Candidate candidate) {
        if (candidate.getAge() < 35
                || !candidate.isAllowedToVote()
                || !"Ukrainian".equals(candidate.getNationality())) {
            return false;
        }

        Pattern pattern = Pattern.compile("(\\d{4})-(\\d{4})");
        Matcher matcher = pattern.matcher(candidate.getPeriodsInUkr());
        if (matcher.find()) {
            try {
                int startYear = Integer.parseInt(matcher.group(1));
                int endYear = Integer.parseInt(matcher.group(2));
                return endYear - startYear >= 10;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
