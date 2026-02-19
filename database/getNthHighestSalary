/*
LeetCode 177 — Nth Highest Salary

Question:
Write a SQL function getNthHighestSalary(N INT) that returns 
the Nth highest DISTINCT salary from the Employee table.
If there is no Nth highest salary, return NULL.

Example Table: Employee

+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+

Example 1:
Input:  N = 2

Step 1: Get DISTINCT salaries
         100, 200, 300

Step 2: Order DESC
         300
         200
         100

Step 3: Skip N-1 rows → skip 1 row
         Remaining:
         200
         100

Return first row → 200

Output:
200


Example 2 (With Duplicates):

+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 200    |
| 4  | 300    |
+----+--------+

Distinct salaries:
300
200
100

If N = 2 → Output = 200


Example 3:
If N = 5 (but only 3 distinct salaries exist)
Output = NULL
*/


CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  DECLARE offset_val INT;
  DECLARE result INT;

  SET offset_val = N - 1;

  SELECT DISTINCT Salary
  INTO result
  FROM Employee
  ORDER BY Salary DESC
  LIMIT offset_val, 1;

  RETURN result;
END;
