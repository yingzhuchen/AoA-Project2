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

### 3. NP-Complete Reduction (Sketch)
- **Formulation.** Universe = required skills \(Q\). Each course \(c_i\) is a set \(S_i \subseteq Q\) with attributes: price \(p_i\), duration \(d_i\), rating \(r_i\). Constraints: \(\sum p_i \le B\), \(\sum d_i \le D\), and \(r_i \ge R\) for chosen courses. Objective: minimize the number of courses whose union covers \(Q\) while satisfying all constraints.
- **NP-hardness.** Classic Set Cover is a special case obtained by making constraints non-restrictive (e.g., \(p_i = d_i = 0\), \(r_i = 1\), and large \(B, D\), low \(R\)). Since Set Cover is NP-complete, this constrained variant is NP-hard.
- **In NP.** Given a candidate course set, checking coverage and constraint satisfaction is polynomial. Thus, the decision version is NP-complete.
- **Heuristic.** Because exact solutions are infeasible at scale, the greedy algorithm provides a fast, near-good solution under the given constraints; empirical results align with the analyzed \(O(m \cdot n)\) scanning behavior (with \(m\) skills, \(n\) courses) when using precomputed incidence structures.
