@Library ('chs-basic-shared-library') _

node('ecs-node'){
  String cdkRepository = params.CDK_REPOSITORY
  String cdkReference = params.CDK_REFERENCE
  testBuildAndRunCdkProject(cdkRepository, cdkReference)
}
