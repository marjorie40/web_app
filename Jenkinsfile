pipeline {
    agent any
    environnement {
    // nom image DockerHub
        registry = "marjorie40/apispringboot2026"
        registryCredential = 'DOCKERHUB_FORJ'
        dockerImage = ''
        }
        // decla des outils a utiliser par Jenkins
        tools {
            maven 'maven'
            jdk 'JDK21'
    }
    // declaration des stages
    stages {
        // nettoyage workspace
        stage('Clean workspace') {
            steps {
                cleanWs()
            }
        }
        // recopie depot dans workspace (credential GitHub account dans Jenkins)
        stage('Git Checkout') {
            steps {
            //recup du depot GitHub du projet
                git branch: 'main',
                    credentialsId: 'GITHUB_FORJ',
                    url: 'https://github.com/marjorie40/demoSpring.git'
            }
        }
        // construction du JAR ou WAR avec maven
        stage('Build Maven') {
            steps {
                // package du projet -Dspring.profiles.active=jenkins
                bat 'mvn clean package'
            }
        }

        // construction de d'limage Docker a partir du Dockerfile
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('marjorie40/apispringboot2026', '-f Dockerfile .')
                }
            }
        }
        // push de l'image dans le dockerHub
        stage('Push to Docker Hub')
            steps {
                // info de connexion credentials dans Jenkins
                script {
                    docker.withRegistry('', registryCredential) {
                        docker.image('marjorie40/apispringboot2026').push()
                    }
                }
            }
        // deploiement multi conteneurs avec docker compose
        stage('Deploy with Docker Compose') {
            steps {
                //initialise le conteneur docker
                script {
                    // construit les services
                    bat 'docker-compose up -d --build --force-recreate --remove-orphans'
                }
            }
        }
        // generer le rapport de tests via allure
        stage('Generate Allure Report') {
            steps {
                bat 'mvn allure:report'
            }
        }
        post {
            always {
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']] // ? n'est pas dans target ici, faut-il :allure-results
                    ])
            }
        }

    }

}