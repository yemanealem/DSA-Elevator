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
END
