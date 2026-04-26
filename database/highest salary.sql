/*
Problem: Department Highest Salary (LeetCode)

We have two tables:

Employee(id, name, salary, departmentId)
Department(id, name)

Goal:
For each department, return:
- Department name
- Employee name(s)
- Their salary
ONLY for employees who have the highest salary in their department.
*/

-- Step 1: Rank employees within each department by salary (highest first)
WITH ranked_employees AS (
    SELECT 
        e.id,
        e.name,
        e.salary,
        e.departmentId,

        -- Assign rank per department
        -- Employees with same salary get same rank
        DENSE_RANK() OVER (
            PARTITION BY e.departmentId 
            ORDER BY e.salary DESC
        ) AS rnk

    FROM Employee e
)

-- Step 2: Join with Department and filter only highest salary (rank = 1)
SELECT 
    d.name AS Department,
    r.name AS Employee,
    r.salary AS Salary
FROM ranked_employees r
JOIN Department d
    ON r.departmentId = d.id
WHERE r.rnk = 1;
