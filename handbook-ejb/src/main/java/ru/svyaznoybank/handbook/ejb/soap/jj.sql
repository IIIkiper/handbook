SELECT id, code, value, remark FROM ( + 
	SELECT h.id, h.type_id AS code, h.value, h.remark, h.update_time, MAX (h.update_time) OVER (PARTITION BY h.type_id) AS max_update_time FROM handbook h 
	INNER JOIN person p ON p.id = h.person_id 
	WHERE (
		(
			UPPER(p.last_name) = UPPER (:LAST_NAME) --IN (SUBSTR(UPPER(:LAST_NAME), 1, INSTR(UPPER(:LAST_NAME), ';') - 1), SUBSTR(UPPER(:LAST_NAME), INSTR(UPPER(:LAST_NAME), ';') + 1)) 
			AND UPPER(p.first_name) = UPPER (:FIRST_NAME) 
			AND (UPPER(p.second_name) = UPPER (:SECOND_NAME) 
			OR (UPPER(p.second_name) IS NULL AND :SECOND_NAME IS NULL)) 
			AND p.birthday = :BIRTHDAY
		) 
		OR 
		(
			p.doc_type = :DOC_TYPE 
			AND p.doc_series = :DOC_SERIES 
			AND p.doc_number = :DOC_NUMBER
		) 
		OR p.customer_id = :CUSTOMER_ID
	) 
	AND (h.product_id = :PRODUCT_ID OR h.product_id IS NULL) 
	AND (h.currency = :CURRENCY OR h.currency IS NULL OR :CURRENCY IS NULL) 
	AND (
		(h.end_date IS NULL OR TRUNC(h.end_date) >= TRUNC(:REQUEST_DATE)) 
		AND
		(h.begin_date IS NULL OR TRUNC(h.begin_date) <= TRUNC(:REQUEST_DATE))
	)
)
WHERE update_time = max_update_time


SUBSTR(
	UPPER(:LAST_NAME), 
	1, 
	INSTR(UPPER(:LAST_NAME), ';') - 1
), 
SUBSTR(
	UPPER(:LAST_NAME), 
	INSTR(UPPER(:LAST_NAME), ';') + 1
)