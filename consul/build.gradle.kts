  val cloudConsulVersion = "2.1.2.RELEASE"

  dependencies {
     compile("org.springframework.cloud:spring-cloud-starter-consul-all")

     implementation(platform("org.springframework.cloud:spring-cloud-consul-dependencies:$cloudConsulVersion"))
  }
