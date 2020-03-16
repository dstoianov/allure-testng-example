[phantomjs]: http://phantomjs.org/download.html
[maven]: http://maven.apache.org/
[git]: http://git-scm.com/
[Travis CI]: https://mediocre.com/forum/topics/phantomjs-2-and-travis-ci-we-beat-our-heads-against-a-wall-so-you-dont-have-to
[linux mail]: http://www.binarytides.com/linux-mailx-command/

## Allure TestNG Example [![Build Status](https://travis-ci.org/dstoianov/allure-testng-example.svg?branch=master)](https://travis-ci.org/dstoianov/allure-testng-example)


To run tests you need to install [maven][maven], [git][git] and [phantomjs][phantomjs].

To generate Allure Report you should perform following steps:

* git clone git@github.com:allure-framework/allure-testng-example.git
* mvn clean test
* mvn site


Travis CI config for PhantomJS you can see here [Travis CI]


9 mail/mailx command examples to send emails from command line on Linux [linux mail]

* mvn versions:display-dependency-updates
* mvn clean test -Dgroups=change_name site
* java -classpath ./target/classes;mail-1.4.7.jar;. mailer.SendMail

