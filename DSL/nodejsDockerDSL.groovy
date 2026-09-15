job('Aplicacion Node.js Docker DSL') {
    description('Aplicación Node JS Docker DSL para el curso de Jenkins')
    scm {
        git('https://github.com/macloujulian/nodejsapp.git', 'master') { node ->
            node / gitConfigName('fcojrc')
            node / gitConfigEmail('fcojrc@yahoo.com.mx')
        }
    }
    triggers {
        scm('H/7 * * * *')
    }
    wrappers {
        nodejs('nodejs')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('fcojrc/nodejsapp')
            tag('${GIT_REVISION,length=7}')
            registryCredentials('356c136a-530c-45ee-ae8a-12c3184b464d')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
    publishers {
	slackNotifier {
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(true)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            customMessage(null)
            sendAs(null)
            commitInfoChoice('NONE')
            teamDomain(null)
            authToken(null)
        }
    }
}
