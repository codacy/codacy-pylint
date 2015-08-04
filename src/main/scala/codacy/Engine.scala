package codacy

import codacy.dockerApi.DockerEngine
import codacy.pmdjava.PmdJava

object Engine extends DockerEngine(PmdJava)