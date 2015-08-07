package codacy

import codacy.dockerApi.DockerEngine
import codacy.pylint.Pylint

object Engine extends DockerEngine(Pylint)