package org.example;

import java.util.*;
import org.example.CourseData.Course;
import static org.example.CourseData.getCourses;
import static org.example.GreedyUtils.greedyCover;

public class GreedySkillCover {

    public static void main(String[] args) {
        Set<String> requiredSkills = Set.of(
                "Java", "Spring Boot", "SQL", "Microservices", "Docker", "REST API", "DevOps", "System Design"
        );

        double maxBudget = 250;
        double maxDuration = 50;   // e.g. limit to 50 learning hours
        double minRating = 4.5;    // Minimum accepted course rating

        List<Course> courses = getCourses();

        List<Course> plan = greedyCover(requiredSkills, courses, maxBudget, maxDuration, minRating);

        if (plan.isEmpty()) {
            System.out.println("Unable to cover all required skills within constraints.");
            return;
        }

        System.out.println("Recommended courses for skill coverage:");
        double totalCost = 0, totalTime = 0;
        for (Course c : plan) {
            System.out.printf(
                    "Course: %s | Skills: %s | Price: %.2f | Duration: %.1f hr | Rating: %.1f%n",
                    c.name, c.skills, c.price, c.duration, c.rating
            );
            totalCost += c.price;
            totalTime += c.duration;
        }

        System.out.printf("%nTotal Price: %.2f, Total Duration: %.1f hr%n", totalCost, totalTime);
    }
}