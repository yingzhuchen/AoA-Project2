package org.example;

import java.util.*;
import org.example.CourseData.Course;

public class GreedyUtils {

    /**
     * Greedy algorithm for constrained skill coverage:
     * Each step, select the course which covers the largest number of uncovered skills per unit cost (customizable score).
     */
    public static List<Course> greedyCover(
            Set<String> requiredSkills,
            List<Course> courses,
            double maxBudget,
            double maxDuration,
            double minRating
    ) {
        Set<String> uncovered = new HashSet<>(requiredSkills);
        List<Course> selected = new ArrayList<>();
        double totalBudget = 0;
        double totalDuration = 0;

        List<Course> available = new ArrayList<>(courses);

        while (!uncovered.isEmpty()) {
            Course best = null;
            double bestScore = -1;
            for (Course c : available) {
                if (totalBudget + c.price > maxBudget) continue;
                if (totalDuration + c.duration > maxDuration) continue;
                if (c.rating < minRating) continue;

                Set<String> newSkills = new HashSet<>(c.skills);
                newSkills.retainAll(uncovered);
                int covered = newSkills.size();
                if (covered == 0) continue;

                double score = covered / c.price; // could customize (e.g., factor rating)
                if (score > bestScore) {
                    bestScore = score;
                    best = c;
                }
            }
            if (best == null) {
                // no feasible course to cover remaining skills
                return Collections.emptyList();
            }
            selected.add(best);
            uncovered.removeAll(best.skills);
            totalBudget += best.price;
            totalDuration += best.duration;
            available.remove(best);
        }
        return selected;
    }
}