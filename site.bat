@echo on

mvn -Dallure.issues.tracker.pattern=http://google.com/issues/%%s -Dallure.tests.management.pattern=http://google.com/tests/%%s -Dproject.build.sourceEncoding=UTF-8 -Dproject.reporting.outputEncoding=UTF-8 site