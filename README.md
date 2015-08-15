[![Build Status](https://circleci.com/gh/codacy/codacy-pylint.svg?style=shield&circle-token=:circle-token)](https://circleci.com/gh/codacy/codacy-pylint)

create the docker: sbt docker:publishLocal

the docker is supposed to be run like so

```
docker run -it -v $srcDir:/src  codacy-engine-pylint:1.0
```

and $srcDir must contain a valid .codacy.json configuration
