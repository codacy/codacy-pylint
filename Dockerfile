# Don't upgrade to alpine:3.10 because we need Python 3.6
FROM alpine:3.9.4
COPY requirements.txt requirements.txt
RUN apk add --no-cache python3 python2 py2-pip
RUN pip install --no-cache-dir -r requirements.txt
RUN pip3 install --no-cache-dir -r requirements.txt
COPY src/codacy_pylint.py codacy_pylint.py
COPY src/codacy_pylint_test.py codacy_pylint_test.py
COPY docs /docs
RUN adduser -u 2004 -D docker
RUN chown -R docker:docker /docs /home/docker
USER docker
ENTRYPOINT [ "python3" ]
CMD [ "codacy_pylint.py" ]
