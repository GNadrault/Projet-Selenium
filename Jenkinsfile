pipeline {
    agent any
    tools {
        maven 'maven_3.5.3'
        jdk 'jdk10'
    }
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
    }
}