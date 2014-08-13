package ru.svyaznoybank.handbook.war.gwt.json.reader;

import ru.svyaznoybank.handbook.war.gwt.json.RequestLog;
import ru.svyaznoybank.handbook.war.gwt.json.RequestLogLoadConfig;
import ru.svyaznoybank.handbook.war.gwt.json.RequestLogPagingResult;

public interface RequestLogABF extends IPagingResultABF<
		RequestLog, 
		RequestLogPagingResult, 
		RequestLogLoadConfig
	> { }