[![Build Status](https://travis-ci.org/codacy/codacy-pylint.svg?branch=master)](https://travis-ci.org/codacy/codacy-pylint)

create the docker: sbt docker:publishLocal

the docker is supposed to be run like so

```
docker run -it -v $srcDir:/src  codacy-engine-pylint:1.0
```

and $srcDir must contain a valid .codacy.json configuration
