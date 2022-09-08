FROM python:3.10-alpine


COPY trains.json server.py /srv/

WORKDIR /srv
RUN pip install flask

CMD flask run --port 8081 --host 0.0.0.0
