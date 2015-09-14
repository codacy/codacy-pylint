[![Build Status](https://circleci.com/gh/codacy/codacy-pylint.svg?style=shield&circle-token=:circle-token)](https://circleci.com/gh/codacy/codacy-pylint)

create the docker: sbt docker:publishLocal

the docker is supposed to be run with the following command:

```
docker run -it -v $srcDir:/src  <DOCKER_NAME>:<DOCKER_VERSION>
```

and $srcDir must contain a valid .codacy.json configuration

## Docs

[Docker Docs](http://docs.codacy.com/v1.0/docs/tool-developer-guide)

[Scala Docker Template Docs](http://docs.codacy.com/v1.0/docs/tool-developer-guide-scala)

## Test

Follow the instructions at [codacy-plugins-test](https://github.com/codacy/codacy-plugins-test/blob/master/README.md#test-definition)
