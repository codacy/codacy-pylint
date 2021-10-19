FROM python:3.6-alpine
COPY requirements.txt src/codacy_pylint.py src/codacy_pylint_test.py ./
COPY docs /docs
RUN apk add --no-cache python3 python2 curl build-base python2-dev && \
    curl https://bootstrap.pypa.io/pip/2.7/get-pip.py --output get-pip.py && \
    python2 get-pip.py && \
    rm get-pip.py && \
    pip3 install --no-cache-dir -r requirements.txt && \
    pip2 install --no-cache-dir -r requirements.txt && \
    apk del curl build-base python2-dev &&\
    adduser -u 2004 -D docker && \
    chown -R docker:docker /docs /home/docker
USER docker
ENTRYPOINT [ "python3" ]
CMD [ "codacy_pylint.py" ]
