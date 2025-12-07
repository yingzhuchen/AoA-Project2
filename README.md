# AoA-Project2

## Network Flow Case: 

## NP-Complete Case: Minimal Budgeted Curriculum Cover with Constraints

### 1. Real-World Problem
Students facing job-market pressure must assemble a minimal set of online courses to cover all required skills (e.g., Java, Docker, Spring Boot, SQL, Microservices) while respecting practical limits:
- **Budget** (total course fees),
- **Time** (total course hours),
- **Quality** (minimum rating per course).

This is the **Minimal Budgeted Curriculum Cover** problem: select the fewest courses whose combined skills cover all requirements without violating budget/time/quality constraints.

### 2. Repository Files
- **CourseData.java**  
  Data model for courses (skills taught, price, duration, rating) and required skills. Handles parsing/holding instances used by the algorithms.

- **GreedySkillCover.java**  
  Implements the greedy course-selection heuristic (e.g., maximizing newly covered skills per cost unit under constraints). Core solver.

- **GreedyCoverExperiment.java**  
  Experimental harness to generate or load instances, run the greedy solver, record runtime/solution size, and write results (e.g., CSV) for plotting.

- **GreedyUtils.java**  
  Utility helpers: set operations on skills, scoring functions, constraint checks (budget/time/rating), and general support routines.

- **outputPicture.py**  
  Python plotting script to visualize experimental results (e.g., runtime vs. skills/courses, selected courses vs. skill count) from the CSV logs.

### 3. NP-Complete Reduction 
- **Formulation.** Treat the required skills as the universe. Each course corresponds to a subset of skills and carries attributes: price, duration, and rating. Constraints require total price within the budget, total duration within the time limit, and each chosen course meeting the rating threshold. The goal is to pick the fewest courses whose combined skills cover all requirements while respecting these constraints.
- **NP-hardness.** Classic Set Cover is a special case when the constraints are non-restrictive (zero costs/durations, permissive budgets/time, and low rating threshold). Since Set Cover is NP-complete, this constrained version is NP-hard.
- **In NP.** Given a candidate set of courses, checking that all skills are covered and that budget/time/rating constraints hold can be done in polynomial time. Therefore, the decision version is NP-complete.
- **Heuristic.** Exact solutions are infeasible at scale, so a greedy algorithm is used to find a near-good solution efficiently under the constraints. Experiments show runtime growth consistent with the expected scan complexity over courses and skills.
