package codacy

import codacy.pylint.Pylint
import com.codacy.tools.scala.seed.DockerEngine

object Engine extends DockerEngine(Pylint)()
