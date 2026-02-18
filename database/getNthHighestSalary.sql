CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      SELECT Salary
      FROM (
          SELECT DISTINCT Salary
          FROM Employee
          ORDER BY Salary DESC
          LIMIT N
      ) AS temp
      ORDER BY Salary ASC
      LIMIT 1
  );
END
