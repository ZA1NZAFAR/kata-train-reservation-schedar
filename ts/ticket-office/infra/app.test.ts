import fetch from 'node-fetch'
import { expect, test } from '@jest/globals'

test('booking four seats from empty train', async () => {
  // Reset the train
  const trainId = 'express_2000'
  let response = await fetch(
    `http://127.0.0.1:8081/reset/${trainId}`,
    { method: 'POST' }
  )
  expect(response.status).toBe(200)


  // Try to make a reservation
  const payload = { train_id: trainId, count: 4 }
  response = await fetch(
    `http://127.0.0.1:8083/reserve`,
    {
      method: 'POST',
      body: JSON.stringify(payload),
      headers: { 'Content-Type': 'application/json' }
    }
  )
  expect(response.status).toBe(200)
  const reservation = await response.json()

  // Check which seats have been reserved
  expect(reservation.seats).toEqual(['1A', '2A', '3A', '4A'])
})


test('booking four additional seats', async () => {
  // Reset the train
  const trainId = 'express_2000'
  let response = await fetch(
    `http://127.0.0.1:8081/reset/${trainId}`,
    { method: 'POST' }
  )
  let text = await response.text()


  // Make a first reservation for 4 seats
  let payload = { train_id: trainId, count: 4 }
  response = await fetch(
    `http://127.0.0.1:8083/reserve`,
    {
      method: 'POST',
      body: JSON.stringify(payload),
      headers: { 'Content-Type': 'application/json' }
    }
  )

  // Make a second reservation with 4 seats

  const reservation = await response.json()
  payload = { train_id: trainId, count: 4 }
  response = await fetch(
    `http://127.0.0.1:8083/reserve`,
    {
      method: 'POST',
      body: JSON.stringify(payload),
      headers: { 'Content-Type': 'application/json' }
    }
  )
  expect(response.status).toBe(200)
})

