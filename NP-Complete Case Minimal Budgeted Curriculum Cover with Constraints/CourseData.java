package org.example;
import java.util.*;

public class CourseData {

    public static class Course {
        public String name;
        public Set<String> skills;
        public double price;
        public double duration;
        public double rating;
        public String platform;
        public String url;
        public String description;
        public List<String> prerequisites;

        public Course(String name, Set<String> skills, double price, double duration, double rating,
                      String platform, String url, String description, List<String> prerequisites) {
            this.name = name;
            this.skills = skills;
            this.price = price;
            this.duration = duration;
            this.rating = rating;
            this.platform = platform;
            this.url = url;
            this.description = description;
            this.prerequisites = prerequisites;
        }

        @Override
        public String toString() {
            return String.format(
                    "Course: %s\nPlatform: %s\nURL: %s\nDescription: %s\nSkills: %s\nPrice: %.2f\nDuration: %.1f hr\nRating: %.1f\nPrerequisites: %s\n",
                    name, platform, url, description, skills, price, duration, rating, prerequisites
            );
        }
    }

    public static List<Course> getCourses() {
        return Arrays.asList(
                new Course(
                        "Java Fundamentals",
                        Set.of("Java", "OOP"),
                        39.99, 16, 4.7,
                        "Coursera", "https://www.coursera.org/learn/java-fundamentals",
                        "Covers basic Java syntax, object-oriented programming, and Java best practices.",
                        List.of()
                ),
                new Course(
                        "Spring Boot Essentials",
                        Set.of("Spring Boot", "Java", "REST API"),
                        59.99, 12, 4.5,
                        "Udemy", "https://www.udemy.com/course/spring-boot-essentials",
                        "Modern Spring Boot development, including REST APIs and microservice basics.",
                        List.of("Java Fundamentals")
                ),
                new Course(
                        "Microservices Deep Dive",
                        Set.of("Microservices", "Spring Boot", "Docker"),
                        69.99, 18, 4.8,
                        "Pluralsight", "https://www.pluralsight.com/courses/microservices-deep-dive",
                        "Comprehensive course on building, scaling and deploying microservices.",
                        List.of("Spring Boot Essentials", "Docker for Developers")
                ),
                new Course(
                        "Docker for Developers",
                        Set.of("Docker", "DevOps"),
                        29.99, 8, 4.9,
                        "Udemy", "https://www.udemy.com/course/docker-for-developers",
                        "Introduction to containers, Docker images, and deployment practices.",
                        List.of()
                ),
                new Course(
                        "Enterprise Application",
                        Set.of("Java", "Microservices", "Docker", "System Design"),
                        89.99, 20, 4.6,
                        "Coursera", "https://www.coursera.org/learn/enterprise-app",
                        "Enterprise grade Java applications integrating microservices and Docker.",
                        List.of("Java Fundamentals", "Docker for Developers")
                ),
                new Course(
                        "RESTful API Best Practices",
                        Set.of("REST API", "Design Patterns"),
                        47.99, 10, 4.6,
                        "Udemy", "https://www.udemy.com/course/restful-api-best-practices",
                        "Practical RESTful API design with lots of hands-on exercises.",
                        List.of("Spring Boot Essentials")
                ),
                new Course(
                        "Advanced SQL & Databases",
                        Set.of("SQL", "Database", "Performance Tuning"),
                        34.99, 14, 4.7,
                        "Datacamp", "https://www.datacamp.com/courses/advanced-sql-databases",
                        "Deep dive into SQL, database architecture, and query optimization.",
                        List.of()
                ),
                new Course(
                        "DevOps Foundation",
                        Set.of("DevOps", "CI/CD", "Docker"),
                        44.99, 11, 4.8,
                        "Coursera", "https://www.coursera.org/learn/devops-foundation",
                        "Learn best DevOps principles and practical tools including Docker and CI/CD.",
                        List.of("Docker for Developers")
                ),
                new Course(
                        "Cloud Native Java",
                        Set.of("Java", "Cloud", "Docker", "Microservices"),
                        77.99, 18, 4.6,
                        "edX", "https://www.edx.org/course/cloud-native-java",
                        "Building Java apps targeting cloud native architectures with microservices.",
                        List.of("Java Fundamentals", "Docker for Developers")
                ),
                new Course(
                        "System Design Masterclass",
                        Set.of("System Design", "Microservices", "REST API"),
                        65.99, 15, 4.8,
                        "Udemy", "https://www.udemy.com/course/system-design-masterclass",
                        "Master system design, scaling, reliability, and distributed systems.",
                        List.of("Java Fundamentals")
                ),
                new Course(
                        "Database Architecture",
                        Set.of("SQL", "Database", "System Design"),
                        49.99, 13, 4.7,
                        "Coursera", "https://www.coursera.org/learn/database-architecture",
                        "Database design, SQL programming, and system analysis.",
                        List.of("Advanced SQL & Databases")
                ),
                new Course(
                        "Kubernetes in Action",
                        Set.of("Docker", "Kubernetes", "DevOps"),
                        69.99, 14, 4.7,
                        "Pluralsight", "https://www.pluralsight.com/courses/kubernetes-in-action",
                        "Learn container orchestration, cloud deployment with Kubernetes.",
                        List.of("Docker for Developers")
                ),
                new Course(
                        "SQL Bootcamp",
                        Set.of("SQL", "Database"),
                        19.99, 8, 4.5,
                        "Udemy", "https://www.udemy.com/course/sql-bootcamp",
                        "Intensive bootcamp covering key SQL techniques and data handling.",
                        List.of()
                ),
                new Course(
                        "CI/CD Pipeline Crash Course",
                        Set.of("CI/CD", "DevOps"),
                        27.99, 7, 4.6,
                        "Pluralsight", "https://www.pluralsight.com/courses/ci-cd-pipeline-crash-course",
                        "Quick start to continuous integration and delivery using popular tools.",
                        List.of("DevOps Foundation")
                ),
                new Course(
                        "Cloud Computing Starter",
                        Set.of("Cloud", "System Design"),
                        39.99, 10, 4.6,
                        "edX", "https://www.edx.org/course/cloud-computing-starter",
                        "Foundations of cloud computing and its impact on system design.",
                        List.of()
                ),
                new Course(
                        "Distributed Database Systems",
                        Set.of("Database", "System Design", "SQL"),
                        59.99, 13, 4.8,
                        "Coursera", "https://www.coursera.org/learn/distributed-database-systems",
                        "High availability and scalability for distributed databases in modern architectures.",
                        List.of("Advanced SQL & Databases")
                ),
                new Course(
                        "Modern Java - Lambdas & Streams",
                        Set.of("Java", "Functional Programming"),
                        32.99, 8, 4.8,
                        "Udemy", "https://www.udemy.com/course/modern-java-lambdas-streams",
                        "Modern Java with functional style programming, lambdas, and streams.",
                        List.of("Java Fundamentals")
                ),
                new Course(
                        "API Gateway Solutions",
                        Set.of("REST API", "System Design", "Microservices"),
                        53.99, 12, 4.7,
                        "Pluralsight", "https://www.pluralsight.com/courses/api-gateway-solutions",
                        "Centralize API management and security for microservices.",
                        List.of("Microservices Deep Dive")
                )
        );
    }
}