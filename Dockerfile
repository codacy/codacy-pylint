FROM python:3.7-slim
# Don't change workdir
WORKDIR /src
COPY requirements.txt requirements.txt
RUN pip3 install -r requirements.txt
COPY src/codacy_pylint.py /codacy_pylint.py
COPY docs /docs
RUN useradd -u 2004 -U docker
RUN mkdir /home/docker
RUN chown -R docker:docker /docs /home/docker
USER docker
ENTRYPOINT [ "python3.7" ]
CMD [ "/codacy_pylint.py" ]
