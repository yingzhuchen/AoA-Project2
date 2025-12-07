import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("file_path_of_results.csv")

plt.figure()
plt.plot(df["Skills"], df["TimeMillis"], marker="o")
plt.xlabel("Number of Skills")
plt.ylabel("Time (ms)")
plt.title("Greedy Cover Running Time vs Skill Count")
plt.grid(True)
plt.show()

plt.figure()
plt.plot(df["Skills"], df["SelectedCourses"], marker="s", color="orange")
plt.xlabel("Number of Skills")
plt.ylabel("Selected Courses")
plt.title("Selected Courses vs Skill Count")
plt.grid(True)
plt.show()