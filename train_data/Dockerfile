FROM python:3.10-alpine


COPY trains.json server.py /srv/

WORKDIR /srv
RUN pip install cherrypy

CMD python server.py
