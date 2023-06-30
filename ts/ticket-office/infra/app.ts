import express from 'express'
import morgan from 'morgan'

import RestClient from './RestClient'
import ReservationController from './ReservationController'

const port = 8083

const restClient = new RestClient()
// This is weird because the RestClient implements two interfaces ...
const reservationController = new ReservationController(restClient, restClient)

const app = express()
app.use(express.json())
app.use(morgan('tiny'))

app.post("/reserve", async (req, res) => {
  const { body } = req
  const response = await reservationController.handle(body)
  res.send(response)
})



app.listen(port, () => {
  console.log(`Ticket Office listening on port ${port}`)
})