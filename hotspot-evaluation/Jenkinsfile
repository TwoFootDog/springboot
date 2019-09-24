@Library('retort-lib') _

def label = "jenkins-${UUID.randomUUID().toString()}"

def ZCP_USERID='jenkins'
def DOCKER_IMAGE='hotspot/hotspot-evaluation'
def K8S_NAMESPACE='dex-dev'

podTemplate(label:label,
    serviceAccount: "zcp-system-sa-${ZCP_USERID}",
    containers: [
    	containerTemplate(name: 'jnlp', image: 'dep-dev-registry.cloudzcp.io/cloudz/jnlp-slave:alpine', args:'${computer.jnlpmac} ${computer.name}'),
        containerTemplate(name: 'maven', image: 'dep-dev-registry.cloudzcp.io/cloudz/maven:3.5.2-jdk-8-alpine', ttyEnabled: true, command: 'cat'),
        // containerTemplate(name: 'docker', image: 'dep-dev-registry.cloudzcp.io/cloudz/docker:stable', ttyEnabled: true, command: 'cat'),
        containerTemplate(name: 'docker', image: 'docker:17-dind', ttyEnabled: true, command: 'dockerd-entrypoint.sh', privileged: true),
        // containerTemplate(name: 'kubectl', image: 'dep-dev-registry.cloudzcp.io/cloudz/k8s-kubectl:v1.9.9', ttyEnabled: true, command: 'cat')
        containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl:v1.13.6', ttyEnabled: true, command: 'cat')
    ],
    volumes: [
        // hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock'),
        persistentVolumeClaim(mountPath: '/root/.m2', claimName: 'zcp-jenkins-mvn-repo')
    ]) {

    node(label) {
        stage('SOURCE CHECKOUT') {
            def repo = checkout scm
            env.SCM_INFO = repo.inspect()
        }

        stage('BUILD') {
            container('maven') {
                mavenBuild goal: 'clean package', systemProperties:['maven.repo.local':"/root/.m2/${JOB_NAME}"], globalSettingsID: 'DEPDEV-REPO'
            }
        }

        stage('BUILD DOCKER IMAGE') {
            container('docker') {
                dockerCmd.build tag: "${HARBOR_REGISTRY}/${DOCKER_IMAGE}:${BUILD_NUMBER}"
                dockerCmd.push registry: HARBOR_REGISTRY, imageName: DOCKER_IMAGE, imageVersion: BUILD_NUMBER, credentialsId: "HARBOR_CREDENTIALS"
            }
        }

        stage('DEPLOY') {
            container('kubectl') {
                kubeCmd.apply file: 'k8s/service.yml', namespace: K8S_NAMESPACE
                yaml.update file: 'k8s/deployment.yml', update: ['.spec.template.spec.containers[0].image': "${HARBOR_REGISTRY}/${DOCKER_IMAGE}:${BUILD_NUMBER}"]

                kubeCmd.apply file: 'k8s/deployment.yml', wait: 300, recoverOnFail: false, namespace: K8S_NAMESPACE
            }
        }
    }
}