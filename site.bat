@echo on

mvn -Dallure.issues.tracker.pattern=http://google.com/issues/%%s -Dproject.build.sourceEncoding=UTF-8 -Dproject.reporting.outputEncoding=UTF-8 site