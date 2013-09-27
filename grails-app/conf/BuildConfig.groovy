grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.repos.spireonSnap.url = "http://nexus.spireon.com/nexus/content/repositories/spireon-snapshot"
grails.project.repos.spireonSnap.type = "maven"
grails.project.repos.spireonRel.url = "http://nexus.spireon.com/nexus/content/repositories/spireon-release"
grails.project.repos.spireonRel.type = "maven"
grails.project.repos.default = "spireonSnap"

grails.project.dependency.resolution = {

    inherits "global"
    log "warn"

    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()
        grailsRepo "http://grails.org/plugins"
//        mavenRepo "http://m2repo.spockframework.org/snapshots"
    }

    dependencies {
	    compile 'redis.clients:jedis:2.2.0'
    }

    plugins {
        build ":release:2.0.3", {
            export = false
        }
        test(':code-coverage:1.2.5') {
            export = false
        }
        test(':codenarc:0.17') {
            export = false
        }
    }
}

codenarc {
    processTestUnit = false
    processTestIntegration = false
    propertiesFile = 'codenarc.properties'
    ruleSetFiles = "file:grails-app/conf/redis-codenarc.groovy"
    reports = {
        RedisReport('xml') {                    // The report name "MyXmlReport" is user-defined; Report type is 'xml'
            outputFile = 'target/codenarc.xml'  // Set the 'outputFile' property of the (XML) Report
            title = 'Grails Redis Plugin'             // Set the 'title' property of the (XML) Report
        }
    }
}
