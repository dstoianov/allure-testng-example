//package jenkins.dsl
//https://jenkinsci.github.io/job-dsl-plugin/#
//https://github.com/jenkinsci/job-dsl-plugin/wiki/The-Configure-Block
//https://wiki.jenkins-ci.org/display/JENKINS/Job+DSL+Plugin


def jobName = "dsl-allure-testng-example"

mavenJob("${jobName}") {

    description('This job was created from groovy script')

    logRotator(-1, 15)

    label('win')

    scm {
        git {
            branch('*/master')
            remote {
                url('https://github.com/dstoianov/allure-testng-example.git')
            }

            browser {
                gitWeb('https://github.com/dstoianov/allure-testng-example')
            }
        }
//                'https://github.com/dstoianov/allure-testng-example.git',
//                '*/master'
//        )
    }

    mavenInstallation('Maven 3.3.9')

    wrappers {
        timestamps()
    }

    jdk('(Default)')

    triggers {
        cron('0 1 * * *')
    }

    goals("clean test")

    postBuildSteps() {
        maven {
            goals('site')
            mavenInstallation('mvn')
        }
    }

    publishers {
        chucknorris()

        archiveJunit('**/target/surefire-reports/*.xml')

        publishHtml {
            report('target/site/allure-maven-plugin') {
                reportName('Allure Tests Report')
            }
            report('target/surefire-reports/html') {
                reportName('ReportNG Report')
            }
            report('target/surefire-reports') {
                reportName('TestNG Report')
            }
        }

        extendedEmail {
            recipientList('test@gmail.com')
            defaultSubject('$PROJECT_DEFAULT_SUBJECT')
            defaultContent('${JELLY_SCRIPT,template="html"}')
            contentType('text/html')
            triggers {
                always {
                    sendTo {
                        recipientList()
                    }
                }
            }
        }
    }


}