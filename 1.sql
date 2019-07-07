SELECT 
	YEAR,
	grantNo,
	orgn,
	projectkeyword 
FROM
	fund 
WHERE
	projectkeyword NOT LIKE "" 
	AND orgn NOT LIKE "" 
	AND YEAR NOT LIKE "" 
	AND YEAR BETWEEN 1998 
	AND 2017 
ORDER BY
	YEAR ASC,
	orgn ASC