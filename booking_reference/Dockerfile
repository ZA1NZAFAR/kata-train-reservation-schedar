FROM python:3.10-alpine

RUN pip install cherrypy

WORKDIR /srv
COPY server.py /srv/
CMD python server.py
