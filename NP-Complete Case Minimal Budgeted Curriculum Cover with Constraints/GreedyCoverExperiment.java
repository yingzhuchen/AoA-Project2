package org.example;

import java.io.PrintWriter;
import java.util.*;
import org.example.CourseData.Course;
import static org.example.GreedyUtils.greedyCover;

public class GreedyCoverExperiment {

    // Synthetic Data Generator
    public static List<Course> generateCourses(int nSkills, int nCourses, int skillsPerCourse) {
        List<String> allSkills = new ArrayList<>();
        for (int i = 0; i < nSkills; i++) allSkills.add("Skill " + i);

        Random rand = new Random(42);
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < nCourses; i++) {
            Set<String> skills = new HashSet<>();
            while (skills.size() < skillsPerCourse) {
                skills.add(allSkills.get(rand.nextInt(nSkills)));
            }
            double price = 10.0 + rand.nextDouble() * 90.0;
            double duration = 5.0 + rand.nextDouble() * 20.0;
            double rating = 3.5 + rand.nextDouble() * 1.5;
            courses.add(new Course("Course " + i, skills, price, duration, rating, "", "", "", List.of()));
        }
        return courses;
    }

    public static void main(String[] args) throws Exception {
        int[] skillSizes = {20, 40, 80, 160, 320}; // problem scales to test
        int coursesPerSkill = 8;   // overlap factor
        int skillsPerCourse = 5;   // density per course

        double maxBudget = Double.POSITIVE_INFINITY;
        double maxDuration = Double.POSITIVE_INFINITY;
        double minRating = 0.0;

        try (PrintWriter pw = new PrintWriter("results.csv")) {
            pw.println("Skills,Courses,TimeMillis,SelectedCourses");
            for (int nSkills : skillSizes) {
                int nCourses = nSkills * coursesPerSkill;
                Set<String> required = new HashSet<>();
                for (int i = 0; i < nSkills; i++) required.add("Skill " + i);
                List<Course> courses = generateCourses(nSkills, nCourses, skillsPerCourse);

                long start = System.currentTimeMillis();
                List<Course> selected = greedyCover(required, courses, maxBudget, maxDuration, minRating);
                long end = System.currentTimeMillis();

                pw.printf("%d,%d,%d,%d%n",
                        nSkills, nCourses, (end - start), selected.size());
            }
        }

        System.out.println("Done. Results written to results.csv");
    }
}